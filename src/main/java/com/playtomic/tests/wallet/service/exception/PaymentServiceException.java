package com.playtomic.tests.wallet.service.exception;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class PaymentServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public PaymentServiceException(String message) {
		super(message);
	}
	
	public PaymentServiceException(String message, BigDecimal bigDecimal) {
		super(getPaymentExceptionMessage(message, bigDecimal));
	}
	
	private static String getPaymentExceptionMessage(String message, BigDecimal bigDecimal) {
    	String bigDecimalStr = NumberFormat.getCurrencyInstance().format(bigDecimal);
    	return String.format(message, bigDecimalStr);
	}
	
}
