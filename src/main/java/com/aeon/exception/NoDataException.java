/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeon.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author jmacaraeg
 */
public class NoDataException extends Exception {

    public NoDataException() {
    }

    public NoDataException(String message) {
        super(message);
    }
    
}
