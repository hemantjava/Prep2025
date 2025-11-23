package com.prep.interview.collection.solid.solution;

import com.prep.interview.collection.solid.solution.factory.PaymentFactory;
import com.prep.interview.collection.solid.solution.service.PaymentProcessor;

public class PaymentService {
    public void processPayment(String type, double amount) {
        PaymentProcessor paymentProcessor = PaymentFactory.payThrough(type);
        paymentProcessor.pay(amount);
    }
}
