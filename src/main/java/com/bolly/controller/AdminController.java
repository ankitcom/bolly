package com.bolly.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bolly.model.ApiResponse;
import com.bolly.model.Movie;
import com.bolly.service.BollyServiceImpl;

@RestController
@ResponseBody
@RequestMapping("/admin")
public class AdminController extends CommonController{

	private static Logger logger =  org.slf4j.LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	BollyServiceImpl bollyServiceImpl;
	
	@RequestMapping(value = "movie", method = RequestMethod.POST, produces = "application/json")
	public ApiResponse addMovie(@RequestBody Movie movie){
		logger.debug("At addMovie");
		int id=bollyServiceImpl.addMovie(movie);
		return ApiResponse.builder().ok(true).message("Movie added with id:"+id).build();
	}
	
	@RequestMapping(value = "movie", method = RequestMethod.GET, produces = "application/json")
	public String getMovies(){
		logger.debug("At getMovies");
		return bollyServiceImpl.getMovies().formatJSON();
	}
	
}
