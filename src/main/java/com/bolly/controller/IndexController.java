package com.bolly.controller;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
public class IndexController {

	private static Logger logger =  org.slf4j.LoggerFactory.getLogger(IndexController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public String home(){
		
		logger.debug("Home");
		return "{\"Ankit\":\"Bhatnagar\"}";
	}
	
}
