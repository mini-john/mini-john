/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkalvered.library.date;

import com.jkalvered.library.enumeration.MomentJournee;
import com.jkalvered.library.exception.MomentException;
import com.kosherjava.zmanim.ZmanimCalendar;
import com.kosherjava.zmanim.hebrewcalendar.JewishDate;
import com.kosherjava.zmanim.util.GeoLocation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;

/**
 * Classe permettant la manipulation des dates gregoriens et les dates
 * hébraïques en fonction de la géolocalisation
 *
 * @author mini-john
 */
public class DateNiddah {

    private static SimpleDateFormat formatDateWithHour = new SimpleDateFormat("dd/MM/yyyy HH:m");
    private static SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
    private static Logger LOGGER = LogManager.getLogger();

    /**
     * Prend une chaine de caractère représentant une date et retourne la date
     * formaté
     *
     * @param dateStr
     * @return la date sur la forme dd/MM/yyyy
     * @throws ParseException
     */
    public static Date parseDate(String dateStr) throws ParseException {
        return formatDate.parse(dateStr);
    }

    /**
     * Prend une chaine de caractère représentant une date avec une heure
     *
     * @param dateStr
     * @return la date sur la forme dd/MM/yyyy HH:mm
     * @throws ParseException
     */
    public static Date parseDateWithHour(String dateStr) throws ParseException {
        return formatDateWithHour.parse(dateStr);
    }

    /**
     * Return la date hébraique associé a la date gregorian
     *
     * @param date
     * @return
     */
    public static JewishDate getDateJewish(Date date) {

        return new JewishDate(date);
    }

    /**
     * Return la date juive associé à la date gregorian en fonction de la
     * geolocalisation
     *
     * @param date
     * @param location
     * @return
     */
    public static JewishDate getDateJewish(Date date, GeoLocation location) {

        Date dateTemp = null;
        switch (DateNiddah.getMomentJournee(date, location)) {
            case Jour, Matin ->
                dateTemp = date;
            case Soir -> {
                Calendar cal = GregorianCalendar.getInstance();
                cal.setTime(date);
                cal.add(Calendar.DAY_OF_MONTH, 1);
                dateTemp = cal.getTime();
            }

        }
        return new JewishDate(dateTemp);
    }

    /**
     * Determine la ona associé à la date et à la geolocalisation
     *
     * @param date
     * @param location
     * @return
     */
    public static Boolean isOnatJour(Date date, GeoLocation location) {
        ZmanimCalendar zc = new ZmanimCalendar(location);
        zc.getCalendar().setTime(date);
        return (zc.getSunrise().before(date) && date.before(zc.getSunset()));

    }

    /**
     * Return le moment de la journée (Matin,Jour,Soir) en fonction de la
     * géolocalisation
     *
     * @param date
     * @param location
     * @return
     * @throws MomentException
     */
    public static MomentJournee getMomentJournee(Date date, GeoLocation location) throws MomentException {
        ZmanimCalendar zc = new ZmanimCalendar(location);
        zc.getCalendar().setTime(date);

        if (zc.getSunrise().after(date)) {
            return MomentJournee.Matin;
        } else if (zc.getSunrise().before(date) && zc.getSunset().after(date)) {
            return MomentJournee.Jour;
        } else if (zc.getSunset().before(date)) {
            return MomentJournee.Soir;
        } else {
            throw new MomentException("L'heure est incorrect" + date.toString());

        }

    }

    /**
     * Vérifique que la JewishDate est un jour en fonction de la géolocalisation
     *
     * @param date
     * @param location
     * @return
     */
    public static boolean isOnatJour(JewishDate date, GeoLocation location) {
        ZmanimCalendar zc = new ZmanimCalendar(location);
        zc.getCalendar().setTime(date.getGregorianCalendar().getTime());
        return (zc.getSunrise().before(date.getGregorianCalendar().getTime()) && date.getGregorianCalendar().getTime().before(zc.getSunset()));

    }

    /**
     * Return le moment de la journée (Matin,Jour,Soir) en fonction de la
     * géolocalisation
     *
     * @param date
     * @param location
     * @return
     * @throws MomentException
     */
    public static MomentJournee getMomentJournee(JewishDate date, GeoLocation location) throws MomentException {
        ZmanimCalendar zc = new ZmanimCalendar(location);
        zc.getCalendar().setTime(date.getGregorianCalendar().getTime());

        if (zc.getSunrise().after(date.getGregorianCalendar().getTime())) {
            return MomentJournee.Matin;
        } else if (zc.getSunrise().before(date.getGregorianCalendar().getTime()) && zc.getSunset().after(date.getGregorianCalendar().getTime())) {
            return MomentJournee.Jour;
        } else if (zc.getSunset().before(date.getGregorianCalendar().getTime())) {
            return MomentJournee.Soir;
        } else {
            throw new MomentException("L'heure est incorrect" + date.toString());
        }
    }

    /**
     * Ajoute nbJour à la date gregorian
     *
     * @param date
     * @param nbJour
     * @return
     */
    public static Date addDay(Date date, int nbJour) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, nbJour);
        return cal.getTime();

    }

    /**
     * Ajoute nbJour à la JewishDate
     *
     * @param date
     * @param nbJour
     * @return
     */
    public static JewishDate addDay(JewishDate date, int nbJour) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date.getGregorianCalendar().getTime());
        cal.add(Calendar.DAY_OF_MONTH, nbJour);
        return new JewishDate(cal.getTime());

    }

    /**
     * Ajoute nbMonth à la date grégorian
     *
     * @param date
     * @param nbMonth
     * @return
     */
    public static Date addMonth(Date date, int nbMonth) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, nbMonth);
        return cal.getTime();

    }

    /**
     * Ajoute nbMonth à la JewishDate jDate
     *
     * @param jDate
     * @param nbMonth
     * @return
     */
    public static JewishDate addMonth(JewishDate jDate, int nbMonth) {
        jDate.forward(Calendar.MONTH, nbMonth);
        return jDate;
    }

    /**
     * Retourne le prochain mois de 3O jours apres jDate
     *
     * @param jDate
     * @return
     */
    public static JewishDate getNextMonthFull(JewishDate jDate) {

        jDate.forward(Calendar.MONTH, 1);
        while (jDate.getDaysInJewishMonth() != 30) {
            jDate.forward(Calendar.MONTH, 1);
        }

        return jDate;
    }
}
