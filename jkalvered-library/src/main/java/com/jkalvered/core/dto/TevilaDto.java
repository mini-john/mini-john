/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO représentant l'entite tevila
 * @see com.jkalvered.core.entite.Tevila
 * @author jonat
 */
@Getter
@Setter
@ToString
public class TevilaDto implements Serializable{
     private Long id;
    private String locationName;
    private double latitude;
    private double longitude;
    private double elevation;
    private String timeZone;
    private PurificationDto purification;
    private Date dateTevila;
    private Boolean etatTevila;
}
