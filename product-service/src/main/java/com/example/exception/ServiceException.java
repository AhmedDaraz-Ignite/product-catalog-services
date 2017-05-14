package com.example.exception;

public class ServiceException extends RuntimeException {
	private transient Object[] args;

	public ServiceException(String message, Object... args) {
		super(message);
		this.args = args;
	}

	public ServiceException(String message, Throwable wrappedException, Object... args) {
		super(message, wrappedException);
		this.args = args;
	}

	public Object[] getArguments() {
		return args;
	}
}
