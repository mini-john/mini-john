/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.entite;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import lombok.Getter;
import lombok.Setter;

/**
 * Entité représentant la bedika qu'une femme fait pendant les cheva nekiym
 * @author jonat
 */
@Entity
public class Bedikot implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "private_sequence")
    @SequenceGenerator(name = "private_sequence", sequenceName = "private_sequence", initialValue = 1,allocationSize = 1)
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
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateBedika1;
    @Getter
    @Setter
    private Boolean etatBedika1;

    @Getter
    @Setter
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateBedika2;
    @Getter
    @Setter
    private Boolean etatBedika2;
    
    
    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ChevaNekiym chevaNekiym;

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
        if (!(object instanceof Bedikot)) {
            return false;
        }
        Bedikot other = (Bedikot) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jkalvered.core.entite.Bedikot[ id=" + id + " ]";
    }

}
