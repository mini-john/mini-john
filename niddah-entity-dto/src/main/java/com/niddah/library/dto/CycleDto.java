/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.library.dto;

import com.niddah.library.enumeration.Ona;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import net.sourceforge.zmanim.hebrewcalendar.JewishDate;
import net.sourceforge.zmanim.util.GeoLocation;

/**
 * Dto modélisant un cycle. Un cycle est le moment où la femme a vu son flux
 * @author mini-john
 */
public class CycleDto implements Comparable<CycleDto>{

    private JewishDate jDate;
    private Date date;
    private int haflaga;
    private Ona ona;
    private double latitude ;
    private double longitude;
    private double elevation ;
    private Long id;
    
    public CycleDto() {
    }

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
    
    public GeoLocation getLGeolocation(String locationName,TimeZone timeZone){
                return new GeoLocation(locationName, latitude, longitude, elevation, timeZone);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CycleDto{ id="+id+ ", jDate=" + jDate + ", date=" + date + ", haflaga=" + haflaga + ", ona=" + ona + ", latitude=" + latitude + ", longitude=" + longitude + ", elevation=" + elevation + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CycleDto other = (CycleDto) obj;
        
        return (this.haflaga==other.haflaga) && (this.ona==other.ona);
    }

    @Override
    public int compareTo(CycleDto o) {
        Calendar cal1=GregorianCalendar.getInstance();
        cal1.setTime(this.date);
        Calendar cal2=GregorianCalendar.getInstance();
        cal2.setTime(o.date); 
       return cal1.compareTo(cal2);
    }
    
}
