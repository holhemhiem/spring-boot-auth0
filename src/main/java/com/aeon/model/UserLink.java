/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeon.model;

import com.aeon.constants.AppConstants;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author jmacaraeg
 */
public class UserLink {
    private final List<Link> links;
    
    private UserLink(UserLinkBuilder builder) {
        this.links = builder.links;
    }

    public List<Link> getLinks() {
        return links;
    }

    @Override
    public String toString() {
        return "UserLink{" + "links=" + links + '}';
    }
    
    private static class Link {
        private String url;
        private String method;

        public Link(String url, String method) {
            this.url = url;
            this.method = method;
        }

        @Override
        public String toString() {
            return "Link{" + "url=" + url + ", method=" + method + '}';
        }
    }
    
    public static class UserLinkBuilder {
        private String baseUrl;
        private List<Link> links;

        public UserLinkBuilder() {
        }
        
        public UserLinkBuilder setRequest(HttpServletRequest request) {
            this.baseUrl = getBaseUri(request);
            return this;
        }
        
        public UserLinkBuilder setLink(String url, String method) {
            if(this.links == null) {
                this.links = new ArrayList<>();
            }
            this.links.add(new Link(baseUrl + url, method));
            System.out.println("LINKZ :: " + this.links);
            return this;
        }
        
        private String getBaseUri(HttpServletRequest request) {
            StringBuilder url = new StringBuilder();
            url.append(request.getScheme());
            url.append(AppConstants.URI_ETC);
            url.append(request.getServerName());
            url.append(request.getContextPath());
            
            return url.toString();
        }
        
        public UserLink build() {
            return new UserLink(this);
        }
    }
}
