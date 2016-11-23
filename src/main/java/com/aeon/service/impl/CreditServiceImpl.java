/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeon.service.impl;

import com.aeon.constants.AppConstants;
import com.aeon.dao.CreditDao;
import com.aeon.exception.NoDataException;
import com.aeon.model.Account;
import com.aeon.service.AccountService;
import com.aeon.service.CreditService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jmacaraeg
 */
@Service
public class CreditServiceImpl implements CreditService {
    
    @Autowired
    private CreditDao creditDao;
    
    @Autowired
    private AccountService accountService;
    
    @Transactional
    @Override
    public Account addCredit(String email, int value) throws NoDataException {
        Account account = getAccountByEmail(email);
        creditDao.updateCredit(account.getAccountId(), value, AppConstants.CREDIT_ADD);
        
        return account;
    }

    @Transactional
    @Override
    public Account subtractCredit(String email, int value) throws NoDataException {
        Account account = getAccountByEmail(email);
        creditDao.updateCredit(account.getAccountId(), value, AppConstants.CREDIT_SUBTRACT);
        
        return account;
    }
    
    private Account getAccountByEmail(String email) throws NoDataException {
        Account account = accountService.getAccountByEmail(email);
        return account;
    }
    
}
