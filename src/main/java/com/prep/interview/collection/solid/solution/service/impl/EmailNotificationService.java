package com.prep.interview.collection.solid.solution.service.impl;

import com.prep.interview.collection.solid.solution.dto.Order;
import com.prep.interview.collection.solid.solution.service.NotificationService;

public class EmailNotificationService implements NotificationService {
    @Override
    public void sendConfirmation(String email, Order order) {
        System.out.println("📧 Sending order confirmation to " + email + " for " + order.toString());
    }
}
