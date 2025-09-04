### Java-first guide to the **15-Factor App** (12-Factor + 3 modern additions), plus

### What are the 15 factors?

1. **One codebase, one app** – single repo per app, many deploys. ([O'Reilly Media][1])
2. **API first** – design/contract before code (OpenAPI, mocks). ([O'Reilly Media][2])
3. **Dependencies** – declare & isolate (Maven/Gradle; no implicit system tools). ([Twelve-Factor App][3], [Domenico Luciani][4])
4. **Build, release, run** – strictly separate stages. ([Twelve-Factor App][3])
5. **Configuration, credentials & code** – config via env, not in code. ([O'Reilly Media][1])
6. **Logs** – treat logs as event streams (stdout/stderr). ([Twelve-Factor App][3])
7. **Disposability** – fast startup, graceful shutdown. ([Twelve-Factor App][3])
8. **Backing services** – attach via URLs; swap without code change. ([Twelve-Factor App][3], [Domenico Luciani][4])
9. **Environment parity** – dev/stage/prod as similar as possible. ([Twelve-Factor App][3])
10. **Admin processes** – run one-off tasks as the same build/release. ([Twelve-Factor App][3])
11. **Port binding** – self-contained services export over a port. ([Twelve-Factor App][3])
12. **Stateless processes** – state in backing services, not memory/disk. ([Twelve-Factor App][3])
13. **Concurrency** – scale out via process model (horizontally). ([Twelve-Factor App][3])
14. **Telemetry** – metrics/traces beyond basic logging. ([O'Reilly Media][1])
15. **Authentication & Authorization** – centralized, standards-based (OIDC/OAuth2). ([O'Reilly Media][1])

---

## Tiny, practical Spring Boot snippets

### 2) API-first (OpenAPI contract, code follows)

```yaml
# openapi.yaml (contract-first)
openapi: 3.0.3
info: { title: Orders API, version: 1.0.0 }
paths:
  /orders:
    get:
      responses:
        '200': { description: OK }
```

Generate stubs/docs (e.g., `openapi-generator-maven-plugin`) and wire your controller to the generated interface. ([O'Reilly Media][2])

```xml
<!-- pom.xml -->
<plugin>
  <groupId>org.openapitools</groupId>
  <artifactId>openapi-generator-maven-plugin</artifactId>
  <version>7.6.0</version>
  <executions>
    <execution>
      <goals><goal>generate</goal></goals>
      <configuration>
        <inputSpec>${project.basedir}/openapi.yaml</inputSpec>
        <generatorName>spring</generatorName>
        <apiPackage>com.acme.api</apiPackage>
      </configuration>
    </execution>
  </executions>
</plugin>
```

### 3) Dependencies (explicit & isolated)

```xml
<!-- pom.xml: declare everything explicitly -->
<dependencies>
  <dependency>
    <groupId>org.springframework.boot</groupId><artifactId>spring-boot-starter-web</artifactId>
  </dependency>
  <!-- no reliance on “system” curl/jq/etc. -->
</dependencies>
```

([Domenico Luciani][4])

### 5) Config/Secrets via env (not in code)

```yaml
# application.yml
server:
  port: ${PORT:8080}
spring:
  datasource:
    url: ${DB_URL}
    username: ${DB_USER}
    password: ${DB_PASSWORD}
```

```bash
# run with env
PORT=8081 DB_URL=jdbc:postgresql://db:5432/app DB_USER=app DB_PASSWORD=secret \
java -jar app.jar
```

([O'Reilly Media][1])

### 6) Logs to stdout (aggregated by the platform)

```properties
# application.properties
logging.file.name= # unset => console
logging.pattern.console=%d %-5level [%t] %logger - %msg%n
```

([Twelve-Factor App][3])

### 7) Disposability (fast start + graceful stop)

```java
// Graceful shutdown hook
@Bean
public TomcatConnectorCustomizer gracefulShutdown() {
  return connector -> Runtime.getRuntime().addShutdownHook(new Thread(() -> {
    connector.pause();
    // close resources, flush, etc.
  }));
}
```

Also enable Spring Boot graceful shutdown:
`server.shutdown=graceful` and `spring.lifecycle.timeout-per-shutdown-phase=20s`. ([Twelve-Factor App][3])

### 8) Backing services via URLs (swappable)

```java
// Use injected DataSource; swapping Postgres->RDS requires only env changes
@Repository
public class OrderRepo {
  private final JdbcTemplate jdbc;
  public OrderRepo(JdbcTemplate jdbc){ this.jdbc = jdbc; }
}
```

([Twelve-Factor App][3], [Domenico Luciani][4])

### 10) Admin processes as one-offs (same image)

```java
// Run DB migration as a one-off job using the same artifact
@SpringBootApplication
public class Migrate implements CommandLineRunner {
  private final Flyway flyway;
  public Migrate(Flyway flyway){ this.flyway = flyway; }
  public void run(String... args){ flyway.migrate(); }
}
```

Invoke with `--spring.main.web-application-type=none`. ([Twelve-Factor App][3])

### 11) Port binding (self-contained HTTP server)

```java
@RestController
@RequestMapping("/health")
class Health {
  @GetMapping public Map<String,String> ok(){ return Map.of("status","up"); }
}
```

```bash
# platform maps inbound traffic to $PORT
java -jar app.jar
```

([Twelve-Factor App][3])

### 12) Stateless processes (sessionless; externalize state)

```java
// Disable server sessions; use stateless JWT
@Bean SecurityFilterChain chain(HttpSecurity http) throws Exception {
  return http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .build();
}
```

Store sessions/cache in Redis if needed, not local memory. ([Twelve-Factor App][3])

### 13) Concurrency (scale out)

```yaml
# k8s Deployment (replicas scale horizontally)
apiVersion: apps/v1
kind: Deployment
spec:
  replicas: 4
  template:
    spec:
      containers:
        - name: app
          image: ghcr.io/acme/app:1.0
          ports: [{ containerPort: 8080 }]
```

([Twelve-Factor App][3])

### 14) Telemetry (Micrometer + Prometheus + tracing)

```xml
<!-- pom.xml -->
<dependency>
  <groupId>io.micrometer</groupId><artifactId>micrometer-registry-prometheus</artifactId>
</dependency>
<dependency>
  <groupId>io.micrometer</groupId><artifactId>micrometer-tracing-bridge-brave</artifactId>
</dependency>
<dependency>
  <groupId>io.zipkin.reporter2</groupId><artifactId>zipkin-reporter-brave</artifactId>
</dependency>
```

```java
// Custom business metric
@Component
class OrderMetrics {
  OrderMetrics(MeterRegistry registry) {
    Counter.builder("orders.created").description("Orders created").register(registry);
  }
}
```

Prometheus scrapes `/actuator/prometheus`; tracing exports to Zipkin/Tempo. ([O'Reilly Media][1])

### 15) Authentication & Authorization (OAuth2/JWT)

```xml
<!-- pom.xml -->
<dependency>
  <groupId>org.springframework.boot</groupId><artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
</dependency>
```

```java
@Bean
SecurityFilterChain security(HttpSecurity http) throws Exception {
  return http.authorizeHttpRequests(auth -> auth
            .requestMatchers("/actuator/health").permitAll()
            .anyRequest().authenticated())
            .oauth2ResourceServer(oauth -> oauth.jwt())
            .build();
}
```

```yaml
# application.yml (issuer/JWK from your IdP: Keycloak/Okta/Auth0)
spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: ${OIDC_ISSUER}
```

([O'Reilly Media][1], [Wikipedia][5])

---

## Build-Release-Run & Containerization

```dockerfile
# Build
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /src
COPY pom.xml ./
COPY src ./src
RUN mvn -q -DskipTests package

# Run
FROM eclipse-temurin:21-jre
ENV PORT=8080
EXPOSE 8080
COPY --from=build /src/target/app.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

Use the same image for web and admin one-offs. ([Twelve-Factor App][3])

---

### TL;DR (what’s “new” vs 12-Factor?)

* **API-First** contracts → generate stubs/docs and enable parallel work.
* **Telemetry** (metrics + traces) → observability beyond logs.
* **AuthN/AuthZ** → standardize security (OIDC/OAuth2, JWT). ([O'Reilly Media][1])

If you want, I can turn this into a ready-to-run **starter repo** (Spring Boot 3/Java 21) with OpenAPI, Micrometer/Prometheus, and OAuth2 JWT wired in.

[1]: https://www.oreilly.com/content/telemetry-an-essential-part-of-any-cloud-native-app/?utm_source=chatgpt.com "Telemetry, an essential part of any cloud-native app - O'Reilly Media"
[2]: https://www.oreilly.com/library/view/beyond-the-twelve-factor/9781492042631/?utm_source=chatgpt.com "Beyond the Twelve-Factor App - O'Reilly Media"
[3]: https://12factor.net/?utm_source=chatgpt.com "The Twelve-Factor App"
[4]: https://domenicoluciani.com/2021/10/30/15-factor-app.html?utm_source=chatgpt.com "The Fifteen-Factor App - Domenico Luciani"
[5]: https://en.wikipedia.org/wiki/Web_API_security?utm_source=chatgpt.com "Web API security"
