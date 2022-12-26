/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkalvered.core.entite;

import com.jkalvered.library.enumeration.Origine;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

/**
 * entite representant le configuration proposé au roleUser(Femme) lui
 * permettant de gérer sa pureté familiale
 *
 * @author mini-john
 */
@Getter
@Setter
@Entity
public class Configuration implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "configuration_sequence")
    @SequenceGenerator(name = "configuration_sequence", sequenceName = "configuration_sequence", initialValue = 1, allocationSize = 1)
    private Long id;
    @OneToOne
    @JoinColumn(name = "id")
    private Personne personne;
    private String locationName;
    @Getter
    private double latitude;
    private double longitude;
    private double elevation;
    private String timeZone;
    private Origine origine;
    private boolean doMohDahouk;
    private boolean bneTorah;

    /*Les prichot particulières*/
    private boolean prichaBenonitHovotDaat;
    private boolean prichaHoutChani;
    private boolean prihaHovotYair;
    private boolean prichaOrZaroua;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Configuration)) {
            return false;
        }
        Configuration other = (Configuration) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.niddah.core.entity.Configuration[ id=" + id + " ]";
    }

}
