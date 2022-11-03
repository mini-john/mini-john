/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.dto;

import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Dto de l'entite chevanekiym
 * @see com.jkalvered.core.entite.ChevaNekiym
 * @author jonat
 */
@Getter
@Setter
@ToString
class ChevaNekiymDto {

    private Long id;
    private String locationName;
    private double latitude;
    private double longitude;
    private double elevation;
    private String timeZone;
    private Date dateDebut;
    private Date dateFin;
    private PurificationDto purification;
    private List<BedikotDto> bedikots;

}
