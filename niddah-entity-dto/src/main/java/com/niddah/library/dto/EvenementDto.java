/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.library.dto;

import java.util.Date;

/**
 *
 * @author mini-john
 */
public class EvenementDto {

    private Long id;
    private Date dateGregorian;
    private int jewishDay;
    private int jewishMont;
    private int jewishYear;

    private String commentaire;
    private AgendaDto agenda;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateGregorian() {
        return dateGregorian;
    }

    public void setDateGregorian(Date dateGregorian) {
        this.dateGregorian = dateGregorian;
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

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public AgendaDto getAgenda() {
        return agenda;
    }

    public void setAgenda(AgendaDto agenda) {
        this.agenda = agenda;
    }
    
    
}
