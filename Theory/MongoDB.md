## Ways to create index:
In **MongoDB**, an **index** improves query performance by allowing the database to quickly locate documents without scanning the entire collection.

### Ways to create index:

---

### 1. **Default Index**

* By default, every collection has an **\_id index** created automatically.

```js
{ "_id": 1 }
```

---

### 2. **Create a Single Field Index**

```js
db.collection.createIndex({ fieldName: 1 })   // 1 = ascending order
db.collection.createIndex({ fieldName: -1 })  // -1 = descending order
```

👉 Example:

```js
db.users.createIndex({ age: 1 })
```

---

### 3. **Compound Index (multiple fields)**

```js
db.collection.createIndex({ field1: 1, field2: -1 })
```

👉 Example:

```js
db.orders.createIndex({ customerId: 1, orderDate: -1 })
```

---

### 4. **Unique Index**

Ensures all values in the field are unique.

```js
db.users.createIndex({ email: 1 }, { unique: true })
```
---

### 6. **Hashed Index**

Used for **sharding**.

```js
db.collection.createIndex({ userId: "hashed" })
```
---

### 8. **TTL Index (Time-to-Live)**

Automatically deletes documents after a specified number of seconds.

```js
db.sessions.createIndex({ createdAt: 1 }, { expireAfterSeconds: 3600 })
```

---

### 9. **Check Existing Indexes**

```js
db.collection.getIndexes()
```

---

⚡ **Best Practice**

* Always create indexes based on **query patterns**.
* Avoid too many indexes (they increase write cost).
* Use **`explain()`** to check query performance:

```js
db.users.find({ age: 25 }).explain("executionStats")
```
Perfect 👍 Let’s look at how we can create **indexes in MongoDB using Spring Data MongoDB** (Java way).

---

## 1. **Single Field Index**

Use `@Indexed` on a field:

```java
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

    @Id
    private String id;

    @Indexed   // ascending by default
    private String name;

    @Indexed(unique = true)   // unique index
    private String email;

    private int age;
}
```

➡️ This will create:

```json
{ "name": 1 }
{ "email": 1, unique: true }
```

---

## 2. **Compound Index**

Use `@CompoundIndex` at the class level:

```java
import org.springframework.data.mongodb.core.index.CompoundIndex;

@Document(collection = "orders")
@CompoundIndex(name = "customer_order_idx", def = "{'customerId': 1, 'orderDate': -1}")
public class Order {
    @Id
    private String id;

    private String customerId;
    private String orderDate;
    private Double amount;
}
```

➡️ Creates index:

```json
{ "customerId": 1, "orderDate": -1 }
```

---

## 3. **Text Index**

Spring Data doesn’t have direct `@TextIndexed`, but you can use:

```java
import org.springframework.data.mongodb.core.index.TextIndexed;

@Document(collection = "articles")
public class Article {

    @Id
    private String id;

    @TextIndexed(weight = 2)
    private String title;

    @TextIndexed
    private String content;
}
```

➡️ Creates text index on `title` & `content`.

---

## 4. **TTL Index (Expire Documents)**

```java
import org.springframework.data.mongodb.core.index.Indexed;
import java.time.Instant;

@Document(collection = "sessions")
public class Session {

    @Id
    private String id;

    private String userId;

    @Indexed(expireAfterSeconds = 3600) // Auto delete after 1 hour
    private Instant createdAt;
}
```

---

## 5. **Programmatic Index Creation**

Sometimes, you want to control index creation via Java code:

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;

import javax.annotation.PostConstruct;

@Configuration
public class MongoIndexConfig {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void initIndexes() {
        mongoTemplate.indexOps("users")
                     .ensureIndex(new Index().on("age", 1));  // age ascending index
    }
}
```

---

✅ **Summary**

* `@Indexed` → single field index
* `@CompoundIndex` → multiple fields
* `@TextIndexed` → full-text search
* `expireAfterSeconds` → TTL
* `MongoTemplate.ensureIndex()` → manual creation

---
1) Find docs where a nested field differs from another field
   A. Compare a nested field to a top-level field (use $expr)

Find docs where nested.field != otherField:

```
db.collection.find({
$expr: { $ne: ["$nested.field", "$otherField"] }
})
```

Explanation: $expr allows comparing fields within the same document.