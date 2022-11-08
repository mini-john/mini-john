/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.service;

import com.jkalvered.core.entite.Niddah;
import com.jkalvered.core.repository.NiddahRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jonat
 */
@Service
@Transactional
public class NiddahService extends CrudService {

    @Autowired
    private NiddahRepository niddahRepository;

    public void addCycle(int idFemme, Date date, String locationName, double latitude, double longitude, double elevation, String timeZone) {
        Niddah niddahPrecedent=this.niddahRepository.getDernierNiddah(idFemme); 
    }
}
