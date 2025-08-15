What is OAuth 2.0?
A protocol where your app (Client) gets permission from 3rd party an Auth Server (Google, Keycloak, etc.) to access
resources (APIs) using access tokens (often JWT).

1️⃣ OAuth2 Login (User Authentication)
Goal: Let users log in via Google, Keycloak, etc.

Add deps:
spring-boot-starter-security, spring-boot-starter-oauth2-client
Config (application.yml):
```xml
spring.security.oauth2.client.registration.google.client-id=...
spring.security.oauth2.client.registration.google.client-secret=...
```
### Security
```java
http.authorizeHttpRequests(a -> a.anyRequest().authenticated())
    .oauth2Login();

```