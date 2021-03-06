package com.playtomic.tests.wallet.service.exception;

/**
 *
 */
public class PaymentServiceException extends RuntimeException {

	private static final long serialVersionUID = 8660831765306284408L;

	public PaymentServiceException() {
		super();
	}
	
	public PaymentServiceException(String message) {
		super(message);
	}
	
}
