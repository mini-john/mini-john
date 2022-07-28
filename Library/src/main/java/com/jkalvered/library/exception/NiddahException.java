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
public class NiddahException extends RuntimeException {

    /**
     * Creates a new instance of <code>NiddahException</code> without detail
     * message.
     */
    public NiddahException() {
    }

    /**
     * Constructs an instance of <code>NiddahException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public NiddahException(String msg) {
        super(msg);
    }
}
