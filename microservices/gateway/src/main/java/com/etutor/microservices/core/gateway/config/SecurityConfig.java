package com.etutor.microservices.core.gateway.config;

import com.etutor.microservices.core.gateway.config.jwt.JwtAuthenticationContextFilter;
import com.etutor.microservices.core.gateway.config.jwt.JwtAuthenticationFilter;
import com.etutor.microservices.core.gateway.service.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(-20)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final TokenAuthenticationService tokenAuthenticationService;

    public SecurityConfig(@Qualifier("userServiceCustomImplementation") final UserDetailsService userDetailsService,
                          final TokenAuthenticationService tokenAuthenticationService) {
        this.userDetailsService = userDetailsService;
        this.tokenAuthenticationService = tokenAuthenticationService;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/login").permitAll()
                .antMatchers(HttpMethod.POST, "/user").permitAll()
                .antMatchers(HttpMethod.POST, "/api/user/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/user/**").permitAll()
                .anyRequest()
                .authenticated();

        JwtAuthenticationContextFilter jJwtAuthenticationContextFilter = new JwtAuthenticationContextFilter(tokenAuthenticationService);
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(tokenAuthenticationService);
        jwtAuthenticationFilter.setFilterProcessesUrl("/api/login");
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager());
        http.addFilterBefore(jJwtAuthenticationContextFilter, UsernamePasswordAuthenticationFilter.class)
                //check the presence of JWT in header
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationTrustResolver authenticationTrustResolver() {
        return new AuthenticationTrustResolverImpl();
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        final FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
}