/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkalvered.library.enumeration;

/**
 * Enumeration représentant la liste des cycles réguliers pris en charge par le système
 * @author mini-john
 */
public enum TypeCycle {
    LoKavoua("LoKavoua"),
    KavouaHahodesh("KavouaHahodesh"),
    KavouaHaflaga("KavouaHaflaga"),
    KavouaHashavoua("KavouaHashavoua"),
    KavouaDilougHahodesh("KavouaDilougHahodesh"),
    KavouaDilougHahodeshHozer("KavouaDilougHahodeshHozer"),
    KavouaDilougHaflaga("KavouaDilougHaflaga"),
    KavouaDilougHaflagaHozer("KavouaDilougHaflagaHozer"),
    KavouaHassiroug("KavouaHassiroug"),
    KavouaHakafoul("KavouaHakafoul"),
    KavouaOness("KavouaOness"),
    KavouaMihouchim("KavouaMihouchim");

    private String name;
    //Constructeur

    TypeCycle(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
