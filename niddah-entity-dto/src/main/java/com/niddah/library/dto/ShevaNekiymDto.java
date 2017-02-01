/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.library.dto;

import java.io.Serializable;

/**
 *
 * @author mini-john
 */
class ShevaNekiymDto extends EvenementDto implements Serializable {
    private HefsekTaharaDto hefsekTahara;
    private Boolean passed;

    public HefsekTaharaDto getHefsekTahara() {
        return hefsekTahara;
    }

    public void setHefsekTahara(HefsekTaharaDto hefsekTahara) {
        this.hefsekTahara = hefsekTahara;
    }

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }
    
    
}
