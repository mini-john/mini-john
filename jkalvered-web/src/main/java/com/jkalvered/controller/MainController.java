/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkalvered.controller;

import com.jkalvered.core.entite.Account;
import com.jkalvered.core.service.CrudService;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger LOGGER = LogManager.getLogger();
    @Autowired
    private CrudService crudService;

    @RequestMapping("/index.do")
    public ModelAndView index() {
        LOGGER.info("La page index est demandée");
        System.out.println("com.jkalvered.configuration.controller.MainController.index()");
        Account account = new Account();
        account.setLogin(new Date().toString());
        account = crudService.add(account, account.getClass());
        LOGGER.info(account);
        return new ModelAndView("/index");
    }

}
