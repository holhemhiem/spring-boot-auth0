/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeon.controller;


import com.aeon.config.ResponseDataProcessor;
import com.aeon.model.Account;
import com.aeon.service.AccountService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jmacaraeg
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private ResponseDataProcessor responseProcessor;
    
    @Autowired
    private AccountService accountService;
    
    @RequestMapping("/profile/{name}")
    public void getProfile(@PathVariable("name") String name,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        Account account = accountService.getAccountByName(name);
        responseProcessor.sendResponse(response, request, account);
    }
}
