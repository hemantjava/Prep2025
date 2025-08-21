package com.prep.interview.design_pattern.creational.factory;

public class Main {
    public static void main(String[] args) {
        Shape shape = ShapeFactory.getShape("SQUARE");
        shape.print();
    }
}
