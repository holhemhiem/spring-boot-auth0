/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeon.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jmacaraeg
 */
@Entity
@Table(name = "M_CREDIT")
public class Credit {
    private transient int accountId;
    private int accountBalance;
    private Date lastTransactionDate;
    private transient Account account;

    public Credit() {
    }

    public Credit(int accountBalance, Date lastTransactionDate, Account account) {
        this.accountBalance = accountBalance;
        this.lastTransactionDate = lastTransactionDate;
        this.account = account;
    }

    @Id
    @Column(name = "ACCOUNT_ID")
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Column(name = "BALANCE")
    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_TRANSACTION_DATE")
    public Date getLastTransactionDate() {
        return lastTransactionDate;
    }

    public void setLastTransactionDate(Date lastTransactionDate) {
        this.lastTransactionDate = lastTransactionDate;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACCOUNT_ID")
    @MapsId
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Credit{" + "accountId=" + accountId + ", accountBalance=" + accountBalance + ", lastTransactionDate=" + lastTransactionDate + '}';
    }
    
}
