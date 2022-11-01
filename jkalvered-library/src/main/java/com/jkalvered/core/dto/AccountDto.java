/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.dto;

import com.jkalvered.core.entite.Personne;
import com.jkalvered.library.enumeration.EtatAccount;
import com.jkalvered.library.enumeration.RoleUser;
import java.util.Date;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author jonat
 */
@Getter
@Setter
@ToString
public class AccountDto {

    private Long id;

    private String login;
    private String password;
    private String mail;
    private int nbEssais;
    private String jeton;
    private Boolean accountBlock = false;
    private Date dateLimiteJeton;
    private EtatAccount etatAccount;
    private Personne personne;
    private RoleUser role;
}
