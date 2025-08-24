package com.prep.interview.design_pattern.creational.abstract_factory;
// Step 1: Product Interfaces
interface Button {
    void paint();
}

interface Checkbox {
    void paint();
}
// Step 2: Concrete Products - Windows
class WindowsButton implements Button {
    public void paint() {
        System.out.println("Render a Windows Button");
    }
}

class WindowsCheckbox implements Checkbox {
    public void paint() {
        System.out.println("Render a Windows Checkbox");
    }
}
// Step 3: Concrete Products - Mac
class MacButton implements Button {
    public void paint() {
        System.out.println("Render a Mac Button");
    }
}

class MacCheckbox implements Checkbox {
    public void paint() {
        System.out.println("Render a Mac Checkbox");
    }
}
// Step 4: Abstract Factory
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// Step 5: Concrete Factories
class WindowsFactory implements GUIFactory {
    public Button createButton() {
        return new WindowsButton();
    }
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}

class MacFactory implements GUIFactory {
    public Button createButton() {
        return new MacButton();
    }
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}
public class  Main{
    public static void main() {
// Example: Choose factory based on OS
        GUIFactory factory = new WindowsFactory(); // or new MacFactory();

        Button button = factory.createButton();
        Checkbox checkbox = factory.createCheckbox();

        button.paint();
        checkbox.paint();
    }

}
