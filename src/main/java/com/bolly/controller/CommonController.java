package com.bolly.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bolly.exception.AppException;
import com.bolly.model.ApiResponse;
import com.bolly.service.BollyServiceImpl;

public abstract class CommonController {
	
	private static Logger logger =  org.slf4j.LoggerFactory.getLogger(CommonController.class);
	
	@Autowired
	BollyServiceImpl bollyServiceImpl;
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ApiResponse handleException(Exception ex) {
		logger.error("Exception occurred:"+ex.getMessage(),ex);
		return ApiResponse.builder().ok(false).message(ex.getMessage()).build();
	}

	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(AppException.class)
	public ApiResponse handleAppException(AppException ex) {
		logger.error("AppException occurred: errorCode:{}, errorMessage:{}",ex.getErrorCode(), ex.getErrorMessage());
		return ApiResponse.builder().ok(false).message(ex.getErrorMessage()).build();
	}
}
