/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.service;

import com.jkalvered.core.repository.CycleRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jonat
 */
public class CycleService extends CrudService {

    @Autowired
    private CycleRepository cycleRepository;

}
