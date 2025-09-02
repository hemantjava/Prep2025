package com.prep.interview.design_pattern.behavioural.strategy;

public class PaymentContext {
    private PaymentStrategy paymentStrategy;

    // allow dynamic change
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void payBill(double amount) {
        if (paymentStrategy == null) {
            throw new IllegalStateException("Payment strategy not set!");
        }
        paymentStrategy.pay(amount);
    }
}
