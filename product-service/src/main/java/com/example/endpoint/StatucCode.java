package com.example.endpoint;

public enum StatucCode {
	PRODUCT_NOT_FOUND(301), INTERNAL_SERVICE_ERROR(302), CANNOT_CREATE_PRODUCT(303);

	private int statusCode;
	
	private StatucCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	
}
