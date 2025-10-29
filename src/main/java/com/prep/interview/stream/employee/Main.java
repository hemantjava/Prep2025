package com.prep.interview.stream.employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee(1, "John", "IT", 60000),
                new Employee(2, "Alice", "HR", 55000),
                new Employee(3, "Bob", "IT", 75000),
                new Employee(4, "Carol", "HR", 70000),
                new Employee(5, "David", "Finance", 65000),
                new Employee(6, "Eve", "IT", 80000),
                new Employee(7, "Frank", "Finance", 64000),
                new Employee(8, "Aia", "Operations", 44000));

        Map.Entry<Character, Long> collect = employees.stream()
                .map(employee -> Character.toUpperCase(employee.name().charAt(0)))
                .collect(Collectors.collectingAndThen(Collectors.groupingByConcurrent(Function.identity(), Collectors.counting())
                        , map ->
                                map.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getValue).orElse(null)));
        System.out.println(collect);
    }
}
