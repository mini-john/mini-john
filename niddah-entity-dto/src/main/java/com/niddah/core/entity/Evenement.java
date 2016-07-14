/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.core.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import net.sourceforge.zmanim.hebrewcalendar.JewishDate;

/**
 *
 * @author mini-john
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Evenement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "evenement_sequence")
    @SequenceGenerator(name = "evenement_sequence", sequenceName = "evenement_sequence")
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateGregorian;
    private int jewishDay;
    private int jewishMont;
    private int jewishYear;
    
    private String commentaire;
    @ManyToOne
    private Agenda agenda;

    public Date getDateGregorian() {
        return dateGregorian;
    }

    public void setDateGregorian(Date dateGregorian) {
        this.dateGregorian = dateGregorian;
    }

  

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getJewishDay() {
        return jewishDay;
    }

    public void setJewishDay(int jewishDay) {
        this.jewishDay = jewishDay;
    }

    public int getJewishMont() {
        return jewishMont;
    }

    public void setJewishMont(int jewishMont) {
        this.jewishMont = jewishMont;
    }

    public int getJewishYear() {
        return jewishYear;
    }

    public void setJewishYear(int jewishYear) {
        this.jewishYear = jewishYear;
    }

    
    public JewishDate getjDate() {
        JewishDate jDate= new JewishDate();
        jDate.setJewishDate(jewishYear, jewishMont, jewishDay);
        return jDate;
    }

    public void setjDate(JewishDate jDate) {
        this.jewishDay=jDate.getJewishDayOfMonth();
        this.jewishMont=jDate.getJewishMonth();
        this.jewishYear=jDate.getJewishYear();
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evenement)) {
            return false;
        }
        Evenement other = (Evenement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.niddah.core.entity.Evenement[ id=" + id + " ]";
    }
    
}
