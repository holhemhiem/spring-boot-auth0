/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeon.model;

import java.util.Calendar;

/**
 *
 * @author jmacaraeg
 */
public class ErrorResponse extends Response {
    private String error;

    public ErrorResponse() {
        super();
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
}
