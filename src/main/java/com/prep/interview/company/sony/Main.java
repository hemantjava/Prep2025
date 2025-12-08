package com.prep.interview.company.sony;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
I have lis of users, can you categorize with Major and Minor users based on their Age 18 years
with below ?
Conditions: MajorUsers is >=18 , MinorUsers is <18
 */
public class Main {
    public static void main(String[] args) {
        List<User> users = Arrays.asList(
                new User("Alice", 17),
                new User("Bob", 20),
                new User("Charlie", 16),
                new User("David", 25),
                new User("Eve", 18),
                new User("Frank", 4)
        );

        users.stream().collect(Collectors.groupingByConcurrent(u -> u.age() >= 18 ? "MajorUsers" : "MinorUsers"))
                .forEach((category, userList) -> {
                    System.out.println(category + ":");
                    userList.forEach(user -> System.out.println(" - " + user.name() + ", Age: " + user.age()));
                });

        users.stream().collect(Collectors.groupingByConcurrent(u -> {
                    if (u.age() >= 18) {
                        return "MajorUsers";
                    } else if (u.age() <= 5) {
                        return "Kids";
                    } else {
                        return "MinorUsers";
                    }
                }))
                .forEach((category, userList) -> {
                    System.out.println(category + ":");
                    userList.forEach(user -> System.out.println(" - " + user.name() + ", Age: " + user.age()));
                });


    }
}
