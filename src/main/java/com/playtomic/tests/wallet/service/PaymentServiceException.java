package com.playtomic.tests.wallet.service;

/**
 *
 */
public class PaymentServiceException extends RuntimeException {
	
	private static final long serialVersionUID = -415598407469568576L;
	
	public PaymentServiceException() {
		super();
	}
	
	public PaymentServiceException(String message) {
		super(message);
	}
	
}
