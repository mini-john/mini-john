/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.web.controller.moncompte;

import javax.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author jonat
 */
@Controller
public class MonCompteController {

    private static final Logger LOGGER = LogManager.getLogger();

    @PostConstruct
    public void init() {
        LOGGER.info("Instantiation du MonCompteController");
    }
    
     @RequestMapping(value = {"/private/moncompte/index.do"})
    public String index(ModelMap map) {
        LOGGER.info("La page index est demandée DJFKDH");
        
        
        return "private/moncompte/index";
    }
}
