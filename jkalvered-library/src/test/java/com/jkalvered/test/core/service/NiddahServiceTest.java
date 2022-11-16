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
import com.jkalvered.core.service.NiddahService;
import com.jkalvered.core.service.PersonneService;
import com.jkalvered.library.date.JkalDate;
import com.jkalvered.library.enumeration.Origine;
import com.jkalvered.library.enumeration.RoleUser;
import com.jkalvered.library.enumeration.Sexe;
import com.jkalvered.library.exception.NiddahDataException;
import java.text.ParseException;
import java.util.Date;
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

/**
 *
 * @author jonat
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.jkalvered.test.core.configuration.AppConfigCore.class, com.jkalvered.test.core.configuration.HibernateConfiguration.class}, loader = AnnotationConfigContextLoader.class)

@Rollback(false)
public class NiddahServiceTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    NiddahService niddahService;
    @Autowired
    PersonneService personneService;

    @Before

    public void initialize() {
        LOGGER.info("Initialise=ation !!!!");
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

            personne.setConfiguration(configuration);
            personne.setAccount(account);
            account.setPersonne(personne);

            Personne personneRes = niddahService.add(personne, personne.getClass());
            LOGGER.info(personneRes.toString());
        } else {
            LOGGER.info("Il y a deja l'utilisateur test dans la base");
        }
    }

    @Test

    public void testInsert() {
        //   initialize();
        Personne personne = niddahService.getById(1L, Personne.class);
        LOGGER.info("login " + personne.getAccount().getLogin());
        LOGGER.info("Origine " + personne.getConfiguration().getOrigine());

    }

    @Test
    @Rollback(false)
    public void testNiddahInsert() throws ParseException {
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
        Date dateDernierRappor = JkalDate.parseDateWithHour("12/11/2022 22:00");
        niddahService.addPeriodNiddah(1L, dateVue, dateDernierRappor, timeZone, localisation);
    }

    @Test
    @Rollback(false)
    public void testNiddahKo() throws ParseException, NiddahDataException {
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
        Date dateDernierRappor = JkalDate.parseDateWithHour("12/11/2022 22:00");
        niddahService.addPeriodNiddah(1L, dateVue, dateDernierRappor, timeZone, localisation);

        niddahService.hefsekTaharaIsKo(1L, 1L,localisation);
    }
}
