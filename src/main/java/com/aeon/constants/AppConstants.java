/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeon.constants;

/**
 *
 * @author jmacaraeg
 */
public class AppConstants {
    public static final String CREDIT_ADD = "add";
    public static final String CREDIT_SUBTRACT = "sub";
    public static final String CREDIT_CHIP_ADD = "addchip";
    public static final String CREDIT_CHIP_SUBTRACT = "subchip";
    
    public static final String PROFILE = "profile";
    
    //Roles
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    
    //URIs
    public static final String URI_SLASH = "/";
    public static final String URI_ETC = "://";
    public static final String URI_CREDIT_ADD = URI_SLASH + CREDIT_ADD;
    public static final String URI_CREDIT_SUBTRACT = URI_SLASH + CREDIT_SUBTRACT;
    public static final String URI_CREDIT_CHIP_ADD = URI_SLASH + CREDIT_CHIP_ADD;
    public static final String URI_CREDIT_CHIP_SUBTRACT = URI_SLASH + CREDIT_CHIP_SUBTRACT;
    public static final String URI_PROFILE = URI_SLASH + PROFILE;
}
