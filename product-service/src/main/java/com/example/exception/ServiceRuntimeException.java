package com.example.exception;

public class ServiceRuntimeException extends Exception {
	private transient Object[] args;

	public ServiceRuntimeException(String message, Object... args) {
		super(message);
		this.args = args;
	}

	public ServiceRuntimeException(String message, Throwable wrappedException, Object... args) {
		super(message, wrappedException);
		this.args = args;
	}

	public Object[] getArguments() {
		return args;
	}
}
