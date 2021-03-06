package com.playtomic.tests.wallet.service.impl;

import java.math.BigDecimal;
import java.text.NumberFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.playtomic.tests.wallet.service.PaymentService;
import com.playtomic.tests.wallet.service.exception.PaymentServiceException;


/**
 * A real implementation would call to a third party's payment service (such as Stripe, Paypal, Redsys...).
 *
 * This is a dummy implementation which throws an error when trying to change less than 10€.
 */
@Service
public class ThirdPartyPaymentServiceImpl implements PaymentService {
	
	private Logger log = LoggerFactory.getLogger(ThirdPartyPaymentServiceImpl.class);
	
    private BigDecimal threshold = new BigDecimal(10);

    @Override
    public void charge(BigDecimal amount) {
    	log.info("Charging {} to third party", NumberFormat.getCurrencyInstance().format(amount));
        if (amount.compareTo(threshold) < 0) {
        	throw new PaymentServiceException("The third party doesn't even bother to charge less than %s", threshold);
        }
    }
    
    @Override
    public void pay(BigDecimal amount) {
    	log.info("Paying {} to external service", NumberFormat.getCurrencyInstance().format(amount));
    }
    
}
