package com.bolly.controller;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bolly.model.ApiResponse;
import com.bolly.model.Movie;

@RestController
@ResponseBody
@RequestMapping("/admin")
public class AdminController extends CommonController{

	private static Logger logger =  org.slf4j.LoggerFactory.getLogger(AdminController.class);
	
	@RequestMapping(value = "movie", method = RequestMethod.POST, produces = "application/json")
	public ApiResponse addMovie(@RequestBody Movie movie){
		logger.debug("At addMovie");
		int id=bollyServiceImpl.addMovie(movie);
		return ApiResponse.builder().ok(true).message("Movie added with id:"+id).build();
	}
	
}
