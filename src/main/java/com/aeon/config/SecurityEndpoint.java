/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeon.config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 *
 * @author jmacaraeg
 */
@Component
public class SecurityEndpoint implements AuthenticationEntryPoint {

    @Autowired
    private ResponseDataProcessor responseProcessor;
    
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, 
            AuthenticationException ae) throws IOException, ServletException {
        responseProcessor.sendErrorResponse(response, request, HttpStatus.UNAUTHORIZED.value(), ae);
    }
    
}
