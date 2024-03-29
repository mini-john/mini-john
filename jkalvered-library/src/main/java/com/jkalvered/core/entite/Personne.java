/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.entite;

import com.jkalvered.library.enumeration.Sexe;
import com.jkalvered.library.enumeration.TypeCycle;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entité representant la personne morale utilisant l'application
 *
 * @author jonat
 */
@Entity
@ToString
public class Personne implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personne_sequence")
    @SequenceGenerator(name = "personne_sequence", sequenceName = "personne_sequence", initialValue = 1, allocationSize = 1)
    private Long id;
    private String nom;
    private String prenom;
    private Boolean autoLocalisation;
    private String locationName;
    private double latitude;
    private double longitude;
    private double elevation;
    private String timeZone;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private TypeCycle typeCycle = TypeCycle.LoKavoua;
    @Enumerated(EnumType.STRING)
    private Sexe sexe;
    @OneToOne(cascade = {CascadeType.ALL}, mappedBy = "personne")
    @ToString.Exclude
    private Account account;
    @OneToOne(cascade = {CascadeType.ALL}, mappedBy = "personne")
    private Configuration configuration;
    @OneToOne(cascade = {CascadeType.ALL})
    private Personne conjoint;
    @Getter
    @Setter
    @OneToMany(mappedBy = "personne", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity = Niddah.class)
    private List<Niddah> niddahs = new ArrayList<>();
    @Getter
    @Setter
    @OneToMany(mappedBy = "personne", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Cycle> vesset = new ArrayList<Cycle>();

    public void addNiddah(Niddah niddah) {
        this.niddahs.add(niddah);
        niddah.setPersonne(this);
    }

    public void removeNiddah(Niddah niddah) {
        this.niddahs.remove(niddah);
        niddah.setPersonne(null);
    }

    public void addCycle(Cycle cycle) {
        this.vesset.add(cycle);
        cycle.setPersonne(this);
    }

    public void removeCycle(Cycle cycle) {
        this.vesset.remove(cycle);
        cycle.setPersonne(null);
    }

    public Long getId() {
        return id;
    }

    public Personne getConjoint() {
        return conjoint;
    }

    public void setConjoint(Personne conjoint) {
        this.conjoint = conjoint;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Boolean getAutoLocalisation() {
        return autoLocalisation;
    }

    public void setAutoLocalisation(Boolean autoLocalisation) {
        this.autoLocalisation = autoLocalisation;
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

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personne)) {
            return false;
        }
        Personne other = (Personne) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
