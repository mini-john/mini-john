/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkalvered.library.date;

import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

import com.kosherjava.zmanim.hebrewcalendar.JewishDate;
import com.kosherjava.zmanim.util.GeoLocation;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 *
 * @author mini-john
 */
public class TestDate {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestDate.class);

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

        Assert.isTrue(true, DateNiddah.isOnatJour(dateStr, location).toString());

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

        Assert.isTrue(!DateNiddah.isOnatJour(dateStr, location));

    }

    @Test
    public void testAddDate() throws ParseException {
        int nbJour = 4;
        Date dateStr = DateNiddah.parseDate("17/03/2016");
        Date dateAdd = DateNiddah.addDay(dateStr, nbJour);

        Assert.isTrue(dateAdd.compareTo(dateStr) > 0);

    }

    @Test
    public void testAddJewishDate() throws ParseException {
        int nbJour = 4;
        Date dateStr = DateNiddah.parseDate("17/03/2016");
        JewishDate dateAdd = DateNiddah.addDay(DateNiddah.getDateJewish(dateStr), nbJour);
        Assert.isTrue(dateAdd.compareTo(DateNiddah.getDateJewish(dateStr)) > 0);

    }

    @Test
    public void testAddMonth() throws ParseException {
        int nbMonth = 1;
        Date dateStr = DateNiddah.parseDate("17/03/2016");
        Date dateAdd = DateNiddah.addMonth(dateStr, nbMonth);
        LOGGER.info(dateAdd.toString());
        Assert.isTrue(dateAdd.compareTo(dateStr) > 0);

    }

    @Test
    public void testAddMonthJewishDate() throws ParseException {
        int nbJour = 1;
        Date dateStr = DateNiddah.parseDate("08/05/2016");
        JewishDate dateAdd = DateNiddah.addMonth(DateNiddah.getDateJewish(dateStr), nbJour);
        LOGGER.info(DateNiddah.getDateJewish(dateStr).toString());
        LOGGER.info(dateAdd.toString());
        Assert.isTrue(dateAdd.compareTo(DateNiddah.getDateJewish(dateStr)) > 0);

    }

    @Test
    public void testAddMonthJewishDateFull() throws ParseException {
        int nbJour = 1;
        Date dateStr = DateNiddah.parseDate("08/05/2016");
        JewishDate dateAdd = DateNiddah.getNextMonthFull(DateNiddah.getDateJewish(dateStr), nbJour);
        LOGGER.info(DateNiddah.getDateJewish(dateStr).toString());
        LOGGER.info(dateAdd.toString());
        Assert.isTrue(dateAdd.compareTo(DateNiddah.getDateJewish(dateStr)) > 0);

    }

}
