/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.dto;

import com.jkalvered.library.enumeration.TypeCycle;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO de l'entite cycle
 * @see com.jkalvered.core.entite.Cycle
 * @author jonat
 */
@Getter
@Setter
public class CycleDto implements Serializable{

    private Long id;
    private String locationName;
    private double latitude;
    private double longitude;
    private double elevation;
    private String timeZone;
    private int haflaga;
    private Date dateVue;
    private TypeCycle typeCycle = TypeCycle.LoKavoua;
    private PersonneDto personne;
    private List<PrichaDto> prichots;
}
