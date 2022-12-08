/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.service;

import com.jkalvered.core.dto.Localisation;
import com.jkalvered.core.dto.PrichaDto;
import com.jkalvered.core.entite.Cycle;
import com.jkalvered.core.entite.Personne;
import com.jkalvered.core.entite.Pricha;
import com.jkalvered.core.modelmapper.JkalveredModelMapper;
import com.jkalvered.core.repository.CycleRepository;
import com.jkalvered.core.repository.PersonneRepository;
import com.jkalvered.library.enumeration.TypeCycle;
import com.jkalvered.library.exception.JKalveredDataException;
import com.jkalvered.library.pricha.PrichaLibrary;
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
public class CycleService extends CrudService {

    @Autowired
    private CycleRepository cycleRepository;
    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private JkalveredModelMapper mapper;

    /**
     * Fonction qui gere les prichots pour une femme dont le cycle est lokavoua
     *
     * @param idFemme
     * @param dateVue
     * @param commentaire
     * @param localisation
     */
    public void addCycleVessetLoKavoua(Long idFemme, Date dateVue, String commentaire, Localisation localisation) {
        Personne personne = cycleRepository.findById(Personne.class, idFemme);

        if (personne.getTypeCycle() != TypeCycle.LoKavoua) {
            throw new JKalveredDataException("La personne d'id=" + personne.getId() + " n'a pas un cycle irregulier");
        }
        Cycle cycle = new Cycle();
        
        if (localisation.getLocalited()) {
            cycle.setElevation(localisation.getElevation());
            cycle.setLatitude(localisation.getLatitude());
            cycle.setLongitude(localisation.getLongitude());
            cycle.setLocationName(localisation.getLocationName());
            cycle.setTimeZone(localisation.getTimeZone());
        } else {
            cycle.setElevation(personne.getElevation());
            cycle.setLatitude(personne.getLatitude());
            cycle.setLongitude(personne.getLongitude());
            cycle.setLocationName(personne.getLocationName());
            cycle.setTimeZone(personne.getTimeZone());
        }
        cycle.setDateVue(dateVue);
        cycle.setTypeCycle(TypeCycle.LoKavoua);
        personne.addCycle(cycle);

        PrichaDto prichaBenonitDto = PrichaLibrary.getPrichaBenonit(dateVue, cycle.getLocationName(), cycle.getLatitude(), cycle.getLongitude(), cycle.getElevation(), cycle.getTimeZone());
        Pricha prichaBenonit = mapper.convert(prichaBenonitDto, Pricha.class);
        cycle.addPricha(prichaBenonit);
        PrichaDto prichaHahodeshDto = PrichaLibrary.getPrichaHahodesh(dateVue, cycle.getLocationName(), cycle.getLatitude(), cycle.getLongitude(), cycle.getElevation(), cycle.getTimeZone());
        Pricha prichaHahodesh = mapper.convert(prichaHahodeshDto, Pricha.class);
        cycle.addPricha(prichaHahodesh);
        Cycle cyclePrecedent = cycleRepository.getDernierCycle(idFemme);

        if (cyclePrecedent != null) {
            PrichaDto prichaHaflagaDto = PrichaLibrary.getPrichaHaflaga(cyclePrecedent.getDateVue(), dateVue, cycle.getLocationName(), cycle.getLatitude(), cycle.getLongitude(), cycle.getElevation(), cycle.getTimeZone());
            Pricha prichaHaflaga = mapper.convert(prichaHaflagaDto, Pricha.class);
            cycle.addPricha(prichaHaflaga);
        }
        cycleRepository.persist(cycle);
    }

}
