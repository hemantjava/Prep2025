
📌 **Definition Reminder:**
The **Liskov Substitution Principle (LSP)** says:
*“Objects of a superclass should be replaceable with objects of a subclass without breaking the application’s behavior.”*

If subclass violates expectations of the base class, LSP is broken.

---

# 🏥 1. Healthcare Domain – **Medical Staff**

❌ **Violation:**

```java
class Doctor {
    void prescribeMedicine() {
        System.out.println("Prescribing medicine...");
    }
}

class Nurse extends Doctor { // ❌ Wrong substitution
    @Override
    void prescribeMedicine() {
        throw new UnsupportedOperationException("Nurses cannot prescribe medicine");
    }
}
```

⚠️ If code expects every `Doctor` to prescribe, substituting `Nurse` breaks LSP.

✅ **Correct (separate hierarchy):**

```java
interface MedicalStaff {
    void performDuties();
}

class Doctor implements MedicalStaff {
    public void performDuties() {
        System.out.println("Doctor prescribing medicine...");
    }
}

class Nurse implements MedicalStaff {
    public void performDuties() {
        System.out.println("Nurse providing care...");
    }
}
```

✔️ Both `Doctor` and `Nurse` can substitute `MedicalStaff` safely.

---

# 🏦 2. Banking Domain – **Account Types**

❌ **Violation:**

```java
class BankAccount {
    void withdraw(double amount) {
        System.out.println("Withdrawing: " + amount);
    }
}

class FixedDepositAccount extends BankAccount { // ❌
    @Override
    void withdraw(double amount) {
        throw new UnsupportedOperationException("Cannot withdraw from FD");
    }
}
```

⚠️ FD cannot behave like a normal account → violates LSP.

✅ **Correct (proper hierarchy):**

```java
interface WithdrawableAccount {
    void withdraw(double amount);
}

class SavingsAccount implements WithdrawableAccount {
    public void withdraw(double amount) {
        System.out.println("Withdrawing from savings: " + amount);
    }
}

class FixedDepositAccount {
    public void closeAccount() {
        System.out.println("FD closed, payout processed.");
    }
}
```

✔️ Now only `SavingsAccount` is withdrawable, FD is separate.

---

# 🛡 3. Insurance Domain – **Policy Types**

❌ **Violation:**

```java
class InsurancePolicy {
    double calculatePremium() { return 1000.0; }
}

class LifeInsurancePolicy extends InsurancePolicy {
    @Override
    double calculatePremium() {
        return 2000.0;
    }
}

class TravelInsurancePolicy extends InsurancePolicy {
    @Override
    double calculatePremium() {
        throw new UnsupportedOperationException("Not applicable for travel insurance"); // ❌
    }
}
```

⚠️ Code expecting all policies to calculate premium breaks.

✅ **Correct:**

```java
interface PremiumCalculable {
    double calculatePremium();
}

class LifeInsurancePolicy implements PremiumCalculable {
    public double calculatePremium() { return 2000.0; }
}

class TravelInsurancePolicy implements PremiumCalculable {
    public double calculatePremium() { return 1500.0; }
}
```

✔️ Both policies can substitute `PremiumCalculable`.

---

# 🛒 4. E-commerce Domain – **Order Delivery**

❌ **Violation:**

```java
class Order {
    void deliver() {
        System.out.println("Delivering order...");
    }
}

class DigitalOrder extends Order { // ❌ Wrong behavior
    @Override
    void deliver() {
        throw new UnsupportedOperationException("Digital orders don’t need delivery");
    }
}
```

⚠️ Substituting `DigitalOrder` breaks delivery logic.

✅ **Correct:**

```java
interface Order {
    void process();
}

class PhysicalOrder implements Order {
    public void process() {
        System.out.println("Delivering physical order...");
    }
}

class DigitalOrder implements Order {
    public void process() {
        System.out.println("Sending download link for digital order...");
    }
}
```

✔️ Both orders process correctly without breaking behavior.

---

# ✅ LSP Summary

| Domain     | Bad Substitution                | Fix                                                  |
| ---------- | ------------------------------- | ---------------------------------------------------- |
| Healthcare | `Nurse extends Doctor`          | Use `MedicalStaff` interface                         |
| Banking    | `FD extends BankAccount`        | Separate `WithdrawableAccount`                       |
| Insurance  | `TravelPolicy` throws exception | Use `PremiumCalculable`                              |
| E-commerce | `DigitalOrder extends Order`    | Use `Order` interface with different implementations |

---
