package com.prep.interview;

import com.prep.interview.shorting.comparable.Employee;

import java.util.Objects;

public class HashCodeAndEquals {
    private int id;
    private String name;

    public HashCodeAndEquals(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof HashCodeAndEquals employee)) return false;
        return this.id == employee.getId() && Objects.equals(this.name,employee.getName());
    }
}
