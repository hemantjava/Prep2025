package com.prep.interview.design_pattern.behavioural.strategy;

public record UpiPayment(String upiId) implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println(amount + " paid using UPI: " + upiId);
    }
}
