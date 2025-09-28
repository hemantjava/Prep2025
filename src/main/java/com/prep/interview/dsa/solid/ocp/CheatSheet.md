 **visual “Bad vs Good” table/diagram** for **all 5 SOLID principles** across the **Healthcare, Banking, Insurance, and E-commerce domains**. This will be **interview-friendly** and very easy to memorize.

---

# **SOLID Principles – Bad vs Good Examples (Domain-wise)**

| Principle | Domain     | Bad Example (Violates Principle)                                                 | Good Example (Follows Principle)                                                   |
| --------- | ---------- | -------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------- |
| **SRP**   | Healthcare | `PatientService` handles **data + persistence + billing**                        | `Patient` (data) + `PatientRepository` (persistence) + `BillingService` (billing)  |
|           | Banking    | `BankTransaction` handles **processing + logging**                               | `Transaction` (data) + `TransactionProcessor` + `TransactionLogger`                |
|           | Insurance  | `InsurancePolicy` handles **calculation + persistence**                          | `Policy` + `PremiumCalculator` + `PolicyRepository`                                |
|           | E-commerce | `OrderService` handles **saving + notification**                                 | `Order` + `OrderRepository` + `OrderNotifier`                                      |
| **OCP**   | Healthcare | `Billing.calculateBill()` modifies for each patient type                         | `BillingStrategy` interface + new classes for each patient type                    |
|           | Banking    | `Account.calculateInterest()` modifies for each account type                     | `InterestCalculator` + `AccountService`                                            |
|           | Insurance  | `PremiumCalculator.calculate()` modifies for each policy type                    | `PolicyPremium` + `InsuranceService`                                               |
|           | E-commerce | `Discount.applyDiscount()` modifies for each discount type                       | `DiscountStrategy` + `CheckoutService`                                             |
| **LSP**   | Healthcare | `Nurse extends Doctor` throws exception for prescribe                            | `MedicalStaff` interface → `Doctor` / `Nurse` implement `performDuties()`          |
|           | Banking    | `FixedDepositAccount extends BankAccount` throws exception on withdraw           | `WithdrawableAccount` → `SavingsAccount`, FD separate                              |
|           | Insurance  | `TravelInsurancePolicy` throws exception on calculatePremium                     | `PremiumCalculable` → `LifeInsurancePolicy` / `TravelInsurancePolicy`              |
|           | E-commerce | `DigitalOrder extends Order` throws exception on deliver                         | `Order` interface → `PhysicalOrder` / `DigitalOrder` implement `process()`         |
| **ISP**   | Healthcare | `DoctorDuties` interface forces GP to implement `performSurgery()`               | Split into `PatientCare`, `Surgery`, `Prescription`                                |
|           | Banking    | `BankAccount` interface forces FD to implement `withdraw()`                      | Split into `Depositable`, `Withdrawable`, `InterestBearing`                        |
|           | Insurance  | `InsurancePolicy` interface forces HealthPolicy to implement `vehicleCoverage()` | Split into `Premium`, `MedicalCoverage`, `VehicleCoverage`                         |
|           | E-commerce | `Product` interface forces PhysicalProduct to implement `download()`             | Split into `Shippable`, `Downloadable`                                             |
| **DIP**   | Healthcare | `PatientNotifier` depends on `EmailService` directly                             | Depends on abstraction `NotificationService` (`EmailService` / `SmsService`)       |
|           | Banking    | `PaymentService` depends on `CreditCardProcessor` directly                       | Depends on `PaymentProcessor` abstraction (`CreditCardProcessor` / `UPIProcessor`) |
|           | Insurance  | `ReportService` depends on `PdfReport` directly                                  | Depends on `ReportGenerator` abstraction (`PdfReport` / `ExcelReport`)             |
|           | E-commerce | `OrderService` depends on `PayPalGateway` directly                               | Depends on `PaymentGateway` abstraction (`PayPalGateway` / `StripeGateway`)        |

---

### ✅ How to Use This Table

1. **Interview flow:**

    * Mention principle + one-line definition.
    * Give **bad example** briefly.
    * Show **refactored “good example”**.
2. **Tip:** Pick one domain you’re most familiar with and explain quickly.

---
