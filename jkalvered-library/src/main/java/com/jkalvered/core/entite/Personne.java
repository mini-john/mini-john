/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.entite;

import com.jkalvered.library.enumeration.Sexe;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author jonat
 */
@Entity
public class Personne implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "personne_sequence")
    @SequenceGenerator(name = "personne_sequence", sequenceName = "personne_sequence")
    private Long id;
    private String nom;
    private String prenom;
    private Boolean autoLocalisation;
    private double latitude;
    private double longitude;
    private double elevation;
    @Enumerated(EnumType.STRING)
    private Sexe sexe;
    @OneToOne(cascade = {CascadeType.ALL}, mappedBy = "personne")
    private Account account;
    @OneToOne(cascade = {CascadeType.ALL}, mappedBy = "personne")
    private Configuration configuration;
    @OneToOne(cascade = {CascadeType.ALL})
    private Personne conjoint;
    

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

    @Override
    public String toString() {
        return "com.jkalvered.core.entite.Femme[ id=" + id + " ]";
    }

}
