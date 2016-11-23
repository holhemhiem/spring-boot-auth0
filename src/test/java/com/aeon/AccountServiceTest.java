/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeon;

import com.aeon.constants.AppConstants;
import com.aeon.exception.NoDataException;
import com.aeon.exception.InvalidTransactionException;
import com.aeon.model.Account;
import com.aeon.service.AccountService;
import com.aeon.service.CreditService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author jmacaraeg
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private CreditService creditService;
    
    @Test
    public void accountServiceIsNotNull() {
        Assert.assertNotNull(accountService);
    }
    
    @Test
    public void creditServiceIsNotNull() {
        Assert.assertNotNull(creditService);
    }
    
    @Test
    public void testGetAccountByEmailWithResult() {
        try {
            accountService.getAccountByEmail("jmacaraeg@aeon.com");
        } catch (NoDataException ex) {
            System.out.println(ex);
        }
    }
    
    @Test
    public void testGetAccountByEmailWithNoResult() {
        try {
            accountService.getAccountByEmail("test@aeon.com");
        } catch (NoDataException ex) {
            System.out.println(ex);
        }
    }
    
//    @Test
    public void testAddAccount() {
        Account account = accountService.createAccount("testing100@test.com", "testing100", "ROLE_ADMIN");
        System.out.println(" :: " + account.toString());
    }
    
    @Test
    public void addCredit() {
        try {
            int valueToAdd = 100;
            Account account = accountService.getAccountByEmail("testing43@test.com");
            int currBalance = account.getCredit().getAccountBalance();
            
            Account afterUpdateAccount = creditService.updateCredit("testing43@test.com", AppConstants.CREDIT_ADD, valueToAdd);
            int afterUpdateBalance = afterUpdateAccount.getCredit().getAccountBalance();
            
            Assert.assertEquals(currBalance + valueToAdd, afterUpdateBalance);
        } catch (NoDataException | InvalidTransactionException ex) {
            System.out.println(ex);
        }
    }
    
    @Test
    public void subtractCredit() {
        try {
            int valueToSubtract = 100;
            Account account = accountService.getAccountByEmail("testing43@test.com");
            int currBalance = account.getCredit().getAccountBalance();
            
            Account afterUpdateAccount = creditService.updateCredit("testing43@test.com", AppConstants.CREDIT_SUBTRACT, valueToSubtract);
            int afterUpdateBalance = afterUpdateAccount.getCredit().getAccountBalance();
            
            Assert.assertEquals(currBalance - valueToSubtract, afterUpdateBalance);
        } catch (NoDataException | InvalidTransactionException ex) {
            System.out.println(ex);
        }
    }
    
    @Test
    public void addNegativeCredit() {
        boolean hasException = false;
        try {
            
            int valueToSubtract = -100;
            Account afterUpdateAccount = creditService.updateCredit("testing43@test.com", AppConstants.CREDIT_SUBTRACT, valueToSubtract);
            
        } catch (NoDataException | InvalidTransactionException ex) {
            hasException = true;
            System.out.println(ex);
        }
        
        Assert.assertTrue(hasException);
    }
    
    @Test
    public void notValidTransaction() {
        boolean hasException = false;
        try {
            int valueToSubtract = 100;
            Account afterUpdateAccount = creditService.updateCredit("testing43@test.com", "delete", valueToSubtract);
        } catch (NoDataException | InvalidTransactionException ex) {
            hasException = true;
            System.out.println(ex);
        }
        
        Assert.assertTrue(hasException);
    }
}
