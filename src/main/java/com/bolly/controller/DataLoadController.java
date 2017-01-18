package com.bolly.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping(value = "from-blog", method = RequestMethod.POST, produces = "application/json")
	public ApiResponse addMoviesFromBlog(){
		logger.debug("At addMoviesFromBlog");
		dataLoadServiceImpl.addMoviesFromBlog();
		return ApiResponse.builder().ok(true).message("Completed. Check logs").build();
	}
	
	@RequestMapping(value = "from-files", method = RequestMethod.POST, produces = "application/json")
	public ApiResponse addMoviesFromFiles(){
		logger.debug("At addMoviesFromFiles");
		dataLoadServiceImpl.addMoviesFromFiles();
		return ApiResponse.builder().ok(true).message("Completed. Check logs").build();
	}
	
	@RequestMapping(value = "from-wiki", method = RequestMethod.GET, produces = "application/json")
	public ApiResponse addMoviesFromWiki(@RequestParam(name="year") String year){
		logger.debug("At addMoviesFromWiki");
		if("2016".equals(year)) dataLoadServiceImpl.addMoviesFromWiki("https://en.wikipedia.org/wiki/List_of_Bollywood_films_of_2016", year);
		else if("2015".equals(year)) dataLoadServiceImpl.addMoviesFromWiki("https://en.wikipedia.org/wiki/List_of_Bollywood_films_of_2015", year);
		
		return ApiResponse.builder().ok(true).message("Completed. Check logs").build();
	}
}
