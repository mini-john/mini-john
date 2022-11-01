/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.dto;

import com.jkalvered.library.date.JkalDate;
import com.jkalvered.library.enumeration.Ona;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author jonat
 */
@Getter
@Setter
@ToString
public class NiddahDto implements Comparable<NiddahDto> {

    private Long id;
    private PersonneDto personne;
    private JkalDate jKalDate;
    private Date dateVue;
    private int haflaga;
    private String locationName;
    private double latitude;
    private double longitude;
    private double elevation;
    private String timeZone;
    private String commentaire;
    @Getter
    @Setter
    private Date dateDernierRapport;

    public NiddahDto(Date dateVue, String locationName, double latitude, double longitude, double elevation, String timeZone) {
        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
        this.timeZone = timeZone;
        this.dateVue = dateVue;
        this.jKalDate = new JkalDate(dateVue, locationName, latitude, longitude, elevation, timeZone);

    }

    public Long getId() {
        return id;
    }

    public PersonneDto getPersonne() {
        return personne;
    }

    public JkalDate getjKalDate() {
        if (jKalDate == null) {
            this.jKalDate = new JkalDate(dateVue, locationName, latitude, longitude, elevation, timeZone);

        }
        return jKalDate;
    }

    public int getHaflaga() {
        return haflaga;
    }

    public String getLocationName() {
        return locationName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getElevation() {
        return elevation;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public Ona getOna() {
        return jKalDate.getOna();
    }

    public void setHaflaga(int haflaga) {
        this.haflaga = haflaga;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public int compareTo(NiddahDto o) {
        Calendar cal1 = GregorianCalendar.getInstance();
        cal1.setTime(this.jKalDate.getDateGregorian());
        Calendar cal2 = GregorianCalendar.getInstance();
        cal2.setTime(o.jKalDate.getDateGregorian());
        return cal1.compareTo(cal2);
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
        final NiddahDto other = (NiddahDto) obj;

        return (this.haflaga == other.haflaga) && (this.getOna() == other.getOna());
    }

}
