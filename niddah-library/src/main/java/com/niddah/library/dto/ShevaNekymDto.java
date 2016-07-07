/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.library.dto;

import java.util.Date;

/**
 * Dto modélisant les sheva nekym (processus de purification de la femme)
 * @author mini-john
 */
public class ShevaNekymDto {

    private Date bedikaMatin;
    private Date bedikaSoir;
    private boolean ok;

    public ShevaNekymDto(Date bedikaMatin, Date bedikaSoir) {
        this.bedikaMatin = bedikaMatin;
        this.bedikaSoir = bedikaSoir;
    }

    public ShevaNekymDto() {
    }

    public Date getBedikaMatin() {
        return bedikaMatin;
    }

    public void setBedikaMatin(Date bedikaMatin) {
        this.bedikaMatin = bedikaMatin;
    }

    public Date getBedikaSoir() {
        return bedikaSoir;
    }

    public void setBedikaSoir(Date bedikaSoir) {
        this.bedikaSoir = bedikaSoir;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    @Override
    public String toString() {
        return "ShevaNekymDto{" + "bedikaMatin=" + bedikaMatin + ", bedikaSoir=" + bedikaSoir + ", ok=" + ok + "}\n";
    }

}
