/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.web.controller.moncompte;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.ZoneId;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = {"/private/moncompte/halaha.do"})
    public String halaha(ModelMap map) {
        LOGGER.info("La page index est demandée DJFKDH");

        return "private/moncompte/halaha";
    }

    @RequestMapping(value = {"/private/moncompte/localisation.do"})
    public ModelAndView localisation(ModelMap map) throws JsonProcessingException {
        LOGGER.info("La page de la gestion de la localisation est demandée ");
        Set<String> allTimezone = ZoneId.getAvailableZoneIds();
        ObjectMapper mapper = new ObjectMapper();
        String allTimeZoneString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(allTimezone);
        map.put("allTimeZone", allTimeZoneString);

        return new ModelAndView("private/moncompte/localisation", map);
    }
}
