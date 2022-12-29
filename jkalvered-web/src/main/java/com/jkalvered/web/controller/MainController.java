/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkalvered.web.controller;

import com.jkalvered.core.dto.PersonneDto;
import com.jkalvered.core.entite.Personne;
import com.jkalvered.core.service.CrudService;
import javax.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author mini-john
 */
@Controller
public class MainController {

    private static final Logger LOGGER = LogManager.getLogger();
   
    @PostConstruct
    public void init() {
        LOGGER.info("Instantiation du min controller");
        

    }
    @Autowired
    private CrudService crudService;

    @RequestMapping(value = {"/public/index.do", "/", "/public/"})
    public ModelAndView index(ModelMap map) {
        LOGGER.info("La page index est demandée DJFKDH");
        
        PersonneDto personneDto= crudService.getById(1L, Personne.class, PersonneDto.class);
        map.put("personne", personneDto);
        return new ModelAndView("public/index",map);
    }

}
