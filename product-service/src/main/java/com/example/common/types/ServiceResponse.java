package com.example.common.types;

import org.springframework.http.HttpStatus;

/**
 * Service response wrapper object, wrapping POJO, application status code, message and Http status code.
 * @author Ahmed.Rabie
 *
 * @param <T>
 */
public class ServiceResponse <T> {
	
	private T data;
	private int httpResponseCode;
	private String appErrorCode;
	private String message;
	
	public static <T> ServiceResponse<T> buildGoodResponse(T data) {
		return new ServiceResponse<>(data, HttpStatus.OK.value());
	}

	public static <T> ServiceResponse<T> buildErrorResponse(HttpStatus httpStatus, String errorCode, String message) {
		return new ServiceResponse<>(null, httpStatus.value(), errorCode, message);
	}

	public static <T> ServiceResponse<T> buildSuccessfulCreateResponse(T data) {
		return new ServiceResponse<>(data, HttpStatus.CREATED.value());
	}
	
	public ServiceResponse(T data, int httpResponseCode) {
		this.data = data;
		this.httpResponseCode = httpResponseCode;
	}

	public ServiceResponse(T data, int httpResponseCode, String appErrorCode, String message) {
		this.data = data;
		this.httpResponseCode = httpResponseCode;
		this.appErrorCode = appErrorCode;
		this.message = message;
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
}
