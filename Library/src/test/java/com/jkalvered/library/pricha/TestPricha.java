/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkalvered.library.pricha;

import com.jkalvered.core.dto.PrichaDto;
import com.jkalvered.library.date.JkalDate;
import com.jkalvered.library.enumeration.Ona;
import java.text.ParseException;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.javatuples.Pair;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 *
 * @author mini-john
 */
public class TestPricha {
    
    private static org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
    
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
        Assert.isTrue(pricha.getDatePricha().getDateGregorian().toString().equals("Tue Sep 13 22:05:00 CEST 2022"), "Probleme dans le calcul de la ona benonit");
        date1 = JkalDate.parseDateWithHour("14/08/2022 14:05");
        pricha = Pricha.getPrichaHahodesh(date1, locationName, latitude, longitude, elevation, timeZone);
        Assert.isTrue(pricha.getOna() == Ona.Jour, "Il ya un problème sur le calcul de la ona");
        Assert.isTrue(pricha.getDatePricha().getDateGregorian().toString().equals("Tue Sep 13 14:05:00 CEST 2022"), "Probleme dans le calcul de la ona benonit");
        
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
    }
    @Test
    public void testPrichaHovotDaat() throws ParseException {
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        String timeZone = "Europe/Paris";
        Date date1 = JkalDate.parseDateWithHour("14/08/2022 04:05");
        Pair<PrichaDto,PrichaDto> prichot= Pricha.getPrichaBenonitHovotDaat(date1, locationName, latitude, longitude, elevation, timeZone);
        LOGGER.info(prichot.toString());
    }
}
