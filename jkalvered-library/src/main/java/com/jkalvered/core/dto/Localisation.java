/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.dto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author jonat
 */
@Getter
@Setter
public class Localisation {

    private String locationName;
    private double latitude;
    private double longitude;
    private double elevation;
    private String timeZone;
    private Boolean localited = false;
}
