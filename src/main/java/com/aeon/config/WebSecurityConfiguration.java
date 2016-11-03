/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeon.config;

import com.auth0.spring.security.api.Auth0SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 *
 * @author jmacaraeg
 */
@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfiguration extends Auth0SecurityConfig {
    @Autowired
    private SecurityEndpoint securityEndpoint;
    /**
     * Provides Auth0 API access
     */
    @Bean
    public Auth0Client auth0Client() {
        return new Auth0Client(clientId, issuer);
    }

    /**
     *  Our API Configuration - for Profile CRUD operations
     *
     *  Here we choose not to bother using the `auth0.securedRoute` property configuration
     *  and instead ensure any unlisted endpoint in our config is secured by default
     */
    @Override
    protected void authorizeRequests(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/", "/test/**").permitAll()
            .antMatchers(HttpMethod.GET, "/api/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
            .antMatchers(HttpMethod.OPTIONS, "/api/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
            .antMatchers(HttpMethod.POST, "/api/**").hasAnyAuthority("ROLE_ADMIN")
            .antMatchers(HttpMethod.PUT, "/api/**").hasAnyAuthority("ROLE_ADMIN")
            .antMatchers(HttpMethod.DELETE, "/api/**").hasAnyAuthority("ROLE_ADMIN")
            .anyRequest().authenticated()
            .and().exceptionHandling().authenticationEntryPoint(securityEndpoint);
    }
    
    String getAuthorityStrategy() {
       return super.authorityStrategy;
    }
}
