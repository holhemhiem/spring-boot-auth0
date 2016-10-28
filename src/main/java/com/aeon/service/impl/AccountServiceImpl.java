/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeon.service.impl;

import com.aeon.dao.AccountDao;
import com.aeon.exception.NoDataException;
import com.aeon.model.Account;
import com.aeon.model.Credit;
import com.aeon.service.AccountService;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jmacaraeg
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Override
    @Transactional
    public Account createAccount(String email, String name, String role) {
        Account account = new Account();
        account.setEmail(email);
        account.setName(name);
        account.setRole(role);
        account.setCredit(new Credit(1000, new Date(Calendar.getInstance().getTimeInMillis()), account));
        accountDao.saveOrUpdate(account);
        
        return account;
    }

    @Override
    @Transactional
    public Account getAccountByEmail(String email) throws NoDataException {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        criterionList.add(Restrictions.eq("email", email));
        try {
            return accountDao.getByCriteria(Account.class, criterionList).get(0);
        } catch ( NoDataException ex) {
            throw new NoDataException("No Data found for email :: " + email);
        }
    }

}
