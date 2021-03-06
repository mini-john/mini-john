/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.core.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author mini-john
 */
@Entity
public class ConfigurationApplication implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "configuration_sequence")
    @SequenceGenerator(name = "configuration_sequence", sequenceName = "configuration_sequence")
     private Long id;
    
    private int nbHourJetonValid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNbHourJetonValid() {
        return nbHourJetonValid;
    }

    public void setNbHourJetonValid(int nbHourJetonValid) {
        this.nbHourJetonValid = nbHourJetonValid;
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
        if (!(object instanceof ConfigurationApplication)) {
            return false;
        }
        ConfigurationApplication other = (ConfigurationApplication) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.niddah.core.entity.ConfigurationApplication[ id=" + id + " ]";
    }
    
}
