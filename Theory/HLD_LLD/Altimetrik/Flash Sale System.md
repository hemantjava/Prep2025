Here is a **complete and clean High-Level Design (HLD)** and **Low-Level Design (LLD)** for the **Flash Sale System**.

---

## ✅ High-Level Design (HLD): Flash Sale System

### 🎯 Requirements

* Limited inventory for flash sale items.
* High-concurrency during flash sale (thousands of customers.
* No exposure of inventory count to customers.
* Strong consistency to prevent overselling.
* Order persistence after purchase.
* Support for global scale.

---

### ✅ 🏗️ High-Level Components

| Component                          | Responsibility                                                                   |
| ---------------------------------- | -------------------------------------------------------------------------------- |
| 🌐 API Gateway                     | Exposes REST APIs for product info and flash sale purchase requests.             |
| ⚡ Flash Sale Service               | Handles flash sale logic: purchase request handling and validations.             |
| 📦 Product Service                 | Shows flash sale product info (without inventory details).                       |
| 🧱 Inventory Service               | Manages inventory counter in Redis (atomic operations).                          |
| 🛑 Distributed Lock Service        | Ensures strong consistency during inventory decrement (e.g., RedLock/Zookeeper). |
| 🎟️ Message Queue (Kafka/RabbitMQ) | Decouples flash sale request from order persistence for scalability.             |
| 🏷️ Order Service                  | Saves confirmed orders into database asynchronously.                             |
| 📊 Monitoring + Metrics            | Prometheus + Grafana for system health and real-time metrics.                    |

---

### ✅ 🌐 High-Level Flow Diagram

```plaintext
[Customer] → [API Gateway] → [Flash Sale Service]
                                       ↓
                     ┌─────────────────┴──────────────────┐
                     ↓                                    ↓
          [Distributed Lock Service]             [Redis (Inventory Counter)]
                     ↓                                    ↓
        Lock → Check & Decrement Inventory Atomically → Release Lock
                                       ↓
                  [Message Queue (Kafka/RabbitMQ)]
                                       ↓
                          [Order Service Worker]
                                       ↓
                          [Orders Database (Postgres/DynamoDB)]
```

---

## ✅ Low-Level Design (LLD)

---

### 1️⃣ Database Schema

#### ➤ Orders Table

| Column        | Type      | Notes                            |
| ------------- | --------- | -------------------------------- |
| order\_id     | BIGINT    | Primary Key                      |
| customer\_id  | BIGINT    | Reference to customer (optional) |
| product\_id   | BIGINT    | Reference to flash sale product  |
| purchased\_at | TIMESTAMP | Purchase timestamp               |

#### ➤ Flash Sale Products Table

| Column            | Type    | Notes                      |
| ----------------- | ------- | -------------------------- |
| product\_id       | BIGINT  | Primary Key                |
| name              | VARCHAR | Product Name               |
| description       | TEXT    | Product Description        |
| price             | DECIMAL | Sale Price                 |
| flash\_sale\_date | DATE    | Should be today (one-time) |

---

### 2️⃣ Inventory Handling Logic (Pseudocode)

```java
public boolean attemptPurchase(long productId) {
    String inventoryKey = "FLASH_SALE_INVENTORY_" + productId;
    
    // Acquire distributed lock
    if (!lockService.acquireLock(productId)) {
        return false;  // Too many concurrent requests
    }
    
    try {
        Long inventoryCount = redis.get(inventoryKey);
        if (inventoryCount != null && inventoryCount > 0) {
            // Atomic decrement
            Long newCount = redis.decr(inventoryKey);
            
            if (newCount >= 0) {
                // Publish purchase event to queue
                kafkaTemplate.send("flash-sale-orders", createOrderMessage(productId));
                return true;
            }
        }
        return false;  // Sold out
    } finally {
        lockService.releaseLock(productId);
    }
}
```

---

### 3️⃣ Asynchronous Order Persistence

* Order Service listens to `flash-sale-orders` topic.
* Persists the order in the database.

```java
@KafkaListener(topics = "flash-sale-orders")
public void processOrder(String orderMessage) {
    // Deserialize and persist the order in DB
    Order order = parseOrderMessage(orderMessage);
    orderRepository.save(order);
}
```

---

### 4️⃣ Monitoring + Alerts

| Metric          | Description                                    |
| --------------- | ---------------------------------------------- |
| Inventory Count | Remaining stock per product.                   |
| Purchase Rate   | Purchases per second.                          |
| Error Rate      | Failed purchases due to inventory/lock issues. |
| Latency         | Request response time during flash sale.       |

Alerts when:

* Inventory goes negative.
* Lock contention too high.
* Errors spike.

---

## ✅ Summary Table

| Feature               | Design Solution                            |
| --------------------- | ------------------------------------------ |
| High Concurrency      | Redis atomic decrement + Distributed locks |
| No Inventory Exposure | API only shows product info                |
| Data Consistency      | Lock + atomic Redis ops                    |
| Scalability           | Async processing with Kafka                |
| Global Scale          | Multi-region deployments, CDN, replication |

---
