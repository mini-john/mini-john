/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.core.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author mini-john
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class ShevaNekiym extends Evenement implements Serializable {

    private static final long serialVersionUID = 1L;
    @ManyToOne(fetch = FetchType.LAZY)
    private HefsekTahara hefsekTahara;
    private Boolean passed;

    public HefsekTahara getHefsekTahara() {
        return hefsekTahara;
    }

    public void setHefsekTahara(HefsekTahara hefsekTahara) {
        this.hefsekTahara = hefsekTahara;
    }

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }

    @Override
    public String toString() {
        return "com.niddah.core.entity.ShevaNekiym[ id=" + this.getId() + " ]";
    }

}
