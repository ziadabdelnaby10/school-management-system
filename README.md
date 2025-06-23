# School Management System - JWT Authentication
*Secure REST API with JSON Web Tokens*

[![JWT Security](https://img.shields.io/badge/Branch-JWT_Security-orange)](https://github.com/ziadabdelnaby10/school-management-system/tree/jwt-security)  
[![Spring Security](https://img.shields.io/badge/Security-JWT-brightgreen)](https://jwt.io)

This branch implements JWT-based authentication on top of the core application from the [`main branch`](https://github.com/ziadabdelnaby10/school-management-system/tree/main).

## 🔐 Key Features
- JWT authentication with access/refresh tokens
- Stateless Spring Security configuration
- Role-based access control (ADMIN/USER)
- Secure HttpOnly cookie storage
- Token refresh mechanism
- Password hashing with BCrypt
- Custom JWT authentication filter  

### Security Configuration
The security setup is configured in `SecurityConfig.java`:

```java
@RequiredArgsConstructor
@Configuration
@Profile("default")
public class SecurityConfig {
    
    @Bean
    SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            CSRFCookieFilter csrfCookieFilter,
            JWTTokenValidatorFilter jwtTokenValidatorFilter,
            AuthoritiesLoggingAfterFilter authoritiesLoggingAfterFilter,
            AuthoritiesLoggingAtFilter authoritiesLoggingAtFilter,
            SchoolUserDetailsService schoolUserDetailsService,
            SchoolUsernamePwdAuthenticationProvider schoolUsernamePwdAuthenticationProvider,
            CustomBasicAuthenticationEntryPointHandler customBasicAuthenticationEntryPointHandler,
            AuthenticationEntryPoint authenticationEntryPoint,
            CustomAccessDeniedHandler customAccessDeniedHandler
    ) throws Exception {

        // Define path patterns for role-based access
        var anyUser = new String[]{"/api/users/login", "/api/users"};
        var anyAdmin = new String[]{"/api/students", "/api/parents", "/api/teachers"};
        var anyStudent = new String[]{"/api/students/**"};
        var anyParent = new String[]{"/api/parents/**"};
        var anyTeacher = new String[]{"/api/teachers/**"};
        var swagger = new String[]{"/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/api-docs/**"};


        http
                // Use custom user details service and authentication provider
                .userDetailsService(schoolUserDetailsService)
                .authenticationProvider(schoolUsernamePwdAuthenticationProvider)

                // Save the SecurityContext on authentication (even for stateless sessions)
                .securityContext(contextConfig -> contextConfig.requireExplicitSave(false))

                // Session management policy - session will always be created
                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // CORS configuration to allow all origins, headers, and methods
                .cors(corsConfig -> corsConfig.configurationSource(request -> {
                    var config = new CorsConfiguration();
                    config.setAllowCredentials(Boolean.TRUE);
                    config.setAllowedOrigins(List.of("http://localhost:4200"));
                    config.setAllowedHeaders(List.of("*"));
                    config.setAllowedMethods(List.of("*"));
                    config.setMaxAge(3600L);
                    config.setExposedHeaders(List.of(HttpHeaders.AUTHORIZATION));
                    return config;
                }))

                // CSRF configuration
                .csrf(csrfConfig -> csrfConfig
                        // CSRF handler to support token as a request attribute
                        .csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler())
                        .ignoringRequestMatchers(anyUser)
                        .ignoringRequestMatchers(swagger)
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))

                // Add custom security filters
                .addFilterAfter(csrfCookieFilter, BasicAuthenticationFilter.class)
//                .addFilterBefore(requestValidationBeforeFilter, BasicAuthenticationFilter.class)
                .addFilterAfter(authoritiesLoggingAfterFilter, BasicAuthenticationFilter.class)
                .addFilterAt(authoritiesLoggingAtFilter, BasicAuthenticationFilter.class)
//                .addFilterAfter(jwtTokenGeneratorFilter, BasicAuthenticationFilter.class)
                .addFilterBefore(jwtTokenValidatorFilter, BasicAuthenticationFilter.class)

                // Require HTTP (non-HTTPS) channel for all requests
                .requiresChannel(requiresChannel -> requiresChannel.anyRequest().requiresInsecure())//For HTTP

                // Authorization rules for different user roles
                .authorizeHttpRequests(
                        (requests) -> requests
                                .requestMatchers(anyAdmin).hasAuthority(SystemRole.MANAGER.toString())
                                .requestMatchers(anyStudent).hasAuthority(SystemRole.STUDENT.toString())
                                .requestMatchers(anyParent).hasAuthority(SystemRole.PARENT.toString())
                                .requestMatchers(anyTeacher).hasAuthority(SystemRole.TEACHER.toString())
                                .requestMatchers(swagger).permitAll()
                                .requestMatchers(anyUser).permitAll()
                                .anyRequest().authenticated()
                )

                // Disable default form-based login
                .formLogin(AbstractHttpConfigurer::disable)

                // Configure HTTP Basic authentication and custom entry point
                .httpBasic(hbc -> hbc.authenticationEntryPoint(authenticationEntryPoint))

                // Exception handling with custom handlers
                .exceptionHandling(ehc -> ehc
                        .accessDeniedHandler(customAccessDeniedHandler)
                        .authenticationEntryPoint(customBasicAuthenticationEntryPointHandler)
                );

        // Build and return the security filter chain
        return http.build();
    }

    @Bean
    SchoolUserDetailsService schoolUserDetailsService(PersonRepository personRepository) {
        return new SchoolUserDetailsService(personRepository);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(SchoolUsernamePwdAuthenticationProvider authenticationProvider) {
        ProviderManager providerManager = new ProviderManager(authenticationProvider);
        providerManager.setEraseCredentialsAfterAuthentication(false);
        return providerManager;
    }
}
```

```java
@Service
@RequiredArgsConstructor
@Slf4j
public class JwtTokenService {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Getter
    @Value("${security.jwt.expiration-time}")
    private long expiration;

    private Claims extractAllClaimsFromToken(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private String buildToken(
            String email,
            Collection<? extends GrantedAuthority> authorities
    ) {
        return Jwts.builder()
                .setIssuer(SchoolConstants.JWT_ISSUER)
                .setSubject(email)
                .claim(SchoolConstants.CLAIM_EMAIL, email)
                .claim(SchoolConstants.CLAIM_AUTHORITIES, authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS384)
                .compact();
    }

    public boolean isTokenValid(String token, String checkedEmail) {
        final String email = extractEmail(token);
        return (email.equals(checkedEmail)) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public String extractEmail(String token) {
        return String.valueOf(extractAllClaimsFromToken(token).get(SchoolConstants.CLAIM_EMAIL));
    }

    public String extractAuthorities(String token) {
        return String.valueOf(extractAllClaimsFromToken(token).get(SchoolConstants.CLAIM_AUTHORITIES));
    }

    public String generateToken(String email,
                                Collection<? extends GrantedAuthority> authorities) {
        return buildToken(email, authorities);
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

```

### Authentication Flow
sequenceDiagram
    participant Client
    participant Server
    Client->>Server: POST /api/auth/login
    Server-->>Client: Access Token + Refresh Token (HttpOnly cookies)
    Client->>Server: API Request (with access token)
    alt Token valid
        Server-->>Client: Requested Data (200 OK)
    else Token expired
        Client->>Server: POST /api/auth/refresh
        Server-->>Client: New Access Token
        Client->>Server: Retry Request
    end


### 👥 Default Users
User Type | Role
Manager | MANAGER
Student | STUDENT
Teacher | TEACHER
Parent | PARENT

### ⚙️ Setup Instructions
- Checkout this branch:
```bash
git checkout jwt-security
```
- Build and run:
```bash
mvn spring-boot:run
```