## In **Spring Boot REST APIs**, versioning is very important to handle backward compatibility and smooth evolution of APIs.
There are multiple strategies for API versioning, and Spring Boot supports them in different ways.

---

## 1. **URI Path Versioning (URL Versioning)**

* Version is included in the URL path.
* Simple and most commonly used approach.

```java
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerV1Controller {
    @GetMapping
    public String getCustomersV1() {
        return "Customer API V1";
    }
}

@RestController
@RequestMapping("/api/v2/customers")
public class CustomerV2Controller {
    @GetMapping
    public String getCustomersV2() {
        return "Customer API V2 with extra fields";
    }
}
```

✅ Easy to implement
❌ URL changes when API version changes

---

## 2. **Request Parameter Versioning**

* Version is sent as a **query parameter**.

```java
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @GetMapping(params = "version=1")
    public String getCustomersV1() {
        return "Customer API V1";
    }

    @GetMapping(params = "version=2")
    public String getCustomersV2() {
        return "Customer API V2";
    }
}
```

✅ Same endpoint, different versions handled
❌ Query params can clutter API calls

---

## 3. **Header Versioning (Custom Header)**

* Version is passed in **HTTP headers**.
* Clean URLs, version handled separately.

```java
@RestController
@RequestMapping("/api/customers")
public class CustomerHeaderController {

    @GetMapping(headers = "X-API-VERSION=1")
    public String getCustomersV1() {
        return "Customer API V1";
    }

    @GetMapping(headers = "X-API-VERSION=2")
    public String getCustomersV2() {
        return "Customer API V2";
    }
}
```

✅ Clean API paths
❌ Requires client to always send headers

---

## 4. **Content Negotiation / Media Type Versioning (Accept Header / MIME Type)**

* Version included in the **`Accept` header** using MIME type.

```java
@RestController
@RequestMapping("/api/customers")
public class CustomerMediaController {

    @GetMapping(produces = "application/vnd.example.v1+json")
    public String getCustomersV1() {
        return "Customer API V1";
    }

    @GetMapping(produces = "application/vnd.example.v2+json")
    public String getCustomersV2() {
        return "Customer API V2";
    }
}
```

✅ Most flexible, widely used in enterprise systems
❌ Harder to test manually without proper tools

---
👉 Best practice: Use URI Path Versioning for public APIs (clear & user-friendly) and Header/Content Negotiation for 
enterprise/internal APIs where cleaner URLs and flexibility are preferred.
---
Great question 👍 — **idempotency** is an important concept in REST APIs, especially for **POST, PUT, DELETE** operations where duplicate requests may cause unintended side effects.

---

# 🔹 What is Idempotency?

* **Idempotent operation**: Making the same request **multiple times** has the **same effect** as making it once.
* For example:

    * `GET /customers/1` → Always returns the same customer (safe + idempotent).
    * `DELETE /customers/1` → Multiple deletes still result in "customer removed".
    * `POST /orders` (creating resources) is **not idempotent** by default — sending the same request multiple times creates multiple orders.

👉 To make `POST` idempotent (like for **payment APIs, order creation, ticket booking**), we introduce **idempotency keys**.

---

# 🔹 Common Strategies for Idempotency in REST APIs

1. **Idempotency-Key (via Header)**

    * Client generates a unique **Idempotency Key (UUID)** and sends it with the request.
    * Server checks if the key already exists in DB/cache:

        * If yes → return **previous response**.
        * If no → process request, store result against key.

---

# 🔹 Spring Boot Example: Idempotent Order Creation

### 1. Create a Model

```java
public class Order {
    private String orderId;
    private String product;
    private int quantity;

    // getters and setters
}
```

---

### 2. Repository (using in-memory storage for simplicity)

```java
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

@Repository
public class IdempotencyRepository {

    // Store key -> response mapping
    private final ConcurrentHashMap<String, Order> store = new ConcurrentHashMap<>();

    public boolean containsKey(String key) {
        return store.containsKey(key);
    }

    public Order get(String key) {
        return store.get(key);
    }

    public void save(String key, Order order) {
        store.put(key, order);
    }
}
```

---

### 3. Service Layer

```java
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {
    private final IdempotencyRepository repo;

    public OrderService(IdempotencyRepository repo) {
        this.repo = repo;
    }

    public Order createOrder(String idempotencyKey, String product, int quantity) {
        if (repo.containsKey(idempotencyKey)) {
            // Return the previously created order
            return repo.get(idempotencyKey);
        }

        // Otherwise, create new order
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());
        order.setProduct(product);
        order.setQuantity(quantity);

        repo.save(idempotencyKey, order);
        return order;
    }
}
```

---

### 4. REST Controller

```java
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order createOrder(
            @RequestHeader("Idempotency-Key") String idempotencyKey,
            @RequestBody Order request) {

        return orderService.createOrder(idempotencyKey, request.getProduct(), request.getQuantity());
    }
}
```

---

### 5. Usage Example

#### Request 1

```
POST /orders
Header: Idempotency-Key: abc-123
Body: { "product": "Laptop", "quantity": 1 }
```

👉 Server Response:

```json
{
  "orderId": "4e7b-98ad-11ef-bb9f",
  "product": "Laptop",
  "quantity": 1
}
```

#### Request 2 (same `Idempotency-Key`)

```
POST /orders
Header: Idempotency-Key: abc-123
Body: { "product": "Laptop", "quantity": 1 }
```

👉 Server Response (same order returned, no duplicate created):

```json
{
  "orderId": "4e7b-98ad-11ef-bb9f",
  "product": "Laptop",
  "quantity": 1
}
```

---

# 🔹 Key Points

* **Idempotency works across retries** → If the client retries due to network failure, server returns same response.
* **Best suited for POST (create) APIs** where duplicates are harmful.
* Use **cache/DB/Redis** to store `Idempotency-Key → Response` mapping (with TTL).
* `PUT` and `DELETE` are inherently idempotent if implemented properly.

---
