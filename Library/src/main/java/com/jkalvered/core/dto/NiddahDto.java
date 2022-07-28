/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.dto;

import com.jkalvered.library.enumeration.Ona;
import com.kosherjava.zmanim.hebrewcalendar.JewishDate;
import com.kosherjava.zmanim.util.GeoLocation;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 *
 * @author jonat
 */
public class NiddahDto  implements Comparable<NiddahDto> {

    private JewishDate jDate;
    private Date date;
    private int haflaga;
    private Ona ona;
    private double latitude;
    private double longitude;
    private double elevation;
    private Long id;
    private FemmeDto femme;

    public JewishDate getjDate() {
        return jDate;
    }

    public void setjDate(JewishDate jDate) {
        this.jDate = jDate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getHaflaga() {
        return haflaga;
    }

    public void setHaflaga(int haflaga) {
        this.haflaga = haflaga;
    }

    public Ona getOna() {
        return ona;
    }

    public void setOna(Ona ona) {
        this.ona = ona;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FemmeDto getFemme() {
        return femme;
    }

    public void setFemme(FemmeDto femme) {
        this.femme = femme;
    }

    public GeoLocation getLGeolocation(String locationName, TimeZone timeZone) {
        return new GeoLocation(locationName, latitude, longitude, elevation, timeZone);

    }

    @Override
    public int compareTo(NiddahDto o) {
        Calendar cal1 = GregorianCalendar.getInstance();
        cal1.setTime(this.date);
        Calendar cal2 = GregorianCalendar.getInstance();
        cal2.setTime(o.date);
        return cal1.compareTo(cal2);}
}
