package com.bolly.controller;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bolly.model.ApiResponse;

public abstract class CommonController {
	
	private static Logger logger =  org.slf4j.LoggerFactory.getLogger(CommonController.class);
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ApiResponse handleException(Exception ex) {
		logger.error("Exception occurred:"+ex.getMessage(),ex);
		return ApiResponse.builder().ok(false).message(ex.getMessage()).build();
	}

}
