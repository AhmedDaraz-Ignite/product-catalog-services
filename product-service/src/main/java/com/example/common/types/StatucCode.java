package com.example.common.types;

/**
 * Application defined status codes
 * @author Ahmed.Rabie
 *
 */
public enum StatucCode {
	
	RESOURCE_CREATED_SUCCESFULLY(100), 
	RESOURCE_NOT_FOUND(301), 
	INTERNAL_SERVICE_ERROR(302), 
	CANNOT_CREATE_RESOURCE(303), 
	ILLEGAL_ARGUMENT_PROVIDED(304);

	private int statusCode;
	
	private StatucCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	
}
