package com.playtomic.tests.wallet.service;

import java.math.BigDecimal;

public interface PaymentService {
	
    public void charge(BigDecimal amount);
    
}
