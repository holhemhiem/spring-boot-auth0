/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeon.config;

import com.aeon.factory.LinkFactory;
import com.aeon.model.Account;
import com.aeon.model.DataResponse;
import com.aeon.model.ErrorResponse;
import com.aeon.model.UserLink;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;


/**
 *
 * @author jmacaraeg
 */
public class ResponseDataProcessor {
    @Autowired
    private Gson gsonBuilder;
    
    public void sendErrorResponse(HttpServletResponse response, HttpServletRequest request,
            int statusCode, Exception ex) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        
        ErrorResponse errorMsg = new ErrorResponse();
        errorMsg.setStatus(statusCode);
        errorMsg.setRequestUrl(request.getRequestURI());
        errorMsg.setError(ex.getMessage());
        
        PrintWriter pw = response.getWriter();
        pw.write(gsonBuilder.toJson(errorMsg));
    }
    
    public void sendResponse(HttpServletResponse response, HttpServletRequest request,
            Object data) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        
        DataResponse dataResponse = new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setRequestUrl(request.getRequestURI());
        
        if(data.getClass().getName().equals(Account.class.getName())) {
            UserLink userLink = LinkFactory.getLinks(request, ((Account) data).getRole());
            ((Account) data).setLinks(userLink);
        }
        
        dataResponse.setData(data);
        
        PrintWriter pw = response.getWriter();
        pw.write(gsonBuilder.toJson(dataResponse));
    }
}
