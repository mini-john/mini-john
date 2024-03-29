/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.entite;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import lombok.Getter;
import lombok.Setter;

/**
 * Entite reprensentant la period de chava nekiym que la femme accompli pendant
 * sa période de purification
 *
 * @author jonat
 */
@Entity
public class ChevaNekiym implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chevanekiym_sequence")
    @SequenceGenerator(name = "chevanekiym_sequence", sequenceName = "chevanekiym_sequence", initialValue = 1,allocationSize = 1)
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
    private Date dateDebut;
    @Getter
    @Setter
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateFin;
    
    @Getter
    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    private Purification purification;
    
    @Getter
    @Setter
    @OrderBy("id")
    @OneToMany(mappedBy = "chevaNekiym", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE})
    private List<Bedikot> bedikots=new ArrayList<>();
    
    public void addBedikot(Bedikot bedikot) {
        this.bedikots.add(bedikot);
        bedikot.setChevaNekiym(this);
    }

    public void removeBedikot(Bedikot bedikot) {
        this.bedikots.remove(bedikot);
        bedikot.setChevaNekiym(null);
    }
    
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
        if (!(object instanceof ChevaNekiym)) {
            return false;
        }
        ChevaNekiym other = (ChevaNekiym) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.jkalvered.core.entite.ChevaNekiym[ id=" + id + " ]";
    }
    
}
