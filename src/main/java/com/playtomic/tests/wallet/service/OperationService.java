package com.playtomic.tests.wallet.service;

import java.math.BigDecimal;

public interface OperationService {

	public void charge(Long walletId, BigDecimal amount);
	
	public void recharge(Long walletId, BigDecimal amount);
	
}
