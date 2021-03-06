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
 * @author mini-john
 */
public class AccountDto {

    private Long id;
    @Size(min = 1)
    @Pattern(regexp = "^[a-zA-Z0-9]*")
    private String login;
   
    private String password;
    
    private String confirmation;
    @Email
    @Size(min = 1)
    private String mail;
    private int nbEssais;
    private String jeton;
    private Boolean accountBlock;
    private Date dateLimiteJeton;
    private EtatAccount etatAccount;
    private PersonneDto personne;
    private RoleUser role;

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

    public void setPersonne(PersonneDto personne) {
        this.personne = personne;
    }

    public PersonneDto getPersonne() {
        return personne;
    }

    public RoleUser getRole() {
        return role;
    }

    public void setRole(RoleUser role) {
        this.role = role;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

}
