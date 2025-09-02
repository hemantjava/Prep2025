package com.prep.interview.design_pattern.behavioural.strategy;

public class UpiPayment implements PaymentStrategy {
    private String upiId;

    public UpiPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public void pay(double amount) {
        System.out.println(amount + " paid using UPI: " + upiId);
    }
}
