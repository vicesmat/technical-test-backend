package com.playtomic.tests.wallet.api.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.ResourceSupport;

public class WalletDto extends ResourceSupport  {

	@NotNull
    private Long walletId;
    @NotNull
	@Min(0)
    private BigDecimal balance;
    
	public Long getWalletId() {
		return walletId;
	}
	
	public void setWalletId(Long walletId) {
		this.walletId = walletId;
	}
	
	public BigDecimal getBalance() {
		return balance;
	}
	
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
    
}
