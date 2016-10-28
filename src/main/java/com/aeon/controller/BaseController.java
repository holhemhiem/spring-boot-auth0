/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aeon.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

/**
 *
 * @author jmacaraeg
 */
@Controller
public class BaseController {
    @Autowired
    Gson gson;
    
    public void sendJSONResponse(HttpServletResponse response, Object obj) {
        try {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            PrintWriter pw = response.getWriter();
            pw.write(gson.toJson(obj));
        } catch (IOException ex) {
            System.out.println(" :: " + ex);
        }
    }
}
