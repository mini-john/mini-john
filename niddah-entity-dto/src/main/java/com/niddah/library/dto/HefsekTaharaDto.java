/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.library.dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author mini-john
 */
class HefsekTaharaDto extends EvenementDto implements Serializable{
    private CycleDto cycle;
    private List<ShevaNekiymDto> shevaNekym;
    private Boolean passed;

    public CycleDto getCycle() {
        return cycle;
    }

    public void setCycle(CycleDto cycle) {
        this.cycle = cycle;
    }

    public List<ShevaNekiymDto> getShevaNekym() {
        return shevaNekym;
    }

    public void setShevaNekym(List<ShevaNekiymDto> shevaNekym) {
        this.shevaNekym = shevaNekym;
    }

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }
    
    
}
