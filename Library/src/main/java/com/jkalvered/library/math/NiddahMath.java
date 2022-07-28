/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkalvered.library.math;

/**
 * Classe permettant de calculer le modulo n d'un nombre
 *
 * @author mini-john
 */
public class NiddahMath {

    /**
     *
     * @param a
     * @param b
     * @return a modulo b
     */
    public static int mod(int a, int b) {
        int c = a % b;
        return (c < 0) ? c + b : c;
    }
}
