/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.library.dto;

/**
 *
 * @author mini-john
 */
public class ConfigurationApplicationDto {
     private Long id;
    
    private int nbHourJetonValid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNbHourJetonValid() {
        return nbHourJetonValid;
    }

    public void setNbHourJetonValid(int nbHourJetonValid) {
        this.nbHourJetonValid = nbHourJetonValid;
    }
    
    
}
