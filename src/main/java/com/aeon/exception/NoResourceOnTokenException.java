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
public class NoResourceOnTokenException extends Exception {

    public NoResourceOnTokenException() {
    }

    public NoResourceOnTokenException(String message) {
        super(message);
    }
}
