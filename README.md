# School Management System - Basic Authentication

This branch implements Spring Security with Basic Authentication on top of the core application from the [`main` branch](https://github.com/ziadabdelnaby10/school-management-system/tree/main).

## 🔒 Security Implementation

### Features
- Spring Security with Basic Authentication
- Role-based access control (ADMIN/USER)
- In-memory user credential storage
- Password encoding with BCrypt
- Secured REST API endpoints

### Security Configuration
The security setup is configured in `SecurityConfig.java`:

```java
@RequiredArgsConstructor
@Configuration
@Profile("default")
public class SecurityConfig {
  //This is a Reminder that this instructions inspired by the spring security course
  //This was build first for mvc to continue in mvc go to the course section 7 the part where the login starts

  /**
   * Configures the Spring Security filter chain for the application.
   * It sets up authentication, authorization rules, CSRF protection, custom filters, CORS, and exception handling.
   *
   * @param http The HttpSecurity object used to configure security for HTTP requests.
   * @param csrfCookieFilter
   * @param jwtTokenValidatorFilter
   * @param authoritiesLoggingAfterFilter
   * @param authoritiesLoggingAtFilter
   * @param schoolUserDetailsService
   * @param schoolUsernamePwdAuthenticationProvider
   * @param customBasicAuthenticationEntryPointHandler
   * @param authenticationEntryPoint
   * @param customAccessDeniedHandler
   * @return The configured SecurityFilterChain bean.
   * @throws Exception if an error occurs during configuration.
   */
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

### 👥 Default Users
User Type | Role
Manager | MANAGER
Student | STUDENT
Teacher | TEACHER
Parent | PARENT

### ⚙️ Setup Instructions
- Checkout this branch:
```bash
git checkout basic-security
```
- Build and run:
```bash
mvn spring-boot:run
```