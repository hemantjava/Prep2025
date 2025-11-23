package com.prep.interview.collection.solid.solution.service.impl;

import com.prep.interview.collection.solid.solution.service.PaymentProcessor;

public class CryptoPayment implements PaymentProcessor {
    @Override
    public void pay(Double amount) {
        System.out.println("Pay through Crypto amount:"+ amount);
    }
}
