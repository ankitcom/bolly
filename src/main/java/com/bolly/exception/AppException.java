package com.bolly.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AppException extends Exception{

	private static final long serialVersionUID = -2061081063329302170L;
	
	private int errorCode;
	private String errorMessage;
}
