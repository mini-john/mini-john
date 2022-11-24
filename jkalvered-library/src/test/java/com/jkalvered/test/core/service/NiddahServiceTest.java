/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.test.core.service;

import com.jkalvered.core.dto.Localisation;
import com.jkalvered.core.dto.PersonneDto;
import com.jkalvered.core.entite.Account;
import com.jkalvered.core.entite.Bedikot;
import com.jkalvered.core.entite.Configuration;
import com.jkalvered.core.entite.Personne;
import com.jkalvered.core.service.NiddahService;
import com.jkalvered.core.service.PersonneService;
import com.jkalvered.library.date.JkalDate;
import com.jkalvered.library.enumeration.NumBedika;
import com.jkalvered.library.enumeration.Origine;
import com.jkalvered.library.enumeration.RoleUser;
import com.jkalvered.library.enumeration.Sexe;
import com.jkalvered.library.exception.NiddahDataException;
import java.text.ParseException;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 *
 * @author jonat
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.jkalvered.test.core.configuration.AppConfigCore.class, com.jkalvered.test.core.configuration.HibernateConfiguration.class}, loader = AnnotationConfigContextLoader.class)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@TestMethodOrder(OrderAnnotation.class)
public class NiddahServiceTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    NiddahService niddahService;
    @Autowired
    PersonneService personneService;

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
    @Order(1)
    public void testInsert() {
        //   initialize();
        LOGGER.info("Test in insertion personne");
        Personne personne = niddahService.getById(1L, Personne.class);
        LOGGER.info("login " + personne.getAccount().getLogin());
        LOGGER.info("Origine " + personne.getConfiguration().getOrigine());

    }

    @Test
    @Order(2)
    public void test1NiddahInsert() throws ParseException {
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
        Date dateDernierRappor = JkalDate.parseDateWithHour("12/11/2022 22:00");
        niddahService.addPeriodNiddah(1L, dateVue, dateDernierRappor, timeZone, localisation);
    }

    @Test
    @Order(3)
    public void test2hefsekTaharaKo() throws ParseException, NiddahDataException {
        LOGGER.info("Test ht ko");
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

        niddahService.hefsekTaharaIsKo(1L, 1L, localisation);
    }

    @Test
    @Order(4)
    public void test3MohDahoukhKo() throws ParseException, NiddahDataException {
        LOGGER.info("Test moh ko");

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

        niddahService.mohdDahoukIsKo(1L, 1L, localisation);
    }

    @Test
    @Order(5)
    public void test4BedikotKo() throws ParseException, NiddahDataException {
        LOGGER.info("Test bedika ko");

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
        Bedikot bd = niddahService.getById(1L, Bedikot.class);
        bd.setEtatBedika1(Boolean.TRUE);
        niddahService.update(bd, Bedikot.class);
        int res = niddahService.setBedikotKO(2L, 1L, NumBedika.Bedika2, localisation);
        Assert.assertTrue("erreur dans le retour de bedikot ko", res == 1);

    }

    @Test
    @Order(6)
    public void testAnalysePurification() throws ParseException, NiddahDataException {
        LOGGER.info("Test niddah purification");
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
        niddahService.analysePurification(1L, 1L);
    }

    @Test
    @Order(6)

    public void testTevilaOK() throws ParseException, NiddahDataException {
        LOGGER.info("Test niddah purification");
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
        Bedikot bd1 = niddahService.getById(1L, Bedikot.class);
        Bedikot bd2 = niddahService.getById(7L, Bedikot.class);

        bd1.setEtatBedika1(Boolean.TRUE);
        bd1.setEtatBedika2(Boolean.TRUE);
        niddahService.update(bd1, bd1.getClass());
        bd2.setEtatBedika1(Boolean.TRUE);
        bd2.setEtatBedika2(Boolean.TRUE);
        niddahService.update(bd2, bd2.getClass());

        niddahService.setTevilaOk(1L, 1L, localisation);
    }
}
