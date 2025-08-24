package com.prep.interview.design_pattern.creational.factory;

public class ShapeFactory {

    public static Shape getShape(String shape) {
        return switch (shape.toUpperCase()) {
            case "CIRCLE" -> new Circle();
            case "RECTANGLE" -> new Rectangle();
            case "SQUARE" -> new Square();
            default -> throw new RuntimeException("Invalid shape");
        };
    }
}
