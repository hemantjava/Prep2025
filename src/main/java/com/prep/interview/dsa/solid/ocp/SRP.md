 **“Bad SRP vs Refactored SRP”** examples for each domain. This is very interview-friendly.

---

# 🏥 1. Healthcare Domain – **Patient Management**

❌ **Bad SRP (one class does too much):**

```java
class PatientService {
    private String name;
    private int age;

    public void savePatient() {
        System.out.println("Saving patient: " + name);
    }

    public void calculateBill() {
        System.out.println("Calculating bill for patient: " + name);
    }
}
```

⚠️ **Issue:** PatientService does **data, persistence, and billing** → violates SRP.

✅ **Refactored SRP:**

```java
class Patient {
    private String name;
    private int age;
    // getters
}

class PatientRepository {
    public void save(Patient patient) { System.out.println("Saving patient: " + patient); }
}

class BillingService {
    public void calculateBill(Patient patient) { System.out.println("Calculating bill for: " + patient); }
}
```

✔️ Each class has a **single responsibility**.

---

# 🏦 2. Banking Domain – **Transaction**

❌ **Bad SRP:**

```java
class BankTransaction {
    private double amount;

    public void processTransaction() { System.out.println("Processing transaction: " + amount); }
    public void logTransaction() { System.out.println("Logging transaction: " + amount); }
}
```

⚠️ **Issue:** Handles **processing + logging** → violates SRP.

✅ **Refactored SRP:**

```java
class Transaction {
    private double amount;
    // getters
}

class TransactionProcessor {
    public void process(Transaction tx) { System.out.println("Processing transaction: " + tx); }
}

class TransactionLogger {
    public void log(Transaction tx) { System.out.println("Logging transaction: " + tx); }
}
```

✔️ Each class has **one reason to change**.

---

# 🛡 3. Insurance Domain – **Policy**

❌ **Bad SRP:**

```java
class InsurancePolicy {
    private double premium;

    public void calculatePremium() { System.out.println("Calculating premium: " + premium); }
    public void savePolicy() { System.out.println("Saving policy"); }
}
```

⚠️ **Issue:** Mixing **business logic + persistence** → violates SRP.

✅ **Refactored SRP:**

```java
class Policy {
    private double premium;
    // getters
}

class PremiumCalculator {
    public double calculate(Policy policy) { return policy.getPremium() * 1.1; }
}

class PolicyRepository {
    public void save(Policy policy) { System.out.println("Saving policy"); }
}
```

✔️ Clear separation of **data, calculation, and persistence**.

---

# 🛒 4. E-commerce Domain – **Order**

❌ **Bad SRP:**

```java
class OrderService {
    private String orderId;
    private double amount;

    public void saveOrder() { System.out.println("Saving order: " + orderId); }
    public void sendNotification() { System.out.println("Sending confirmation for: " + orderId); }
}
```

⚠️ **Issue:** Handles **persistence + notification** → violates SRP.

✅ **Refactored SRP:**

```java
class Order {
    private String orderId;
    private double amount;
    // getters
}

class OrderRepository {
    public void save(Order order) { System.out.println("Saving order: " + order.getOrderId()); }
}

class OrderNotifier {
    public void notify(Order order) { System.out.println("Order confirmed: " + order.getOrderId()); }
}
```

✔️ Each class handles **one responsibility** only.

---

# ✅ Summary Table

| Domain     | Bad SRP Issue                | Refactored SRP Classes                               |
| ---------- | ---------------------------- | ---------------------------------------------------- |
| Healthcare | Data + persistence + billing | Patient, PatientRepository, BillingService           |
| Banking    | Processing + logging         | Transaction, TransactionProcessor, TransactionLogger |
| Insurance  | Business logic + persistence | Policy, PremiumCalculator, PolicyRepository          |
| E-commerce | Persistence + notification   | Order, OrderRepository, OrderNotifier                |

---
