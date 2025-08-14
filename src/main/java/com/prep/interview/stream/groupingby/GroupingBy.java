package com.prep.interview.stream.groupingby;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
class Emp {
    String name;
    String dept;
}

//Group employees by department & count
public class GroupingBy {
    public static void main(String[] args) {
        List<Emp> employees = Arrays.asList(
                new Emp("A", "IT"),
                new Emp("B", "HR"),
                new Emp("C", "IT"),
                new Emp("D", "Finance")
        );
        ConcurrentMap<String, Long> collect = employees.parallelStream()
                .collect(Collectors.groupingByConcurrent(Emp::getDept, Collectors.counting()));
        System.out.println(collect);
    }
}
