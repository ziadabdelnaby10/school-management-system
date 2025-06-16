package com.ziad.school.config;

import com.ziad.school.exceptionhandling.CustomAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("production")
public class SecurityProductionConfig {

//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationEntryPoint authenticationEntryPoint) throws Exception {
//        http.requiresChannel(requiresChannel -> requiresChannel.anyRequest().requiresSecure())//For HTTPS
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(
//                        (requests) -> requests
//                                .requestMatchers("/api/users/**").permitAll()
//                                .anyRequest().authenticated()
//                )
//                .formLogin(Customizer.withDefaults())
//                .httpBasic(hbc -> hbc.authenticationEntryPoint(authenticationEntryPoint))
//                .exceptionHandling(ehc -> ehc.accessDeniedHandler(new CustomAccessDeniedHandler()).accessDeniedPage("/denied"));
//        return http.build();
//    }

//    @Bean
//    UserDetailsService userDetailsService(DataSource dataSource) {
////        UserDetails user = User.withUsername("user").password("{noop}12345").authorities("read").build();
////        UserDetails admin = User.withUsername("admin")
////                .password("{bcrypt}$2a$12$poqBFUojTjgdrvyXDrltyObz4.Fis6nsLSCWK6ZpM5E6pE0fzHjra")
////                .authorities("admin").build();
//        return new JdbcUserDetailsManager(dataSource);
//    }

//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }

//    @Bean
//    public CompromisedPasswordChecker compromisedPasswordChecker() {
//        return new HaveIBeenPwnedRestApiPasswordChecker();
//    }
}
