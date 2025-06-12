package com.ziad.school.config;

import com.ziad.school.exceptionhandling.CustomAccessDeniedHandler;
import com.ziad.school.repository.PersonRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
@Profile("default")
public class SecurityConfig {

    //This is a Reminder that this instructions inspired by the spring security course
    //This was build first for mvc to continue in mvc go to the course section 7 the part where the login starts
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http , AuthenticationEntryPoint authenticationEntryPoint) throws Exception {
        http.cors(corsConfig -> corsConfig.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        var config = new CorsConfiguration();
                        config.setAllowCredentials(true);
                        config.addAllowedOrigin("*");
                        config.addAllowedHeader("*");
                        config.addAllowedMethod("*");
                        config.setMaxAge(3600L);
                        return config;
                    }
                }))
                .requiresChannel(requiresChannel -> requiresChannel.anyRequest().requiresInsecure())//For HTTP
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        (requests) -> requests
                                .requestMatchers("/api/users/**" , "/swagger-ui.html",
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**",
                                        "/swagger-resources/**",
                                        "/api-docs/**").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(hbc -> hbc.authenticationEntryPoint(authenticationEntryPoint))
                .exceptionHandling(ehc -> ehc.accessDeniedHandler(new CustomAccessDeniedHandler()).accessDeniedPage("/denied"));
        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService(PersonRepository personRepository) {
        return new SchoolUserDetailsService(personRepository);
    }

    @Bean
    AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService , PasswordEncoder passwordEncoder) {
        return new SchoolUsernamePwdAuthenticationProvider(userDetailsService, passwordEncoder);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
