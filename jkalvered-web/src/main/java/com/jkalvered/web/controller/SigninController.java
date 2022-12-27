/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkalvered.web.controller;

import com.jkalvered.core.service.PersonneService;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author mini-john
 */
@Controller
public class SigninController {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
    @Autowired
    PersonneService personneService;
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(method = RequestMethod.GET, value = "/public/signup.do")
    public String getSignUp(ModelMap model) {
        LOGGER.info("La page de connection est demandée");
        return "public/signup";
    }

}
