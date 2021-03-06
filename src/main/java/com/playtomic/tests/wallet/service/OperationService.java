package com.playtomic.tests.wallet.service;

import java.math.BigDecimal;

import com.playtomic.tests.wallet.api.dto.WalletDto;

public interface OperationService {

	public WalletDto charge(Long walletId, BigDecimal amount);
	
	public WalletDto recharge(Long walletId, BigDecimal amount);
	
}
