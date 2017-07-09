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
import javax.persistence.Table;

/**
 *
 * @author Boccara Jonathan
 */
@Entity
@Table(name = "newsletter")
public class Newsletter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "newsletter_sequence")
    @SequenceGenerator(name = "newsletter_sequence", sequenceName = "newsletter_sequence")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    

}
