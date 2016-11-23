/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeon.exception;

/**
 *
 * @author jmacaraeg
 */
public class UnsupportedOperation extends Exception {

    public UnsupportedOperation() {
    }

    public UnsupportedOperation(String message) {
        super(message);
    }

    public UnsupportedOperation(String message, Throwable cause) {
        super(message, cause);
    }
}
