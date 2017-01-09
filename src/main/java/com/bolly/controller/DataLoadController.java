package com.bolly.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bolly.model.ApiResponse;
import com.bolly.service.DataLoadServiceImpl;

@RestController
@ResponseBody
@RequestMapping("/admin")
public class DataLoadController extends CommonController{

	private static Logger logger =  LoggerFactory.getLogger(DataLoadController.class);
	
	@Autowired
	DataLoadServiceImpl dataLoadServiceImpl;
	
	@RequestMapping(value = "from-blog", method = RequestMethod.GET, produces = "application/json")
	public ApiResponse addMoviesFromBlog(){
		logger.debug("At addMoviesFromBlog");
		dataLoadServiceImpl.addMoviesFromBlog();
		return ApiResponse.builder().ok(true).message("Completed. Check logs").build();
	}
	
}
