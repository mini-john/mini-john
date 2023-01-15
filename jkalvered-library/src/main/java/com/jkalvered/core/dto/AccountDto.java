/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.dto;

import com.jkalvered.library.enumeration.EtatAccount;
import com.jkalvered.library.enumeration.RoleUser;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO de l'entité Account
 * @see com.jkalvered.core.entite.Account
 * @author jonat
 */
@Getter
@Setter

public class AccountDto implements Serializable{

    private Long id;

    private String login;
    private String password;
    private String mail;
    private int nbEssais;
    private String jeton;
    private Boolean accountBlock = false;
    private Date dateLimiteJeton;
    private EtatAccount etatAccount;
    private PersonneDto personne;
    private RoleUser roleUser;
}
