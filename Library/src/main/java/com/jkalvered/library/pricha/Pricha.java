/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkalvered.library.pricha;

import com.jkalvered.core.dto.PrichaDto;
import com.jkalvered.library.constante.Constantes;
import com.jkalvered.library.date.JkalDate;
import com.jkalvered.library.date.JkalDate;
import com.jkalvered.library.enumeration.Ona;
import com.jkalvered.library.enumeration.TypePricha;
import com.jkalvered.library.exception.MomentException;
import com.jkalvered.library.exception.NiddahException;
import com.kosherjava.zmanim.ZmanimCalendar;
import com.kosherjava.zmanim.hebrewcalendar.JewishDate;
import com.kosherjava.zmanim.util.GeoLocation;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;

import org.joda.time.Days;
import org.joda.time.LocalDate;


/**
 * Classe calculant les prichots d'un cycle
 *
 * @author mini-john
 */
public class Pricha {

    private static final Logger LOGGER = Logger.getLogger(Pricha.class.getName());

//    /**
//     * Pré rempli une prichaDto
//     *
//     * @param prichaDto
//     * @param dateVue
//     * @param zc
//     * @param date
//     * @param location
//     * @return prichaDto
//     */
//    private static void fillPrichaDto(PrichaDto prichaDto, Date dateVue, ZmanimCalendar zc, JewishDate date, GeoLocation location) {
//        switch (JkalDate.getMomentJournee(dateVue, location)) {
//            case Jour -> {
//                prichaDto.setjDate(date);
//                prichaDto.setDateGregorian(zc.getCalendar().getTime());
//                prichaDto.setDateBedika1(zc.getSunrise());
//                prichaDto.setDateBedika2(zc.getSunset());
//                prichaDto.setOna(Ona.Jour);
//            }
//            case Matin -> {
//                prichaDto.setjDate(date);
//                prichaDto.setDateGregorian(zc.getCalendar().getTime());
//                prichaDto.setDateBedika2(zc.getSunrise());
//                zc.getCalendar().roll(Calendar.DAY_OF_MONTH, -1);
//                prichaDto.setDateBedika1(zc.getSunset());
//                prichaDto.setOna(Ona.Nuit);
//            }
//            case Soir -> {
//                zc.getCalendar().roll(Calendar.DAY_OF_MONTH, +1);
//                prichaDto.setjDate(JkalDate.getDateJewish(zc.getCalendar().getTime()));
//                prichaDto.setDateGregorian(zc.getCalendar().getTime());
//                prichaDto.setDateBedika2(zc.getSunrise());
//                zc.getCalendar().roll(GregorianCalendar.DATE, -1);
//
//                prichaDto.setDateBedika1(zc.getSunset());
//                prichaDto.setOna(Ona.Nuit);
//            }
//        }
//
//    }

    /**
     * Pré rempli une prichaDto
     *
     * @param prichaDto
     * @param dateVue
     * @param zc
     * @param date
     * @param location
     * @return prichaDto
     */
    private static void fillPrichaDto(PrichaDto prichaDto, Date dateVue, ZmanimCalendar zc, Date date, GeoLocation location) {
        switch (JkalDate.getMomentJournee(dateVue, location)) {
            case Jour -> {
                prichaDto.setjDate(JkalDate.getDateJewish(zc.getCalendar().getTime()));
                prichaDto.setDateGregorian(zc.getCalendar().getTime());
                prichaDto.setDateBedika1(zc.getSunrise());
                prichaDto.setDateBedika2(zc.getSunset());
                prichaDto.setOna(Ona.Jour);
            }
            case Matin -> {
                prichaDto.setjDate(JkalDate.getDateJewish(zc.getCalendar().getTime()));
                prichaDto.setDateGregorian(zc.getCalendar().getTime());
                prichaDto.setDateBedika2(zc.getSunrise());
                zc.getCalendar().roll(Calendar.DAY_OF_MONTH, -1);
                prichaDto.setDateBedika1(zc.getSunset());
                prichaDto.setOna(Ona.Nuit);
            }
            case Soir -> {
                zc.getCalendar().roll(Calendar.DAY_OF_MONTH, +1);
                prichaDto.setjDate(JkalDate.getDateJewish(zc.getCalendar().getTime()));
                prichaDto.setDateGregorian(zc.getCalendar().getTime());
                prichaDto.setDateBedika2(zc.getSunrise());
                zc.getCalendar().roll(GregorianCalendar.DATE, -1);

                prichaDto.setDateBedika1(zc.getSunset());
                prichaDto.setOna(Ona.Nuit);
            }
        }

    }

    /**
     * Retourne la pricha benoit du cycle
     *
     * @param date
     * @param location
     * @return
     */
    public static PrichaDto getPrichaBenonit(Date date, GeoLocation location) {
        PrichaDto prichaDto = new PrichaDto();
        Date dateBenonit = JkalDate.addDay(date, Constantes.NB_JOUR_ONAT_BENONIT);
        prichaDto.setTypePricha(TypePricha.Benonit);
        ZmanimCalendar zc = new ZmanimCalendar(location);
        zc.getCalendar().setTime(dateBenonit);
        fillPrichaDto(prichaDto, date, zc, dateBenonit, location);
        return prichaDto;
    }

    /**
     * Retourne la Pricha Hachodesh correspondant
     *
     * @param date
     * @param location
     * @return
     */
    public static PrichaDto getPrichaHahodesh(Date date, GeoLocation location) {
        PrichaDto prichaDto = new PrichaDto();
        JewishDate dateHahodesh = JkalDate.addMonth(JkalDate.getDateJewish(date), 1);
        ZmanimCalendar zc = new ZmanimCalendar(location);
        zc.getCalendar().setTime(dateHahodesh.getGregorianCalendar().getTime());
        prichaDto.setTypePricha(TypePricha.Hahodesh);
        fillPrichaDto(prichaDto, date, zc, dateHahodesh.getGregorianCalendar().getTime(), location);
        return prichaDto;
    }

    /**
     * Retourne la pricha Haflaga
     *
     * @param date1
     * @param date2
     * @param location
     * @return
     */
    public static PrichaDto getPrichaHaflaga(Date date1, Date date2, GeoLocation location) {
        PrichaDto prichaDto = new PrichaDto();
        if (date2.before(date1)) {
            throw new NiddahException("La date 1 : " + date1 + " doit être avant la 2 : " + date2);
        }
        int days = Days.daysBetween(new LocalDate(date1), new LocalDate(date2)).getDays();
        Date dateHaflaga = JkalDate.addDay(date2, days - 1);
        prichaDto.setTypePricha(TypePricha.Haflaga);
        ZmanimCalendar zc = new ZmanimCalendar(location);
        zc.getCalendar().setTime(dateHaflaga);
        fillPrichaDto(prichaDto, date2, zc, dateHaflaga, location);
        prichaDto.setHaflagaDay(days);
        return prichaDto;
    }

    /**
     * Retourne la Pricha Karti Oupleti
     *
     * @param date
     * @param location
     * @return
     */
    public static List<PrichaDto> getPrichaKartiOupleti(Date date, GeoLocation location) {
        List<PrichaDto> kartiOupleti = new ArrayList<>();
        PrichaDto prichaDto = new PrichaDto();
        Date dateBenonit = JkalDate.addDay(date, Constantes.NB_JOUR_ONAT_BENONIT);
        prichaDto.setTypePricha(TypePricha.KartiOupleti);
        ZmanimCalendar zc = new ZmanimCalendar(location);
        zc.getCalendar().setTime(dateBenonit);
        fillPrichaDto(prichaDto, date, zc, dateBenonit, location);
        kartiOupleti.add(prichaDto);
        PrichaDto prichaComplementaire = new PrichaDto();
        prichaComplementaire.setTypePricha(TypePricha.KartiOupleti);
        switch (prichaDto.getOna()) {
            case Jour -> {
                prichaComplementaire.setjDate(JkalDate.getDateJewish(zc.getCalendar().getTime()));
                prichaComplementaire.setDateGregorian(zc.getCalendar().getTime());
                prichaComplementaire.setDateBedika2(zc.getSunrise());
                zc.getCalendar().roll(Calendar.DAY_OF_MONTH, -1);
                prichaComplementaire.setDateBedika1(zc.getSunset());
                prichaComplementaire.setOna(Ona.Nuit);
            }
            case Nuit -> {
                zc.getCalendar().roll(Calendar.DAY_OF_MONTH, 1);
                prichaComplementaire.setjDate(JkalDate.getDateJewish(zc.getCalendar().getTime()));
                prichaComplementaire.setDateGregorian(zc.getCalendar().getTime());
                prichaComplementaire.setDateBedika1(zc.getSunrise());

                prichaComplementaire.setDateBedika2(zc.getSunset());
                prichaComplementaire.setOna(Ona.Jour);
            }
            default ->
                throw new MomentException("Proble de ona sur la pricha " + prichaComplementaire.toString());
        }
        kartiOupleti.add(prichaComplementaire);
        return kartiOupleti;
    }

    /**
     * Retourne la Pricha Benonit HovotDaat
     *
     * @param date
     * @param location
     * @return
     */
    public static List<PrichaDto> getPrichaBenonitHovotDaat(Date date, GeoLocation location) {
        List<PrichaDto> hovotDaat = new ArrayList<>();
        PrichaDto prichaDto = new PrichaDto();
        Date dateBenonit = JkalDate.addDay(date, Constantes.NB_JOUR_ONAT_BENONIT);
        prichaDto.setTypePricha(TypePricha.HovotDaat);
        ZmanimCalendar zc = new ZmanimCalendar(location);
        zc.getCalendar().setTime(dateBenonit);
        fillPrichaDto(prichaDto, date, zc, dateBenonit, location);
        hovotDaat.add(prichaDto);

        prichaDto = new PrichaDto();
        dateBenonit = JkalDate.addDay(date, Constantes.NB_JOUR_ONAT_BENONIT_HOVOT_DAAT);
        prichaDto.setTypePricha(TypePricha.HovotDaat);
        zc.getCalendar().setTime(dateBenonit);
        fillPrichaDto(prichaDto, date, zc, dateBenonit, location);
        hovotDaat.add(prichaDto);
        return hovotDaat;
    }

    /**
     * Retourne la pricha benonit selon le hout chani
     *
     * @param date
     * @param location
     * @return
     */
    public static List<PrichaDto> getPrichaBenonitHoutChani(Date date, GeoLocation location) {
        List<PrichaDto> houtChani = new ArrayList<>();
        ZmanimCalendar zc = new ZmanimCalendar(location);

        PrichaDto prichaDto = new PrichaDto();
        Date dateBenonit = JkalDate.addDay(date, Constantes.NB_JOUR_ONAT_BENONIT);
        prichaDto.setTypePricha(TypePricha.HoutChani);
        zc.getCalendar().setTime(dateBenonit);
        fillPrichaDto(prichaDto, date, zc, dateBenonit, location);
        houtChani.add(prichaDto);
        PrichaDto prichaComplementaire = new PrichaDto();
        prichaComplementaire.setTypePricha(TypePricha.HoutChani);
        switch (prichaDto.getOna()) {
            case Jour -> {
                prichaComplementaire.setjDate(JkalDate.getDateJewish(zc.getCalendar().getTime()));
                prichaComplementaire.setDateGregorian(zc.getCalendar().getTime());
                prichaComplementaire.setDateBedika2(zc.getSunrise());
                zc.getCalendar().roll(Calendar.DAY_OF_MONTH, -1);
                prichaComplementaire.setDateBedika1(zc.getSunset());
                prichaComplementaire.setOna(Ona.Nuit);
            }
            case Nuit -> {
                zc.getCalendar().roll(Calendar.DAY_OF_MONTH, 1);
                prichaComplementaire.setjDate(JkalDate.getDateJewish(zc.getCalendar().getTime()));
                prichaComplementaire.setDateGregorian(zc.getCalendar().getTime());
                prichaComplementaire.setDateBedika1(zc.getSunrise());

                prichaComplementaire.setDateBedika2(zc.getSunset());
                prichaComplementaire.setOna(Ona.Jour);
            }
            default ->
                throw new MomentException("Proble de ona sur la pricha " + prichaComplementaire.toString());
        }
        houtChani.add(prichaComplementaire);

        prichaDto = new PrichaDto();
        dateBenonit = JkalDate.addDay(date, Constantes.NB_JOUR_ONAT_BENONIT_HOVOT_DAAT);
        prichaDto.setTypePricha(TypePricha.HoutChani);
        zc.getCalendar().setTime(dateBenonit);
        fillPrichaDto(prichaDto, date, zc, dateBenonit, location);
        houtChani.add(prichaDto);
        prichaComplementaire = new PrichaDto();
        prichaComplementaire.setTypePricha(TypePricha.HoutChani);
        switch (prichaDto.getOna()) {
            case Jour -> {
                prichaComplementaire.setjDate(JkalDate.getDateJewish(zc.getCalendar().getTime()));
                prichaComplementaire.setDateGregorian(zc.getCalendar().getTime());
                prichaComplementaire.setDateBedika2(zc.getSunrise());
                zc.getCalendar().roll(Calendar.DAY_OF_MONTH, -1);
                prichaComplementaire.setDateBedika1(zc.getSunset());
                prichaComplementaire.setOna(Ona.Nuit);
            }
            case Nuit -> {
                zc.getCalendar().roll(Calendar.DAY_OF_MONTH, 1);
                prichaComplementaire.setjDate(JkalDate.getDateJewish(zc.getCalendar().getTime()));
                prichaComplementaire.setDateGregorian(zc.getCalendar().getTime());
                prichaComplementaire.setDateBedika1(zc.getSunrise());

                prichaComplementaire.setDateBedika2(zc.getSunset());
                prichaComplementaire.setOna(Ona.Jour);
            }
            default ->
                throw new MomentException("Proble de ona sur la pricha " + prichaComplementaire.toString());
        }
        houtChani.add(prichaComplementaire);

        return houtChani;
    }

    /**
     * Retourne les prichots or Zaroua
     *
     * @param benonit
     * @param hahodesh
     * @param haflaga
     * @param location
     * @return
     */
    public static List<PrichaDto> prichaOrZaroua(PrichaDto benonit, PrichaDto hahodesh, PrichaDto haflaga, GeoLocation location) {
        List<PrichaDto> orZaroua = new ArrayList<>();
        ZmanimCalendar zc = new ZmanimCalendar(location);
        orZaroua.add(getOnaPrecedente(benonit, zc));
        orZaroua.add(getOnaPrecedente(hahodesh, zc));
        orZaroua.add(getOnaPrecedente(haflaga, zc));
        return orZaroua;
    }

    /**
     * Retourn la ona précedente d'une pricha
     *
     * @param pricha
     * @param zc
     * @return
     */
    private static PrichaDto getOnaPrecedente(PrichaDto pricha, ZmanimCalendar zc) {
        PrichaDto prichaPrecedente = new PrichaDto();

        pricha.setTypePricha(TypePricha.OrZaroua);
        switch (pricha.getOna()) {
            case Jour -> {
                prichaPrecedente.setjDate(JkalDate.getDateJewish(zc.getCalendar().getTime()));
                prichaPrecedente.setDateGregorian(zc.getCalendar().getTime());
                prichaPrecedente.setDateBedika2(zc.getSunrise());
                zc.getCalendar().roll(Calendar.DAY_OF_MONTH, -1);
                prichaPrecedente.setDateBedika1(zc.getSunset());
                prichaPrecedente.setOna(Ona.Nuit);
            }
            case Nuit -> {
                zc.getCalendar().roll(Calendar.DAY_OF_MONTH, -1);
                prichaPrecedente.setjDate(JkalDate.getDateJewish(zc.getCalendar().getTime()));
                prichaPrecedente.setDateGregorian(zc.getCalendar().getTime());
                prichaPrecedente.setDateBedika1(zc.getSunrise());

                prichaPrecedente.setDateBedika2(zc.getSunset());
                prichaPrecedente.setOna(Ona.Jour);
            }
            default ->
                throw new MomentException("Probleme de ona precedente sur la pricha " + pricha.toString());
        }
        return prichaPrecedente;
    }
    //TODO : reste les yeme nevoukim !! en fonction de la base de données
}
