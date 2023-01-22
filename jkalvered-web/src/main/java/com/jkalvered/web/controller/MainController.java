/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkalvered.web.controller;

import com.jkalvered.core.service.CrudService;
import javax.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author mini-john
 */
@Controller
public class MainController {

    private static final Logger LOGGER = LogManager.getLogger();
   
    @PostConstruct
    public void init() {
        LOGGER.info("Instantiation du MainController");
        

    }
    @Autowired
    private CrudService crudService;

    @RequestMapping(value = {"/public/index.do", "/", "/public/"})
    public String index(ModelMap map) {
        LOGGER.info("La page index est demandée DJFKDH");
        
        
        return "public/index";
    }

}
