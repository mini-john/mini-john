/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.entite;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

/**
 * Entité represantant le statut de niddah que la femme obtient quand un flux apparait
 * TODO: a voir si on met quand elle se déclare tame sans l'apparition du flux
 * @author jonat
 */
@Entity(name = "Niddah")
public class Niddah implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "niddah_sequence")
    @SequenceGenerator(name = "niddah_sequence", sequenceName = "niddah_sequence")
    private Long id;
    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Personne personne;
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
    private int haflaga;
    @Getter
    @Setter
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateVue;
    @Getter
    @Setter
    private String commentaire;
    @Getter
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDernierRapport;

    
    
    @Getter
    @Setter
    @OneToMany(mappedBy = "niddah", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Purification> purifications;

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
        if (!(object instanceof Niddah)) {
            return false;
        }
        Niddah other = (Niddah) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jkalvered.core.entite.Cycle[ id=" + id + " ]";
    }

}
