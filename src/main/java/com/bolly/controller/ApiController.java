package com.bolly.controller;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
public class ApiController extends CommonController{

	private static Logger logger =  org.slf4j.LoggerFactory.getLogger(ApiController.class);

	
}
