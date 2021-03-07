package com.playtomic.tests.wallet.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.playtomic.tests.wallet.api.WalletController;
import com.playtomic.tests.wallet.api.dto.WalletDto;
import com.playtomic.tests.wallet.service.WalletService;
import com.playtomic.tests.wallet.service.exception.NotFoundException;

@RunWith(SpringRunner.class)
@WebMvcTest(WalletController.class)
public class WalletControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private WalletService walletService;
	
	@Test
	public void should_returnWallet_when_exists() throws Exception {
		Long walletId = 1L;
		Integer balance = 20;
		WalletDto walletDto = new WalletDto();
		walletDto.setWalletId(walletId);
		walletDto.setBalance(new BigDecimal(balance));
		given(walletService.getWalletById(walletId)).willReturn(walletDto);
		
		this.mockMvc.perform(get("/wallets/1"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.walletId").value(1))
			.andExpect(jsonPath("$.balance").value(balance))
			.andExpect(jsonPath("$._links.self").exists())
			.andExpect(jsonPath("$._links.payment").exists())
			.andExpect(jsonPath("$._links.top-up").exists())
			;
	}
	
	@Test
	public void should_returnNotFoundError_when_doesNotExist() throws Exception {
		given(walletService.getWalletById(1L)).willThrow(new NotFoundException());
		
		this.mockMvc.perform(get("/wallets/1"))
			.andDo(print())
			.andExpect(status().isNotFound())
			;
	}
	
	@Test
	public void should_returnInternalServerError_when_givingString() throws Exception {		
		this.mockMvc.perform(get("/wallets/one"))
			.andDo(print())
			.andExpect(status().isInternalServerError())
			;
	}
	
}
