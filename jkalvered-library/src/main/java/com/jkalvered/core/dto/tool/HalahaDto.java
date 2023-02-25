/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.dto.tool;

import com.jkalvered.library.enumeration.Origine;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author jonat
 */
@Getter
@Setter
@ToString
public class HalahaDto {

    private Long id;
    private Origine origine;
    private boolean doMohDahouk;
    private boolean bneTorah;

    /*Les prichot particulières*/
    private boolean prichaBenonitHovotDaat;
    private boolean prichaHoutChani;
    private boolean prihaHovotYair;
    private boolean prichaOrZaroua;

}
