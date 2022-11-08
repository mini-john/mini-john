/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.test.core.service;

import com.jkalvered.core.entite.Account;
import com.jkalvered.core.entite.Configuration;
import com.jkalvered.core.entite.Personne;
import com.jkalvered.core.service.NiddahService;
import com.jkalvered.library.enumeration.Origine;
import com.jkalvered.library.enumeration.RoleUser;
import com.jkalvered.library.enumeration.Sexe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jonat
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.jkalvered.test.core.configuration.AppConfigCore.class, com.jkalvered.test.core.configuration.HibernateConfiguration.class}, loader = AnnotationConfigContextLoader.class)
@Transactional
@Rollback(false)
public class NiddahServiceTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    NiddahService niddahService;

    @Before
    public void initialize() {
        LOGGER.info("Initialise=ation !!!!");
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

        personne.setConfiguration(configuration);
        personne.setAccount(account);
        account.setPersonne(personne);

        Personne personneRes = niddahService.add(personne, personne.getClass());
        LOGGER.info(personneRes.toString());
    }

    @Test
//    @Transactional
    public void testInsert() {
//        initialize();
        Personne personne = niddahService.getById(1L, Personne.class);
        LOGGER.info("login " + personne.getAccount().getLogin());

    }

}
