package com.prep.interview.design_pattern.behavioural.strategy;

public record PayPalPayment(String email) implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println(amount + " paid using PayPal account: " + email);
    }
}
