/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.library.dto;

import javax.validation.constraints.Size;

/**
 *
 * @author mini-john
 */
public class FemmeDto {

    private Long id;
    @Size(min = 1, message = "Taille Invalide")
    private String nom;
    @Size(min = 1, message = "Taille Invalide")
    private String prenom;
    private Boolean autoLocalisation;
    private double latitude;
    private double longitude;
    private double elevation;
    private AccountDto account;
    private ConfigurationDto configuration;

    public Long getId() {
        return id;
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

    public AccountDto getAccount() {
        return account;
    }

    public void setAccount(AccountDto account) {
        this.account = account;
    }

    public ConfigurationDto getConfiguration() {
        return configuration;
    }

    public void setConfiguration(ConfigurationDto configuration) {
        this.configuration = configuration;
    }

}
