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
public interface CreditService {
    public Account addCredit(String email, int value) throws NoDataException;
    public Account subtractCredit(String email, int value) throws NoDataException;
}
