package com.playtomic.tests.wallet.service.impl;

import java.math.BigDecimal;
import java.text.NumberFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playtomic.tests.wallet.api.dto.WalletDto;
import com.playtomic.tests.wallet.repository.WalletRepository;
import com.playtomic.tests.wallet.repository.entity.Wallet;
import com.playtomic.tests.wallet.service.PaymentService;
import com.playtomic.tests.wallet.service.WalletService;
import com.playtomic.tests.wallet.service.exception.PaymentServiceException;
import com.playtomic.tests.wallet.service.mapper.WalletMapper;

@Service
public class WalletServiceImpl implements WalletService {
	
    @Autowired
    WalletMapper walletMapper;
	
	@Autowired
	PaymentService paymentService;
	
    @Autowired
    WalletRepository walletRepository;

    @Override
	public WalletDto getWalletById(Long id) {
    	return walletMapper.toDto(walletRepository.findOne(id));
    }
    
    @Override
	public WalletDto charge(Long walletId, BigDecimal amount) {
		Wallet wallet = walletRepository.findOne(walletId);
		BigDecimal newBalance = wallet.getBalance().subtract(amount);
		if (newBalance.compareTo(new BigDecimal(0)) < 0) {
        	String balanceStr = NumberFormat.getCurrencyInstance().format(wallet.getBalance());
        	String message = String.format("You're living beyond your means, you only have  %s", balanceStr);
            throw new PaymentServiceException(message);
		} else {
			wallet.setBalance(newBalance);
			return walletMapper.toDto(walletRepository.save(wallet));
		}
	}

	@Override
	public WalletDto recharge(Long walletId, BigDecimal amount) {
		paymentService.charge(amount);
		Wallet wallet = walletRepository.findOne(walletId);
		wallet.setBalance(wallet.getBalance().add(amount));
		return walletMapper.toDto(walletRepository.save(wallet));
	}

}
