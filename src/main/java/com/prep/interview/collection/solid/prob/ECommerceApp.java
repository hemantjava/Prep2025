package com.prep.interview.collection.solid.prob;

import java.util.ArrayList;
import java.util.List;

class Order {
    String product;
    int quantity;
    double price;

    public Order(String product, int quantity, double price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public double getTotal() {
        return quantity * price;
    }
}

class OrderService {
    private List<Order> orders = new ArrayList<>();

    public void createOrder(String product, int quantity, double price) {
        Order order = new Order(product, quantity, price);
        orders.add(order);
        System.out.println("Order created for: " + product);
    }

    public void processPayment(String type, double amount) {
        if (type.equals("CreditCard")) {
            System.out.println("Processing Credit Card payment: " + amount);
        } else if (type.equals("PayPal")) {
            System.out.println("Processing PayPal payment: " + amount);
        } else if (type.equals("Crypto")) {
            System.out.println("Processing Crypto payment: " + amount);
        }
    }

    public void saveToDatabase() {
        System.out.println("Saving orders to MySQL database...");
    }

    public void sendEmailConfirmation(String email) {
        System.out.println("Sending order confirmation email to: " + email);
    }
}

public class ECommerceApp {
    public static void main(String[] args) {
        OrderService service = new OrderService();
        service.createOrder("Laptop", 1, 1500.0);
        service.processPayment("CreditCard", 1500.0);
        service.saveToDatabase();
        service.sendEmailConfirmation("customer@shop.com");
    }
}