package com.playtomic.tests.wallet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playtomic.tests.wallet.api.dto.WalletDto;
import com.playtomic.tests.wallet.repository.WalletRepository;
import com.playtomic.tests.wallet.service.WalletService;
import com.playtomic.tests.wallet.service.mapper.WalletMapper;

@Service
public class WalletServiceImpl implements WalletService {
	
    @Autowired
    WalletRepository walletRepository;
    
    @Autowired
    WalletMapper walletMapper;

    @Override
	public WalletDto getWalletById(Long id) {
    	return walletMapper.toDto(walletRepository.findOne(id));
    }

}
