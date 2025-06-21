package com.ziad.school.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class RequestValidationBeforeFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null && StringUtils.startsWithIgnoreCase(header.trim() , "Basic ")) {
            byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
            byte[] decoded;
            try{
                decoded = Base64.getDecoder().decode(base64Token);
                String token = new String(decoded, StandardCharsets.UTF_8);
                int delim = token.indexOf(":");
                if(delim == -1)
                    throw new BadCredentialsException("Invalid Basic authentication token");
                if(token.substring(0,delim).toLowerCase().contains("test")){
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }
            }catch (IllegalArgumentException e){
                throw new BadCredentialsException("Failed to decode custom filter");
            }
        }
        filterChain.doFilter(request, response);
    }
}
