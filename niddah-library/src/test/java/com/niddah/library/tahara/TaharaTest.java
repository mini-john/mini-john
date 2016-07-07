/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.library.tahara;

import com.niddah.library.date.DateNiddah;
import com.niddah.library.dto.ShevaNekymDto;
import com.niddah.library.enumeration.Origine;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import net.sourceforge.zmanim.util.GeoLocation;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mini-john
 */
public class TaharaTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaharaTest.class);

    @BeforeClass
    public static void setUpClass() throws Exception {
        ;
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testDateHT() throws ParseException {
        Date dateStr = DateNiddah.parseDate("17/03/2016");
        Date ht = Tahara.getDateHefsekPossible(dateStr, Origine.Askenaze);

    }

    @Test
    public void testShevaNekym() throws ParseException {
        Locale.setDefault(Locale.FRENCH);
        Date dateStr = DateNiddah.parseDate("21/03/2016");
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Paris");
        GeoLocation location = new GeoLocation(locationName, latitude, longitude, elevation, timeZone);
        List<ShevaNekymDto> list = Tahara.getShevaNekiym(dateStr, location);
        LOGGER.info(list.toString());
        assertEquals(list.size(), 7);
    }

}
