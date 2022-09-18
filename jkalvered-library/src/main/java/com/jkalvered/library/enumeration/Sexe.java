/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkalvered.library.enumeration;

/**
 *
 * @author Boccara Jonathan
 */
public enum Sexe {
    Femme("Femme"),Homme("Homme");
    private final String name;
     //Constructeur
    Sexe(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
