package com.etutor.microservices.core.gateway.config.jwt;

import com.etutor.microservices.core.gateway.service.TokenAuthenticationService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JwtAuthenticationContextFilter extends GenericFilterBean {

    private final TokenAuthenticationService tokenAuthenticationService;

    public JwtAuthenticationContextFilter(
        final TokenAuthenticationService tokenAuthenticationService) {
        this.tokenAuthenticationService = tokenAuthenticationService;
    }

    @Override
    public void doFilter(final ServletRequest request,
        final ServletResponse response,
        final FilterChain filterChain)
        throws IOException, ServletException {
        Authentication authentication = tokenAuthenticationService
            .getAuthentication((HttpServletRequest) request);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
