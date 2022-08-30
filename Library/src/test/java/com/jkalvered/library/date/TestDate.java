/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkalvered.library.date;

import com.jkalvered.library.enumeration.MomentJournee;
import com.jkalvered.library.enumeration.Ona;
import com.kosherjava.zmanim.ZmanimCalendar;
import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

import com.kosherjava.zmanim.hebrewcalendar.JewishDate;
import com.kosherjava.zmanim.util.GeoLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 *
 * @author mini-john
 */
public class TestDate {
    
    private static final Logger LOGGER = LogManager.getLogger();
    
    @Test
    public void testSunsetAndSurise() throws ParseException {
        Date dateStr = JkalDate.parseDate("16/08/2022");
        String locationName = "Paris";
        double latitude = 48.853;
        double longitude = 2.349;
        double elevation = 0;
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Paris");
        GeoLocation location = new GeoLocation(locationName, latitude, longitude, elevation, timeZone);
        ZmanimCalendar zc = new ZmanimCalendar(location);
        zc.getCalendar().setTime(dateStr);
        
        Assert.state(zc.getSunrise().toString().equals("Tue Aug 16 06:44:29 CEST 2022"), "Probleme dans l'heure de lever du soleil pour {1}" + locationName);
        Assert.state(zc.getSunset().toString().equals("Tue Aug 16 21:04:20 CEST 2022"), "Probleme dans l'heure de coucher du soleil pour {1}" + locationName);
        
    }
    
    @Test
    public void testDate() throws ParseException {
        Date dateStr = JkalDate.parseDate("17/03/2016");
        
        JewishDate date = JkalDate.getDateJewish(dateStr);
        LOGGER.info(date.toString());
        Assert.hasText("7 Adar II, 5776", date.toString());
        
    }
    
    @Test
    public void testDateWithHour() throws ParseException {
        Date dateStr = JkalDate.parseDateWithHour("29/08/2022 20:13");
        Date dateRes = JkalDate.parseDateWithHour("29/08/2022 20:12");
        DateTime dateTimeRes=new DateTime(dateRes);
        String locationName = "Nice";
        double latitude = 43.70;
        double longitude = 7.27;
        double elevation = 0;
        String timeZone = "Europe/Paris";
        JkalDate jkal = new JkalDate(dateStr, locationName, latitude, longitude, elevation, timeZone);
        DateTime dateJkal=new DateTime(jkal.getCoucherSoleil());
        Assert.isTrue(jkal.getOna() == Ona.Nuit, "Un probleme sur le calcul de la ona existe");
        Assert.isTrue(dateTimeRes.isEqual(dateJkal), "Un probleme est survenu lors du calcul du coucher du soleil");
        
        
    }
    
    @Test
    public void testOnaJourJKalDate() throws ParseException {
        
        Date dateStr = JkalDate.parseDateWithHour("14/08/2022 14:24");
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        String timeZone = "Europe/Paris";
        
        JkalDate jkalDate = new JkalDate(dateStr, locationName, latitude, longitude, elevation, timeZone);
        Assert.isTrue(jkalDate.getOna() == Ona.Jour, jkalDate.toString() + " n'est pas une ona jour");
    }
    
    @Test
    public void testOnaNuitJKalDate() throws ParseException {
        
        Date dateStr = JkalDate.parseDateWithHour("14/08/2022 22:24");
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        String timeZone = "Europe/Paris";
        
        JkalDate jkalDate = new JkalDate(dateStr, locationName, latitude, longitude, elevation, timeZone);
        Assert.isTrue(jkalDate.getOna() == Ona.Nuit, jkalDate.toString() + " n'est pas une ona nuit");
    }
    
    @Test
    public void testAddDate() throws ParseException {
        int nbJour = 4;
        Date dateStr = JkalDate.parseDate("17/03/2016");
        Date dateAdd = JkalDate.addDay(dateStr, nbJour);
        
        Assert.isTrue(dateAdd.compareTo(dateStr) > 0, "L'ajout du nombre de jour à échouer");
        
    }
    
    @Test
    public void testAddMonth() throws ParseException {
        int nbMonth = 1;
        Date dateStr = JkalDate.parseDate("17/03/2016");
        Date dateAdd = JkalDate.addMonth(dateStr, nbMonth);
        LOGGER.info(dateAdd.toString());
        Assert.isTrue(dateAdd.compareTo(dateStr) > 0, "l'ajout du nombre de mois à échouer");
        
    }
    
    @Test
    public void testAddMonthJewishDate() throws ParseException {
        int nbJour = 1;
        Date dateStr = JkalDate.parseDate("08/05/2016");
        JewishDate dateAdd = JkalDate.addMonth(JkalDate.getDateJewish(dateStr), nbJour);
        LOGGER.info(JkalDate.getDateJewish(dateStr).toString());
        LOGGER.info(dateAdd.toString());
        Assert.isTrue(dateAdd.compareTo(JkalDate.getDateJewish(dateStr)) > 0, "l'ajout du nombre de month jewish à echouer");
        
    }
    
    @Test
    public void testGetNextMonthFull() throws ParseException {
        Date dateStr = JkalDate.parseDate("27/08/2022");
        JewishDate dateAdd = JkalDate.getNextMonthFull(JkalDate.getDateJewish(dateStr));
        LOGGER.info(JkalDate.getDateJewish(dateStr).toString());
        LOGGER.info(dateAdd.toString());
        Assert.isTrue(dateAdd.compareTo(JkalDate.getDateJewish(dateStr)) > 0, "le prochain moi à 30 jours à échouer");
        
    }
    
    @Test
    public void testMomentJourneeWithDateGregorian() throws ParseException {
        Date dateStr = JkalDate.parseDateWithHour("14/08/2022 04:05");
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        String timeZone = "Europe/Paris";
        JkalDate jkalDate = new JkalDate(dateStr, locationName, latitude, longitude, elevation, timeZone);
        MomentJournee momentJournee = jkalDate.getMomentJournee();
        
        Assert.state(MomentJournee.Matin == momentJournee, "ce n'est pas le matin");
        dateStr = JkalDate.parseDateWithHour("14/08/2022 08:05");
        jkalDate = new JkalDate(dateStr, locationName, latitude, longitude, elevation, timeZone);
        momentJournee = jkalDate.getMomentJournee();
        Assert.state(MomentJournee.Jour == momentJournee, "ce n'est pas le jour");
        dateStr = JkalDate.parseDateWithHour("14/08/2022 21:05");
        jkalDate = new JkalDate(dateStr, locationName, latitude, longitude, elevation, timeZone);
        momentJournee = jkalDate.getMomentJournee();
        Assert.state(MomentJournee.Soir == momentJournee, "ce n'est pas le soir" + dateStr);
    }
    
    @Test
    public void getNombreJourEntreDeuxDate() throws ParseException {
        Date date1 = JkalDate.parseDateWithHour("14/08/2022 04:05");
        Date date2 = JkalDate.parseDateWithHour("14/09/2022 05:05");
        int res = JkalDate.getNombreJourEntreDeuxDate(date1, date2);
        LOGGER.debug("nombre de jour d'intervalle" + res);
    }
    
    @Test
    public void getNombreJourEntreDeuxJKalDate() throws ParseException {
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        String timeZone = "Europe/Paris";
        Date date1 = JkalDate.parseDateWithHour("14/08/2022 04:05");
        Date date2 = JkalDate.parseDateWithHour("14/09/2022 05:05");
        JkalDate jkal1, jKal2;
        jkal1 = new JkalDate(date1, locationName, latitude, longitude, elevation, timeZone);
        jKal2 = new JkalDate(date2, locationName, latitude, longitude, elevation, timeZone);
        int res = jkal1.getNombreJourEcart(jKal2);
        Assert.state(res == 32, "Il y a un probleme dans le calcul du nombre de jour d'ecart");
    }
    
    @Test
    public void getNombreJourEntreDeuxJewishDate() {
        throw new UnsupportedOperationException("a faire");
    }
}
