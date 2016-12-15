/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeon.service.impl;

import com.aeon.constants.AppConstants;
import com.aeon.dao.CreditDao;
import com.aeon.exception.NoDataException;
import com.aeon.exception.InvalidTransactionException;
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
    public Account updateCredit(String email, String transactionType, int value) throws NoDataException, InvalidTransactionException {
        Account account = getAccountByEmail(email);
        
        if(value > 0) {
            switch(transactionType) {
                case AppConstants.CREDIT_ADD:
                    creditDao.updateCredit(account.getAccountId(), value, AppConstants.CREDIT_ADD);
                    break;
                case AppConstants.CREDIT_SUBTRACT:
                    creditDao.updateCredit(account.getAccountId(), value, AppConstants.CREDIT_SUBTRACT);
                    break;
                case AppConstants.CREDIT_CHIP_ADD:
                    creditDao.updateCredit(account.getAccountId(), value, AppConstants.CREDIT_CHIP_ADD);
                    break;
                case AppConstants.CREDIT_CHIP_SUBTRACT:
                    creditDao.updateCredit(account.getAccountId(), value, AppConstants.CREDIT_CHIP_SUBTRACT);
                    break;
                default:
                    throw new InvalidTransactionException("Unsupported Transaction.");
            }
        } else {
            throw new InvalidTransactionException("Invalid value supplied.");
        }
        
        return account;
    }
    
    private Account getAccountByEmail(String email) throws NoDataException {
        return accountService.getAccountByEmail(email);
    }
}
