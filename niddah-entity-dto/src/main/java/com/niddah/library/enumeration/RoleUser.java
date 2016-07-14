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
public enum RoleUser {
    Admin("Admin"),User("User");
    private String name;
     //Constructeur
    RoleUser(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
