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
public class NiddahDataException extends Exception {

    /**
     * Creates a new instance of <code>NiddahDataException</code> without detail
     * message.
     */
    public NiddahDataException() {
    }

    /**
     * Constructs an instance of <code>NiddahDataException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NiddahDataException(String msg) {
        super(msg);
    }
}
