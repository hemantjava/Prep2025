package com.prep.interview.collection.solid.solution;


import com.prep.interview.collection.solid.solution.dto.Order;
import com.prep.interview.collection.solid.solution.service.NotificationService;
import com.prep.interview.collection.solid.solution.service.OrderRepository;

import java.util.ArrayList;
import java.util.List;


//Dependency inversion
public class OrderService {
    private final List<Order> orders = new ArrayList<>();
    private final PaymentService paymentService;
    private final NotificationService notificationService;
    private final OrderRepository orderRepository;

    public OrderService(PaymentService paymentService, NotificationService notificationService,
                        OrderRepository orderRepository) {
        this.paymentService = paymentService;
        this.notificationService = notificationService;
        this.orderRepository = orderRepository;
    }

    public Order createOrder(String product, int quantity, double price) {
        Order order = new Order(product, quantity, price);
        orders.add(order);
        System.out.println("Order created for: " + product);
        return order;
    }

    public void processPayment(String type, double amount) {
        paymentService.processPayment(type, amount);
    }

    public void saveToDatabase() {
        orderRepository.saveToDatabase(orders);

    }
    public void sendEmailConfirmation(String email, Order order) {
        notificationService.sendConfirmation(email,order);
    }
}
