package com.playtomic.tests.wallet.service.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NotFoundException() {
		super("It seems you're looking for something that doesn't exist");
	}
	
}
