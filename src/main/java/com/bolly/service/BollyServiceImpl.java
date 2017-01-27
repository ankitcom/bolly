package com.bolly.service;

import static com.bolly.constants.Constants.ACTOR_IDS;
import static com.bolly.constants.Constants.ACTOR_NAMES;
import static com.bolly.constants.Constants.TYPES;
import static com.bolly.constants.ErrorConstant.MOVIE_NOT_FOUND;
import static com.bolly.jooq.Tables.ACTOR;
import static com.bolly.jooq.Tables.DIRECTOR;
import static com.bolly.jooq.Tables.MOVIE;
import static com.bolly.jooq.Tables.MOVIE_ACTOR;
import static com.bolly.jooq.Tables.MOVIE_TYPE;
import static org.jooq.impl.DSL.groupConcat;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolly.exception.AppException;
import com.bolly.model.Movie;
import com.bolly.model.Movie.MovieBuilder;
import com.bolly.model.Person;

/**
 * 
 * @author ankit.bhatnagar
 *
 */

@Service
public class BollyServiceImpl {
	
	private Logger logger = LoggerFactory.getLogger(BollyServiceImpl.class);
	
	@Autowired
	DSLContext dsl;
	
	public List<Movie> getRecentMovies(int recentCount){

		List<Movie> movies=new ArrayList<>(recentCount);
		dsl.select().from(MOVIE).where(MOVIE.RATING.isNotNull()).orderBy(MOVIE.RELEASE_DATE.desc()).limit(recentCount).fetch().forEach(record -> {
			movies.add(
				Movie.builder()
				.id(record.getValue(MOVIE.ID))
				.name(record.getValue(MOVIE.NAME))
				.rating(record.getValue(MOVIE.RATING).floatValue()/2)
				.releaseDate(record.getValue(MOVIE.RELEASE_DATE))
				.releaseYear(record.getValue(MOVIE.RELEASE_YEAR))
				.imageUrl(record.getValue(MOVIE.IMAGE_URL))
				.build()
			);
		});
		
		logger.trace("Movies count:{}",movies.size());
		return movies;
	}
	
	public Movie getMovie(int id) throws AppException{
		Record record = dsl.select(MOVIE.fields()).select(groupConcat(ACTOR.ID,",").as(ACTOR_IDS),groupConcat(ACTOR.NAME,",").as(ACTOR_NAMES)
				,groupConcat(MOVIE_TYPE.TYPE_ID,",").as(TYPES), DIRECTOR.NAME)
		.from(MOVIE).join(DIRECTOR).on(MOVIE.DIRECTOR_ID.equal(DIRECTOR.ID)).leftJoin(MOVIE_ACTOR).on(MOVIE.ID.equal(MOVIE_ACTOR.MOVIE_ID))
		.leftJoin(ACTOR).on(MOVIE_ACTOR.ACTOR_ID.equal(ACTOR.ID)).leftJoin(MOVIE_TYPE).on(MOVIE.ID.equal(MOVIE_TYPE.MOVIE_ID))
		.where(MOVIE.ID.equal(id)).groupBy(MOVIE.ID).fetchOne();
		
		if(record==null) throw new AppException(101, MOVIE_NOT_FOUND);
		
		MovieBuilder mb = Movie.builder().id(record.getValue(MOVIE.ID)).name(record.getValue(MOVIE.NAME)).onlineStreamLink(record.getValue(MOVIE.ONLINE_STREAM_LINK))
		.rating(record.getValue(MOVIE.RATING).floatValue()/2).review(record.getValue(MOVIE.REVIEW)).releaseDate(record.getValue(MOVIE.RELEASE_DATE))
		.releaseYear(record.getValue(MOVIE.RELEASE_YEAR)).writer(record.getValue(MOVIE.WRITER));
		
		mb.director(Person.builder().id(record.getValue(DIRECTOR.ID)).name(record.getValue(DIRECTOR.NAME)).build());
		
		String actorIdsGet=record.getValue(ACTOR_IDS, String.class);
		if(actorIdsGet!=null){
			Set<Person> actors=new HashSet<>();
			String[] actorIds=actorIdsGet.split(",");
			String[] actorNames=record.getValue(ACTOR_NAMES, String.class).split(",");
			for(int i=0;i<actorIds.length;i++){
				actors.add(Person.builder().id(Integer.parseInt(actorIds[i])).name(actorNames[i]).build());
			}
			mb.actors(actors);
		}
		
		String typesGet=record.getValue(TYPES, String.class);
		if(typesGet!=null){
			Set<Integer> typeIds=new HashSet<>();
			String[] types=typesGet.split(",");
			for(int i=0;i<types.length;i++){
				typeIds.add(Integer.parseInt(types[i]));
			}
			mb.typeIds(typeIds);
		}
		
		return mb.build();
	}

	@Transactional
	public int addMovie(Movie movie) {
		
		dsl.insertInto(DIRECTOR).set(DIRECTOR.NAME,movie.getDirector().getName()).onDuplicateKeyIgnore().execute();
		
		Calendar cal=Calendar.getInstance();
		cal.setTimeInMillis(movie.getReleaseDate().getTime());
		int releaseYear=cal.get(Calendar.YEAR);
		int releaseDecade=(releaseYear/10) * 10;
		int movieId = dsl.insertInto(MOVIE)
			.set(MOVIE.NAME,movie.getName())
			.set(MOVIE.ONLINE_STREAM_LINK,movie.getOnlineStreamLink())
			.set(MOVIE.RATING,movie.getRating()!=null?(byte)(movie.getRating()*2):null)
			.set(MOVIE.RELEASE_DATE,new Date(movie.getReleaseDate().getTime()))
			.set(MOVIE.RELEASE_YEAR,releaseYear)
			.set(MOVIE.RELEASE_DECADE,releaseDecade)
			.set(MOVIE.REVIEW, movie.getReview())
			.set(MOVIE.WRITER, movie.getWriter())
			.set(MOVIE.DIRECTOR_ID, dsl.select(DIRECTOR.ID).from(DIRECTOR).where(DIRECTOR.NAME.equal(movie.getDirector().getName())))
			.set(MOVIE.IMAGE_URL, movie.getImageUrl())
			.returning(MOVIE.ID).fetchOne().getId();
		
		if(movie.getActors()!=null){
			movie.getActors().forEach( actor -> {
				dsl.insertInto(ACTOR).set(ACTOR.NAME,actor.getName()).onDuplicateKeyIgnore().execute();
				dsl.insertInto(MOVIE_ACTOR).set(MOVIE_ACTOR.MOVIE_ID,movieId)
					.set(MOVIE_ACTOR.ACTOR_ID,dsl.select(ACTOR.ID).from(ACTOR).where(ACTOR.NAME.equal(actor.getName()))).execute();
			} );
		}
		
		if(movie.getTypeIds()!=null) movie.getTypeIds().forEach(id -> dsl.insertInto(MOVIE_TYPE).set(MOVIE_TYPE.MOVIE_ID,movieId).set(MOVIE_TYPE.TYPE_ID,id.byteValue()).execute());
		
		return movieId;
	}
	
	public void updateReleaseDate(Movie movie){
		Calendar cal=Calendar.getInstance();
		cal.setTimeInMillis(movie.getReleaseDate().getTime());
		int releaseYear=cal.get(Calendar.YEAR);
		cal.setTimeInMillis(movie.getReleaseDate().getTime());
		dsl.update(MOVIE).set(MOVIE.RELEASE_DATE,new Date(movie.getReleaseDate().getTime()))
			.where(MOVIE.NAME.equal(movie.getName()).and(MOVIE.RELEASE_YEAR.equal(releaseYear))).execute();
	}
	
	public void updateImageUrls(Movie movie){
		dsl.update(MOVIE).set(MOVIE.IMAGE_URL,movie.getImageUrl())
			.where(MOVIE.NAME.equal(movie.getName()).and(MOVIE.RELEASE_YEAR.equal(movie.getReleaseYear()))).execute();
	}
}
