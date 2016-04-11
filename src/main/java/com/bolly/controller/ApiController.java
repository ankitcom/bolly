package com.bolly.controller;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
public class ApiController {

	private static Logger logger =  org.slf4j.LoggerFactory.getLogger(ApiController.class);
	
	@RequestMapping(value = "home", method = RequestMethod.GET, produces = "application/json")
	public String home(){
		logger.debug("Hi:{}","Ankit");
		return "{\"Ankit\":\"Bhatnagar\"}";
	}
	
}
