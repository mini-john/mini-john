/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.controller;

import com.niddah.core.entity.Account;
import com.niddah.core.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author mini-john
 */
@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    @Autowired
    AccountService accountService;

    @RequestMapping("/index.do")
    public ModelAndView index() {
        logger.info("La page index est demandée");
        return new ModelAndView("index");
    }

  

}
