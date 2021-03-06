/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.library.dto;

import com.niddah.library.enumeration.Ona;
import com.niddah.library.enumeration.TypePricha;
import java.io.Serializable;
import java.util.Date;
import net.sourceforge.zmanim.hebrewcalendar.JewishDate;

/**
 * Dto modélisant une pricha en fonction de la date du cycle et du type de pricha
 * @author mini-john
 */
public class PrichaDto  extends EvenementDto implements Serializable{

    private TypePricha typePricha;
    private Date date;
    private JewishDate jDate;
    private Date dateBedika1;
    private Date dateBedika2;
    private boolean etatBedika1;
    private boolean etatBedika2;
    private int haflagaDay;
    private Ona ona;

    public PrichaDto() {
    }

    public TypePricha getTypePricha() {
        return typePricha;
    }

    public void setTypePricha(TypePricha typePricha) {
        this.typePricha = typePricha;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public JewishDate getjDate() {
        return jDate;
    }

    public void setjDate(JewishDate jDate) {
        this.jDate = jDate;
    }

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

    @Override
    public String toString() {
        return "PrichaDto{" + "typePricha=" + typePricha + ", date=" + date + ", jDate=" + jDate + ", dateBedika1=" + dateBedika1 + ", dateBedika2=" + dateBedika2 + ", etatBedika1=" + etatBedika1 + ", etatBedika2=" + etatBedika2 + ", haflagaDay=" + haflagaDay + ", ona=" + ona + '}';
    }

   

}
