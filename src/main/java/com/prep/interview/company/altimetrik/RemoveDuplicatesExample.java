package com.prep.interview.company.altimetrik;

import java.util.*;


// Remove duplicate objects from List
/*
* 1. override hashCode and record methods
* 2. Using record:
✅ Less Boilerplate — No need to manually override equals(), hashCode(), or toString().
* */
public class RemoveDuplicatesExample {
    public static void main(String[] args) {
        List<Order> orders = Arrays.asList(
                new Order("Electronics", "Smartphone"),
                new Order("Electronics", "Smartphone"),
                new Order("Groceries", "Apples"),
                new Order("Electronics", "Laptop"),
                new Order("Groceries", "Apples")
        );

        // Remove duplicates using Set
        List<Order> distinctOrders = orders.stream()
                .distinct()
                .toList();

        distinctOrders.forEach(System.out::println);
        System.out.println("===============================================");
        List<RecordOrder> recordOrders = List.of(
                new RecordOrder("Electronics", "Smartphone"),
                new RecordOrder("Electronics", "Smartphone"),
                new RecordOrder("Groceries", "Apples"),
                new RecordOrder("Electronics", "Laptop"),
                new RecordOrder("Groceries", "Apples")
        );

        recordOrders.stream()
                .distinct()
                .toList().forEach(System.out::println);
    }
}
