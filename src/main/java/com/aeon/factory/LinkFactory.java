/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeon.factory;

import com.aeon.constants.AppConstants;
import com.aeon.model.UserLink;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author jmacaraeg
 */
public class LinkFactory {
    public static UserLink getLinks(HttpServletRequest request, String role) {
        System.out.println(" :: " + request);
        System.out.println(" :: " + role);

        switch(role) {
            case AppConstants.ROLE_ADMIN:
                return new UserLink.UserLinkBuilder()
                        .setRequest(request)
                        .setLink(AppConstants.URI_PROFILE, RequestMethod.GET.name())
                        .setLink(AppConstants.URI_CREDIT_ADD, RequestMethod.PUT.name())
                        .setLink(AppConstants.URI_CREDIT_SUBTRACT, RequestMethod.PUT.name())
                        .setLink(AppConstants.URI_CREDIT_CHIP_ADD, RequestMethod.PUT.name())
                        .setLink(AppConstants.URI_CREDIT_CHIP_SUBTRACT, RequestMethod.PUT.name())
                        .build();
            case AppConstants.ROLE_USER:
                return new UserLink.UserLinkBuilder()
                        .setRequest(request)
                        .setLink(AppConstants.URI_PROFILE, RequestMethod.GET.name())
                        .setLink(AppConstants.URI_CREDIT_CHIP_ADD, RequestMethod.PUT.name())
                        .setLink(AppConstants.URI_CREDIT_CHIP_SUBTRACT, RequestMethod.PUT.name())
                        .build();
            default:
                return new UserLink.UserLinkBuilder()
                        .setRequest(request)
                        .setLink(AppConstants.URI_PROFILE, RequestMethod.GET.name())
                        .build();
        }
    }
}
