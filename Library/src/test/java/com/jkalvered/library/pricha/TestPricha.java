/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkalvered.library.pricha;


import com.jkalvered.core.dto.PrichaDto;
import com.jkalvered.library.date.DateNiddah;
import com.kosherjava.zmanim.util.GeoLocation;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mini-john
 */
public class TestPricha {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TestPricha.class);
    
    @Test
    public void testBenonit() throws ParseException {
        Locale.setDefault(Locale.FRENCH);
        Date dateStr = DateNiddah.parseDateWithHour("17/03/2016 0:24");
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Paris");
        GeoLocation location = new GeoLocation(locationName, latitude, longitude, elevation, timeZone);
        PrichaDto prichaBenoni = Pricha.getPrichaBenonit(dateStr, location);
        PrichaDto prichaHahodesh = Pricha.getPrichaHahodesh(dateStr, location);
        
        Date dateStr2 = DateNiddah.parseDateWithHour("17/04/2016 22:24");
        PrichaDto prichaHaflaga = Pricha.getPrichaHaflaga(dateStr, dateStr2, location);
        LOGGER.info("Premiere date " + dateStr + " en hebreu " + DateNiddah.getDateJewish(dateStr));
        LOGGER.info("Pricha Benonit " + prichaBenoni.toString());
        
        LOGGER.info("Pricha Hahodesh" + prichaHahodesh.toString());
        LOGGER.info("Deuxieme date " + dateStr2 + " en hebreu " + DateNiddah.getDateJewish(dateStr2));
        LOGGER.info("Pricha Haflaga " + prichaHaflaga.toString());
    }
    
    @Test
    public void testHoutChani() throws ParseException {
        Locale.setDefault(Locale.FRENCH);
        Date dateStr = DateNiddah.parseDateWithHour("17/03/2016 15:24");
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Paris");
        GeoLocation location = new GeoLocation(locationName, latitude, longitude, elevation, timeZone);
        List<PrichaDto> prichaBenoni = Pricha.getPrichaBenonitHoutChani(dateStr, location);
        
        LOGGER.info("Le resultat est " + prichaBenoni.toString());
        
    }
    
    @Test
    public void testOrZaroua() throws ParseException {
        Locale.setDefault(Locale.FRENCH);
        Date dateStr = DateNiddah.parseDateWithHour("17/03/2016 0:24");
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Paris");
        GeoLocation location = new GeoLocation(locationName, latitude, longitude, elevation, timeZone);
        PrichaDto prichaBenoni = Pricha.getPrichaBenonit(dateStr, location);
        PrichaDto prichaHahodesh = Pricha.getPrichaHahodesh(dateStr, location);
        
        Date dateStr2 = DateNiddah.parseDateWithHour("17/04/2016 22:24");
        PrichaDto prichaHaflaga = Pricha.getPrichaHaflaga(dateStr, dateStr2, location);
        LOGGER.info("Premiere date " + dateStr + " en hebreu " + DateNiddah.getDateJewish(dateStr));
        LOGGER.info("Pricha Benonit " + prichaBenoni.toString());
        
        LOGGER.info("Pricha Hahodesh" + prichaHahodesh.toString());
        LOGGER.info("Deuxieme date " + dateStr2 + " en hebreu " + DateNiddah.getDateJewish(dateStr2));
        LOGGER.info("Pricha Haflaga " + prichaHaflaga.toString());
        
        List<PrichaDto> orZaroua=Pricha.prichaOrZaroua(prichaBenoni, prichaHahodesh, prichaHaflaga, location);
        LOGGER.info("Pricha orZaroua " + orZaroua.toString());
    }
    
}
