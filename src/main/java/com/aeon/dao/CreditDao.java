/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeon.dao;

import com.aeon.exception.NoDataException;
import com.aeon.model.Credit;

/**
 *
 * @author jmacaraeg
 */
public interface CreditDao extends GenericDao<Credit> {
    public void updateCredit(int id, int value, String action) throws NoDataException;
}
