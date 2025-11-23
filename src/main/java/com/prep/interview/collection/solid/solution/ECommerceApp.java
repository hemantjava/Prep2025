package com.prep.interview.collection.solid.solution;


import com.prep.interview.collection.solid.solution.dto.Order;
import com.prep.interview.collection.solid.solution.service.NotificationService;
import com.prep.interview.collection.solid.solution.service.OrderRepository;
import com.prep.interview.collection.solid.solution.service.impl.EmailNotificationService;
import com.prep.interview.collection.solid.solution.service.impl.MySQLOrderRepository;

public class ECommerceApp {
    public static void main(String[] args) {

        PaymentService paymentService = new PaymentService();
        NotificationService notificationService = new EmailNotificationService();
        OrderRepository orderRepository = new MySQLOrderRepository();

        OrderService service = new OrderService(paymentService, notificationService, orderRepository);

        Order laptop = service.createOrder("Laptop", 2, 1500.0);
        service.processPayment("paypal", laptop.getTotal());
        service.saveToDatabase();
        service.sendEmailConfirmation("customer@shop.com",laptop);
    }
}