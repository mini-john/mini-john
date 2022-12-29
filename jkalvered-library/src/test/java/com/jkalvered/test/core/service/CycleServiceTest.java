/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.test.core.service;

import com.jkalvered.core.dto.Localisation;
import com.jkalvered.core.dto.PersonneDto;
import com.jkalvered.core.entite.Account;
import com.jkalvered.core.entite.Configuration;
import com.jkalvered.core.entite.Personne;
import com.jkalvered.core.service.CycleService;
import com.jkalvered.core.service.PersonneService;
import com.jkalvered.library.date.JkalDate;
import com.jkalvered.library.enumeration.Origine;
import com.jkalvered.library.enumeration.RoleUser;
import com.jkalvered.library.enumeration.Sexe;
import com.jkalvered.library.enumeration.TypeCycle;
import java.text.ParseException;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jonat
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.jkalvered.test.core.configuration.AppConfigCore.class, com.jkalvered.test.core.configuration.HibernateConfiguration.class}, loader = AnnotationConfigContextLoader.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CycleServiceTest {

    @Autowired
    CycleService cycleService;
    @Autowired
    PersonneService personneService;
    private static final Logger LOGGER = LogManager.getLogger();

    @Before
    public void initialize() {
        LOGGER.info("Initialisation !!!!");
        PersonneDto personneLoad = personneService.getPersonneByLogin("login");
        if (personneLoad == null) {
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
            Personne personneRes = cycleService.add(personne, personne.getClass());
            LOGGER.info(personneRes.toString());
        } else {
            LOGGER.info("Il y a deja l'utilisateur test dans la base");
        }
    }

    @Test
    @Order(1)
    public void testInsert() {
        //   initialize();
        LOGGER.info("Test in insertion personne");
        Personne personne = cycleService.getById(1L, Personne.class);
        LOGGER.info("login " + personne.getAccount().getLogin());
        LOGGER.info("Origine " + personne.getConfiguration().getOrigine());

    }

    @Test
    @Order(2)
    public void test1AddCycleLoKavoua() throws ParseException {
        LOGGER.info("Test niddah insert");
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        String timeZone = "Europe/Paris";
        Localisation localisation = new Localisation();
        localisation.setElevation(elevation);
        localisation.setLongitude(longitude);
        localisation.setLatitude(latitude);
        localisation.setLocationName(locationName);
        localisation.setTimeZone(timeZone);
        localisation.setLocalited(Boolean.TRUE);
        Date dateVue = JkalDate.parseDateWithHour("13/11/2022 12:00");
        cycleService.addCycleVessetLoKavoua(1L, dateVue, timeZone, localisation);
    }

    @Test
    @Order(2)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void test2AddPrichotSpecial() throws ParseException {
        LOGGER.info("Test niddah insert");
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        String timeZone = "Europe/Paris";
        Localisation localisation = new Localisation();
        localisation.setElevation(elevation);
        localisation.setLongitude(longitude);
        localisation.setLatitude(latitude);
        localisation.setLocationName(locationName);
        localisation.setTimeZone(timeZone);
        localisation.setLocalited(Boolean.TRUE);
        Date dateVue = JkalDate.parseDateWithHour("13/11/2022 12:00");
        cycleService.addCycleVessetLoKavoua(1L, dateVue, timeZone, localisation);
        cycleService.addPrichotSpecial(1L, 1L, localisation);
        dateVue = JkalDate.parseDateWithHour("13/12/2022 12:00");
        cycleService.addCycleVessetLoKavoua(1L, dateVue, timeZone, localisation);
        cycleService.addPrichotSpecial(1L, 2L, localisation);
    }
}
