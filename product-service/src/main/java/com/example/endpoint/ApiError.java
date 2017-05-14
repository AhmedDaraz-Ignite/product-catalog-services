package com.example.endpoint;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiError {

	private HttpStatus httpStatus;
	private String message;
	private List<String> errors;

	public ApiError(HttpStatus status, String message, List<String> errs) {
		super();
		this.httpStatus = status;
		this.message = message;
		this.errors = errs;
	}

	public ApiError(HttpStatus status, String message, String error) {
		super();
		this.httpStatus = status;
		this.message = message;
		errors = Arrays.asList(error);
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public List<String> getErrors() {
		return errors;
	}
}