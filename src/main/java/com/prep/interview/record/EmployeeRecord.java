package com.prep.interview.record;

import lombok.Builder;

@Builder
public record EmployeeRecord(int id, int age, String firstName, String lastName) {

    public static final int VALUE = 10;
    public String upperCase(){
        return firstName.toUpperCase();
    }

}
