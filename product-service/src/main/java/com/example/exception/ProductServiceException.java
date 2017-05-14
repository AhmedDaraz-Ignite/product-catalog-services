package com.example.exception;

import com.example.endpoint.StatucCode;

@SuppressWarnings("serial")
public class ProductServiceException extends RuntimeException {
	
	private final transient Object[] args;
	private final StatucCode statusCode;
	private final String message;
	
	public ProductServiceException(String message, StatucCode statusCode, Object... args) {
		super(message);
		this.statusCode = statusCode;
		this.message = message;
		this.args = args;
	}

	public ProductServiceException(String message, StatucCode statusCode, Throwable wrappedException, Object... args) {
		super(message, wrappedException);
		this.statusCode = statusCode;
		this.message = message;
		this.args = args;
	}

	public Object[] getArguments() {
		return args;
	}

	public StatucCode getStatusCode() {
		return statusCode;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
