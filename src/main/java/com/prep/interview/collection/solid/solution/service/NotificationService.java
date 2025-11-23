package com.prep.interview.collection.solid.solution.service;


import com.prep.interview.collection.solid.solution.dto.Order;

public interface NotificationService {
    void sendConfirmation(String email, Order order);
}
