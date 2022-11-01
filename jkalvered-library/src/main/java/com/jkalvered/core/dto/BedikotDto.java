/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author jonat
 */
@Getter
@Setter
public class BedikotDto {

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
