/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeon.controller;

import com.aeon.constants.Auth0PrincipalKeys;
import com.aeon.exception.NoDataException;
import com.aeon.exception.NoResourceOnTokenException;
import com.aeon.model.Account;
import com.aeon.service.AccountService;
import com.aeon.util.Auth0PrincipalParser;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jmacaraeg
 */
@RestController
@RequestMapping(value = "/api")
public class ProfileController extends BaseController {
    
    @Autowired
    private AccountService accountService;
    
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public void getProfiles(HttpServletRequest request, HttpServletResponse response,
            final Principal principal) {
        try {
            String email = (String) Auth0PrincipalParser.getValue(principal, Auth0PrincipalKeys.EMAIL);
            String name = (String) Auth0PrincipalParser.getValue(principal, Auth0PrincipalKeys.NICKNAME);
            List<String> roles = (List<String>) Auth0PrincipalParser.getValue(principal, Auth0PrincipalKeys.ROLES);
            System.out.println("Roles :: " + roles);
            Account account = null;
            try {
                account = accountService.getAccountByEmail(email);
                sendJSONResponse(response, account);
            } catch (NoDataException ex) {
                System.out.println(ex);
                account = accountService.createAccount(email, name, roles.get(0));
                sendJSONResponse(response, account);
            }
        } catch (NoResourceOnTokenException ex) {
            System.out.println("Token does not contain the requested value :: " + ex);
        }
    }
}
