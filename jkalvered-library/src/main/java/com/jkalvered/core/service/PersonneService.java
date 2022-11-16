/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.service;

import com.jkalvered.core.dto.PersonneDto;
import com.jkalvered.core.entite.Personne;
import com.jkalvered.core.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jonat
 */
@Service
@Transactional
public class PersonneService extends CrudService {

    @Autowired
    PersonneRepository personneRepository;

    public PersonneDto getPersonneByLogin(String login) {
        Personne personne =personneRepository.getByLogin(login);
        PersonneDto res=this.jkalveredModelMapper.convert(personne, PersonneDto.class);
        return res;
    }

}
