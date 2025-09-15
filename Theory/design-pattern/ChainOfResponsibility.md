### ✅ Chain of Responsibility Pattern — behavioral design pattern:

The **Chain of Responsibility (CoR) Pattern** lets multiple objects handle a request **without coupling the sender to the receiver**. Each handler decides either to handle the request or pass it to the next handler in the chain.

---

### ✅ Example: Loan Approval Process

#### 1️⃣ Define the Handler Interface:

```java
public abstract class LoanHandler {
    protected LoanHandler nextHandler;

    public void setNextHandler(LoanHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(double amount);
}
```

---

#### 2️⃣ Concrete Handlers:

```java
public class Manager extends LoanHandler {
    @Override
    public void handleRequest(double amount) {
        if (amount <= 5000) {
            System.out.println("Manager approved loan of $" + amount);
        } else if (nextHandler != null) {
            nextHandler.handleRequest(amount);
        }
    }
}

public class Director extends LoanHandler {
    @Override
    public void handleRequest(double amount) {
        if (amount <= 20000) {
            System.out.println("Director approved loan of $" + amount);
        } else if (nextHandler != null) {
            nextHandler.handleRequest(amount);
        }
    }
}

public class CEO extends LoanHandler {
    @Override
    public void handleRequest(double amount) {
        if (amount <= 100000) {
            System.out.println("CEO approved loan of $" + amount);
        } else { //
            System.out.println("Loan amount too large to approve.");
        }
    }
}
```

---

#### 3️⃣ Client Code to Configure the Chain and Test:

```java
public class LoanApprovalDemo {
    public static void main(String[] args) {
        LoanHandler manager = new Manager();
        LoanHandler director = new Director();
        LoanHandler ceo = new CEO();

        // Set up the chain: Manager → Director → CEO
        manager.setNextHandler(director);
        director.setNextHandler(ceo);

        // Test different loan amounts
        System.out.println("-- Requesting $3,000 --");
        manager.handleRequest(3000);

        System.out.println("\n-- Requesting $15,000 --");
        manager.handleRequest(15000);

        System.out.println("\n-- Requesting $75,000 --");
        manager.handleRequest(75000);

        System.out.println("\n-- Requesting $150,000 --");
        manager.handleRequest(150000);
    }
}
```

---

### ✅ Sample Output:

```
-- Requesting $3,000 --
Manager approved loan of $3000

-- Requesting $15,000 --
Director approved loan of $15000

-- Requesting $75,000 --
CEO approved loan of $75000

-- Requesting $150,000 --
Loan amount too large to approve.
```

---

### ✅ Summary:

* Each handler (Manager, Director, CEO) checks if it can handle the loan amount.
* If not, it passes the request to the next handler.
* No tight coupling between client and handlers.
* Flexible and extensible design.

### ✅ Chain of Responsibility Pattern — Logger Example

In a **Logger system**, different log levels (INFO, DEBUG, ERROR) can be handled by different loggers in a chain.

---

#### 1️⃣ Handler Abstract Class:

```java
public abstract class Logger {
    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;

    protected int level;
    protected Logger nextLogger;

    public void setNextLogger(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void logMessage(int level, String message) {
        if (this.level <= level) {
            write(message);
        }
        if (nextLogger != null) {
            nextLogger.logMessage(level, message);
        }
    }

    protected abstract void write(String message);
}
```

---

#### 2️⃣ Concrete Logger Implementations:

```java
public class InfoLogger extends Logger {
    public InfoLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("INFO: " + message);
    }
}

public class DebugLogger extends Logger {
    public DebugLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("DEBUG: " + message);
    }
}

public class ErrorLogger extends Logger {
    public ErrorLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("ERROR: " + message);
    }
}
```

---

#### 3️⃣ Client Code to Set Up the Chain and Log Messages:

```java
public class LoggerChainDemo {
    private static Logger getChainOfLoggers() {
        Logger errorLogger = new ErrorLogger(Logger.ERROR);
        Logger debugLogger = new DebugLogger(Logger.DEBUG);
        Logger infoLogger = new InfoLogger(Logger.INFO);

        // Setting up the chain: INFO → DEBUG → ERROR
        infoLogger.setNextLogger(debugLogger);
        debugLogger.setNextLogger(errorLogger);

        return infoLogger;
    }

    public static void main(String[] args) {
        Logger loggerChain = getChainOfLoggers();

        System.out.println("-- Logging INFO level message --");
        loggerChain.logMessage(Logger.INFO, "This is an information.");

        System.out.println("\n-- Logging DEBUG level message --");
        loggerChain.logMessage(Logger.DEBUG, "This is a debug level message.");

        System.out.println("\n-- Logging ERROR level message --");
        loggerChain.logMessage(Logger.ERROR, "This is an error message.");
    }
}
```

---

### ✅ Sample Output:

```
-- Logging INFO level message --
INFO: This is an information.
DEBUG: This is an information.
ERROR: This is an information.

-- Logging DEBUG level message --
DEBUG: This is a debug level message.
ERROR: This is a debug level message.

-- Logging ERROR level message --
ERROR: This is an error message.
```

---

### ✅ Summary:

* Every logger handles the message if the severity matches or exceeds its level.
* Messages flow through the chain, and multiple loggers can act on the same message.
* Decouples the client from specific logger implementations.
* Easy to extend (add new log levels or loggers).
