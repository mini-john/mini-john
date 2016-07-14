/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.core.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 *
 * @author mini-john
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class HefsekTahara extends Evenement implements Serializable {

    private static final long serialVersionUID = 1L;
    @ManyToOne(fetch = FetchType.LAZY)
    private Cycle cycle;
    @OneToMany(mappedBy = "hefsekTahara")
    private List<ShevaNekiym> shevaNekym;
    private Boolean passed;

    public List<ShevaNekiym> getShevaNekym() {
        return shevaNekym;
    }

    public void setShevaNekym(List<ShevaNekiym> shevaNekym) {
        this.shevaNekym = shevaNekym;
    }

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }
    

    public Cycle getCycle() {
        return cycle;
    }

    public void setCycle(Cycle cycle) {
        this.cycle = cycle;
    }
  
  

  
  
  

   

    @Override

    public int hashCode() {

        return super.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        return super.equals(object);
    }

    @Override
    public String toString() {
        return "com.niddah.core.entity.Hefsek[ id=" + this.getId() + " ]";
    }

}
