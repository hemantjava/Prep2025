
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