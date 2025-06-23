package com.ziad.school.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@Component
public class AuthoritiesLoggingAtFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Authentication Validation is in progress");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
