In Spring Boot, you can connect to **multiple databases** by configuring multiple `DataSource` beans and then binding them with `EntityManagerFactory` + `TransactionManager`.

Here’s a **sample project structure** for using **two databases** (say, `db1` and `db2`):

---

## ✅ 1. `application.yml` (configure multiple DBs)

```yaml
spring:
  datasource:
    db1:
      url: jdbc:mysql://localhost:3306/db1
      username: root
      password: password
      driver-class-name: com.mysql.cj.jdbc.Driver

    db2:
      url: jdbc:postgresql://localhost:5432/db2
      username: postgres
      password: password
      driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

---

## ✅ 2. Entities

### `entity/db1/User.java`

```java
package com.example.demo.entity.db1;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // getters & setters
}
```

### `entity/db2/Product.java`

```java
package com.example.demo.entity.db2;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    // getters & setters
}
```

---

## ✅ 3. Repository Interfaces

### `repo/db1/UserRepository.java`

```java
package com.example.demo.repo.db1;

import com.example.demo.entity.db1.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
```

### `repo/db2/ProductRepository.java`

```java
package com.example.demo.repo.db2;

import com.example.demo.entity.db2.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
```

---

## ✅ 4. Configurations for Multiple Databases

### `config/Db1Config.java`

```java
package com.example.demo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.demo.repo.db1",
        entityManagerFactoryRef = "db1EntityManager",
        transactionManagerRef = "db1TransactionManager"
)
@EntityScan("com.example.demo.entity.db1")
public class Db1Config {

    @Bean(name = "db1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "db1EntityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("db1DataSource") DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.example.demo.entity.db1");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaPropertyMap(new HashMap<>());
        return em;
    }

    @Bean(name = "db1TransactionManager")
    public JpaTransactionManager transactionManager(
            @Qualifier("db1EntityManager") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory.getObject());
    }
}
```

### `config/Db2Config.java`

```java
package com.example.demo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.demo.repo.db2",
        entityManagerFactoryRef = "db2EntityManager",
        transactionManagerRef = "db2TransactionManager"
)
@EntityScan("com.example.demo.entity.db2")
public class Db2Config {

    @Bean(name = "db2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "db2EntityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("db2DataSource") DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.example.demo.entity.db2");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaPropertyMap(new HashMap<>());
        return em;
    }

    @Bean(name = "db2TransactionManager")
    public JpaTransactionManager transactionManager(
            @Qualifier("db2EntityManager") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory.getObject());
    }
}
```

---

## ✅ 5. Usage in Service

```java
package com.example.demo.service;

import com.example.demo.entity.db1.User;
import com.example.demo.entity.db2.Product;
import com.example.demo.repo.db1.UserRepository;
import com.example.demo.repo.db2.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MultiDbService {

    private final UserRepository userRepo;
    private final ProductRepository productRepo;

    public MultiDbService(UserRepository userRepo, ProductRepository productRepo) {
        this.userRepo = userRepo;
        this.productRepo = productRepo;
    }

    @Transactional("db1TransactionManager")
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Transactional("db2TransactionManager")
    public Product saveProduct(Product product) {
        return productRepo.save(product);
    }
}
```

---

⚡ With this setup:

* `UserRepository` uses **MySQL (`db1`)**
* `ProductRepository` uses **Postgres (`db2`)**

---
## Other approach

Perfect 👍 Let’s go with a **simpler approach** in **Spring Boot 3.x** using a **primary DataSource** + **secondary DataSource**.

This avoids a lot of boilerplate (no need for explicit `EntityManagerFactory` beans if you use Spring Boot’s auto-config for the primary DB).

---

# ✅ Step 1: `application.yml`

```yaml
spring:
  datasource:
    primary:
      url: jdbc:mysql://localhost:3306/db1
      username: root
      password: password
      driver-class-name: com.mysql.cj.jdbc.Driver

    secondary:
      url: jdbc:postgresql://localhost:5432/db2
      username: postgres
      password: password
      driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

---

# ✅ Step 2: Entities

### `entity/db1/User.java`

```java
package com.example.demo.entity.db1;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // getters & setters
}
```

### `entity/db2/Product.java`

```java
package com.example.demo.entity.db2;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    // getters & setters
}
```

---

# ✅ Step 3: Repositories

### `repo/db1/UserRepository.java`

```java
package com.example.demo.repo.db1;

import com.example.demo.entity.db1.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
```

### `repo/db2/ProductRepository.java`

```java
package com.example.demo.repo.db2;

import com.example.demo.entity.db2.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
```

---

# ✅ Step 4: Configurations

### `PrimaryDbConfig.java`

```java
package com.example.demo.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.demo.repo.db1",
        transactionManagerRef = "primaryTxManager"
)
@EntityScan("com.example.demo.entity.db1")
public class PrimaryDbConfig {

    @Primary
    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "primaryTxManager")
    public JpaTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }
}
```

---

### `SecondaryDbConfig.java`

```java
package com.example.demo.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.demo.repo.db2",
        transactionManagerRef = "secondaryTxManager"
)
@EntityScan("com.example.demo.entity.db2")
public class SecondaryDbConfig {

    @Bean(name = "secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondaryTxManager")
    public JpaTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }
}
```

---

# ✅ Step 5: Service Layer

```java
package com.example.demo.service;

import com.example.demo.entity.db1.User;
import com.example.demo.entity.db2.Product;
import com.example.demo.repo.db1.UserRepository;
import com.example.demo.repo.db2.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MultiDbService {

    private final UserRepository userRepo;
    private final ProductRepository productRepo;

    public MultiDbService(UserRepository userRepo, ProductRepository productRepo) {
        this.userRepo = userRepo;
        this.productRepo = productRepo;
    }

    @Transactional("primaryTxManager")
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Transactional("secondaryTxManager")
    public Product saveProduct(Product product) {
        return productRepo.save(product);
    }
}
```

---

# ✅ Step 6: Usage

```java
@RestController
@RequestMapping("/test")
public class TestController {

    private final MultiDbService service;

    public TestController(MultiDbService service) {
        this.service = service;
    }

    @PostMapping("/user")
    public User saveUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    @PostMapping("/product")
    public Product saveProduct(@RequestBody Product product) {
        return service.saveProduct(product);
    }
}
```

---

✨ This approach is **simpler** because:

* Primary DB uses Spring Boot’s default auto-config.
* Secondary DB only requires minimal configuration.
* No manual `EntityManagerFactory` beans unless you need fine-grained control.

---
