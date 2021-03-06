package com.playtomic.tests.wallet.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playtomic.tests.wallet.api.dto.WalletDto;
import com.playtomic.tests.wallet.repository.WalletRepository;
import com.playtomic.tests.wallet.repository.entity.Wallet;
import com.playtomic.tests.wallet.service.OperationService;
import com.playtomic.tests.wallet.service.PaymentServiceException;
import com.playtomic.tests.wallet.service.mapper.WalletMapper;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    WalletMapper walletMapper;
	
	@Autowired
	ThirdPartyPaymentService thirdPartyPaymentService;
    
    @Autowired
    WalletRepository walletRepository;
    
	@Override
	public WalletDto charge(Long walletId, BigDecimal amount) {
		Wallet wallet = walletRepository.findOne(walletId);
		wallet.setBalance(wallet.getBalance().subtract(amount));
		return walletMapper.toDto(walletRepository.save(wallet));
	}

	@Override
	public WalletDto recharge(Long walletId, BigDecimal amount) {
		try {
			thirdPartyPaymentService.charge(amount);
			Wallet wallet = walletRepository.findOne(walletId);
			wallet.setBalance(wallet.getBalance().add(amount));
			return walletMapper.toDto(walletRepository.save(wallet));
		} catch (PaymentServiceException e) {
			throw new RuntimeException("Recharge failed.", e);
		}
	}
	
}
