/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkalvered.library.vesset;


import com.jkalvered.core.dto.NiddahDto;
import com.jkalvered.library.date.DateNiddah;
import com.jkalvered.library.enumeration.Ona;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mini-john
 */
public class VessetTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(VessetTest.class);

    public VessetTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testFillCycle() throws ParseException {
        Locale.setDefault(Locale.FRENCH);
        Date dateStr = DateNiddah.parseDateWithHour("17/03/2016 12:24");
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Paris");

        NiddahDto cycleDto = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        LOGGER.debug(cycleDto.toString());
        assertEquals(cycleDto.getOna(), Ona.Jour);
    }

    @Test
    public void testGetHaflaga() throws ParseException {
        Locale.setDefault(Locale.FRENCH);
        Date dateStr = DateNiddah.parseDateWithHour("17/02/2016 12:24");
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Paris");

        NiddahDto cycleDto = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        dateStr = DateNiddah.parseDateWithHour("17/03/2016 12:24");
        NiddahDto cycleDto2 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        LOGGER.info(Vesset.getHaflagaEntreDeuxCycle(cycleDto, cycleDto2) + "");

    }

    @Test
    public void testVessetHahodesh() throws ParseException {
        Locale.setDefault(Locale.FRENCH);
        Date dateStr = DateNiddah.parseDateWithHour("10/02/2016 12:24");
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Paris");
        ArrayList<NiddahDto> listCycle = new ArrayList<>();
        NiddahDto cycleDto = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        listCycle.add(cycleDto);
        dateStr = DateNiddah.parseDateWithHour("11/03/2016 12:24");
        NiddahDto cycleDto2 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto2.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto, cycleDto2));
        listCycle.add(cycleDto2);
        dateStr = DateNiddah.parseDateWithHour("9/04/2016 12:24");
        NiddahDto cycleDto3 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto2.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto2, cycleDto3));
        listCycle.add(cycleDto3);
        boolean resultTest = Vesset.isCycleKavouaHahodesh(listCycle);
        assertTrue(resultTest);
        LOGGER.info("Le cycle est " + resultTest);

    }

    @Test
    public void testVessetHahodeshTwoYear() throws ParseException {
        Locale.setDefault(Locale.FRENCH);
        Date dateStr = DateNiddah.parseDateWithHour("13/07/2018 12:24");
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Paris");
        ArrayList<NiddahDto> listCycle = new ArrayList<>();
        NiddahDto cycleDto = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        listCycle.add(cycleDto);
        dateStr = DateNiddah.parseDateWithHour("12/08/2018 12:24");
        NiddahDto cycleDto2 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto2.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto, cycleDto2));
        listCycle.add(cycleDto2);
        dateStr = DateNiddah.parseDateWithHour("10/09/2018 12:24");
        NiddahDto cycleDto3 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto2.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto2, cycleDto3));
        listCycle.add(cycleDto3);
        boolean resultTest = Vesset.isCycleKavouaHahodesh(listCycle);
        assertTrue(resultTest);
        LOGGER.info("Le cycle est  sur deux ans " + resultTest);

    }

    @Test
    public void testVessetHaflaga() throws ParseException {
        Locale.setDefault(Locale.FRENCH);
        Date dateStr = DateNiddah.parseDateWithHour("10/02/2016 12:24");
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Paris");
        ArrayList<NiddahDto> listCycle = new ArrayList<>();
        NiddahDto cycleDto = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        listCycle.add(cycleDto);
        dateStr = DateNiddah.parseDateWithHour("11/02/2016 12:24");
        NiddahDto cycleDto2 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto2.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto, cycleDto2));
        listCycle.add(cycleDto2);
        dateStr = DateNiddah.parseDateWithHour("12/02/2016 12:24");
        NiddahDto cycleDto3 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto3.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto2, cycleDto3));
        listCycle.add(cycleDto3);
        dateStr = DateNiddah.parseDateWithHour("13/02/2016 12:24");
        NiddahDto cycleDto4 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto4.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto3, cycleDto4));
        listCycle.add(cycleDto4);
        boolean resultTest = Vesset.isCycleKavouaHaflaga(listCycle);
        assertTrue(resultTest);
        LOGGER.info("Le cycle est haflaga regulier " + resultTest);

    }

    @Test
    public void testVessetHachavoua() throws ParseException {
        Locale.setDefault(Locale.FRENCH);
        Date dateStr = DateNiddah.parseDateWithHour("1/02/2016 12:24");
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Paris");
        ArrayList<NiddahDto> listCycle = new ArrayList<>();
        NiddahDto cycleDto = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        listCycle.add(cycleDto);
        dateStr = DateNiddah.parseDateWithHour("8/02/2016 12:24");
        NiddahDto cycleDto2 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto2.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto, cycleDto2));
        listCycle.add(cycleDto2);
        dateStr = DateNiddah.parseDateWithHour("15/02/2016 12:24");
        NiddahDto cycleDto3 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto3.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto2, cycleDto3));
        listCycle.add(cycleDto3);
        dateStr = DateNiddah.parseDateWithHour("23/02/2016 12:24");
        NiddahDto cycleDto4 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto4.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto3, cycleDto4));
        listCycle.add(cycleDto4);
        LOGGER.info("Le cycle est hashavoua regulier " + Vesset.isCycleKavouaHashavoua(listCycle));

    }

    @Test
    public void testVessetDilougHahodesh() throws ParseException {
        Locale.setDefault(Locale.FRENCH);
        Date dateStr = DateNiddah.parseDateWithHour("11/01/2016 12:24");
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Paris");
        ArrayList<NiddahDto> listCycle = new ArrayList<>();
        NiddahDto cycleDto = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        listCycle.add(cycleDto);
        dateStr = DateNiddah.parseDateWithHour("11/02/2016 12:24");
        NiddahDto cycleDto2 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto2.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto, cycleDto2));
        listCycle.add(cycleDto2);
        dateStr = DateNiddah.parseDateWithHour("13/03/2016 12:24");
        NiddahDto cycleDto3 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto3.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto2, cycleDto3));
        listCycle.add(cycleDto3);
        dateStr = DateNiddah.parseDateWithHour("12/04/2016 12:24");
        NiddahDto cycleDto4 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto4.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto3, cycleDto4));
        listCycle.add(cycleDto4);
        LOGGER.info("Le cycle est diloug hahodesh regulier " + Vesset.isCycleKavouaDilougHahodesh(listCycle));

        assertEquals(Vesset.isCycleKavouaDilougHahodesh(listCycle), true);

    }

    @Test
    public void testVessetDilougHahodeshHozer() throws ParseException {
        Locale.setDefault(Locale.FRENCH);
        Date dateStr = DateNiddah.parseDateWithHour("1/01/2016 12:24");
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Paris");
        ArrayList<NiddahDto> listCycle = new ArrayList<>();
        NiddahDto cycleDto = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);

        dateStr = DateNiddah.parseDateWithHour("11/01/2016 12:24");
        NiddahDto cycleDto2 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto2.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto, cycleDto2));
        listCycle.add(cycleDto2);
        dateStr = DateNiddah.parseDateWithHour("11/02/2016 12:24");
        NiddahDto cycleDto3 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto3.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto2, cycleDto3));
        listCycle.add(cycleDto3);
        dateStr = DateNiddah.parseDateWithHour("13/03/2016 12:24");
        NiddahDto cycleDto4 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto4.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto3, cycleDto4));
        listCycle.add(cycleDto4);
        dateStr = DateNiddah.parseDateWithHour("12/04/2016 12:24");
        NiddahDto cycleDto5 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto5.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto4, cycleDto5));
        listCycle.add(cycleDto5);
        dateStr = DateNiddah.parseDateWithHour("9/05/2016 12:24");//9
        NiddahDto cycleDto6 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto6.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto5, cycleDto6));
        listCycle.add(cycleDto6);
        dateStr = DateNiddah.parseDateWithHour("8/06/2016 12:24");//8
        NiddahDto cycleDto7 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto7.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto6, cycleDto7));
        listCycle.add(cycleDto7);
        dateStr = DateNiddah.parseDateWithHour("9/07/2016 12:24");
        NiddahDto cycleDto8 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto8.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto7, cycleDto8));
        listCycle.add(cycleDto8);
        dateStr = DateNiddah.parseDateWithHour("8/08/2016 12:24");
        NiddahDto cycleDto9 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto9.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto8, cycleDto9));
        listCycle.add(cycleDto9);
        dateStr = DateNiddah.parseDateWithHour("04/09/2016 12:24");
        NiddahDto cycleDto10 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto10.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto9, cycleDto10));
        listCycle.add(cycleDto10);
        dateStr = DateNiddah.parseDateWithHour("04/10/2016 12:24");
        NiddahDto cycleDto11 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto10.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto10, cycleDto11));
        listCycle.add(cycleDto11);
        dateStr = DateNiddah.parseDateWithHour("04/11/2016 12:24");
        NiddahDto cycleDto12 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto10.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto11, cycleDto12));
        listCycle.add(cycleDto12);
        dateStr = DateNiddah.parseDateWithHour("04/12/2016 12:24");
        NiddahDto cycleDto13 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto10.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto12, cycleDto13));
        listCycle.add(cycleDto13);
        boolean result = Vesset.isCycleKavouaDilougHahodeshHozer(listCycle);
        LOGGER.info("Le cycle est diloug haodesh hozer regulier " + result);
        assertEquals(result, true);
    }

    @Test
    public void testVessetDilougHaflaga() throws ParseException {
        Locale.setDefault(Locale.FRENCH);
        Date dateStr = DateNiddah.parseDateWithHour("1/01/2016 12:24");
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Paris");
        ArrayList<NiddahDto> listCycle = new ArrayList<>();
        NiddahDto cycleDto = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        listCycle.add(cycleDto);
        dateStr = DateNiddah.parseDateWithHour("10/01/2016 12:24");
        NiddahDto cycleDto2 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto2.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto, cycleDto2));
        listCycle.add(cycleDto2);
        dateStr = DateNiddah.parseDateWithHour("20/01/2016 12:24");
        NiddahDto cycleDto3 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto3.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto2, cycleDto3));
        listCycle.add(cycleDto3);
        dateStr = DateNiddah.parseDateWithHour("31/01/2016 12:24");
        NiddahDto cycleDto4 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto4.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto3, cycleDto4));
        listCycle.add(cycleDto4);
        dateStr = DateNiddah.parseDateWithHour("12/02/2016 12:24");
        NiddahDto cycleDto5 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto5.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto4, cycleDto5));
        listCycle.add(cycleDto5);
        LOGGER.info("Le cycle est diloug haflaga regulier " + Vesset.isCycleKavouaDilougHaflaga(listCycle));

    }

    @Test
    public void testVessetDilougHaflagaHozer() throws ParseException {
        Locale.setDefault(Locale.FRENCH);
        Date dateStr = DateNiddah.parseDateWithHour("1/01/2016 12:24");
        String locationName = "Nice";
        double latitude = 43.700000;
        double longitude = 7.250000;
        double elevation = 0;
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Paris");
        ArrayList<NiddahDto> listCycle = new ArrayList<>();
        NiddahDto cycleDto = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);

        dateStr = DateNiddah.parseDateWithHour("2/01/2016 12:24");
        NiddahDto cycleDto2 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto2.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto, cycleDto2));
        listCycle.add(cycleDto2);
        dateStr = DateNiddah.parseDateWithHour("4/01/2016 12:24");
        NiddahDto cycleDto3 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto3.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto2, cycleDto3));
        listCycle.add(cycleDto3);
        dateStr = DateNiddah.parseDateWithHour("7/01/2016 12:24");
        NiddahDto cycleDto4 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto4.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto3, cycleDto4));
        listCycle.add(cycleDto4);
        dateStr = DateNiddah.parseDateWithHour("8/01/2016 12:24");
        NiddahDto cycleDto5 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto5.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto4, cycleDto5));
        listCycle.add(cycleDto5);
        dateStr = DateNiddah.parseDateWithHour("10/01/2016 12:24");
        NiddahDto cycleDto6 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto6.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto5, cycleDto6));
        listCycle.add(cycleDto6);
        dateStr = DateNiddah.parseDateWithHour("13/01/2016 12:24");
        NiddahDto cycleDto7 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto7.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto6, cycleDto7));
        listCycle.add(cycleDto7);
        dateStr = DateNiddah.parseDateWithHour("14/01/2016 12:24");
        NiddahDto cycleDto8 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto8.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto7, cycleDto8));
        listCycle.add(cycleDto8);
        dateStr = DateNiddah.parseDateWithHour("16/01/2016 12:24");
        NiddahDto cycleDto9 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto9.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto8, cycleDto9));
        listCycle.add(cycleDto9);
        dateStr = DateNiddah.parseDateWithHour("19/01/2016 12:24");
        NiddahDto cycleDto10 = Vesset.fillNiddahDto(dateStr, latitude, longitude, elevation, locationName, timeZone);
        cycleDto10.setHaflaga(Vesset.getHaflagaEntreDeuxCycle(cycleDto9, cycleDto10));
        listCycle.add(cycleDto10);
        LOGGER.info("Le cycle est diloug haflaga hozer regulier " + Vesset.isCycleKavouaDilougHaflagaHozer(listCycle));

    }

}
