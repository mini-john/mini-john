/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkalvered.library.enumeration;

/**
 * Enumeration listant les prichots pris en charge par le système
 * @author mini-john
 */
public enum TypePricha {
    Benonit("Benonit"), 
    Hahodesh("Hahodesh"),
    Haflaga("Haflaga"),
    KartiOupleti("KartiOupleti"),
    HovotDaat("HovotDaat"),
    HoutChani("HoutChani"),
    OrZaroua("OrZaroua");


    private String name = "";

    //Constructeur
    TypePricha(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
