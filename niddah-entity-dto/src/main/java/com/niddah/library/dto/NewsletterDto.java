/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.library.dto;

import java.util.Date;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author Boccara Jonathan
 */
public class NewsletterDto {

    private Long id;
    private Date dateInscription;
    private Date dateSuppresion;
    private boolean actif;
    @Email
    private String email;

    public Long getId() {
        return id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
