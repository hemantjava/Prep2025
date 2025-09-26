package com.prep.interview.design_pattern.behavioural.strategy;

public record CreditCardPayment(String cardNumber) implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println(amount + " paid using Credit Card: " + cardNumber);
    }
}
