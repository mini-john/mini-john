/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkalvered.library.enumeration;

/**
 *
 * @author mini-john
 */
public enum MomentJournee {
    Matin("Matin"),Jour("Jour"),Soir("Soir");
    
    private String name;
     //Constructeur
    MomentJournee(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
