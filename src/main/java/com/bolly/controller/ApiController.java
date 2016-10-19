package com.bolly.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bolly.exception.AppException;
import com.bolly.model.Movie;

@RestController
@ResponseBody
public class ApiController extends CommonController{

	private static Logger logger =  org.slf4j.LoggerFactory.getLogger(ApiController.class);

	@RequestMapping(value = "movie", params={"recent_count"}, method = RequestMethod.GET, produces = "application/json")
	public List<Movie> getRecentMovies(@RequestParam("recent_count") int recentCount){
		logger.debug("At getRecentMovies");
		return bollyServiceImpl.getRecentMovies(recentCount);
	}
	
	@RequestMapping(value = "movie/{id}", method = RequestMethod.GET, produces = "application/json")
	public Movie getMovie(@PathVariable("id") int id) throws AppException{
		logger.debug("At getMovie");
		return bollyServiceImpl.getMovie(id);
	}
	
}
