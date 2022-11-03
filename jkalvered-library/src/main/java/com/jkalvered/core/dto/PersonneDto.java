/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.dto;

import com.jkalvered.library.enumeration.Sexe;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO représentant l'entité personne
 * @see com.jkalvered.core.entite.Personne
 * @author jonat
 */
@Getter
@Setter
@ToString
public class PersonneDto {

    private Long id;
    private String nom;
    private String prenom;
    private Boolean autoLocalisation;
    private double latitude;
    private double longitude;
    private double elevation;
    private Sexe sexe;
    private AccountDto account;
    private ConfigurationDto configuration;
    private PersonneDto conjoint;
    private List<NiddahDto> niddahs;
    private List<CycleDto> vesset;
}
