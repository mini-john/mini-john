/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.dto;

import com.jkalvered.library.enumeration.Sexe;
import com.jkalvered.library.enumeration.TypeCycle;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO représentant l'entité personne
 *
 * @see com.jkalvered.core.entite.Personne
 * @author jonat
 */
@Getter
@Setter

public class PersonneDto implements Serializable{

    private Long id;
    private String nom;
    private String prenom;
    private Boolean autoLocalisation;
    private double latitude;
    private double longitude;
    private double elevation;
    private TypeCycle typeCycle = TypeCycle.LoKavoua;
    private Sexe sexe;
    private AccountDto account;
    private ConfigurationDto configuration;
    private PersonneDto conjoint;
    private List<NiddahDto> niddahs;
    private List<CycleDto> vesset;
}
