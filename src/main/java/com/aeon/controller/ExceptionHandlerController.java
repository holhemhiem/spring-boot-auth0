/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeon.controller;


import com.aeon.config.ResponseDataProcessor;
import com.aeon.exception.NoDataException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author jmacaraeg
 */
@ControllerAdvice
public class ExceptionHandlerController {
    @Autowired
    private ResponseDataProcessor responseProcessor;
    
    @ExceptionHandler(NoDataException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void noDataExceptionHandler(NoDataException ex, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        responseProcessor.sendErrorResponse(response, request, HttpStatus.NOT_FOUND.value(), ex);
    }
    
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void accessDeniedExceptionHandler(AccessDeniedException ex, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        responseProcessor.sendErrorResponse(response, request, HttpStatus.UNAUTHORIZED.value(), ex);
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public void genericExceptionHandler(Exception ex, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        responseProcessor.sendErrorResponse(response, request, HttpStatus.SERVICE_UNAVAILABLE.value(), ex);
    }
}
