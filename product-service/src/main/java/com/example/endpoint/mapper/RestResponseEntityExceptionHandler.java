package com.example.endpoint.mapper;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.endpoint.ApiError;
import com.example.endpoint.StatucCode;
import com.example.exception.ProductServiceException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);
	
	@ExceptionHandler(value = { ProductServiceException.class })
	protected ResponseEntity<Object> handleServiceException(ProductServiceException ex, WebRequest request) {
		
		ApiError apiError = new ApiError(mapToHttpStatus(ex.getStatusCode()),
				formatMessage(ex.getMessage(), ex.getArguments()), ex.getStatusCode().name());
		LOG.error("An error occured", ex);
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getHttpStatus());
	}
	
	
	private HttpStatus mapToHttpStatus(StatucCode statusCode) {
		switch (statusCode) {
		case PRODUCT_NOT_FOUND:
			return HttpStatus.NOT_FOUND;
		case CANNOT_CREATE_PRODUCT:
			return HttpStatus.NO_CONTENT;
		case INTERNAL_SERVICE_ERROR:
			return HttpStatus.NOT_ACCEPTABLE;
		default:
			break;
		}
		return HttpStatus.OK;
	}
	
	private String formatMessage(String msg, Object[] args) {
		return MessageFormat.format(msg, args);
	}
}
