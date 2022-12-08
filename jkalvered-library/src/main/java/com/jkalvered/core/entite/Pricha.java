/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.entite;

import com.jkalvered.library.enumeration.TypePricha;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import lombok.Getter;
import lombok.Setter;

/**
 * Entité representant une pricha que la femmme doit respecter à l'approche de son cycle
 * @author jonat
 */
@Entity
public class Pricha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pricha_sequence")
    @SequenceGenerator(name = "pricha_sequence", sequenceName = "pricha_sequence", initialValue = 1,allocationSize = 1)
    private Long id;

    @Getter
    @Setter
    private String locationName;
    @Getter
    @Setter
    private double latitude;
    @Getter
    @Setter
    private double longitude;
    @Getter
    @Setter
    private double elevation;
    @Getter
    @Setter
    private String timeZone;

    @Getter
    @Setter
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date datePricha;

    @Getter
    @Setter
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateBedika1;
    @Getter
    @Setter
    private Boolean etatBedika1;

    @Getter
    @Setter
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateBedika2;
    @Getter
    @Setter
    private Boolean etatBedika2;
    
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    TypePricha typePricha;
    
    @Getter
    @Setter
    @ManyToOne( fetch = FetchType.LAZY)
    private Cycle vesset;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Pricha)) {
            return false;
        }
        Pricha other = (Pricha) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jkalvered.core.entite.Pricha[ id=" + id + " ]";
    }

}
