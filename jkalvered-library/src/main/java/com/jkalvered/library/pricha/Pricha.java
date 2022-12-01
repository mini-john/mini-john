/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkalvered.library.pricha;

import com.jkalvered.core.dto.PrichaDto;
import com.jkalvered.library.constante.Constantes;
import com.jkalvered.library.date.JkalDate;
import com.jkalvered.library.enumeration.TypePricha;
import com.jkalvered.library.exception.JKalVeredException;
import com.kosherjava.zmanim.ZmanimCalendar;
import com.kosherjava.zmanim.hebrewcalendar.JewishDate;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.javatuples.Pair;
import org.joda.time.Days;
import org.joda.time.LocalDate;

/**
 * Classe calculant les prichots d'un cycle
 *
 * @author mini-john
 */
public class Pricha {

    private static org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    /**
     * Retourne la pricha benoit du cycle
     *
     * @param date
     * @param locationName
     * @param latitude
     * @param longitude
     * @param elevation
     * @param timeZone
     * @return
     */
    public static PrichaDto getPrichaBenonit(Date date, String locationName, double latitude, double longitude, double elevation, String timeZone) {
        Date dateBenonit = JkalDate.addDay(date, Constantes.NB_JOUR_ONAT_BENONIT);
        PrichaDto prichaDto = new PrichaDto(dateBenonit, locationName, latitude, longitude, elevation, timeZone);
        prichaDto.setTypePricha(TypePricha.Benonit);
        prichaDto.fillDateBedika(date, (ZmanimCalendar) prichaDto.getzc());

        return prichaDto;
    }

    /**
     * Retourne la Pricha Hachodesh correspondant
     *
     * @param date
     * @param locationName
     * @param latitude
     * @param longitude
     * @param elevation
     * @param timeZone
     * @return
     */
    public static PrichaDto getPrichaHahodesh(Date date, String locationName, double latitude, double longitude, double elevation, String timeZone) {

        JewishDate jDate = JkalDate.getDateJewish(date);
        jDate = JkalDate.addMonth(jDate, 1);
        Date dateHahodesh = jDate.getGregorianCalendar().getTime();
        dateHahodesh = JkalDate.copyHourInTwoDate(date, dateHahodesh);
        PrichaDto prichaDto = new PrichaDto(dateHahodesh, locationName, latitude, longitude, elevation, timeZone);
        prichaDto.setTypePricha(TypePricha.Hahodesh);
        prichaDto.fillDateBedika(date, (ZmanimCalendar) prichaDto.getzc());
        return prichaDto;
    }

    /**
     * Retourne pricha yom haflaga
     *
     * @param datePrecedente
     * @param dateVue
     * @param locationName
     * @param latitude
     * @param longitude
     * @param elevation
     * @param timeZone
     * @return
     */
    public static PrichaDto getPrichaHaflaga(Date datePrecedente, Date dateVue, String locationName, double latitude, double longitude, double elevation, String timeZone) {
        if (dateVue.before(datePrecedente)) {
            throw new JKalVeredException("La date 1 : " + datePrecedente + " doit être avant la 2 : " + dateVue);
        }
        int days = Days.daysBetween(new LocalDate(datePrecedente), new LocalDate(dateVue)).getDays() + 1;
        Date dateHaflaga = JkalDate.addDay(dateVue, days - 2);

        PrichaDto prichaDto = new PrichaDto(dateHaflaga, locationName, latitude, longitude, elevation, timeZone);
        prichaDto.setHaflagaDay(days);
        prichaDto.setTypePricha(TypePricha.Haflaga);
        prichaDto.fillDateBedika(dateVue, (ZmanimCalendar) prichaDto.getzc());
        return prichaDto;
    }

    /**
     * Retourne prichat benonit sous l'opinion hovot daat
     *
     * @param date
     * @param locationName
     * @param latitude
     * @param longitude
     * @param elevation
     * @param timeZone
     * @return
     */
    public static Pair<PrichaDto, PrichaDto> getPrichaBenonitHovotDaat(Date date, String locationName, double latitude, double longitude, double elevation, String timeZone) {
        Date dateHovotDaat1 = JkalDate.addDay(date, Constantes.NB_JOUR_ONAT_BENONIT);
        PrichaDto prichaHovotDaat1Dto = new PrichaDto(dateHovotDaat1, locationName, latitude, longitude, elevation, timeZone);
        prichaHovotDaat1Dto.setTypePricha(TypePricha.HovotDaat);
        prichaHovotDaat1Dto.fillDateBedika(date, (ZmanimCalendar) prichaHovotDaat1Dto.getzc());
        Date dateHovotDaat2 = JkalDate.addDay(date, Constantes.NB_JOUR_ONAT_BENONIT + 1);
        PrichaDto prichaHovotDaat2Dto = new PrichaDto(dateHovotDaat2, locationName, latitude, longitude, elevation, timeZone);
        prichaHovotDaat2Dto.setTypePricha(TypePricha.HovotDaat);
        prichaHovotDaat2Dto.fillDateBedika(date, (ZmanimCalendar) prichaHovotDaat1Dto.getzc());
        Pair<PrichaDto, PrichaDto> res = new Pair<>(prichaHovotDaat1Dto, prichaHovotDaat2Dto);
        return res;
    }

    /**
     * retourne pricha hout chani
     *
     * @param date
     * @param locationName
     * @param latitude
     * @param longitude
     * @param elevation
     * @param timeZone
     * @return
     */
    public static Pair<PrichaDto, PrichaDto> getPrichaHoutChani(Date date, String locationName, double latitude, double longitude, double elevation, String timeZone) {
        Date dateHovotDaat1 = JkalDate.addDay(date, Constantes.NB_JOUR_ONAT_BENONIT);
        PrichaDto prichaHovotDaat1Dto = new PrichaDto(dateHovotDaat1, locationName, latitude, longitude, elevation, timeZone);
        prichaHovotDaat1Dto.setTypePricha(TypePricha.HoutChani);
        prichaHovotDaat1Dto.fillDateBedikaJourEntier(date, (ZmanimCalendar) prichaHovotDaat1Dto.getzc());
        Date dateHovotDaat2 = JkalDate.addDay(date, Constantes.NB_JOUR_ONAT_BENONIT + 1);
        PrichaDto prichaHovotDaat2Dto = new PrichaDto(dateHovotDaat2, locationName, latitude, longitude, elevation, timeZone);
        prichaHovotDaat2Dto.setTypePricha(TypePricha.HoutChani);
        prichaHovotDaat2Dto.fillDateBedikaJourEntier(date, (ZmanimCalendar) prichaHovotDaat1Dto.getzc());
        Pair<PrichaDto, PrichaDto> res = new Pair<>(prichaHovotDaat1Dto, prichaHovotDaat2Dto);
        return res;
    }

    /**
     * Retourne pricha hovot yair
     *
     * @param date
     * @param locationName
     * @param latitude
     * @param longitude
     * @param elevation
     * @param timeZone
     * @return
     */
    public static PrichaDto getPrichaHovotYair(Date date, String locationName, double latitude, double longitude, double elevation, String timeZone) {
        Date dateBenonit = JkalDate.addDay(date, Constantes.NB_JOUR_ONAT_BENONIT + 1);
        PrichaDto prichaDto = new PrichaDto(dateBenonit, locationName, latitude, longitude, elevation, timeZone);
        prichaDto.setTypePricha(TypePricha.HovotYair);
        prichaDto.fillDateBedika(date, (ZmanimCalendar) prichaDto.getzc());

        return prichaDto;
    }

    /**
     * Retourne pricha or zaroua
     *
     * @param dateVue
     * @param pricha
     * @return
     */
    public static PrichaDto getPrichaOrZaroua(Date dateVue, PrichaDto pricha) {
        if (pricha.getTypePricha() == TypePricha.Benonit || pricha.getTypePricha() == TypePricha.Haflaga || pricha.getTypePricha() == TypePricha.Hahodesh) {
            PrichaDto prichaDto = new PrichaDto(pricha.getDateJPricha().getDateGregorian(), pricha.getDateJPricha().getLocationName(), pricha.getDateJPricha().getLatitude(), pricha.getDateJPricha().getLongitude(), pricha.getDateJPricha().getElevation(), pricha.getDateJPricha().getTimeZone().toZoneId().toString());
            prichaDto.setTypePricha(TypePricha.OrZaroua);
            prichaDto.fillDateBedikaOrZaroua(dateVue, pricha, (ZmanimCalendar) prichaDto.getzc());

            return prichaDto;
        } else {
            throw new JKalVeredException("La pricha Or Zaroua ne se fait que sur une pricha de type hahodesh benonit ou haflaga et pas sur un type " + pricha.getTypePricha());
        }
    }
}
