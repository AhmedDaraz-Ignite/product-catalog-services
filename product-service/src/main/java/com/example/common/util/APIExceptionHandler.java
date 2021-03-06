package com.example.common.util;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.common.exception.ProductServiceException;
import com.example.common.types.ServiceResponse;
import com.example.common.types.StatucCode;

/**
 * Controller advice, intercepting applications exceptions and return rest API relevant response.
 * @author Ahmed.Rabie
 *
 */
@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(APIExceptionHandler.class);
	
	/**
	 * Handle ProductServiceException exceptions
	 * @param ProductServiceException
	 * @param request
	 * @return Response with error information in standard application json payload.
	 */
	@ExceptionHandler(value = { ProductServiceException.class })
	protected ResponseEntity<Object> handleServiceException(ProductServiceException ex, WebRequest request) {
		
		LOG.error("An error occured", ex);

		ServiceResponse<Object> response = ServiceResponse.buildErrorResponse(mapToHttpStatus(ex.getStatusCode()), formatMessage(ex.getMessage(), ex.getArguments()), ex.getStatusCode().name());
		return new ResponseEntity<>(response, mapToHttpStatus(ex.getStatusCode()));

	}
	
	/**
	 * Handle IllegalArgumentException exceptions
	 * @param IllegalArgumentException
	 * @param request
	 * @return Response with error information in standard application json payload.
	 */
	@ExceptionHandler(value = { IllegalArgumentException.class })
	protected ResponseEntity<Object> handleRuntimeException(IllegalArgumentException ex, WebRequest request) {
		LOG.error("An error occured", ex);
		
		ServiceResponse<Object> response = ServiceResponse.buildErrorResponse(HttpStatus.BAD_REQUEST, "Provided arguments invalid", StatucCode.ILLEGAL_ARGUMENT_PROVIDED.name());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handle RuntimeException exceptions
	 * @param RuntimeException
	 * @param request
	 * @return Response with error information in standard application json payload.
	 */
	@ExceptionHandler(value = { RuntimeException.class })
	protected ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
		LOG.error("An error occured", ex);
		
		ServiceResponse<Object> response = ServiceResponse.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal error occured", StatucCode.INTERNAL_SERVICE_ERROR.name());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Map from application status code to HttpStatus code.
	 * @param statusCode
	 * @return HttpStatus
	 */
	private HttpStatus mapToHttpStatus(StatucCode statusCode) {
		switch (statusCode) {
		case RESOURCE_NOT_FOUND:
			return HttpStatus.NOT_FOUND;
		case CANNOT_CREATE_RESOURCE:
			return HttpStatus.NO_CONTENT;
		case INTERNAL_SERVICE_ERROR:
			return HttpStatus.NOT_ACCEPTABLE;
		default:
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}
	
	/**
	 * Format exception message 
	 * @param msg
	 * @param args
	 * @return
	 */
	private String formatMessage(String msg, Object[] args) {
		return MessageFormat.format(msg, args);
	}
}
