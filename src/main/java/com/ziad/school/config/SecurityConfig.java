package com.ziad.school.config;

import com.ziad.school.exceptionhandling.CustomAccessDeniedHandler;
import com.ziad.school.exceptionhandling.CustomBasicAuthenticationEntryPoint;
import com.ziad.school.filter.*;
import com.ziad.school.model.base.SystemRole;
import com.ziad.school.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@RequiredArgsConstructor
@Configuration
@Profile("default")
public class SecurityConfig {
    //This is a Reminder that this instructions inspired by the spring security course
    //This was build first for mvc to continue in mvc go to the course section 7 the part where the login starts

    private final PersonRepository personRepository;

    private final CustomBasicAuthenticationEntryPoint customBasicAuthenticationEntryPoint;

    private final AuthenticationEntryPoint authenticationEntryPoint;

    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    /**
     * Configures the Spring Security filter chain for the application.
     * It sets up authentication, authorization rules, CSRF protection, custom filters, CORS, and exception handling.
     *
     * @param http The HttpSecurity object used to configure security for HTTP requests.
     * @return The configured SecurityFilterChain bean.
     * @throws Exception if an error occurs during configuration.
     */
    @Bean
    SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            CSRFCookieFilter csrfCookieFilter,
            RequestValidationBeforeFilter requestValidationBeforeFilter,
            JWTTokenValidatorFilter jwtTokenValidatorFilter,
            JWTTokenGeneratorFilter jwtTokenGeneratorFilter,
            AuthoritiesLoggingAfterFilter authoritiesLoggingAfterFilter,
            AuthoritiesLoggingAtFilter authoritiesLoggingAtFilter,
            SchoolUserDetailsService schoolUserDetailsService,
            SchoolUsernamePwdAuthenticationProvider schoolUsernamePwdAuthenticationProvider
    ) throws Exception {

        // Define path patterns for role-based access
        var anyUser = new String[]{"/api/users/login", "/api/users"};
        var anyAdmin = new String[]{"/api/students", "/api/parents", "/api/teachers"};
        var anyStudent = new String[]{"/api/students/**"};
        var anyParent = new String[]{"/api/parents/**"};
        var anyTeacher = new String[]{"/api/teachers/**"};
        var swagger = new String[]{"/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/api-docs/**"};

        // CSRF handler to support token as a request attribute
        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();

        http
                // Use custom user details service and authentication provider
                .userDetailsService(schoolUserDetailsService)
                .authenticationProvider(schoolUsernamePwdAuthenticationProvider)

                // Save the SecurityContext on authentication (even for stateless sessions)
//                .securityContext(contextConfig -> contextConfig.requireExplicitSave(false))

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
                        .csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
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
                                .requestMatchers(anyAdmin).hasAuthority(SystemRole.ROLE_MANAGER.toString())
                                .requestMatchers(anyStudent).hasAuthority(SystemRole.ROLE_STUDENT.toString())
                                .requestMatchers(anyParent).hasAuthority(SystemRole.ROLE_PARENT.toString())
                                .requestMatchers(anyTeacher).hasAuthority(SystemRole.ROLE_TEACHER.toString())
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
                        .authenticationEntryPoint(customBasicAuthenticationEntryPoint)
                );

        // Build and return the security filter chain
        return http.build();
    }

    @Bean
    SchoolUserDetailsService schoolUserDetailsService() {
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
