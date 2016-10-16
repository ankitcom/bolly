package com.bolly.model;

import lombok.Builder;
import lombok.Getter;

/**
 * 
 * @author ankit.bhatnagar
 *
 */
@Builder
@Getter
public class ApiResponse {

	private boolean ok;
	private String message;

}
