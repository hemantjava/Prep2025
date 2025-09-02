
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>

```
```yml ---application.yml
spring:
  cache:
    type: redis
  redis:
    host: localhost
    port: 6379
    password: yourpassword   # optional

```
## OR
```java
@Configuration
@EnableRedisRepositories
public class RedisConfig {

    @Bean
    public JedisConnectionFactory connectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName("localhost");
        configuration.setPort(6379);
        return new JedisConnectionFactory(configuration);
    }

    @Bean
    public RedisTemplate<String, Object> template() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new JdkSerializationRedisSerializer());
        template.setValueSerializer(new JdkSerializationRedisSerializer());
        template.setEnableTransactionSupport(true);
        template.afterPropertiesSet();
        return template;
    }

}
```
```java
@SpringBootApplication
@EnableCaching
public class MyApp {
    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args);
    }
}

```
```java
@Service
public class ProductService {

    @Cacheable(value = "products", key = "#id")
    public Product getProductById(Long id) {
        // fetch from DB
        return productRepository.findById(id).orElseThrow();
    }

    @CachePut(value = "products", key = "#product.id")
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @CacheEvict(value = "products", key = "#id")
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

```
## ✅ Flow:

* @Cacheable → first check Redis → if not found, call method → store in Redis.
* @CachePut → update DB and cache.
* @CacheEvict → remove from cache.

## Good question 👍. Let’s break it down clearly.

---

## 🔹 1. Read-Through Cache

**Definition:**
When the application requests data, it always goes through the cache.

* If data is present → cache returns it.
* If not present → cache **loads it from the database** (via cache provider) and stores it before returning.

**Flow:**

```
App → Cache → (DB if miss) → Cache stores → App
```

**When to use:**

* Frequently read data.
* You want **cache-aside behavior automated** (no need for app to fetch DB manually).
* Example: Product catalog, user profiles.

---

## 🔹 2. Write-Through Cache

**Definition:**
When the application writes data, it first goes to the cache, and the cache **synchronously writes it to the DB**.

**Flow:**

```
App → Cache → DB (write-through)
```

**When to use:**

* When strong **consistency** is required between cache and DB.
* Example: Banking transactions, inventory updates.

---

## 🔹 Comparison Table

| Feature         | Read-Through Cache                                     | Write-Through Cache                         |
| --------------- | ------------------------------------------------------ | ------------------------------------------- |
| **Reads**       | Cache miss → auto load from DB                         | Cache hit/miss handled same as read-through |
| **Writes**      | App updates DB (cache updated manually or invalidated) | App writes to cache → cache writes DB       |
| **Consistency** | Eventual consistency                                   | Strong consistency (cache + DB always same) |
| **Use Case**    | Data mostly read, occasional updates                   | Data requires strict consistency on writes  |

---

## 🔹 Example in Java (Ehcache or Redis via Spring)

**Read-Through (Spring Cache)**

```java
@Cacheable("users")
public User getUserById(Long id) {
    return userRepository.findById(id).orElseThrow();
}
```

👉 If user not in cache → load from DB → store in cache.

**Write-Through (Spring Cache + @CachePut)**

```java
@CachePut(value = "users", key = "#user.id")
public User saveUser(User user) {
    return userRepository.save(user); // save in DB + cache
}
```

👉 Save operation writes to both cache and DB.

---

✅ **Summary:**

* **Read-Through:** Great for heavy-read, less-write systems (e.g., product catalog).
* **Write-Through:** Great for consistency-sensitive systems (e.g., transactions, inventory).

---

Do you also want me to explain **Write-Behind (a.k.a. Write-Back)** caching — where cache writes asynchronously to DB 
(better performance but risk of data loss)?
