/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeon.controller;


import com.aeon.config.ResponseDataProcessor;
import com.aeon.constants.Auth0PrincipalKeys;
import com.aeon.exception.NoDataException;
import com.aeon.model.Account;
import com.aeon.service.AccountService;
import com.aeon.service.CreditService;
import com.aeon.util.Auth0PrincipalParser;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jmacaraeg
 */
@RestController
@RequestMapping(value = "/api")
public class ProfileController {
    @Autowired
    private ResponseDataProcessor responseProcessor;
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private CreditService creditService;
    
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public void getProfiles(HttpServletRequest request, HttpServletResponse response,
            final Principal principal) throws Exception {

        String email = (String) Auth0PrincipalParser.getValue(principal, Auth0PrincipalKeys.EMAIL);
        String name = (String) Auth0PrincipalParser.getValue(principal, Auth0PrincipalKeys.NICKNAME);
        List<String> roles = (List<String>) Auth0PrincipalParser.getValue(principal, Auth0PrincipalKeys.ROLES);
        
        Account account;
        try {
            account = accountService.getAccountByEmail(email);
            System.out.println(" :: " + account.toString());
            responseProcessor.sendResponse(response, request, account);
        } catch (NoDataException ex) {
            account = accountService.createAccount(email, name, roles.get(0));
            System.out.println(" :: " + account.toString());
            responseProcessor.sendResponse(response, request, account);
        }
    }
    
    @RequestMapping(value = "/profile/action/{action}", method = RequestMethod.PUT)
    public void updateCredit(HttpServletRequest request, HttpServletResponse response,
            final Principal principal, 
            @PathVariable("action") String action,
            @RequestParam(name = "v") int val) throws Exception {
        
        String email = (String) Auth0PrincipalParser.getValue(principal, Auth0PrincipalKeys.EMAIL);
        
        Account account = creditService.updateCredit(email, action, val);
        
        responseProcessor.sendResponse(response, request, account);
    }
}
