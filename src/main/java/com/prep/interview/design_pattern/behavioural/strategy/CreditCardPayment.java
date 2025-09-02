package com.prep.interview.design_pattern.behavioural.strategy;

public class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println(amount + " paid using Credit Card: " + cardNumber);
    }
}
