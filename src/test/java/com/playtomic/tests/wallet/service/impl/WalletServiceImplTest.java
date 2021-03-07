package com.playtomic.tests.wallet.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.playtomic.tests.wallet.WalletApplicationIT;
import com.playtomic.tests.wallet.api.dto.WalletDto;
import com.playtomic.tests.wallet.repository.WalletReadRepository;
import com.playtomic.tests.wallet.repository.WalletWriteRepository;
import com.playtomic.tests.wallet.repository.entity.Wallet;
import com.playtomic.tests.wallet.service.PaymentService;
import com.playtomic.tests.wallet.service.WalletService;
import com.playtomic.tests.wallet.service.exception.NotFoundException;
import com.playtomic.tests.wallet.service.exception.PaymentServiceException;
import com.playtomic.tests.wallet.service.mapper.WalletMapper;

public class WalletServiceImplTest extends WalletApplicationIT {
	
	@Autowired
	WalletService walletService;
	
	@Autowired
    WalletMapper walletMapper;
	
	@MockBean
	PaymentService paymentService;
	
	@MockBean
    WalletReadRepository walletReadRepository;
    
    @MockBean
    WalletWriteRepository walletWriteRepository;
	
	@Test
	public void should_chargeToWallet_when_walletExistsAndHasEnoughMoney() throws Exception {
		Long walletId = 1L;
		BigDecimal amount = new BigDecimal(20);
		Wallet wallet = getWallet(walletId, new BigDecimal(100));
		given(walletWriteRepository.findById(walletId)).willReturn(Optional.of(wallet));
		given(walletWriteRepository.save(wallet)).willReturn((wallet));
		
		WalletDto walletDto = walletService.charge(walletId, amount);
		
		then(paymentService).should().pay(amount);
		assertEquals(new BigDecimal(80), walletDto.getBalance());
	}
	
	@Test(expected = NotFoundException.class)
	public void should_throwNotFoundException_when_walletDoesNotExist() throws Exception {
		Long walletId = 1L;
		BigDecimal amount = new BigDecimal(20);
		given(walletWriteRepository.findById(walletId)).willReturn(Optional.empty());
		
		walletService.charge(walletId, amount);
	}
	
	@Test(expected = PaymentServiceException.class)
	public void should_throwPaymentServiceException_when_walletDoesNotHaveEnoughMoney() throws Exception {
		Long walletId = 1L;
		BigDecimal amount = new BigDecimal(200);
		Wallet wallet = getWallet(walletId, new BigDecimal(100));
		given(walletWriteRepository.findById(walletId)).willReturn(Optional.of(wallet));
		
		walletService.charge(walletId, amount);
	}
	
	private Wallet getWallet(Long walletId, BigDecimal balance) {
		Wallet wallet = new Wallet();
		wallet.setId(walletId);
		wallet.setBalance(balance);
		return wallet;
	}
	
}
