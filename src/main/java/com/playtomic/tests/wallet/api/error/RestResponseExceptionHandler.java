package com.playtomic.tests.wallet.api.error;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.playtomic.tests.wallet.service.exception.PaymentServiceException;

@RestControllerAdvice
public class RestResponseExceptionHandler {
	
	private Logger log = LoggerFactory.getLogger(RestResponseExceptionHandler.class);
	
	@ExceptionHandler({PaymentServiceException.class})
    public ResponseEntity<ErrorDto> handleControlledException(HttpServletRequest request, Throwable ex) {
		log.info(ex.getMessage());
        return handleException(ex, HttpStatus.CONFLICT, ex.getLocalizedMessage());
    }
	
//	@ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorDto> handleUncontrolledException(HttpServletRequest request, Throwable ex) {
//		log.error(ex.getMessage());
//        return handleException(ex, HttpStatus.INTERNAL_SERVER_ERROR, "Ups, this was unexpected");
//    }
	
	private ResponseEntity<ErrorDto> handleException(Throwable ex, HttpStatus code, String message) {
		ErrorDto errorDto = new ErrorDto(code.value(), message);
        return ResponseEntity.status(code).body(errorDto);
    }
    
}
