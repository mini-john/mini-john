/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.dto;

import com.jkalvered.core.entite.Personne;
import com.jkalvered.library.enumeration.Origine;
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
public class ConfigurationDto {

    private Long id;
    private Personne personne;
    private String locationName;
    private double latitude;
    private double longitude;
    private double elevation;
    private String timeZone;
    private Origine origine;
}
