/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.library.dto;

import com.niddah.library.enumeration.EtatAccount;
import com.niddah.library.enumeration.RoleUser;
import java.util.Date;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author Boccara Jonathan
 */
public class AdminDto {

    private Long id;
    private String login;

    private String password;

    private String confirmation;
    private String mail;
    private int nbEssais;
    private String jeton;
    private Boolean accountBlock;
    private Date dateLimiteJeton;
    private EtatAccount etatAccount;
    private PersonneDto personne;
    private RoleUser role;

    public AdminDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getNbEssais() {
        return nbEssais;
    }

    public void setNbEssais(int nbEssais) {
        this.nbEssais = nbEssais;
    }

    public String getJeton() {
        return jeton;
    }

    public void setJeton(String jeton) {
        this.jeton = jeton;
    }

    public Boolean getAccountBlock() {
        return accountBlock;
    }

    public void setAccountBlock(Boolean accountBlock) {
        this.accountBlock = accountBlock;
    }

    public Date getDateLimiteJeton() {
        return dateLimiteJeton;
    }

    public void setDateLimiteJeton(Date dateLimiteJeton) {
        this.dateLimiteJeton = dateLimiteJeton;
    }

    public EtatAccount getEtatAccount() {
        return etatAccount;
    }

    public void setEtatAccount(EtatAccount etatAccount) {
        this.etatAccount = etatAccount;
    }

    public PersonneDto getPersonne() {
        return personne;
    }

    public void setPersonne(PersonneDto personne) {
        this.personne = personne;
    }

    public RoleUser getRole() {
        return role;
    }

    public void setRole(RoleUser role) {
        this.role = role;
    }

}
