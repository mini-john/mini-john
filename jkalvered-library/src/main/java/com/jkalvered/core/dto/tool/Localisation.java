/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.dto.tool;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
public class Localisation implements Serializable {

    private Long id;
    @NotNull
    @Size(min = 1)
    private String locationName;
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
    @NotNull
    private Double elevation;
    @NotNull
    private String timeZone;
    private Boolean localited = false;
}
