/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.core.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Boccara Jonathan
 */
@Entity
@Table(name = "Newsletter", uniqueConstraints = {
    @UniqueConstraint(name = "emailExist", columnNames = {"email"})})
public class Newsletter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "newsletter_sequence")
    @SequenceGenerator(name = "newsletter_sequence", sequenceName = "newsletter_sequence")
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date dateInscription;
    @Temporal(TemporalType.DATE)
    private Date dateSuppresion;
    private boolean actif;

    private String email;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public Date getDateSuppresion() {
        return dateSuppresion;
    }

    public void setDateSuppresion(Date dateSuppresion) {
        this.dateSuppresion = dateSuppresion;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

}
