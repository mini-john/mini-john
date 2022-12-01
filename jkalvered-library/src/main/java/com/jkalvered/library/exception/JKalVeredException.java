/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkalvered.library.exception;

/**
 *
 * @author Boccara Jonathan
 */
public class JKalVeredException extends RuntimeException {

    /**
     * Creates a new instance of <code>JKalVeredException</code> without detail
     * message.
     */
    public JKalVeredException() {
    }

    /**
     * Constructs an instance of <code>JKalVeredException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public JKalVeredException(String msg) {
        super(msg);
    }
}
