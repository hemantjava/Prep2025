
**JWT (JSON Web Token)** is a stateless authentication token in the format `header.payload.signature`.
In Spring Security 6:

1. **User login** with credential server send back jwt token 
2. **Client Requests** → Sends `Authorization: Bearer <token>` in headers.
3. **JWT Filter** → Reads token, validates signature & expiry, loads user, sets authentication in `SecurityContext`.
4. **Security Config** → Configures stateless session, permits `/auth/**`, protects other endpoints.
5. **Refresh Flow** → When access expires, use refresh token to get a new one.

**Key Points:**

* Stateless (no session storage).
* Access token short-lived, refresh token longer.
* Works well for APIs, especially with mobile apps.
![img.png](..%2F..%2Fimages%2Fsecurity%2Fimg.png)
```java

  public JwtService(
      @Value("${app.jwt.secret}") String secret,
      @Value("${app.jwt.access-exp-ms}") long accessExpMs,
      @Value("${app.jwt.refresh-exp-ms}") long refreshExpMs) {
    this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    this.accessExpMs = accessExpMs;
    this.refreshExpMs = refreshExpMs;
  }

  public String generateAccessToken(UserDetails user, Map<String, Object> extraClaims) {
    return buildToken(user.getUsername(), extraClaims, accessExpMs);
  }

  public String generateRefreshToken(UserDetails user) {
    return buildToken(user.getUsername(), Map.of("type", "refresh"), refreshExpMs);
  }
  public boolean isTokenValid(String token, UserDetails user) {
          return user.getUsername().equals(extractUsername(token)) && !isExpired(token);
          }

  public boolean isExpired(String token) {
          return parseAllClaims(token).getExpiration().before(new Date());
          }
```
## (reads Authorization: Bearer ...)
```java

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;
```
## Security config (SecurityFilterChain, stateless)
```java

@Configuration
public class SecurityConfig {

  private final JwtAuthFilter jwtAuthFilter;
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        // Replace with custom JPA UserDetailsService in real apps
        return new InMemoryUserDetailsManager(
                User.withUsername("user")
                        .password(encoder.encode("password"))
                        .roles("USER")
                        .build(),
                User.withUsername("admin")
                        .password(encoder.encode("password"))
                        .roles("ADMIN")
                        .build());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsService uds,
                                                            PasswordEncoder encoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(uds);
        provider.setPasswordEncoder(encoder);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // for stateless JWT; if using cookies, reconsider
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(reg -> reg
                        .requestMatchers("/api/auth/**", "/actuator/health").permitAll()
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider(userDetailsService(passwordEncoder()), passwordEncoder()))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
```
![img_1.png](..%2F..%2Fimages%2Fsecurity%2Fimg_1.png)

-----
# JWT + Refresh Token — Detailed Spring Boot guide (with code snippets)

Nice — below is a complete, practical flow + code pieces you can drop into a Spring Boot app. I’ll show the **architecture & assumptions**, the **endpoints**, the **data model**, **core services / utils**, the **security filter + config**, and **best practices** (rotation, storage, revocation). I’ll keep snippets concise but production-oriented.

---

## Assumptions

* Spring Boot + Spring Security (modern config style), JPA (Hibernate), and a `User` entity already exists.
* Using an **opaque refresh token** (UUID stored server-side) — recommended for easy revocation and rotation.
* Access token = JWT (short-lived, e.g. 15m). Refresh token = long-lived opaque token (e.g. days/weeks).
* Using `jjwt` (io.jsonwebtoken) for JWT handling.
* Passwords hashed with BCrypt.

---

## High-level flow (quick)

1. `POST /api/auth/login` — client sends credentials.
2. Server authenticates; returns **access JWT** (short-lived) + **refresh token** (opaque UUID stored in DB).
3. Client uses access JWT in `Authorization: Bearer <token>` for APIs.
4. When access JWT expires, client calls `POST /api/auth/refresh` with refresh token (or sends it via httpOnly cookie).
5. Server validates refresh token (exists, not expired, not revoked), then issues **new access JWT** — and optionally **rotates** refresh token (recommended).
6. `POST /api/auth/logout` — server invalidates refresh token(s) (delete/mark revoked).

---

# Code snippets

> Note: paste these into relevant packages and wire beans as usual.

---

## 1) `RefreshToken` JPA entity

```java
@Entity
@Table(name = "refresh_tokens")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String token; // opaque token (UUID)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Instant expiryDate;

    private boolean revoked = false;

    // getters/setters, constructors
}
```

Repository:

```java
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    int deleteByUser(User user);       // useful for logout
}
```

---

## 2) JWT utility (generation & validation)

```java
@Service
public class JwtUtils {
    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationMs}") // e.g. 15 * 60 * 1000
    private long jwtExpirationMs;

    private Key key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(UserDetails userDetails) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("roles", userDetails.getAuthorities().stream()
                       .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtExpirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException ex) {
            // log if needed
        }
        return false;
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                   .parseClaimsJws(token).getBody().getSubject();
    }
}
```

`application.properties` (example):

```
app.jwtSecret=very_long_random_secret_key_here_change_in_prod
app.jwtExpirationMs=900000          # 15 minutes
app.jwtRefreshExpirationMs=604800000 # 7 days (used in RefreshTokenService)
```

---

## 3) RefreshTokenService (create/verify/rotate/delete)

```java
@Service
public class RefreshTokenService {
    @Value("${app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository; // your User repo

    public RefreshTokenService(RefreshTokenRepository r, UserRepository u){
        this.refreshTokenRepository = r;
        this.userRepository = u;
    }

    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken rt = new RefreshToken();
        rt.setUser(userRepository.findById(userId).orElseThrow());
        rt.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        rt.setToken(UUID.randomUUID().toString());
        rt.setRevoked(false);
        return refreshTokenRepository.save(rt);
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException("Refresh token expired. Please login again.");
        }
        if (token.isRevoked()) {
            throw new RuntimeException("Refresh token revoked. Please login again.");
        }
        return token;
    }

    public void revokeRefreshToken(RefreshToken token) {
        token.setRevoked(true);
        refreshTokenRepository.save(token);
    }

    public int revokeAllUserTokens(User user) {
        return refreshTokenRepository.deleteByUser(user);
    }

    public RefreshToken rotateRefreshToken(RefreshToken existing) {
        existing.setToken(UUID.randomUUID().toString());
        existing.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        return refreshTokenRepository.save(existing);
    }
}
```

---

## 4) AuthController — login, refresh, logout

```java
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;

    public AuthController(AuthenticationManager authMgr, JwtUtils jwtUtils,
                          RefreshTokenService rts, UserRepository ur, UserDetailsService uds) {
        this.authenticationManager = authMgr;
        this.jwtUtils = jwtUtils;
        this.refreshTokenService = rts;
        this.userRepository = ur;
        this.userDetailsService = uds;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest req) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String accessToken = jwtUtils.generateAccessToken(userDetails);

        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());

        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken.getToken(), "Bearer", jwtUtils.getExpiryInfo()));
    }

    // Request body: { "refreshToken": "<token>" }
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody TokenRefreshRequest request) {
        String requestToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestToken)
            .map(refreshTokenService::verifyExpiration)
            .map(rt -> {
                User user = rt.getUser();
                UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

                // Option A: issue new access token and rotate refresh token
                String newAccessToken = jwtUtils.generateAccessToken(userDetails);
                RefreshToken rotated = refreshTokenService.rotateRefreshToken(rt);

                return ResponseEntity.ok(new TokenRefreshResponse(newAccessToken, rotated.getToken()));
            })
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Refresh token not found"));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody LogoutRequest request) {
        // revoke the passed refresh token or revoke all for user
        refreshTokenService.findByToken(request.getRefreshToken())
            .ifPresent(refreshTokenService::revokeRefreshToken);
        return ResponseEntity.ok(Map.of("message","Logged out successfully"));
    }
}
```

DTOs (simple):

```java
public record LoginRequest(String username, String password) {}
public record AuthResponse(String accessToken, String refreshToken, String tokenType, Long expiresIn) {}
public record TokenRefreshRequest(String refreshToken) {}
public record TokenRefreshResponse(String accessToken, String refreshToken) {}
public record LogoutRequest(String refreshToken) {}
```

---

## 5) JwtAuthenticationFilter (set SecurityContext from access token)

```java
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtUtils jwtUtils, UserDetailsService uds){
        this.jwtUtils = jwtUtils;
        this.userDetailsService = uds;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {

        String header = req.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            if (jwtUtils.validateToken(token)) {
                String username = jwtUtils.getUsernameFromToken(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken auth =
                   new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        chain.doFilter(req, res);
    }
}
```

---

## 6) SecurityConfig (modern Spring Security)

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtFilter;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(JwtAuthenticationFilter f, UserDetailsService uds) {
        this.jwtFilter = f;
        this.userDetailsService = uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                 .requestMatchers("/api/auth/**").permitAll()
                 .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
```

---

# Best practices & important notes

**Storage of refresh tokens (client side):**

* Prefer storing refresh tokens in **httpOnly, Secure cookies** (prevents JS access, lowers XSS risk). Use SameSite=Lax/Strict appropriately.
* Avoid localStorage for refresh tokens in browsers (XSS risk).
* Access token can be stored in memory (JS variable) and refreshed on page reload via cookie.

**Rotation & detection of replay:**

* **Rotate refresh tokens** on use: issue a new refresh token and invalidate the old one. This prevents stolen refresh tokens from being used multiple times.
* Keep metadata (device id, IP, user agent) with refresh token for anomaly detection.

**Revocation / logout:**

* Store refresh tokens server-side (DB). To log out, delete the token or mark revoked.
* Access JWTs are stateless; if you need immediate access token revocation, you need a blacklist (store revoked JWT IDs until expiry) or short access token lifetime.

**Expiration:**

* Short access token lifetime (5–30 minutes).
* Longer refresh token lifetime (days to weeks), but rotate and allow server-side revocation.

**Tokens vs sessions:**

* Access tokens are stateless and self-contained. Refresh tokens are stateful (DB) in this pattern — you have server side control.

**Security improvements:**

* Use **asymmetric keys (RS256)** in production for JWT signing (private key on auth server, public key distributed to resource servers).
* Use `aud` and `iss` claims and verify them.
* Add `jti` claim to JWTs if you plan to support blacklisting.

**Refresh token as a JWT?**

* You may make refresh tokens JWTs, but opaque tokens stored server-side are simpler to revoke/rotate.

**CSRF:**

* If refresh token is in cookie, protect `/refresh` with CSRF mitigation (or design to use SameSite cookies + double-submit cookie). Many APIs use cookie + SameSite + POST with CSRF token.

---

# Example request/response short summary

**Login request**

```
POST /api/auth/login
{ "username": "alice", "password": "secret" }
```

**Response**

```json
{
  "accessToken": "<jwt>",
  "refreshToken": "1a2b3c-uuid",
  "tokenType": "Bearer",
  "expiresIn": 900000
}
```

**Refresh request**

```
POST /api/auth/refresh
{ "refreshToken": "1a2b3c-uuid" }
```

**Response** (rotated)

```json
{
  "accessToken": "<new-jwt>",
  "refreshToken": "new-uuid"
}
```

---

If you want, I can:

* Give you a **complete GitHub-style project skeleton** (pom, main app, entities, repos, controllers) in a single downloadable ZIP, or
* Show the variant where the refresh token is delivered via **httpOnly cookie** (with code for setting cookies and CSRF notes), or
* Provide a **Postman collection** for testing these endpoints.

