/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeon.util;

import com.aeon.exception.NoResourceOnTokenException;
import com.auth0.spring.security.api.Auth0JWTToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author jmacaraeg
 */
public class Auth0PrincipalParser {

    public static Map<String, Object> convertToMap(Principal principal) {
        ObjectMapper objMapper = new ObjectMapper();
        Map<String, Object> map = objMapper.convertValue(((Auth0JWTToken) principal).getDetails(), Map.class);
        
        return map;
    }

    public static Map<String, Object> getValues(Principal principal, String... keys) throws NoResourceOnTokenException {
        Map<String, Object> returnMap = new HashMap<>();
        ObjectMapper objMapper = new ObjectMapper();
        Map<String, Object> map = objMapper.convertValue(((Auth0JWTToken) principal).getDetails(), Map.class);
        
        map.entrySet().stream().forEach((entrySet) -> {
            for(String key : keys) {
                if(key.equals(entrySet.getKey())) {
                    returnMap.put(entrySet.getKey(), entrySet.getValue());
                }
            }
        });
        
        if(returnMap.size() > 0) {
            return returnMap;
        } else {
            throw new NoResourceOnTokenException("Keys do not exist.");
        }
    }

    public static Object getValue(Principal principal, String key) throws NoResourceOnTokenException {
        ObjectMapper objMapper = new ObjectMapper();
        Map<String, Object> map = objMapper.convertValue(((Auth0JWTToken) principal).getDetails(), Map.class);
        
        if(map.containsKey(key)) {
            return map.get(key);
        } else {
            throw new NoResourceOnTokenException(key + " does not exist.");
        }
    }
}
