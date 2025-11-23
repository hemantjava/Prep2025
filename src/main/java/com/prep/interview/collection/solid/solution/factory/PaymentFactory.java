package com.prep.interview.collection.solid.solution.factory;

import com.prep.interview.collection.solid.solution.service.PaymentProcessor;
import com.prep.interview.collection.solid.solution.service.impl.CreditCardPayment;
import com.prep.interview.collection.solid.solution.service.impl.CryptoPayment;
import com.prep.interview.collection.solid.solution.service.impl.DebitCardPayment;
import com.prep.interview.collection.solid.solution.service.impl.PaypalPayment;


//Factory methods
public class PaymentFactory {

    public static PaymentProcessor payThrough(String type) {
        return switch (type.toUpperCase()) {
            case "CREDITCARD" -> new CreditCardPayment();
            case "CRYPTO" -> new CryptoPayment();
            case "DEBITCARD" -> new DebitCardPayment();
            case "PAYPAL" -> new PaypalPayment();
            default -> throw new RuntimeException("Payment not supported exception type:"+ type);
        };

    }
}
