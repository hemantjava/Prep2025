package com.prep.interview.collection.solid.solution.service.impl;

import com.prep.interview.collection.solid.solution.service.PaymentProcessor;

public class DebitCardPayment implements PaymentProcessor {
    @Override
    public void pay(Double amount) {
        System.out.println("Pay through debit card amount:"+ amount);
    }
}
