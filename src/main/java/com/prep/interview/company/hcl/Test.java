package com.prep.interview.company.hcl;

import com.prep.interview.shorting.comparable.Employee;

import java.util.Comparator;
import java.util.List;

public class Test {
    static void main() {
        List<String> ls = List.of("Apple", "Banana", "Mango"); // 6-Banana
        //find max length string and reverse it
        ls.stream().max(Comparator.comparing(String::length))
                .map(s -> new StringBuilder(s).reverse().toString())
                .ifPresent(System.out::println);
        List<Emp> employees =  List.of(new Emp("heman",10),new Emp("sahu",12),
                new Emp("kumar",11));
        //second-highest salary
        employees.stream().sorted(Comparator.comparing(Emp::getSalary).reversed()).skip(1).limit(1)
                .map(Emp::getName).findFirst().ifPresent(System.out::println);
    }



//employees.stream().sort(Comparator.comparing(Emploree::getSalary),C).skip(1).limit(1).map(Employee::getName).orElse("");*/
}
