
📌 **Definition Reminder:**
**OCP:** *“Software entities (classes, modules, functions) should be open for extension but closed for modification.”*
This means you can add new functionality **without changing existing code**.

---

# 🏥 1. Healthcare Domain – **Billing**

❌ **Violation (modifying existing class for new cases):**

```java
class Billing {
    public double calculateBill(String patientType, double amount) {
        if (patientType.equals("InPatient")) {
            return amount * 1.2; // 20% extra
        } else if (patientType.equals("OutPatient")) {
            return amount * 1.1; // 10% extra
        }
        return amount;
    }
}
```

⚠️ Every time a new patient type is added, we must modify `Billing` → violates OCP.

✅ **Refactored OCP (using abstraction):**

```java
interface BillingStrategy {
    double calculateBill(double amount);
}

class InPatientBilling implements BillingStrategy {
    public double calculateBill(double amount) { return amount * 1.2; }
}

class OutPatientBilling implements BillingStrategy {
    public double calculateBill(double amount) { return amount * 1.1; }
}

class BillingService {
    public double calculateBill(BillingStrategy strategy, double amount) {
        return strategy.calculateBill(amount);
    }
}
```

✔️ Adding a new patient type requires **new class**, not changing existing code.

---

# 🏦 2. Banking Domain – **Interest Calculation**

❌ **Violation:**

```java
class Account {
    public double calculateInterest(String accountType, double balance) {
        if (accountType.equals("Savings")) return balance * 0.05;
        else if (accountType.equals("FixedDeposit")) return balance * 0.07;
        return 0;
    }
}
```

⚠️ Modifying class for new account types → violates OCP.

✅ **Refactored OCP:**

```java
interface InterestCalculator {
    double calculate(double balance);
}

class SavingsCalculator implements InterestCalculator {
    public double calculate(double balance) { return balance * 0.05; }
}

class FixedDepositCalculator implements InterestCalculator {
    public double calculate(double balance) { return balance * 0.07; }
}

class AccountService {
    public double calculateInterest(InterestCalculator calculator, double balance) {
        return calculator.calculate(balance);
    }
}
```

✔️ Adding new account types → just create a new `InterestCalculator` implementation.

---

# 🛡 3. Insurance Domain – **Premium Calculation**

❌ **Violation:**

```java
class PremiumCalculator {
    public double calculate(String policyType, double base) {
        if (policyType.equals("Health")) return base * 1.2;
        else if (policyType.equals("Vehicle")) return base * 1.1;
        return base;
    }
}
```

⚠️ Modifying class for each new policy type → violates OCP.

✅ **Refactored OCP:**

```java
interface PolicyPremium {
    double calculate(double base);
}

class HealthPolicyPremium implements PolicyPremium {
    public double calculate(double base) { return base * 1.2; }
}

class VehiclePolicyPremium implements PolicyPremium {
    public double calculate(double base) { return base * 1.1; }
}

class InsuranceService {
    public double calculatePremium(PolicyPremium policy, double base) {
        return policy.calculate(base);
    }
}
```

✔️ New policies → new class implementing `PolicyPremium`.

---

# 🛒 4. E-commerce Domain – **Discounts**

❌ **Violation:**

```java
class Discount {
    public double applyDiscount(String type, double price) {
        if (type.equals("BlackFriday")) return price * 0.8;
        else if (type.equals("NewYear")) return price * 0.9;
        return price;
    }
}
```

⚠️ Adding new discounts → modifying existing class → violates OCP.

✅ **Refactored OCP:**

```java
interface DiscountStrategy {
    double apply(double price);
}

class BlackFridayDiscount implements DiscountStrategy {
    public double apply(double price) { return price * 0.8; }
}

class NewYearDiscount implements DiscountStrategy {
    public double apply(double price) { return price * 0.9; }
}

class CheckoutService {
    public double applyDiscount(DiscountStrategy strategy, double price) {
        return strategy.apply(price);
    }
}
```

✔️ New discounts → just create a new `DiscountStrategy`.

---

# ✅ Summary Table for OCP

| Domain     | Bad Practice (modifying class)                      | Refactored (OCP applied)                |
| ---------- | --------------------------------------------------- | --------------------------------------- |
| Healthcare | `Billing.calculateBill(String patientType, …)`      | `BillingStrategy` + `BillingService`    |
| Banking    | `Account.calculateInterest(String accountType, …)`  | `InterestCalculator` + `AccountService` |
| Insurance  | `PremiumCalculator.calculate(String policyType, …)` | `PolicyPremium` + `InsuranceService`    |
| E-commerce | `Discount.applyDiscount(String type, …)`            | `DiscountStrategy` + `CheckoutService`  |

---

