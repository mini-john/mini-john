/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkalvered.library.date;

import com.jkalvered.library.enumeration.MomentJournee;
import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

import com.kosherjava.zmanim.hebrewcalendar.JewishDate;
import com.kosherjava.zmanim.util.GeoLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.util.Assert;

/**
 *
 * @author mini-john
 */
public class TestDate {

    private static Logger LOGGER = LogManager.getLogger();

    @Test
    public void testSunsetAndSurise() {
        throw new UnsupportedOperationException("a faire");

    }

    @Test
    public void testDate() throws ParseException {
        Date dateStr = DateNiddah.parseDate("17/03/2016");

        JewishDate date = DateNiddah.getDateJewish(dateStr);
        LOGGER.info(date.toString());
        Assert.hasText("7 Adar II, 5776", date.toString());
        Assert.hasText("7 Adar II, 5776", date.toString());
        Assert.hasText("7 Adar II, 5776", date.toString());

    }

    @Test
    public void testOnaJourDateNormal() throws ParseException {

        Date dateStr = DateNiddah.parseDateWithHour("17/03/2016 14:24");
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Paris");
        GeoLocation location = new GeoLocation(locationName, latitude, longitude, elevation, timeZone);

        Assert.isTrue(DateNiddah.isOnatJour(dateStr, location), "Le test de ona jour a echoué");

    }

    @Test
    public void testOnaNuitDateNormal() throws ParseException {

        Date dateStr = DateNiddah.parseDateWithHour("17/03/2016 22:24");
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Paris");
        GeoLocation location = new GeoLocation(locationName, latitude, longitude, elevation, timeZone);

        Assert.isTrue(!DateNiddah.isOnatJour(dateStr, location), dateStr.toString() + " n'est pas une ona nuit");

    }

    @Test
    public void testOnaJourJewishDate() throws ParseException {

        Date dateStr = DateNiddah.parseDateWithHour("14/08/2022 14:24");
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Paris");
        GeoLocation location = new GeoLocation(locationName, latitude, longitude, elevation, timeZone);

        JewishDate dateJewish = DateNiddah.getDateJewish(dateStr, location);
        Assert.isTrue(DateNiddah.isOnatJour(dateJewish, location), dateJewish.toString() + " n'est pas une ona jour");

    }

    @Test
    public void testOnaNuitJewishDate() throws ParseException {

        Date dateStr = DateNiddah.parseDateWithHour("14/08/2022 22:24");
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Paris");
        GeoLocation location = new GeoLocation(locationName, latitude, longitude, elevation, timeZone);
        JewishDate dateJewish = DateNiddah.getDateJewish(dateStr, location);
        Assert.isTrue(!DateNiddah.isOnatJour(dateJewish, location), dateJewish.toString() + " n'est pas une ona nuit");

    }

    @Test
    public void testAddDate() throws ParseException {
        int nbJour = 4;
        Date dateStr = DateNiddah.parseDate("17/03/2016");
        Date dateAdd = DateNiddah.addDay(dateStr, nbJour);

        Assert.isTrue(dateAdd.compareTo(dateStr) > 0, "L'ajout du nombre de jour à échouer");

    }

    @Test
    public void testAddJewishDate() throws ParseException {
        int nbJour = 4;
        Date dateStr = DateNiddah.parseDate("17/03/2016");
        JewishDate dateAdd = DateNiddah.addDay(DateNiddah.getDateJewish(dateStr), nbJour);
        Assert.isTrue(dateAdd.compareTo(DateNiddah.getDateJewish(dateStr)) > 0, "l'ajout du nombre de jewishday à échouer");

    }

    @Test
    public void testAddMonth() throws ParseException {
        int nbMonth = 1;
        Date dateStr = DateNiddah.parseDate("17/03/2016");
        Date dateAdd = DateNiddah.addMonth(dateStr, nbMonth);
        LOGGER.info(dateAdd.toString());
        Assert.isTrue(dateAdd.compareTo(dateStr) > 0, "l'ajout du nombre de mois à échouer");

    }

    @Test
    public void testAddMonthJewishDate() throws ParseException {
        int nbJour = 1;
        Date dateStr = DateNiddah.parseDate("08/05/2016");
        JewishDate dateAdd = DateNiddah.addMonth(DateNiddah.getDateJewish(dateStr), nbJour);
        LOGGER.info(DateNiddah.getDateJewish(dateStr).toString());
        LOGGER.info(dateAdd.toString());
        Assert.isTrue(dateAdd.compareTo(DateNiddah.getDateJewish(dateStr)) > 0, "l'ajout du nombre de month jewish à echouer");

    }

    @Test
    public void testGetNextMonthFull() throws ParseException {
        Date dateStr = DateNiddah.parseDate("27/08/2022");
        JewishDate dateAdd = DateNiddah.getNextMonthFull(DateNiddah.getDateJewish(dateStr));
        LOGGER.info(DateNiddah.getDateJewish(dateStr).toString());
        LOGGER.info(dateAdd.toString());
        Assert.isTrue(dateAdd.compareTo(DateNiddah.getDateJewish(dateStr)) > 0, "le prochain moi à 30 jours à échouer");

    }

    @Test
    public void testMomentJourneeWithDateGregorian() throws ParseException {
        Date dateStr = DateNiddah.parseDateWithHour("14/08/2022 04:05");
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Paris");
        GeoLocation location = new GeoLocation(locationName, latitude, longitude, elevation, timeZone);
        MomentJournee momentJournee = DateNiddah.getMomentJournee(dateStr, location);
        Assert.state(MomentJournee.Matin == momentJournee, "ce n'est pas le matin");
        dateStr = DateNiddah.parseDateWithHour("14/08/2022 08:05");
        momentJournee = DateNiddah.getMomentJournee(dateStr, location);
        Assert.state(MomentJournee.Jour == momentJournee, "ce n'est pas le jour");
        dateStr = DateNiddah.parseDateWithHour("14/08/2022 21:05");
        momentJournee = DateNiddah.getMomentJournee(dateStr, location);
        Assert.state(MomentJournee.Soir == momentJournee, "ce n'est pas le soir"+ dateStr);
    }
    @Test
    public void testMomentJourneeWithJewishDate() throws ParseException {
        Date dateStr = DateNiddah.parseDateWithHour("14/08/2022 04:05");
        JewishDate jDate=DateNiddah.getDateJewish(dateStr);
        LOGGER.debug("la daet gregorien dans le jewish date"+ jDate.getGregorianCalendar().getTime());
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Paris");
        GeoLocation location = new GeoLocation(locationName, latitude, longitude, elevation, timeZone);
        MomentJournee momentJournee = DateNiddah.getMomentJournee(jDate, location);
        Assert.state(MomentJournee.Matin == momentJournee, "ce n'est pas le matin");
        dateStr = DateNiddah.parseDateWithHour("14/08/2022 08:05");
        jDate=DateNiddah.getDateJewish(dateStr);
        momentJournee = DateNiddah.getMomentJournee(jDate, location);
        Assert.state(MomentJournee.Jour == momentJournee, "ce n'est pas le jour");
        dateStr = DateNiddah.parseDateWithHour("14/08/2022 21:05");
        jDate=DateNiddah.getDateJewish(dateStr);
        momentJournee = DateNiddah.getMomentJournee(jDate, location);
        Assert.state(MomentJournee.Soir == momentJournee, "ce n'est pas le soir"+ dateStr);
    }
}
