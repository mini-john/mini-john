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
class ConfigurationDto {
    private Long id;
    private FemmeDto femme;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    
    public FemmeDto getFemme() {
        return femme;
    }

    public void setFemme(FemmeDto femme) {
        this.femme = femme;
    }
    
    
    
}
