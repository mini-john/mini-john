/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package com.jkalvered.library.exception;

/**
 *
 * @author jonat
 */
public class BedikaException extends JKalVeredException {

    /**
     * Creates a new instance of <code>BedikaException</code> without detail
     * message.
     */
    public BedikaException() {
    }

    /**
     * Constructs an instance of <code>BedikaException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public BedikaException(String msg) {
        super(msg);
    }
}
