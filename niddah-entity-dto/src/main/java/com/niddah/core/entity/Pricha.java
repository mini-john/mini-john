/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.core.entity;

import com.niddah.library.enumeration.Ona;
import com.niddah.library.enumeration.TypePricha;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;

/**
 *
 * @author mini-john
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Pricha extends Evenement implements Serializable {

    @ManyToOne
    private Cycle cycle;
    private TypePricha typePricha;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateBedika1;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateBedika2;
    private boolean etatBedika1;
    private boolean etatBedika2;
    private int haflagaDay;
    private Ona ona;

    public Date getDateBedika1() {
        return dateBedika1;
    }

    public void setDateBedika1(Date dateBedika1) {
        this.dateBedika1 = dateBedika1;
    }

    public Date getDateBedika2() {
        return dateBedika2;
    }

    public void setDateBedika2(Date dateBedika2) {
        this.dateBedika2 = dateBedika2;
    }

    public boolean isEtatBedika1() {
        return etatBedika1;
    }

    public void setEtatBedika1(boolean etatBedika1) {
        this.etatBedika1 = etatBedika1;
    }

    public boolean isEtatBedika2() {
        return etatBedika2;
    }

    public void setEtatBedika2(boolean etatBedika2) {
        this.etatBedika2 = etatBedika2;
    }

    public int getHaflagaDay() {
        return haflagaDay;
    }

    public void setHaflagaDay(int haflagaDay) {
        this.haflagaDay = haflagaDay;
    }

    public Ona getOna() {
        return ona;
    }

    public void setOna(Ona ona) {
        this.ona = ona;
    }
    
    

    public TypePricha getTypePricha() {
        return typePricha;
    }

    public void setTypePricha(TypePricha typePricha) {
        this.typePricha = typePricha;
    }

    public Cycle getCycle() {
        return cycle;
    }

    public void setCycle(Cycle cycle) {
        this.cycle = cycle;
    }

    @Override
    public String toString() {
        return "com.niddah.core.entity.Prichot[ id=" + this.getId() + " ]";
    }

}
