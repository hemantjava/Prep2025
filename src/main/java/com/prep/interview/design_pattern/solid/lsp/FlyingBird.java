package com.prep.interview.design_pattern.solid.lsp;

public class FlyingBird extends Bird {
    @Override
    public void move() {
        fly();
    }

    public void fly() {
        System.out.println("Bird is flying");
    }
}



