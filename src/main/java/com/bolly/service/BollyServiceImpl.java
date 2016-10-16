package com.bolly.service;

import static com.bolly.jooq.Tables.ACTOR;
import static com.bolly.jooq.Tables.DIRECTOR;
import static com.bolly.jooq.Tables.MOVIE;
import static com.bolly.jooq.Tables.MOVIE_ACTOR;
import static com.bolly.jooq.Tables.MOVIE_TYPE;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolly.model.Movie;

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
	
	public Result<Record> getMovies(){

		Result<Record> result=dsl.select().from(MOVIE).fetch();
		
		for (Record r : result) {
		    Integer id = r.getValue(MOVIE.ID);
		    String name = r.getValue(MOVIE.NAME);
		    int rating = r.getValue(MOVIE.RATING);

		    logger.debug("ID: {} first name:{}  last name: {}",id, name, rating);
		}
		return result;
	}

	@Transactional
	public int addMovie(Movie movie) {
		
		if(movie.getDirector().getId()<=0){
			movie.getDirector().setId(dsl.insertInto(DIRECTOR).set(DIRECTOR.NAME,movie.getDirector().getName()).returning(DIRECTOR.ID).fetchOne().getId());
		}
		
		List<Integer> actorIds=new ArrayList<>();
		if(movie.getActors()!=null){
			movie.getActors().forEach( actor -> {
				if(actor.getId()>0){
					actorIds.add(actor.getId());
				}else{
					actorIds.add(dsl.insertInto(ACTOR).set(ACTOR.NAME,actor.getName()).returning(ACTOR.ID).fetchOne().getId());
				}
			} );
		}
		
		Calendar cal=Calendar.getInstance();
		cal.setTimeInMillis(movie.getReleaseDate().getTime());
		int releaseYear=cal.get(Calendar.YEAR);
		int movieId = dsl.insertInto(MOVIE)
			.set(MOVIE.NAME,movie.getName())
			.set(MOVIE.ONLINE_STREAM_LINK,movie.getOnlineStreamLink())
			.set(MOVIE.RATING,movie.getRating()).set(MOVIE.RELEASE_DATE,movie.getReleaseDate())
			.set(MOVIE.RELEASE_YEAR,releaseYear)
			.set(MOVIE.RELEASE_DECADE,(releaseYear/10) * 10)
			.set(MOVIE.REVIEW, movie.getReview())
			.set(MOVIE.WRITER, movie.getWriter())
			.set(MOVIE.DIRECTOR_ID, movie.getDirector().getId())
			.returning(MOVIE.ID).fetchOne().getId();
		
		if(movie.getTypeIds()!=null) movie.getTypeIds().forEach(id -> dsl.insertInto(MOVIE_TYPE).set(MOVIE_TYPE.MOVIE_ID,movieId).set(MOVIE_TYPE.TYPE_ID,id).execute());
		actorIds.forEach(id -> dsl.insertInto(MOVIE_ACTOR).set(MOVIE_ACTOR.MOVIE_ID,movieId).set(MOVIE_ACTOR.ACTOR_ID,id).execute());
		
		return movieId;
	}
}
