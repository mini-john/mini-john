/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkalvered.core.dto;

import com.jkalvered.library.date.JkalDate;
import com.jkalvered.library.enumeration.Ona;
import com.jkalvered.library.enumeration.TypePricha;
import com.jkalvered.library.exception.JKalVeredException;
import com.kosherjava.zmanim.ZmanimCalendar;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;

/**
 * Dto modélisant une pricha en fonction de la date du cycle et du type de
 * pricha
 *
 * @author mini-john
 */
@Getter
@Setter
public class PrichaDto implements Comparable<PrichaDto> , Serializable{

    private static org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    /*
    Champ necessaire à la création de l'objet
     */
    @Getter
    @Setter
    private Long id;

    private String locationName;
    private double latitude;
    private double longitude;
    private double elevation;
    private String timeZone;
    private JkalDate dateJPricha;
    @Getter
    @Setter
    private Date datePricha;

    /*
    Champs rajouté après la creation de l'objet
     */
    private String commentaire;
    private TypePricha typePricha;
    private Date dateBedika1;
    private Date dateBedika2;
    private boolean etatBedika1;
    private boolean etatBedika2;
    private int haflagaDay;

    public PrichaDto(Date datePricha, String locationName, double latitude, double longitude, double elevation, String timeZone) {

        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
        this.timeZone = timeZone;
        this.datePricha = datePricha;
        this.dateJPricha = new JkalDate(datePricha, locationName, latitude, longitude, elevation, timeZone);
    }

    public PrichaDto() {
    }

//    public String getLocationName() {
//        return locationName;
//    }
//
//    public void setLocationName(String locationName) {
//        this.locationName = locationName;
//    }
//
//    public double getLatitude() {
//        return latitude;
//    }
//
//    public void setLatitude(double latitude) {
//        this.latitude = latitude;
//    }
//
//    public double getLongitude() {
//        return longitude;
//    }
//
//    public void setLongitude(double longitude) {
//        this.longitude = longitude;
//    }
//
//    public double getElevation() {
//        return elevation;
//    }
//
//    public void setElevation(double elevation) {
//        this.elevation = elevation;
//    }
//
//    public String getTimeZone() {
//        return timeZone;
//    }
//
//    public void setTimeZone(String timeZone) {
//        this.timeZone = timeZone;
//    }
    public JkalDate getDateJPricha() {
        if (dateJPricha == null) {
        this.dateJPricha = new JkalDate(datePricha, locationName, latitude, longitude, elevation, timeZone);

        }
        return dateJPricha;
    }

    public void setDateJPricha(JkalDate dateJPricha) {
        this.dateJPricha = dateJPricha;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public TypePricha getTypePricha() {
        return typePricha;
    }

    public void setTypePricha(TypePricha typePricha) {
        this.typePricha = typePricha;
    }

    public Date getDateBedika1() {
        return dateBedika1;
    }

    public void setDateBedika1(Date dateBedika1) {
        this.dateBedika1 = dateBedika1;
    }

    public Date getDateBedika2() {
        return dateBedika2;
    }

    public void setDateBedika2(Date dateBedika2) {
        this.dateBedika2 = dateBedika2;
    }

    public boolean isEtatBedika1() {
        return etatBedika1;
    }

    public void setEtatBedika1(boolean etatBedika1) {
        this.etatBedika1 = etatBedika1;
    }

    public boolean isEtatBedika2() {
        return etatBedika2;
    }

    public void setEtatBedika2(boolean etatBedika2) {
        this.etatBedika2 = etatBedika2;
    }

    public int getHaflagaDay() throws JKalVeredException {
        if (typePricha != TypePricha.Haflaga) {
            throw new JKalVeredException("Une haflga est demandée alors que le type n'est pas haflaga = " + this.toString());
        }
        return haflagaDay;
    }

    public void setHaflagaDay(int haflagaDay) {
        this.haflagaDay = haflagaDay;
    }

    public Ona getOna() {
        return dateJPricha.getOna();
    }

    /**
     * Rempli les date de bedika
     *
     * @param dateVue
     * @param zcalendar
     */
    public void fillDateBedika(Date dateVue, ZmanimCalendar zcalendar) {
        zcalendar.getCalendar().setTime(dateJPricha.getDateGregorian());
        switch (this.dateJPricha.getMomentJournee(dateVue)) {
            case Jour -> {
                this.dateBedika1 = dateJPricha.getLeverSoleil(dateJPricha.getDateGregorian());
                this.dateBedika2 = dateJPricha.getCoucherSoleil(dateJPricha.getDateGregorian());

            }
            case Matin -> {
                this.dateBedika2 = dateJPricha.getLeverSoleil(dateJPricha.getDateGregorian());
                zcalendar.getCalendar().roll(Calendar.DAY_OF_MONTH, -1);
                this.dateBedika1 = zcalendar.getSunset();
            }
            case Soir -> {
                this.dateBedika1 = dateJPricha.getCoucherSoleil(dateJPricha.getDateGregorian());
                zcalendar.getCalendar().roll(Calendar.DAY_OF_MONTH, +1);
                this.dateBedika2 = zcalendar.getSunrise();
            }

        }
    }

    @Override
    public int compareTo(PrichaDto o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String toString() {
        return "PrichaDto{" + "locationName=" + locationName + ", latitude=" + latitude + ", longitude=" + longitude + ", elevation=" + elevation + ", timeZone=" + timeZone + ", datePricha=" + dateJPricha + ", commentaire=" + commentaire + ", typePricha=" + typePricha + ", dateBedika1=" + dateBedika1 + ", dateBedika2=" + dateBedika2 + ", etatBedika1=" + etatBedika1 + ", etatBedika2=" + etatBedika2 + ", haflagaDay=" + haflagaDay + '}';
    }

    public ZmanimCalendar getzc() {
        return (ZmanimCalendar) this.dateJPricha.getZc().clone();
    }

    /**
     * rempli les date de bedika pour pricha hout chani ou karti oupleti
     *
     * @param dateVue
     * @param zcalendar
     */
    public void fillDateBedikaJourEntier(Date dateVue, ZmanimCalendar zcalendar) {
        zcalendar.getCalendar().setTime(dateJPricha.getDateGregorian());
        switch (this.dateJPricha.getMomentJournee(dateVue)) {
            case Jour -> {
                this.dateBedika2 = dateJPricha.getCoucherSoleil(dateJPricha.getDateGregorian());
                zcalendar.getCalendar().roll(Calendar.DAY_OF_MONTH, -1);
                this.dateBedika1 = zcalendar.getSunset();
            }
            case Matin -> {
                this.dateBedika2 = dateJPricha.getCoucherSoleil(dateJPricha.getDateGregorian());
                zcalendar.getCalendar().roll(Calendar.DAY_OF_MONTH, -1);
                this.dateBedika1 = zcalendar.getSunset();
            }
            case Soir -> {
                this.dateBedika1 = dateJPricha.getCoucherSoleil(dateJPricha.getDateGregorian());
                zcalendar.getCalendar().roll(Calendar.DAY_OF_MONTH, +1);
                this.dateBedika2 = zcalendar.getSunrise();
            }

        }
    }

    /**
     * remplie date bedika pour pricha orzaroua
     *
     * @param dateVue
     * @param pricha
     * @param zcalendar
     */
    public void fillDateBedikaOrZaroua(Date dateVue, PrichaDto pricha, ZmanimCalendar zcalendar) {

        dateBedika2 = pricha.getDateBedika1();
        switch (this.dateJPricha.getMomentJournee(dateVue)) {

            case Jour -> {
                zcalendar.getCalendar().roll(Calendar.DAY_OF_MONTH, -1);
                this.dateBedika1 = zcalendar.getSunset();
            }
            case Matin -> {
                zcalendar.getCalendar().roll(Calendar.DAY_OF_MONTH, -1);
                this.dateBedika1 = zcalendar.getSeaLevelSunrise();
            }
            case Soir -> {
                this.dateBedika1 = zcalendar.getSeaLevelSunrise();
                zcalendar.getCalendar().roll(Calendar.DAY_OF_MONTH, +1);
            }
        }
    }

}
