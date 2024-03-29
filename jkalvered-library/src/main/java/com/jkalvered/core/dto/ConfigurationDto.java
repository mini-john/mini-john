/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.dto;

import com.jkalvered.core.entite.Personne;
import com.jkalvered.library.enumeration.Origine;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO de l'entite configuration
 *
 * @see com.jkalvered.core.entite.Configuration
 * @author jonat
 */
@Getter
@Setter
@ToString
public class ConfigurationDto implements Serializable{

    private Long id;
    private Personne personne;
    private String locationName;
    private double latitude;
    private double longitude;
    private double elevation;
    private String timeZone;
    private Origine origine;
    private boolean doMohDahouk;
    private boolean bneTorah;

    /*Les prichot particulières*/
    private boolean prichaBenonitHovotDaat;
    private boolean prichaHoutChani;
    private boolean prihaHovotYair;
    private boolean prichaOrZaroua;

}
