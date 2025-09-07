package com.prep.interview.company.altimetrik;

import java.util.Objects;

public class Order {
    private final String type;
    private final String description;

    public Order(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(type, order.type) &&
                Objects.equals(description, order.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, description);
    }

    @Override
    public String toString() {
        return "Order{" +
                "type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
