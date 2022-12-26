/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.service;

import com.jkalvered.core.dto.Localisation;
import com.jkalvered.core.dto.PrichaDto;
import com.jkalvered.core.entite.Configuration;
import com.jkalvered.core.entite.Cycle;
import com.jkalvered.core.entite.Personne;
import com.jkalvered.core.entite.Pricha;
import com.jkalvered.core.modelmapper.JkalveredModelMapper;
import com.jkalvered.core.repository.CycleRepository;
import com.jkalvered.core.repository.PersonneRepository;
import com.jkalvered.library.enumeration.TypeCycle;
import com.jkalvered.library.exception.JKalveredDataException;
import com.jkalvered.library.pricha.PrichaLibrary;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javatuples.Pair;
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

    private static final Logger LOGGER = LogManager.getLogger();

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
            if(dateVue.before(cyclePrecedent.getDateVue())){
                throw new JKalveredDataException("Problème de date !! le cycle précédent à une date superieur au cycle encours");
            }
            PrichaDto prichaHaflagaDto = PrichaLibrary.getPrichaHaflaga(cyclePrecedent.getDateVue(), dateVue, cycle.getLocationName(), cycle.getLatitude(), cycle.getLongitude(), cycle.getElevation(), cycle.getTimeZone());
            Pricha prichaHaflaga = mapper.convert(prichaHaflagaDto, Pricha.class);
            cycle.addPricha(prichaHaflaga);
        }
        cycleRepository.persist(cycle);
    }

    /**
     * Fonction qui gère l'insertion des Prichots spécial en fonction de la
     * configuration de la personne
     *
     * @param idFemme
     * @param idCycle
     * @param localisation
     */
    public void addPrichotSpecial(Long idFemme, Long idCycle, Localisation localisation) {
        Personne personne = cycleRepository.findById(Personne.class, idFemme);
        Configuration configuration = personne.getConfiguration();
        List<Pricha> listToPersist = new ArrayList<>();
        if (personne.getTypeCycle() != TypeCycle.LoKavoua) {
            throw new JKalveredDataException("La personne d'id=" + personne.getId() + " n'a pas un cycle irregulier");
        }
        Cycle cycle = cycleRepository.getCycleByIdAndIdFemme(idCycle, idFemme);
        if (cycle == null) {
            throw new JKalveredDataException("Le cycle d'id:" + idCycle + "n'existe pas pour la personne d'id=" + idFemme);
        }
        if (configuration.isPrichaBenonitHovotDaat()) {
            Pair<PrichaDto, PrichaDto> prichaBenonitHovotDaat = PrichaLibrary.getPrichaBenonitHovotDaat(cycle.getDateVue(), cycle.getLocationName(), cycle.getLatitude(), cycle.getLongitude(), cycle.getElevation(), cycle.getTimeZone());
            Pricha prichaBenonitHovotDaat1 = mapper.convert(prichaBenonitHovotDaat.getValue0(), Pricha.class);
            Pricha prichaBenonitHovotDaat2 = mapper.convert(prichaBenonitHovotDaat.getValue1(), Pricha.class);

            listToPersist.add(prichaBenonitHovotDaat1);
            listToPersist.add(prichaBenonitHovotDaat2);

        }
        if (configuration.isPrichaHoutChani()) {
            Pair<PrichaDto, PrichaDto> prichaHoutChani = PrichaLibrary.getPrichaHoutChani(cycle.getDateVue(), cycle.getLocationName(), cycle.getLatitude(), cycle.getLongitude(), cycle.getElevation(), cycle.getTimeZone());
            Pricha prichaHoutChani1 = mapper.convert(prichaHoutChani.getValue0(), Pricha.class);
            Pricha prichaHoutChani2 = mapper.convert(prichaHoutChani.getValue1(), Pricha.class);

            listToPersist.add(prichaHoutChani1);
            listToPersist.add(prichaHoutChani2);

        }

        if (configuration.isPrihaHovotYair()) {
            PrichaDto prichaHovotYairDTO = PrichaLibrary.getPrichaHovotYair(cycle.getDateVue(), cycle.getLocationName(), cycle.getLatitude(), cycle.getLongitude(), cycle.getElevation(), cycle.getTimeZone());
            Pricha prichaHovotYair = mapper.convert(prichaHovotYairDTO, Pricha.class);
            listToPersist.add(prichaHovotYair);
        }

        if (configuration.isPrichaOrZaroua()) {
            LOGGER.info("Taille du bouzin {}", cycle.getPrichots().size());
            for (Pricha prichot : cycle.getPrichots()) {
                PrichaDto conterted = mapper.convert(prichot, PrichaDto.class);
                PrichaDto prichaOrZarouaDTO = PrichaLibrary.getPrichaOrZaroua(cycle.getDateVue(), conterted);
                Pricha prichaOrZaroua = mapper.convert(prichaOrZarouaDTO, Pricha.class);
                listToPersist.add(prichaOrZaroua);
            }

        }
        listToPersist.forEach(prichot -> {
            cycle.addPricha(prichot);
            cycleRepository.persist(prichot);
        });

    }

}
