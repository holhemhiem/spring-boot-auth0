/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeon.service;

import com.aeon.exception.NoDataException;
import com.aeon.model.Account;

/**
 *
 * @author jmacaraeg
 */
public interface AccountService {
    public Account createAccount(String email, String name, String role);
    public Account getAccountByEmail(String email) throws NoDataException;
    public Account getAccountByName(String name) throws NoDataException;
}
