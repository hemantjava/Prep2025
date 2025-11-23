package com.prep.interview.collection.solid.solution.service;



import com.prep.interview.collection.solid.solution.dto.Order;

import java.util.List;

public interface OrderRepository {
    public void saveToDatabase(List<Order> orders);
}
