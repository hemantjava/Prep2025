## **Decorator Design Pattern** 
`is a **structural design pattern** that allows you to dynamically add behavior or responsibilities to an object without 
modifying its code. It wraps the original object in a new object that adds the new behavior.`

---

### ✅ Purpose:

* Dynamically add functionality to objects.
* Avoid subclass explosion.
* Follow the **Open/Closed Principle** (open for extension, closed for modification).

---

## ✅ Structure of Decorator Pattern:

1. **Component Interface**: Defines the basic behavior.
2. **Concrete Component**: The core implementation.
3. **Decorator Abstract Class**: Implements the same interface and holds a reference to a Component.
4. **Concrete Decorators**: Extend the Decorator and add extra behavior.

---

## ✅ Example in Java

#### 1️⃣ Component Interface

```java
public interface Coffee {
    double getCost();
    String getDescription();
}
```

#### 2️⃣ Concrete Component

```java
public class SimpleCoffee implements Coffee {
    @Override
    public double getCost() {
        return 5.0;
    }

    @Override
    public String getDescription() {
        return "Simple Coffee";
    }
}
```

#### 3️⃣ Decorator Abstract Class

```java
public abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    public double getCost() {
        return decoratedCoffee.getCost();
    }

    public String getDescription() {
        return decoratedCoffee.getDescription();
    }
}
```

#### 4️⃣ Concrete Decorators

▶ **Milk Decorator**

```java
public class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return super.getCost() + 1.5;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", with Milk";
    }
}
```

▶ **Sugar Decorator**

```java
public class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.5;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", with Sugar";
    }
}
```

---

#### 5️⃣ Client (Usage Example)

```java
public class Client {
    public static void main(String[] args) {
        Coffee simpleCoffee = new SimpleCoffee();
        System.out.println(simpleCoffee.getDescription() + " $" + simpleCoffee.getCost());

        Coffee milkCoffee = new MilkDecorator(simpleCoffee);
        System.out.println(milkCoffee.getDescription() + " $" + milkCoffee.getCost());

        Coffee milkSugarCoffee = new SugarDecorator(milkCoffee);
        System.out.println(milkSugarCoffee.getDescription() + " $" + milkSugarCoffee.getCost());
    }
}
```

---

## ✅ Sample Output

```
Simple Coffee $5.0
Simple Coffee, with Milk $6.5
Simple Coffee, with Milk, with Sugar $7.0
```

---

### ✅ Why use Decorator Pattern?

* **Flexible**: Add/remove functionality at runtime.
* Avoids deep inheritance trees.
* Enhances code maintainability and readability.

---
