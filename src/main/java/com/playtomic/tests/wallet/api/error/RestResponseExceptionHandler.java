package com.playtomic.tests.wallet.api.error;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.playtomic.tests.wallet.service.exception.NotFoundException;
import com.playtomic.tests.wallet.service.exception.PaymentServiceException;

@RestControllerAdvice
public class RestResponseExceptionHandler {
	
	private static final String EXCEPTION_STR = "Exception: ";
	
	private Logger log = LoggerFactory.getLogger(RestResponseExceptionHandler.class);
	
	@ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorDto> handleNotFoundException(HttpServletRequest request, Throwable ex) {
		info(ex);
        return handleException(ex, HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
    }
	
	@ExceptionHandler({PaymentServiceException.class})
    public ResponseEntity<ErrorDto> handleConflictException(HttpServletRequest request, Throwable ex) {
		info(ex);
        return handleException(ex, HttpStatus.CONFLICT, ex.getLocalizedMessage());
    }
	
	@ExceptionHandler({	MethodArgumentNotValidException.class,
						HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorDto> handleBadRequestException(HttpServletRequest request, Throwable ex) {
		info(ex);
        return handleException(ex, HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
    }
	
	@ExceptionHandler({MethodArgumentTypeMismatchException.class})
	public ResponseEntity<ErrorDto> handleMismatchException(HttpServletRequest request, Throwable ex) {
		info(ex);
		return handleException(ex, HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());
	}
	
	@ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorDto> handleUncontrolledException(HttpServletRequest request, Throwable ex) {
		debug(ex);
        return handleException(ex, HttpStatus.INTERNAL_SERVER_ERROR, "Ups, this was unexpected");
    }
	
	private ResponseEntity<ErrorDto> handleException(Throwable ex, HttpStatus code, String message) {
		ErrorDto errorDto = new ErrorDto(code.value(), message);
        return ResponseEntity.status(code).body(errorDto);
    }
	
	private void info(Throwable ex) {
		log.info(EXCEPTION_STR.concat(ex.getMessage()));
	}
	
	private void debug(Throwable ex) {
		ex.printStackTrace();
	}
    
}
