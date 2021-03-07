package com.playtomic.tests.wallet.service.impl;


import java.math.BigDecimal;

import org.junit.Test;

import com.playtomic.tests.wallet.WalletApplicationIT;
import com.playtomic.tests.wallet.service.exception.PaymentServiceException;

public class ThirdPartyPaymentServiceImplTest extends WalletApplicationIT {

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
