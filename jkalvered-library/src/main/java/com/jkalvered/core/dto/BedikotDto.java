/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * Dto de l'entite bedikot
 * @see com.jkalvered.core.entite.Bedikot
 * @author jonat
 */
@Getter
@Setter
public class BedikotDto implements Serializable{

    private Long id;
    private String locationName;
    private double latitude;
    private double longitude;
    private double elevation;
    private String timeZone;
    private Date dateBedika1;
    private Boolean etatBedika1;
    private Date dateBedika2;
    private Boolean etatBedika2;
    private ChevaNekiymDto chevaNekiym;

}
