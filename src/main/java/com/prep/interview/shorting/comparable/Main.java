package com.prep.interview.shorting.comparable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Employee> employeeList = new ArrayList<>(List.of(new Employee(1,"sonu"),
                new Employee(2,"hemant")));
        employeeList.sort(Comparator.comparing(Employee::getName,Comparator.reverseOrder()));
        System.out.println(employeeList);
        System.out.println(Integer.compare(1,1));
        System.out.println(Integer.compare(1,2));
        System.out.println(Integer.compare(2,1));
    }
}
