package com.prep.interview.collection.solid.solution.service.impl;

import com.prep.interview.collection.solid.solution.dto.Order;
import com.prep.interview.collection.solid.solution.service.OrderRepository;


import java.util.List;

public class MySQLOrderRepository implements OrderRepository {
    @Override
    public void saveToDatabase(List<Order> orders) {
        System.out.println("💾 Saving orders to MySQL database...");
        orders.forEach(System.out::println);
    }
}
