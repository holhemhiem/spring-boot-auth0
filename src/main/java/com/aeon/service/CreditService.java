/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeon.service;

import com.aeon.exception.NoDataException;
import com.aeon.exception.InvalidTransactionException;
import com.aeon.model.Account;

/**
 *
 * @author jmacaraeg
 */
public interface CreditService {
    public Account updateCredit(String email, String transactionType, int value) throws NoDataException, InvalidTransactionException;
}
