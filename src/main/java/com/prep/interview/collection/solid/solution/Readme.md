
| Violation                               | Description                                                                                                                                   |
| --------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------- |
| **S — Single Responsibility Principle** | `OrderService` does *too many things* — creates orders, processes payments, saves to DB, and sends emails. Each is a separate responsibility. |
| **O — Open/Closed Principle**           | Every time a new payment method is added, you must modify `processPayment()` — violating “open for extension, closed for modification.”       |
| **L — Liskov Substitution Principle**   | Not applicable here, but will matter once we introduce interfaces.                                                                            |
| **I — Interface Segregation Principle** | Not violated yet, but we’ll ensure future-proof design by using smaller interfaces.                                                           |
| **D — Dependency Inversion Principle**  | `OrderService` depends directly on concrete classes like MySQL and email logic — it should depend on abstractions instead.                    |


| Principle                     | How It’s Applied                                                                                                                |
| ----------------------------- | ------------------------------------------------------------------------------------------------------------------------------- |
| **S (Single Responsibility)** | Each class has *one* job — OrderService orchestrates, repository saves, notification sends, etc.                                |
| **O (Open/Closed)**           | To add a new payment type (like UPI), just create a new class implementing `PaymentProcessor`. No need to modify existing code. |
| **L (Liskov Substitution)**   | Any `PaymentProcessor` subclass can replace another without breaking functionality.                                             |
| **I (Interface Segregation)** | Interfaces are focused — `PaymentProcessor`, `OrderRepository`, `NotificationService` — each handles one concern.               |
| **D (Dependency Inversion)**  | `OrderService` depends on abstractions (interfaces), not concrete classes — enabling loose coupling and easier testing.         |
