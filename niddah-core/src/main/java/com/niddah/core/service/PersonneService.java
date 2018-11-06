/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.core.service;

import com.niddah.core.entity.Personne;
import com.niddah.core.repository.PersonneRepository;
import com.niddah.library.dto.PersonneDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mini-john
 */
@Service
@Transactional
public class PersonneService extends CrudService {

    @Autowired
    PersonneRepository personneRepository;

    public PersonneDto getByEmail(String email) {
        Personne personne = personneRepository.getByEmail(email);
        return super.niddahCastor.convert(personne, PersonneDto.class);
    }

    public PersonneDto getByLogin(String login) {
        Personne personne = personneRepository.getByLogin(login);
        return super.niddahCastor.convert(personne, PersonneDto.class);
    }

    public boolean isLoginActifExist(String login) {
        return personneRepository.isLoginActifExist(login);
    }

    public boolean isMailActifExist(String mail) {
        return personneRepository.isMailActifExist(mail);
    }

    public PersonneDto isMailCreationExist(String mail) {
        Personne personne = personneRepository.isMailCreationExist(mail);
        if (personne == null) {
            return null;
        }
        return super.niddahCastor.convert(personne, PersonneDto.class);
    }

    public PersonneDto isLoginCreationExist(String login) {
        Personne personne = personneRepository.isLoginCreationExist(login);
        if (personne == null) {
            return null;
        }

        return super.niddahCastor.convert(personne, PersonneDto.class);
    }
     public PersonneDto mergeAdmin(PersonneDto entityDTO) {

        Personne object = niddahCastor.convert(entityDTO, Personne.class);
        object = personneRepository.mergeAdmin(object);

      

        return  niddahCastor.convert(object, entityDTO.getClass());

    }

}
