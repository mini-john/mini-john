/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.library.enumeration;

/**
 *
 * @author mini-john
 */
public enum EtatAccount {
    creation("Creation"),
    activation("activation"),
    actif("actif"),bloque("bloque"),
    changementMotDePasse("ChangementMotDePasse");
    private String name;
     //Constructeur
    EtatAccount(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

