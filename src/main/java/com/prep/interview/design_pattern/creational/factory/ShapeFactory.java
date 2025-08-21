package com.prep.interview.design_pattern.creational.factory;

public class ShapeFactory {

    public static Shape getShape(String shape) {
        switch (shape.toUpperCase()) {
            case "CIRCLE":
                return new Circle();
            case "RECTANGLE":
                return new Rectangle();
            case "SQUARE":
                return new Square();
            default:
                return null;
        }
    }
}
