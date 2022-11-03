/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkalvered.core.entite;

import com.jkalvered.library.enumeration.EtatAccount;
import com.jkalvered.library.enumeration.RoleUser;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;
import lombok.ToString;

/**
 * Entite représentant le compte utilisateur
 * Le compte a un userRole permettant de determine quel type de personne se connecte (Femme,mari,rav ou admin)
 * @author jonat
 */
@Entity
@Table(name = "Account",
        uniqueConstraints = {
            @UniqueConstraint(name = "emailExist", columnNames = {"mail"}),
            @UniqueConstraint(name = "loginExist", columnNames = {"login"})})
@ToString
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "account_sequence")
    @SequenceGenerator(name = "account_sequence", sequenceName = "account_sequence")
    private Long id;

    private String login;
    private String password;
    private String mail;
    private int nbEssais;
    private String jeton;
    private Boolean accountBlock = false;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateLimiteJeton;
    @Enumerated(EnumType.STRING)
    private EtatAccount etatAccount;
    @OneToOne
    @JoinColumn(name = "id")
    private Personne personne;
    @Enumerated(EnumType.STRING)
    private RoleUser roleUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleUser getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(RoleUser roleUser) {
        this.roleUser = roleUser;
    }

    public String getLogin() {
        return login;
    }

    public String getJeton() {
        return jeton;
    }

    public void setJeton(String jeton) {
        this.jeton = jeton;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

}
