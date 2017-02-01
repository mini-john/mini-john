/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.core.entity;

import com.niddah.library.enumeration.Ona;
import com.niddah.library.enumeration.TypeCycle;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author mini-john
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Cycle extends Evenement implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "cycle")
    private List<HefsekTahara> hefsekTaharas;
    @OneToMany(mappedBy = "cycle")
    private List<Pricha> prichots;
    private TypeCycle typeCycle;
    private int haflaga;
    private Ona ona;
    private double latitude;
    private double longitude;
    private double elevation;

    public int getHaflaga() {
        return haflaga;
    }

    public void setHaflaga(int haflaga) {
        this.haflaga = haflaga;
    }

    public Ona getOna() {
        return ona;
    }

    public void setOna(Ona ona) {
        this.ona = ona;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }
    
    

    public TypeCycle getTypeCycle() {
        return typeCycle;
    }

    public void setTypeCycle(TypeCycle typeCycle) {
        this.typeCycle = typeCycle;
    }

    public List<Pricha> getPrichots() {
        return prichots;
    }

    public void setPrichots(List<Pricha> prichots) {
        this.prichots = prichots;
    }

    public List<HefsekTahara> getHefsekTaharas() {
        return hefsekTaharas;
    }

    public void setHefsekTaharas(List<HefsekTahara> hefsekTaharas) {
        this.hefsekTaharas = hefsekTaharas;
    }

    @Override
    public String toString() {
        return "com.niddah.core.entity.Cycle[ id=" + this.getId() + " ]";
    }

}
