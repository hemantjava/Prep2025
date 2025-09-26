package com.prep.interview.design_pattern.behavioural.observer.example1;

/**
 * @param name Observer name / concrete class name
 */
public record ConcreteObserver(String name) implements Observer {

    @Override // Added extra name for identification
    public void update(String message) {
        System.out.println(name + " received message: " + message);
    }
}
