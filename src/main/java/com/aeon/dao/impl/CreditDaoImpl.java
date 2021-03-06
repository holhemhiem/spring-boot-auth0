/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeon.dao.impl;

import com.aeon.constants.AppConstants;
import com.aeon.dao.CreditDao;
import com.aeon.exception.NoDataException;
import com.aeon.model.Credit;
import java.util.Calendar;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jmacaraeg
 */
@Repository
public class CreditDaoImpl extends GenericDaoImpl<Credit> implements CreditDao {

    @Override
    public void updateCredit(int id, int value, String action) throws NoDataException {
        Credit credit = get(Credit.class, id);
        int newCredit = credit.getAccountBalance();
        int newChips = credit.getChips();
        switch (action) {
            case AppConstants.CREDIT_ADD:
                newCredit = credit.getAccountBalance() + value;
                break;
            case AppConstants.CREDIT_SUBTRACT:
                newCredit = credit.getAccountBalance() - value;
                break;
            case AppConstants.CREDIT_CHIP_ADD:
                newChips = credit.getChips() + value;
                newCredit = credit.getAccountBalance() - (AppConstants.CONVERT_RATE * value);
                break;
            case AppConstants.CREDIT_CHIP_SUBTRACT:
                newChips = credit.getChips() - value;
                break;
        }
        
        credit.setAccountBalance(newCredit);
        credit.setChips(newChips);
        credit.setLastTransactionDate(Calendar.getInstance().getTime());
        
        saveOrUpdate(credit);
    }
}
