package com.playtomic.tests.wallet.service.impl;


import org.junit.Test;

import com.playtomic.tests.wallet.service.exception.PaymentServiceException;

import java.math.BigDecimal;

public class ThirdPartyPaymentServiceImplTest {

    ThirdPartyPaymentServiceImpl s = new ThirdPartyPaymentServiceImpl();

    @Test(expected = PaymentServiceException.class)
    public void test_exception() throws PaymentServiceException {
        s.charge(new BigDecimal(5));
    }

    @Test
    public void test_ok() throws PaymentServiceException {
        s.charge(new BigDecimal(15));
    }
}
