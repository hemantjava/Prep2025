## **Command Design Pattern** 
`The Command Design Pattern behavioral designdesign pattern encapsulates a request as an object, allowing parameterization of clients, queuing, and undo operations.
It decouples the sender (Invoker) from the receiver by encapsulating actions in command objects.`

---

### ✅ Structure of Command Pattern:

1. **Command Interface**: Declares an interface for executing an operation.
2. **Concrete Command**: Implements the command interface and defines the binding between a Receiver and an action.
3. **Receiver**: Knows how to perform the operation.
4. **Invoker**: Asks the command to carry out the request.
5. **Client**: Creates a ConcreteCommand object and sets its receiver.

---

### ✅ Example in Java

#### 1️⃣ Command Interface

```java
public interface Command {
    void execute();
}
```

#### 2️⃣ Receiver

```java
public class Light {
    public void turnOn() {
        System.out.println("Light is ON");
    }

    public void turnOff() {
        System.out.println("Light is OFF");
    }
}
```

#### 3️⃣ Concrete Commands

```java
public class TurnOnLightCommand implements Command {
    private Light light;

    public TurnOnLightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}

public class TurnOffLightCommand implements Command {
    private Light light;

    public TurnOffLightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}
```

#### 4️⃣ Invoker

```java
public class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}
```

#### 5️⃣ Client (Usage Example)

```java
public class Client {
    public static void main(String[] args) {
        Light light = new Light();

        Command turnOn = new TurnOnLightCommand(light);
        Command turnOff = new TurnOffLightCommand(light);

        RemoteControl remote = new RemoteControl();

        // Turn ON the light
        remote.setCommand(turnOn);
        remote.pressButton();

        // Turn OFF the light
        remote.setCommand(turnOff);
        remote.pressButton();
    }
}
```

---

### ✅ Output

```
Light is ON
Light is OFF
```

---

### ✅ Benefits of Command Pattern

* Decouples sender and receiver.
* Supports undo/redo functionality by storing commands.
* Allows dynamic composition of commands.
* Useful for implementing transactional behavior.

---
