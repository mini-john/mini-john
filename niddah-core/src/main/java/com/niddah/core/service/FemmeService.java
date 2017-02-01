/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.core.service;

import com.niddah.core.castor.NiddahCastor;
import com.niddah.core.entity.Femme;
import com.niddah.core.repository.FemmeRepository;
import com.niddah.library.dto.FemmeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mini-john
 */
@Service
@Transactional
public class FemmeService extends CrudService{

    @Autowired
    FemmeRepository femmeRepository;
    

    

}
