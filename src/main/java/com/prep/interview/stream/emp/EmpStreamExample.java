package com.prep.interview.stream.emp;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class EmpStreamExample {
    public static void main(String[] args) {
        // Sample list of employees
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Alice", "IT", 70000, LocalDate.of(2020, 1, 15)),
                new Employee(2, "Bob", "HR", 50000, LocalDate.of(2019, 3, 10)),
                new Employee(3, "Charlie", "IT", 95000, LocalDate.of(2021, 7, 1)),
                new Employee(4, "David", "Finance", 80000, LocalDate.of(2018, 6, 25)),
                new Employee(5, "Eve", "HR", 60000, LocalDate.of(2022, 5, 20)),
                new Employee(6, "Frank", "Finance", 85000, LocalDate.of(2021, 11, 5))
        );


        // 1. Filter employees who joined after a specific date and belong to a specific department
        LocalDate filterDate = LocalDate.of(2020, 1, 1);
        String filterDept = "IT";

        List<Employee> filteredEmployees = employees.stream()
                .filter(emp -> emp.getJoiningDate().isAfter(filterDate) && emp.getDepartment().equals(filterDept))
                .toList();

        System.out.println("Filtered Employees (joined after " + filterDate + " in dept " + filterDept + "):");
        filteredEmployees.forEach(System.out::println);

        // 2. Group employees by department and calculate average salary
        Map<String, Double> avgSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)));

        System.out.println("\nAverage Salary by Department:");
        avgSalaryByDept.forEach((dept, avgSal) -> System.out.println(dept + " -> " + avgSal));

        // 3. Sort employees by salary in descending order and collect top 3 highest-paid
        List<Employee> top3HighestPaid = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .limit(3)
                .toList();

        System.out.println("\nTop 3 Highest Paid Employees:");
        top3HighestPaid.forEach(System.out::println);

        // 4. Convert list of employees into a map with id as key and Employee as value
        Map<Integer, Employee> employeeMap = employees.stream()
                .collect(Collectors.toMap(Employee::getId, emp -> emp));

        System.out.println("\nEmployee Map (id -> Employee):");
        employeeMap.forEach((id, emp) -> System.out.println(id + " -> " + emp));

        // 5.Group by departments and departments should be ascending order
        Map<String, List<Employee>> collect = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, TreeMap::new, Collectors.toList()));
        collect.forEach((k, v) -> System.out.println(k + "->" + v));

        // 6. Highest paid employee per department
        Map<String, Optional<Employee>> highestPaidByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))
                ));
        System.out.println("Highest paid by department:");
        highestPaidByDept.forEach((dept, emp) ->
                System.out.println(dept + " -> " + emp.orElse(null)));

        // 7. Lowest paid employee per department
        Map<String, Optional<Employee>> lowestPaidByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.minBy(Comparator.comparingDouble(Employee::getSalary))
                ));


        System.out.println("\nLowest paid by department:");
        lowestPaidByDept.forEach((dept, emp) ->
                System.out.println(dept + " -> " + emp.orElse(null)));
    }
}
