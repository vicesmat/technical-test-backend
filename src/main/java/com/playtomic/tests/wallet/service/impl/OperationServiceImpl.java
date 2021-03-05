package com.playtomic.tests.wallet.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playtomic.tests.wallet.repository.WalletRepository;
import com.playtomic.tests.wallet.repository.entity.Wallet;
import com.playtomic.tests.wallet.service.OperationService;
import com.playtomic.tests.wallet.service.PaymentServiceException;

@Service
public class OperationServiceImpl implements OperationService {
	
	@Autowired
	ThirdPartyPaymentService thirdPartyPaymentService;
    
    @Autowired
    WalletRepository walletRepository;

	@Override
	public void charge(Long walletId, BigDecimal amount) {
		Wallet wallet = walletRepository.findOne(walletId);
		wallet.setBalance(wallet.getBalance().subtract(amount));
		walletRepository.save(wallet);
	}

	@Override
	public void recharge(Long walletId, BigDecimal amount) {
		try {
			thirdPartyPaymentService.charge(amount);
			Wallet wallet = walletRepository.findOne(walletId);
			wallet.setBalance(wallet.getBalance().add(amount));
			walletRepository.save(wallet);
		} catch (PaymentServiceException e) {
			e.printStackTrace();
		}
	}
	
}
