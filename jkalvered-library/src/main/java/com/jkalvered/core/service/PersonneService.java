/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.service;

import com.jkalvered.core.dto.PersonneDto;
import com.jkalvered.core.dto.tool.Localisation;
import com.jkalvered.core.entite.Account;
import com.jkalvered.core.entite.Configuration;
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
        Personne personne = personneRepository.getByLogin(login);
        PersonneDto res = this.jkalveredModelMapper.convert(personne, PersonneDto.class);
        return res;
    }

    public Account getAccountByLogin(String login) {
        Account personne = personneRepository.getByLogin(login).getAccount();

        return personne;
    }

    public void updateLocalisation(Long personneId, Localisation localisation) {
        Personne personne = personneRepository.findById(Personne.class, personneId);
        Configuration configuration = personne.getConfiguration();
        configuration.setLatitude(localisation.getLatitude());
        configuration.setLongitude(localisation.getLongitude());
        configuration.setElevation(localisation.getElevation());
        configuration.setTimeZone(localisation.getTimeZone());
        configuration.setLocationName(localisation.getLocationName());
        personneRepository.update(configuration);

    }

}
