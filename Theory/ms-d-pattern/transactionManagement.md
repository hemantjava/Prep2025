## Transaction management in micro-service

In microservices, transaction management is tricky because a single business
process may span multiple services, with its own database — and we can’t use
a single ACID transaction across them, like in a monolith.

* Instead, we use distributed transaction patterns to keep data consistent.

## Common Approaches

### A. Avoid Distributed Transactions (Best Practice)

* Keep each service transaction local.
* Design business flows so each service completes its own transaction independently.
* Use event-driven communication to coordinate with other services

### B. Saga Pattern (Most popular)
* **1. Choreography Saga (event-driven pattern)**
* **2. Orchestration Saga (central coordinator)**
* [SAGA.md](SAGA.md)

### C. Two-Phase Commit (2PC)

* Rare in microservices because:Slower
  Great question 👍 Let’s break it down.

## 🔹 Transaction Management in Spring Boot

Spring Boot provides **declarative transaction management** using `@Transactional`. It integrates with Spring’s `PlatformTransactionManager` and works on top of JDBC, JPA, Hibernate, etc.

A **transaction** is a unit of work that either **completes fully (commit)** or **fails completely (rollback)** to maintain **data consistency**.

### Enabling Transaction Management

```java
@SpringBootApplication
@EnableTransactionManagement   // enables annotation-driven transaction management
public class MyApp { }
```

### Using `@Transactional`

```java
@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    public void processPayment(Payment payment) {
        paymentRepository.save(payment);
        // if an exception occurs here, transaction rolls back
    }
}
```

---

## 🔹 Propagation Levels in Spring

**Propagation** defines how transactions behave when one transactional method calls another.

Spring provides several levels:

1. **REQUIRED (default)**

    * Joins the existing transaction if one exists; otherwise, creates a new one.
    * ✅ Most commonly used.

   ```java
   @Transactional(propagation = Propagation.REQUIRED)
   ```

2. **REQUIRES\_NEW**

    * Suspends the current transaction and starts a **new one**.
    * The outer transaction will not affect this one.

   ```java
   @Transactional(propagation = Propagation.REQUIRES_NEW)
   ```

3. **NESTED**

    * Executes inside a nested transaction if an existing one exists.
    * Uses **savepoints**: rollback will revert only inner transaction, not the entire parent.
    * Requires a JDBC driver with savepoint support.

   ```java
   @Transactional(propagation = Propagation.NESTED)
   ```

4. **MANDATORY**

    * Must run inside an existing transaction.
    * If no transaction exists, throws an exception.

   ```java
   @Transactional(propagation = Propagation.MANDATORY)
   ```

5. **NEVER**

    * Must execute **without** a transaction.
    * If a transaction exists, throws an exception.

   ```java
   @Transactional(propagation = Propagation.NEVER)
   ```

6. **SUPPORTS**

    * If a transaction exists, join it; otherwise, run non-transactionally.
    * Good for read-only operations.

   ```java
   @Transactional(propagation = Propagation.SUPPORTS)
   ```

7. **NOT\_SUPPORTED**

    * Suspends the current transaction and runs **without** one.
    * Used when transaction overhead is unnecessary.

   ```java
   @Transactional(propagation = Propagation.NOT_SUPPORTED)
   ```

---

## 🔹 Example with Multiple Propagations

```java
@Service
public class OrderService {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private InventoryService inventoryService;

    @Transactional(propagation = Propagation.REQUIRED)
    public void placeOrder(Order order) {
        inventoryService.reduceStock(order); // joins transaction
        paymentService.makePayment(order);   // may use REQUIRES_NEW
    }
}

@Service
public class PaymentService {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void makePayment(Order order) {
        // payment in separate transaction
    }
}
```

Here:

* `placeOrder()` starts a transaction.
* `reduceStock()` joins it (`REQUIRED`).
* `makePayment()` runs in a **new independent transaction**.

So if payment fails, `reduceStock()` may still commit depending on error handling.

---
Perfect 👍 Let’s extend into **rollback rules**.

---

# 🔹 Rollback Rules in Spring

By default:

* A transaction will **rollback on `RuntimeException` or `Error`**.
* It will **NOT rollback on checked exceptions** (`Exception`, `IOException`, etc.).

You can customize rollback rules using attributes of `@Transactional`.

---

## 1. `rollbackFor`

Forces rollback on specific exceptions (checked or unchecked).

```java
@Transactional(rollbackFor = Exception.class)
public void processOrder(Order order) throws Exception {
    // even if Exception (checked) occurs → rollback
}
```

---

## 2. `noRollbackFor`

Prevents rollback for certain exceptions.

```java
@Transactional(noRollbackFor = IllegalArgumentException.class)
public void updateStock(Order order) {
    // if IllegalArgumentException occurs → transaction will NOT rollback
}
```

---

## 3. Combined Example

```java
@Transactional(
    rollbackFor = {SQLException.class, IOException.class},
    noRollbackFor = {CustomBusinessException.class}
)
public void handlePayment(Order order) throws Exception {
    // Will rollback for SQL/IO issues
    // Will NOT rollback for business validation failures
}
```

---

# 🔹 Propagation + Rollback Together

```java
@Service
public class OrderService {

    @Autowired
    private PaymentService paymentService;

    @Transactional(propagation = Propagation.REQUIRED)
    public void placeOrder(Order order) {
        try {
            paymentService.makePayment(order); // runs in REQUIRES_NEW
        } catch (Exception e) {
            // even if payment fails, main order transaction may continue
        }
    }
}

@Service
public class PaymentService {
    @Transactional(
        propagation = Propagation.REQUIRES_NEW,
        rollbackFor = Exception.class
    )
    public void makePayment(Order order) throws Exception {
        // rollback for any Exception inside this independent transaction
    }
}
```

Here:

* `OrderService.placeOrder()` uses **REQUIRED** → one main transaction.
* `PaymentService.makePayment()` uses **REQUIRES\_NEW** → its own transaction.
* Even if payment fails and rolls back, the **order transaction may still commit** (depends on handling).

---

✅ **Summary:**

* **Propagation** → Defines how transactions interact across methods.
* **Rollback rules** → Define when a transaction should rollback or commit on exceptions.

---