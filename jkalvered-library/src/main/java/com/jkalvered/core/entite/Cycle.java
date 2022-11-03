
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.entite;

import com.jkalvered.library.enumeration.TypeCycle;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import lombok.Getter;
import lombok.Setter;

/**
 * entité représentant l'ensemble des vesset que la femme rencontre à chaque fois quelle est niddah
 * l'analyse de ces entité permet de déterminer si elle a un cycle kavoua ou pas
 * @author jonat
 */
@Entity()
@Table(name = "Vesset")
public class Cycle implements Serializable {

    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "vesset_sequence")
    @SequenceGenerator(name = "vesset_sequence", sequenceName = "vesset_sequence")
    @Id
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
    private int haflaga;

    @Getter
    @Setter
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateVue;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private TypeCycle typeCycle = TypeCycle.LoKavoua;
    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Personne personne;

    @Getter
    @Setter
    @OneToMany(mappedBy = "vesset", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Pricha> prichots;

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
        if (!(object instanceof Cycle)) {
            return false;
        }
        Cycle other = (Cycle) object;
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
