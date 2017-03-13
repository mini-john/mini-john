/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.controller.personne;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Boccara Jonathan
 */
@Controller
public class Main {
     private static final Logger logger = LoggerFactory.getLogger(Main.class);
     
     @RequestMapping("/private/index.do")
       public String getSignIn(ModelMap model){
           return "private/index";
       }
}
