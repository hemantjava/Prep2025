
📌 **Definition Reminder:**
The **Dependency Inversion Principle (DIP)** says:
*“High-level modules should not depend on low-level modules. Both should depend on abstractions.”*
This avoids tight coupling and makes systems more flexible.

---

# 🏥 1. Healthcare Domain – **Patient Notification**

❌ **Violation (tight coupling):**

```java
class EmailService {
    public void sendEmail(String message) {
        System.out.println("Sending email: " + message);
    }
}

class PatientNotifier {
    private EmailService emailService = new EmailService(); // ❌ Direct dependency

    public void notify(String msg) {
        emailService.sendEmail(msg);
    }
}
```

✅ **With DIP (depend on abstraction):**

```java
interface NotificationService {
    void notify(String message);
}

class EmailService implements NotificationService {
    public void notify(String message) {
        System.out.println("Sending email: " + message);
    }
}

class SmsService implements NotificationService {
    public void notify(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

class PatientNotifier {
    private NotificationService service;

    public PatientNotifier(NotificationService service) {
        this.service = service;
    }

    public void notify(String msg) {
        service.notify(msg);
    }
}

public class HealthcareDemo {
    public static void main(String[] args) {
        PatientNotifier notifier = new PatientNotifier(new SmsService());
        notifier.notify("Appointment reminder");
    }
}
```

✔️ High-level `PatientNotifier` depends only on `NotificationService`, not on `EmailService` directly.

---

# 🏦 2. Banking Domain – **Payment Processing**

❌ **Violation:**

```java
class CreditCardProcessor {
    public void process(double amount) {
        System.out.println("Processing credit card payment: " + amount);
    }
}

class PaymentService {
    private CreditCardProcessor processor = new CreditCardProcessor(); // ❌ Tight coupling

    public void pay(double amount) {
        processor.process(amount);
    }
}
```

✅ **With DIP:**

```java
interface PaymentProcessor {
    void process(double amount);
}

class CreditCardProcessor implements PaymentProcessor {
    public void process(double amount) {
        System.out.println("Credit card payment: " + amount);
    }
}

class UpiProcessor implements PaymentProcessor {
    public void process(double amount) {
        System.out.println("UPI payment: " + amount);
    }
}

class PaymentService {
    private PaymentProcessor processor;

    public PaymentService(PaymentProcessor processor) {
        this.processor = processor;
    }

    public void pay(double amount) {
        processor.process(amount);
    }
}

public class BankingDemo {
    public static void main(String[] args) {
        PaymentService service = new PaymentService(new UpiProcessor());
        service.pay(500.0);
    }
}
```

✔️ `PaymentService` doesn’t care if it’s UPI, card, or wallet — it depends only on abstraction.

---

# 🛡 3. Insurance Domain – **Policy Reporting**

❌ **Violation:**

```java
class PdfReport {
    public void generate(String policy) {
        System.out.println("Generating PDF for policy: " + policy);
    }
}

class ReportService {
    private PdfReport report = new PdfReport(); // ❌ Tight coupling

    public void createReport(String policy) {
        report.generate(policy);
    }
}
```

✅ **With DIP:**

```java
interface ReportGenerator {
    void generate(String policy);
}

class PdfReport implements ReportGenerator {
    public void generate(String policy) {
        System.out.println("PDF report for policy: " + policy);
    }
}

class ExcelReport implements ReportGenerator {
    public void generate(String policy) {
        System.out.println("Excel report for policy: " + policy);
    }
}

class ReportService {
    private ReportGenerator generator;

    public ReportService(ReportGenerator generator) {
        this.generator = generator;
    }

    public void createReport(String policy) {
        generator.generate(policy);
    }
}

public class InsuranceDemo {
    public static void main(String[] args) {
        ReportService service = new ReportService(new ExcelReport());
        service.createReport("HealthPolicy123");
    }
}
```

✔️ Report service works with any report format.

---

# 🛒 4. E-commerce Domain – **Order Payments**

❌ **Violation:**

```java
class PayPalGateway {
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment: " + amount);
    }
}

class OrderService {
    private PayPalGateway gateway = new PayPalGateway(); // ❌ Tight coupling

    public void checkout(double amount) {
        gateway.processPayment(amount);
    }
}
```

✅ **With DIP:**

```java
interface PaymentGateway {
    void processPayment(double amount);
}

class PayPalGateway implements PaymentGateway {
    public void processPayment(double amount) {
        System.out.println("PayPal payment: " + amount);
    }
}

class StripeGateway implements PaymentGateway {
    public void processPayment(double amount) {
        System.out.println("Stripe payment: " + amount);
    }
}

class OrderService {
    private PaymentGateway gateway;

    public OrderService(PaymentGateway gateway) {
        this.gateway = gateway;
    }

    public void checkout(double amount) {
        gateway.processPayment(amount);
    }
}

public class EcommerceDemo {
    public static void main(String[] args) {
        OrderService order = new OrderService(new StripeGateway());
        order.checkout(1200.0);
    }
}
```

✔️ Order service depends only on abstraction `PaymentGateway`.

---

# ✅ Summary of DIP Examples

| Domain     | High-level Module | Abstraction           | Low-level Modules                     |
| ---------- | ----------------- | --------------------- | ------------------------------------- |
| Healthcare | `PatientNotifier` | `NotificationService` | `EmailService`, `SmsService`          |
| Banking    | `PaymentService`  | `PaymentProcessor`    | `CreditCardProcessor`, `UpiProcessor` |
| Insurance  | `ReportService`   | `ReportGenerator`     | `PdfReport`, `ExcelReport`            |
| E-commerce | `OrderService`    | `PaymentGateway`      | `PayPalGateway`, `StripeGateway`      |

---
