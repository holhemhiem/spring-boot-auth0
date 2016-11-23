/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeon.util;

import com.aeon.constants.AppConstants;
import com.aeon.exception.InvalidTransactionException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author jmacaraeg
 */
public class LinkGenerator {
    public static List<String> generateLinkList(String role) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest servletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
        servletRequest.getRequestURI();
        List<String> linkList = new ArrayList<>();
        switch(role) {
            case AppConstants.ROLE_ADMIN:
                linkList.add(servletRequest.getRequestURI());
                break;
            case AppConstants.ROLE_USER:
                linkList.add(servletRequest.getRequestURI());
                break;
            default:
        }
        
        return linkList;
    }
}
