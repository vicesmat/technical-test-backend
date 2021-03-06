package com.playtomic.tests.wallet.service.impl;

import java.math.BigDecimal;
import java.text.NumberFormat;

import org.springframework.context.annotation.Primary;
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
	
    private BigDecimal threshold = new BigDecimal(10);

    @Override
    public void charge(BigDecimal amount) {
        if (amount.compareTo(threshold) < 0) {
        	String thresholdStr = NumberFormat.getCurrencyInstance().format(threshold);
        	String message = String.format("We don't even bother to charge less than %s", thresholdStr);
            throw new PaymentServiceException(message);
        }
    }
    
}
