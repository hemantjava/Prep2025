
📌 **Definition Reminder:**
The **Interface Segregation Principle (ISP)** says:
*“Clients should not be forced to depend on methods they do not use.”*
In other words, **prefer smaller, role-specific interfaces over large, fat ones.**

---

# 🏥 1. Healthcare Domain – **Doctor Roles**

❌ **Violation (fat interface):**

```java
interface DoctorDuties {
    void treatPatient();
    void performSurgery();
    void prescribeMedicine();
}

class GeneralPhysician implements DoctorDuties {
    public void treatPatient() { System.out.println("GP treats patient."); }
    public void performSurgery() { throw new UnsupportedOperationException(); } // ❌
    public void prescribeMedicine() { System.out.println("GP prescribes medicine."); }
}
```

✅ **Refactored with ISP:**

```java
interface PatientCare {
    void treatPatient();
}

interface Surgery {
    void performSurgery();
}

interface Prescription {
    void prescribeMedicine();
}

class GeneralPhysician implements PatientCare, Prescription {
    public void treatPatient() { System.out.println("GP treats patient."); }
    public void prescribeMedicine() { System.out.println("GP prescribes medicine."); }
}

class Surgeon implements PatientCare, Surgery {
    public void treatPatient() { System.out.println("Surgeon diagnoses patient."); }
    public void performSurgery() { System.out.println("Surgeon performs surgery."); }
}
```

✔️ Each doctor now implements only what they need.

---

# 🏦 2. Banking Domain – **Accounts**

❌ **Violation:**

```java
interface BankAccount {
    void deposit(double amount);
    void withdraw(double amount);
    void calculateInterest();
}

class FixedDepositAccount implements BankAccount {
    public void deposit(double amount) { }
    public void withdraw(double amount) { throw new UnsupportedOperationException(); } // ❌
    public void calculateInterest() { }
}
```

✅ **Refactored with ISP:**

```java
interface Depositable {
    void deposit(double amount);
}

interface Withdrawable {
    void withdraw(double amount);
}

interface InterestBearing {
    void calculateInterest();
}

class FixedDepositAccount implements Depositable, InterestBearing {
    public void deposit(double amount) { }
    public void calculateInterest() { }
}

class SavingsAccount implements Depositable, Withdrawable, InterestBearing {
    public void deposit(double amount) { }
    public void withdraw(double amount) { }
    public void calculateInterest() { }
}
```

✔️ `FixedDepositAccount` doesn’t need a useless withdraw method anymore.

---

# 🛡 3. Insurance Domain – **Policies**

❌ **Violation:**

```java
interface InsurancePolicy {
    void calculatePremium();
    void provideMedicalCoverage();
    void provideVehicleCoverage();
}

class HealthPolicy implements InsurancePolicy {
    public void calculatePremium() { }
    public void provideMedicalCoverage() { }
    public void provideVehicleCoverage() { throw new UnsupportedOperationException(); } // ❌
}
```

✅ **Refactored with ISP:**

```java
interface Premium {
    void calculatePremium();
}

interface MedicalCoverage {
    void provideMedicalCoverage();
}

interface VehicleCoverage {
    void provideVehicleCoverage();
}

class HealthPolicy implements Premium, MedicalCoverage {
    public void calculatePremium() { }
    public void provideMedicalCoverage() { }
}

class CarInsurancePolicy implements Premium, VehicleCoverage {
    public void calculatePremium() { }
    public void provideVehicleCoverage() { }
}
```

✔️ Each policy implements only relevant coverages.

---

# 🛒 4. E-commerce Domain – **Product Operations**

❌ **Violation:**

```java
interface Product {
    void ship();
    void download();
}

class PhysicalProduct implements Product {
    public void ship() { System.out.println("Shipped item."); }
    public void download() { throw new UnsupportedOperationException(); } // ❌
}
```

✅ **Refactored with ISP:**

```java
interface Shippable {
    void ship();
}

interface Downloadable {
    void download();
}

class PhysicalProduct implements Shippable {
    public void ship() { System.out.println("Shipped item."); }
}

class DigitalProduct implements Downloadable {
    public void download() { System.out.println("Downloaded item."); }
}
```

✔️ No more unused methods for physical products.

---

# ✅ Summary of ISP Examples

| Domain     | Before (Violation)                  | After (ISP Applied)                                         |
| ---------- | ----------------------------------- | ----------------------------------------------------------- |
| Healthcare | One big `DoctorDuties` interface    | Split into `PatientCare`, `Surgery`, `Prescription`         |
| Banking    | One big `BankAccount` interface     | Split into `Depositable`, `Withdrawable`, `InterestBearing` |
| Insurance  | One big `InsurancePolicy` interface | Split into `Premium`, `MedicalCoverage`, `VehicleCoverage`  |
| E-commerce | One big `Product` interface         | Split into `Shippable`, `Downloadable`                      |

---
