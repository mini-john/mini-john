/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkalvered.web.controller;

import com.jkalvered.core.dto.PersonneDto;
import com.jkalvered.core.entite.Account;
import com.jkalvered.core.entite.Configuration;
import com.jkalvered.core.entite.Personne;
import com.jkalvered.core.service.CrudService;
import com.jkalvered.core.service.PersonneService;
import com.jkalvered.library.enumeration.Origine;
import com.jkalvered.library.enumeration.RoleUser;
import com.jkalvered.library.enumeration.Sexe;
import com.jkalvered.library.enumeration.TypeCycle;
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
    @Autowired
    private PersonneService personneService;

    @PostConstruct
    public void init() {
        LOGGER.info("Instantiation du min controller");
        LOGGER.info("Rajout de l'utilisateur test");
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        Account account = new Account();
        account.setLogin("login");
        account.setMail("jonathan.boccara@gmail.com");
        account.setPassword("azerty");
        account.setRoleUser(RoleUser.Femme);

        Personne personne = new Personne();
        personne.setNom("Boccara");
        personne.setPrenom("Virginie");
        personne.setSexe(Sexe.Femme);
        Configuration configuration = new Configuration();
        configuration.setPersonne(personne);
        configuration.setOrigine(Origine.Sefarade);
        configuration.setElevation(elevation);
        configuration.setLatitude(latitude);
        configuration.setLocationName(locationName);
        configuration.setLongitude(longitude);
        configuration.setTimeZone(locationName);
        configuration.setDoMohDahouk(true);
        configuration.setPrichaHoutChani(true);
        configuration.setPrichaBenonitHovotDaat(true);
        configuration.setPrihaHovotYair(true);
        configuration.setPrichaOrZaroua(true);

        personne.setConfiguration(configuration);
        personne.setAccount(account);
        account.setPersonne(personne);
        personne.setTypeCycle(TypeCycle.LoKavoua);
        Personne personneRes = personneService.add(personne, personne.getClass());
        LOGGER.info(personneRes.toString());

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
