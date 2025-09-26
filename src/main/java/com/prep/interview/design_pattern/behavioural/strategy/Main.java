package com.prep.interview.design_pattern.behavioural.strategy;

public class Main {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        // Pay via Credit Card
        PaymentStrategy paymentStrategy = new CreditCardPayment("1234-5678-9876-5432");
        context.setPaymentStrategy(paymentStrategy);
        context.payBill(2500.0);

        // Switch to PayPal
        PaymentStrategy paymentStrategy1 = new PayPalPayment("hemant@example.com");
        context.setPaymentStrategy(paymentStrategy1);
        context.payBill(1200.0);

        // Switch to UPI
        PaymentStrategy paymentStrategy2 = new UpiPayment("hemant@upi");
        context.setPaymentStrategy(paymentStrategy2);
        context.payBill(500.0);
    }
}
