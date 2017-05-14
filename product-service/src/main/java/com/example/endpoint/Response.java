package com.example.endpoint;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response<T> {
	private T data;
	private int httpResponseCode;
	private String appErrorCode;
	private String message;
	
	private Response(int httpResponseCode, String appErrorCode, String message, T data) {
		this.data = data;
		this.httpResponseCode = httpResponseCode;
		this.appErrorCode = appErrorCode;
		this.message = message;
	}
	
	private Response(int httpResponseCode, T data) {
		this.data = data;
		this.httpResponseCode = httpResponseCode;
	}

	public T getData() {
		return data;
	}

	public int getHttpResponseCode() {
		return httpResponseCode;
	}

	public String getAppErrorCode() {
		return appErrorCode;
	}

	public String getMessage() {
		return message;
	}
	
	public static <T> ResponseEntity<T> buildGoodResponse(T data) {
		return new ResponseEntity<T>(data, HttpStatus.OK);
	}

	public static <T> ResponseEntity<T> buildSuccessfulCreateResponse(T data) {
		return new ResponseEntity<T>(data, HttpStatus.CREATED);
	}

/*	public static <T> ResponseEntity<T> buildErrorResponse(HttpStatus httpResponseCode, StatucCode errorCode) {
		return new ResponseEntity<T>(errorCode, httpResponseCode);
	}

	public static <T> ResponseEntity<T> buildNoDataFoundResponse(String message) {
		return new ResponseEntity<T>(HttpStatus.NO_CONTENT.value(), null, message, null);
	}*/
}
