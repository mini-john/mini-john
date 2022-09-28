/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkalvered.library.pricha;

import com.jkalvered.core.dto.PrichaDto;
import com.jkalvered.library.date.JkalDate;
import com.jkalvered.library.enumeration.Ona;
import com.jkalvered.library.exception.NiddahException;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javatuples.Pair;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 *
 * @author mini-john
 */
public class TestPricha {

    private static final Logger LOGGER = LogManager.getLogger();

    @Before
    public void setUp() {
        Locale.setDefault(Locale.FRENCH);
        LOGGER.info("before");
    }

    @Test
    public void testPrichaBenonit() throws ParseException {
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        String timeZone = "Europe/Paris";
        Date date1 = JkalDate.parseDateWithHour("14/08/2022 04:05");
        PrichaDto pricha = Pricha.getPrichaBenonit(date1, locationName, latitude, longitude, elevation, timeZone);
        Assert.isTrue(pricha.getOna() == Ona.Nuit, "Il ya un problème sur le calcul de la ona");
        Assert.isTrue(pricha.getDatePricha().getDateGregorian().toString().equals("Mon Sep 12 04:05:00 CEST 2022"), "Probleme dans le calcul de la ona benonit");
        date1 = JkalDate.parseDateWithHour("14/08/2022 14:05");
        pricha = Pricha.getPrichaBenonit(date1, locationName, latitude, longitude, elevation, timeZone);
        Assert.isTrue(pricha.getOna() == Ona.Jour, "Il ya un problème sur le calcul de la ona");
        Assert.isTrue(pricha.getDatePricha().getDateGregorian().toString().equals("Mon Sep 12 14:05:00 CEST 2022"), "Probleme dans le calcul de la ona benonit");
        date1 = JkalDate.parseDateWithHour("14/08/2022 22:05");
        pricha = Pricha.getPrichaBenonit(date1, locationName, latitude, longitude, elevation, timeZone);
        Assert.isTrue(pricha.getOna() == Ona.Nuit, "Il ya un problème sur le calcul de la ona");
        Assert.isTrue(pricha.getDatePricha().getDateGregorian().toString().equals("Mon Sep 12 22:05:00 CEST 2022"), "Probleme dans le calcul de la ona benonit");
        LOGGER.info("Test Benonit Success");
    }

    @Test
    public void testPrichaHagodesh() throws ParseException {
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        String timeZone = "Europe/Paris";
        Date date1 = JkalDate.parseDateWithHour("14/08/2022 22:05");
        PrichaDto pricha = Pricha.getPrichaHahodesh(date1, locationName, latitude, longitude, elevation, timeZone);
        Assert.isTrue(pricha.getOna() == Ona.Nuit, "Il ya un problème sur le calcul de la ona");
        Assert.isTrue(pricha.getDatePricha().getDateGregorian().toString().equals("Tue Sep 13 22:05:00 CEST 2022"), "Probleme dans le calcul de la ona Hagodesh");
        date1 = JkalDate.parseDateWithHour("14/08/2022 14:05");
        pricha = Pricha.getPrichaHahodesh(date1, locationName, latitude, longitude, elevation, timeZone);
        Assert.isTrue(pricha.getOna() == Ona.Jour, "Il ya un problème sur le calcul de la ona");
        Assert.isTrue(pricha.getDatePricha().getDateGregorian().toString().equals("Tue Sep 13 14:05:00 CEST 2022"), "Probleme dans le calcul de la ona Hagodesh");

    }

    @Test
    public void testPrichaYomHaflaga() throws ParseException {
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        String timeZone = "Europe/Paris";
        Date datePrecedente = JkalDate.parseDateWithHour("13/07/2022 22:05");
        Date dateVue = JkalDate.parseDateWithHour("14/08/2022 22:05");
        PrichaDto pricha = Pricha.getPrichaHaflaga(datePrecedente, dateVue, locationName, latitude, longitude, elevation, timeZone);
        LOGGER.info(pricha.toString());
        Assert.isTrue(pricha.getHaflagaDay() == 33, "Probleme de calcul de la haflaga");
    }

    @Test
    public void testPrichaHovotDaat() throws ParseException {
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        String timeZone = "Europe/Paris";
        Date date1 = JkalDate.parseDateWithHour("14/08/2022 04:05");
        Pair<PrichaDto, PrichaDto> prichot = Pricha.getPrichaBenonitHovotDaat(date1, locationName, latitude, longitude, elevation, timeZone);
        PrichaDto prichaBenonit = Pricha.getPrichaBenonit(date1, locationName, latitude, longitude, elevation, timeZone);
        int days = Days.daysBetween(new LocalDate(prichaBenonit.getDatePricha().getDateGregorian()), new LocalDate(prichot.getValue1().getDatePricha().getDateGregorian())).getDays();
        Assert.isTrue(days == 1, "Probleme dans le calcul de la pricha hovot daat");
    }

    @Test
    public void testPrichaHoutChani() throws ParseException {
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        String timeZone = "Europe/Paris";
        Date date1 = JkalDate.parseDateWithHour("14/08/2022 22:05");
        Pair<PrichaDto, PrichaDto> prichot = Pricha.getPrichaHoutChani(date1, locationName, latitude, longitude, elevation, timeZone);
        PrichaDto prichaBenonit = Pricha.getPrichaBenonit(date1, locationName, latitude, longitude, elevation, timeZone);
        int days = Days.daysBetween(new LocalDate(prichaBenonit.getDatePricha().getDateGregorian()), new LocalDate(prichot.getValue1().getDatePricha().getDateGregorian())).getDays();
        Assert.isTrue(days == 1, "Probleme dans le calcul de la pricha hout chani");

    }

    @Test
    public void testPrichaOrZaroua() throws ParseException {
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        String timeZone = "Europe/Paris";
        Date date1 = JkalDate.parseDateWithHour("14/08/2022 14:05");
        PrichaDto pricha = Pricha.getPrichaBenonit(date1, locationName, latitude, longitude, elevation, timeZone);
        PrichaDto prichaOrZaroua = Pricha.getPrichaOrZaroua(date1, pricha);
        Assert.isTrue(pricha.getDateBedika1().compareTo(prichaOrZaroua.getDateBedika2()) == 0, "Probleme dans le calcul de la pricha or zaroua");

        date1 = JkalDate.parseDateWithHour("14/08/2022 22:05");
        pricha = Pricha.getPrichaBenonit(date1, locationName, latitude, longitude, elevation, timeZone);
        prichaOrZaroua = Pricha.getPrichaOrZaroua(date1, pricha);
        Assert.isTrue(pricha.getDateBedika1().compareTo(prichaOrZaroua.getDateBedika2()) == 0, "Probleme dans le calcul de la pricha or zaroua");

        date1 = JkalDate.parseDateWithHour("14/08/2022 2:05");
        pricha = Pricha.getPrichaBenonit(date1, locationName, latitude, longitude, elevation, timeZone);
        prichaOrZaroua = Pricha.getPrichaOrZaroua(date1, pricha);
        Assert.isTrue(pricha.getDateBedika1().compareTo(prichaOrZaroua.getDateBedika2()) == 0, "Probleme dans le calcul de la pricha or zaroua");

    }

    @Test
    public void testPrichaException() throws ParseException {
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        String timeZone = "Europe/Paris";
        Date date1 = JkalDate.parseDateWithHour("14/08/2022 14:05");
        PrichaDto pricha = Pricha.getPrichaBenonit(date1, locationName, latitude, longitude, elevation, timeZone);
        PrichaDto prichaOrZaroua = Pricha.getPrichaOrZaroua(date1, pricha);
        Exception exception = assertThrows(NiddahException.class, () -> {
            prichaOrZaroua.getHaflagaDay();
        });
        String expectedMessage = "Une haflga est demandée";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
