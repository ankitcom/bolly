package com.bolly.service;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolly.model.Movie;
import com.bolly.model.Movie.MovieBuilder;
import com.bolly.model.Person;

/**
 * 
 * @author ankit.bhatnagar
 *
 */

@Service
public class DataLoadServiceImpl {
	
	private Logger logger = LoggerFactory.getLogger(DataLoadServiceImpl.class);
	
	@Autowired
	DSLContext dsl;
	
	@Autowired
	BollyServiceImpl bollyServiceImpl;
	
	public void addMoviesFromBlog(){
		logger.trace("addMoviesFromBlog");
		
		String thisLink="https://maggyreviews.com/2014/02/14/gunday-review/";
		String nextLink=thisLink;
		boolean isNext=true;
		while(isNext){
			try{
				thisLink=nextLink;
				if(thisLink.indexOf("dangal")>0) isNext=false;
				Document doc=Jsoup.connect(thisLink).get();
				Elements elems=doc.getElementsByClass("entry-content");
				Element elem=elems.get(0);
				String text=elem.text();
//				logger.trace("text:{}",text);
				nextLink=doc.getElementsByAttributeValueMatching("rel", "next").get(0).attr("href");
				
				MovieBuilder movieBuilder = Movie.builder();
				String dirStr="Director:";
				String actStr="Actors:";
				String wriStr="Writer:";
				String ratStr="Rating:";
				String str5="/ 5";
				if(text.indexOf(dirStr)<0||text.indexOf(actStr)<0||text.indexOf(ratStr)<0){
					logger.debug("Not a valid content to make Movie entry. Content Text:{}",text);
					continue;
				}
				movieBuilder.director(Person.builder().name(text.substring(text.indexOf(dirStr)+dirStr.length(), text.indexOf(actStr)).trim()).build());
				String actorsStr=text.substring(text.indexOf(actStr)+actStr.length(), text.indexOf(wriStr)>-1?text.indexOf(wriStr):text.indexOf(ratStr));
				String[] actors=actorsStr.split(",");
				Set<Person> actorsSet=new HashSet<>();
				for(byte i=0;i<actors.length;i++){
					actorsSet.add(Person.builder().name(actors[i].trim()).build());
				}
				movieBuilder.actors(actorsSet);
				movieBuilder.rating((byte)(Float.parseFloat(text.substring(text.indexOf(ratStr)+ratStr.length(), text.indexOf(str5)).trim())*2));
				if(text.indexOf(wriStr)>-1){
					movieBuilder.writer(text.substring(text.indexOf(wriStr)+wriStr.length(), text.indexOf(ratStr)).trim());
				}
				String review=text.substring(0, text.indexOf(dirStr))+"\n"+text.substring(text.indexOf(str5)+str5.length(), text.indexOf("Share this:"));
				movieBuilder.review(review);
				movieBuilder.name(doc.getElementsByClass("entry-title").get(0).text());
				
				movieBuilder.releaseDate(new SimpleDateFormat("yyyy-MM-dd").parse(doc.getElementsByClass("entry-date published").get(0).attr("datetime").substring(0, 10)));
				movieBuilder.imageUrl(doc.getElementsByAttributeValueMatching("property", "og:image").get(0).attr("content"));
				
				Movie movie=movieBuilder.build();
	//			logger.trace("Movie:{}",movie);
				bollyServiceImpl.addMovie(movie);
			}catch(DataAccessException e){
				if(e.getMessage().indexOf("Duplicate entry")<0){
					logger.error("Error occured for link:{}",thisLink);
					logger.error(e.getMessage(),e);
				}
			}catch(Exception e){
				logger.error("Error occured for link:{}",thisLink);
				logger.error(e.getMessage(),e);
			}
		}
	}
	
}
