/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.entite;

import com.jkalvered.library.date.JkalDate;
import com.jkalvered.library.enumeration.Ona;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

/**
 * Entité represantant le statut de niddah que la femme obtient quand un flux
 * apparait TODO: a voir si on met quand elle se déclare tame sans l'apparition
 * du flux
 *
 * @author jonat
 */
@Entity(name = "Niddah")
public class Niddah implements Serializable, Comparable<Niddah> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "niddah_sequence")
    @SequenceGenerator(name = "niddah_sequence", sequenceName = "niddah_sequence", initialValue = 1, allocationSize = 1)
    private Long id;
    @Getter
    @Setter
    @ManyToOne( fetch = FetchType.LAZY)
    private Personne personne;
    @Getter
    @Setter
    private String locationName;
    @Getter
    @Setter
    private double latitude;
    @Getter
    @Setter
    private double longitude;
    @Getter
    @Setter
    private double elevation;
    @Getter
    @Setter
    private String timeZone;
    @Getter
    @Setter
    private int haflaga;
    @Getter
    @Setter
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateVue;
    @Getter
    @Setter
    private String commentaire;

    @Getter
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDernierRapport;

    @Getter
    @Setter
    @OneToMany(mappedBy = "niddah", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH})
    private List<Purification> purifications = new ArrayList<>();

    public Niddah() {
        super();
    }

    public void addPurification(Purification purification) {
        this.purifications.add(purification);
        purification.setNiddah(this);
    }

    public void removePurification(Purification purification) {
        this.purifications.remove(purification);
        purification.setNiddah(null);
    }

    public Niddah(Date dateVue, String locationName, double latitude, double longitude, double elevation, String timeZone) {
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

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "com.jkalvered.core.entite.Cycle[ id=" + id + " ]";
    }
    @Transient
    private JkalDate jKalDate;

    public JkalDate getjKalDate() {
        if (jKalDate == null) {
            this.jKalDate = new JkalDate(dateVue, locationName, latitude, longitude, elevation, timeZone);

        }
        return jKalDate;
    }

    public Ona getOna() {
        getjKalDate();
        return jKalDate.getOna();
    }

    @Override
    public int compareTo(Niddah o) {
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
        final Niddah other = (Niddah) obj;

        return (this.haflaga == other.haflaga) && (this.getOna() == other.getOna());
    }
}
