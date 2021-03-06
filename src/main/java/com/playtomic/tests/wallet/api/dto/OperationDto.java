package com.playtomic.tests.wallet.api.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

public class OperationDto {

	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = Integer.MAX_VALUE, fraction = 2)
	private BigDecimal amount;
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
}
