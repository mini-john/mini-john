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
 * DTO representant l'entite purification
 * @see com.jkalvered.core.entite.Purification
 * @author jonat
 */
@Getter
@Setter
@ToString
class PurificationDto {

    private Long id;
    private String locationName;
    private double latitude;
    private double longitude;
    private double elevation;
    private String timeZone;
    private NiddahDto niddah;
    private HefsekTaharaDto hefsekTahara;
    private MohDahoukDto mohDahouk;
    private Date datePurification;
    private List<ChevaNekiymDto> listChevaNekiym;
}
