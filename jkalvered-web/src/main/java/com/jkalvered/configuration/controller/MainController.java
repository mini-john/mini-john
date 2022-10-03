/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkalvered.configuration.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
   

    @RequestMapping("/index.do")
    public ModelAndView index() {
        LOGGER.info("La page index est demandée");
        System.out.println("com.jkalvered.configuration.controller.MainController.index()");
        return new ModelAndView("/index");
    }

  

}
