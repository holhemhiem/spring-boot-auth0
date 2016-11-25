/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeon.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author jmacaraeg
 */
@Entity(name = "M_USER_PROFILE")
@Table(name = "M_USER_PROFILE")
public class Account implements Serializable {
    private transient int accountId;
    private String email;
    private String name;
    private String role;
    private UserLink links;
    private Credit credit;
    
    @Id
    @GeneratedValue
    @Column(name = "ACCOUNT_ID")
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Column(name = "EMAIL", unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "GIVEN_NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "ACCESS_TYPE")
    public String getRole() {
        return role;
    }

    public void setRole(String accessType) {
        this.role = accessType;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }
    
    @Transient
    @JsonSerialize
    public UserLink getLinks() {
        return links;
    }
    
    public void setLinks(UserLink links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "Account{" + "accountId=" + accountId + ", email=" + email + ", name=" + name + ", role=" + role + ", links=" + links + ", credit=" + credit + '}';
    }
    
}
