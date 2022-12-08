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
 * DTO representant l'entité niddah
 * @see com.jkalvered.core.entite.Niddah
 * @author jonat
 */
@Getter
@Setter
@ToString
public class NiddahDto  {

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
    
    private Date dateDernierRapport;

    public NiddahDto() {
    }

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

    

    public void setHaflaga(int haflaga) {
        this.haflaga = haflaga;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    

}
