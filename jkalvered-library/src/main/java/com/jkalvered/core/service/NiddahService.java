/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.core.service;

import com.jkalvered.core.dto.Localisation;
import com.jkalvered.core.entite.Bedikot;
import com.jkalvered.core.entite.ChevaNekiym;
import com.jkalvered.core.entite.HefsekTahara;
import com.jkalvered.core.entite.MohDahouk;
import com.jkalvered.core.entite.Niddah;
import com.jkalvered.core.entite.Personne;
import com.jkalvered.core.entite.Purification;
import com.jkalvered.core.entite.Tevila;
import com.jkalvered.core.repository.NiddahRepository;
import com.jkalvered.library.constante.Constantes;
import com.jkalvered.library.constante.JKalveredCodeRetour;
import com.jkalvered.library.date.JkalDate;
import com.jkalvered.library.enumeration.NumBedika;
import com.jkalvered.library.exception.NiddahDataException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service gérant la période niddah de la personne
 *
 * @author jonat
 */
@Service
@Transactional
public class NiddahService extends CrudService {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private NiddahRepository niddahRepository;
    @Autowired
    private PersonneService personneService;

    /**
     * Cet méthode a pour objectif de créer et d'inirtialiser le statut niddah
     * de la femme. Les entité créées sont niddah purification hefsektahara
     * mohdahouk et shevanekyim
     *
     * @param idFemme
     * @param dateVue
     * @param dateDernierRapport
     * @param commentaire
     * @param localisation
     */
    public void addPeriodNiddah(Long idFemme, Date dateVue, Date dateDernierRapport, String commentaire, Localisation localisation) {
        if (dateVue.before(dateDernierRapport)) {
            throw new IllegalArgumentException("La date de Vue doit etre apres la date de dernier rapport");
        }
        Personne personne = personneService.getById(idFemme, Personne.class);
        LOGGER.info("Création du statut Niddah de la femme ayant pour Id=%s", personne.getId());
        Niddah niddahEncours = new Niddah();
        personne.addNiddah(niddahEncours);
        niddahEncours.setDateVue(dateVue);
        niddahEncours.setDateDernierRapport(dateDernierRapport);
        niddahEncours.setCommentaire(commentaire);
        if (localisation.getLocalited()) {
            niddahEncours.setElevation(localisation.getElevation());
            niddahEncours.setLatitude(localisation.getLatitude());
            niddahEncours.setLongitude(localisation.getLongitude());
            niddahEncours.setLocationName(localisation.getLocationName());
            niddahEncours.setTimeZone(localisation.getTimeZone());
        } else {
            niddahEncours.setElevation(personne.getElevation());
            niddahEncours.setLatitude(personne.getLatitude());
            niddahEncours.setLongitude(personne.getLongitude());
            niddahEncours.setLocationName(personne.getLocationName());
            niddahEncours.setTimeZone(personne.getTimeZone());
        }
        LOGGER.info("Rajout d'une purification pour la personne d'id{}", personne.getId());
        Purification purification = new Purification();
        niddahEncours.addPurification(purification);
        purification.setElevation(niddahEncours.getElevation());
        purification.setLatitude(niddahEncours.getLatitude());
        purification.setLongitude(niddahEncours.getLongitude());
        purification.setLocationName(niddahEncours.getLocationName());
        purification.setTimeZone(niddahEncours.getTimeZone());
        Date datePurification;

        //Calcul des jours d'attente
        LOGGER.info("Calcul des jours d'attente pour la personne d'id={}", personne.getId());
        Calendar gCal = GregorianCalendar.getInstance();
        switch (personne.getConfiguration().getOrigine()) {
            case Askenaze -> {

                gCal.setTime(dateVue);
                gCal.add(Calendar.DAY_OF_MONTH, Constantes.NB_JOUR_ATTENTE_ASHKENAZE);
            }
            case Sefarade -> {
                JkalDate jKalAttente = new JkalDate(dateDernierRapport, purification.getLocationName(), purification.getLatitude(), purification.getLongitude(), purification.getElevation(), purification.getTimeZone());
                gCal.setTime(dateDernierRapport);

                switch (jKalAttente.getMomentJournee()) {
                    case Jour -> {
                        gCal.add(Calendar.HOUR_OF_DAY, Constantes.NB_JOUR_ATTENTE_SEPHARADE_EN_HEURE_JOUR);

                    }
                    case Matin -> {
                        gCal.add(Calendar.HOUR_OF_DAY, Constantes.NB_JOUR_ATTENTE_SEPHARADE_EN_HEURE_JOUR);

                    }
                    case Soir -> {
                        gCal.add(Calendar.HOUR_OF_DAY, Constantes.NB_JOUR_ATTENTE_SEPHARADE_EN_HEURE_NUIT);

                    }
                    default ->
                        throw new AssertionError();
                }
                LOGGER.info("Voila le resultat " + gCal.getTime());
            }
            default ->
                gCal.add(Calendar.DAY_OF_MONTH, Constantes.NB_JOUR_ATTENTE_DEFAULT);
        }
        datePurification = gCal.getTime();
        purification.setDatePurification(datePurification);

        //calcul de la date du hefsek tahara
        LOGGER.info("Rajout du hefsek tahara pour la personne d'id={}", personne.getId());
        HefsekTahara ht = new HefsekTahara();
        purification.setHefsekTahara(ht);
        ht.setPurification(purification);
        ht.setElevation(niddahEncours.getElevation());
        ht.setLatitude(niddahEncours.getLatitude());
        ht.setLongitude(niddahEncours.getLongitude());
        ht.setLocationName(niddahEncours.getLocationName());
        ht.setTimeZone(niddahEncours.getTimeZone());
        JkalDate jKalDateHT = new JkalDate(datePurification, ht.getLocationName(), ht.getLatitude(), ht.getLongitude(), ht.getElevation(), ht.getTimeZone());
        ht.setDateHefsek(jKalDateHT.getCoucherSoleil());

        //calcul du Mohdahouk
        if (personne.getConfiguration().isDoMohDahouk()) {
            LOGGER.info("Rajout du mohdahouk pour la personne d'id={}", personne.getId());
            MohDahouk md = new MohDahouk();
            purification.setMohDahouk(md);
            md.setPurification(purification);
            md.setElevation(niddahEncours.getElevation());
            md.setLatitude(niddahEncours.getLatitude());
            md.setLongitude(niddahEncours.getLongitude());
            md.setLocationName(niddahEncours.getLocationName());
            md.setTimeZone(niddahEncours.getTimeZone());
            JkalDate jKalDateMD = new JkalDate(datePurification, md.getLocationName(), md.getLatitude(), md.getLongitude(), md.getElevation(), md.getTimeZone());
            md.setDateMoh(jKalDateMD.getCoucherSoleil());
        } else {
            LOGGER.info("La personne d'id={} n'a pas l'habitude de faire un mohdahouk", personne.getId());
        }

        //Calcul des cheva nekiym
        LOGGER.info("Rajout de la periode des chevanekyim pour la personne d'id={}", personne.getId());
        ChevaNekiym chevaNekiym = new ChevaNekiym();
        purification.setChevaNekiym(chevaNekiym);
        chevaNekiym.setPurification(purification);
        chevaNekiym.setElevation(niddahEncours.getElevation());
        chevaNekiym.setLatitude(niddahEncours.getLatitude());
        chevaNekiym.setLongitude(niddahEncours.getLongitude());
        chevaNekiym.setLocationName(niddahEncours.getLocationName());
        chevaNekiym.setTimeZone(niddahEncours.getTimeZone());
        gCal.setTime(datePurification);
        gCal.add(Calendar.DAY_OF_MONTH, 1);
        chevaNekiym.setDateDebut(gCal.getTime());
        int i = 0;
        for (i = 0; i < 7; i++) {
            Bedikot bd = new Bedikot();
            chevaNekiym.addBedikot(bd);
            bd.setElevation(niddahEncours.getElevation());
            bd.setLatitude(niddahEncours.getLatitude());
            bd.setLongitude(niddahEncours.getLongitude());
            bd.setLocationName(niddahEncours.getLocationName());
            bd.setTimeZone(niddahEncours.getTimeZone());
            JkalDate jKalDateBedikot = new JkalDate(gCal.getTime(), ht.getLocationName(), ht.getLatitude(), ht.getLongitude(), ht.getElevation(), ht.getTimeZone());
            bd.setDateBedika1(jKalDateBedikot.getLeverSoleil());
            bd.setDateBedika2(jKalDateBedikot.getCoucherSoleil());
            if (i < 6) {
                gCal.add(Calendar.DAY_OF_MONTH, 1);
            }

        }
        chevaNekiym.setDateFin(gCal.getTime());

        //calcul de la date de la tevila
        LOGGER.info("Rajout de la tevila pout la personne d'id={}", personne.getId());
        Tevila tevila = new Tevila();
        purification.setTevila(tevila);
        tevila.setPurification(purification);
        tevila.setElevation(niddahEncours.getElevation());
        tevila.setLatitude(niddahEncours.getLatitude());
        tevila.setLongitude(niddahEncours.getLongitude());
        tevila.setLocationName(niddahEncours.getLocationName());
        tevila.setTimeZone(niddahEncours.getTimeZone());
        tevila.setDateTevila(gCal.getTime());
        niddahRepository.persist(niddahEncours);
    }

    /**
     * Fonction qui regenère une période de purification car le hfsek tahara est
     * ko
     *
     * @param idHt
     * @param idFemme
     * @param localisation
     * @throws NiddahDataException
     */
    public void hefsekTaharaIsKo(Long idHt, Long idFemme, Localisation localisation) throws NiddahDataException {
        HefsekTahara ht = niddahRepository.getHefsekTaharaByIdAndIdFemme(idHt, idFemme);

        if (ht == null) {
            throw new NiddahDataException("Le hefsektahra d'id:" + idHt + "n'existe pas pour la personne d'id=" + idFemme);
        }
        ht.setEtatHefsek(Boolean.FALSE);
        Personne personne = ht.getPurification().getNiddah().getPersonne();
        LOGGER.info("La personne d'id={} et de login={} a un hefsek tahara pas bon", personne.getId(), personne.getAccount().getLogin());

        //Suppression des bedikot 
        Purification oldPurification = ht.getPurification();
        // oldPurification.getNiddah().removePurification(oldPurification);
        Niddah niddahEncours = ht.getPurification().getNiddah();
        ChevaNekiym tmp = oldPurification.getChevaNekiym();
        oldPurification.deleteChevaNekiym();
        this.niddahRepository.delete(tmp);

        //niddahEncours.getPurifications().
        if (localisation.getLocalited()) {
            niddahEncours.setElevation(localisation.getElevation());
            niddahEncours.setLatitude(localisation.getLatitude());
            niddahEncours.setLongitude(localisation.getLongitude());
            niddahEncours.setLocationName(localisation.getLocationName());
            niddahEncours.setTimeZone(localisation.getTimeZone());
        } else {
            niddahEncours.setElevation(personne.getElevation());
            niddahEncours.setLatitude(personne.getLatitude());
            niddahEncours.setLongitude(personne.getLongitude());
            niddahEncours.setLocationName(personne.getLocationName());
            niddahEncours.setTimeZone(personne.getTimeZone());
        }

        LOGGER.info("Rejout d'une purification pour la personne d'id={}", personne.getId());
        Purification newPurification = new Purification();
        niddahEncours.addPurification(newPurification);
        Calendar gCal = GregorianCalendar.getInstance();
        gCal.setTime(ht.getDateHefsek());
        gCal.add(Calendar.DAY_OF_MONTH, 1);
        Date dateNewPurification = gCal.getTime();
        newPurification.setDatePurification(dateNewPurification);
        newPurification.setElevation(niddahEncours.getElevation());
        newPurification.setLatitude(niddahEncours.getLatitude());
        newPurification.setLongitude(niddahEncours.getLongitude());
        newPurification.setLocationName(niddahEncours.getLocationName());
        newPurification.setTimeZone(niddahEncours.getTimeZone());
        //calcul de la date du hefsek tahara
        LOGGER.info("Rejout d'un heffsekt tahara pour la personne d'Id={}", personne.getId());
        ht = new HefsekTahara();
        newPurification.setHefsekTahara(ht);
        ht.setPurification(newPurification);
        ht.setElevation(niddahEncours.getElevation());
        ht.setLatitude(niddahEncours.getLatitude());
        ht.setLongitude(niddahEncours.getLongitude());
        ht.setLocationName(niddahEncours.getLocationName());
        ht.setTimeZone(niddahEncours.getTimeZone());
        JkalDate jKalDateHT = new JkalDate(dateNewPurification, ht.getLocationName(), ht.getLatitude(), ht.getLongitude(), ht.getElevation(), ht.getTimeZone());
        ht.setDateHefsek(jKalDateHT.getCoucherSoleil());

        //calcul du Mohdahouk
        if (personne.getConfiguration().isDoMohDahouk()) {
            LOGGER.info("Rejout du mohDahouk pour la personne d'Id={}", personne.getId());
            MohDahouk md = new MohDahouk();
            newPurification.setMohDahouk(md);
            md.setPurification(newPurification);
            md.setElevation(niddahEncours.getElevation());
            md.setLatitude(niddahEncours.getLatitude());
            md.setLongitude(niddahEncours.getLongitude());
            md.setLocationName(niddahEncours.getLocationName());
            md.setTimeZone(niddahEncours.getTimeZone());
            JkalDate jKalDateMD = new JkalDate(dateNewPurification, md.getLocationName(), md.getLatitude(), md.getLongitude(), md.getElevation(), md.getTimeZone());
            md.setDateMoh(jKalDateMD.getCoucherSoleil());
        } else {
            LOGGER.info("La personne d'id={} n'a pas l'habitude de faire le mohdahouk");
        }

        //Calcul des cheva nekiym
        LOGGER.info("Rajout des cheva Nekyim pour la personne d'id={}", personne.getId());
        ChevaNekiym chevaNekiym = new ChevaNekiym();
        newPurification.setChevaNekiym(chevaNekiym);
        chevaNekiym.setPurification(newPurification);
        chevaNekiym.setElevation(niddahEncours.getElevation());
        chevaNekiym.setLatitude(niddahEncours.getLatitude());
        chevaNekiym.setLongitude(niddahEncours.getLongitude());
        chevaNekiym.setLocationName(niddahEncours.getLocationName());
        chevaNekiym.setTimeZone(niddahEncours.getTimeZone());
        gCal.setTime(dateNewPurification);
        gCal.add(Calendar.DAY_OF_MONTH, 1);
        chevaNekiym.setDateDebut(gCal.getTime());
        int i = 0;
        for (i = 0; i < 7; i++) {
            Bedikot bd = new Bedikot();
            chevaNekiym.addBedikot(bd);
            bd.setElevation(niddahEncours.getElevation());
            bd.setLatitude(niddahEncours.getLatitude());
            bd.setLongitude(niddahEncours.getLongitude());
            bd.setLocationName(niddahEncours.getLocationName());
            bd.setTimeZone(niddahEncours.getTimeZone());
            JkalDate jKalDateBedikot = new JkalDate(gCal.getTime(), ht.getLocationName(), ht.getLatitude(), ht.getLongitude(), ht.getElevation(), ht.getTimeZone());
            bd.setDateBedika1(jKalDateBedikot.getLeverSoleil());
            bd.setDateBedika2(jKalDateBedikot.getCoucherSoleil());
            if (i < 6) {
                gCal.add(Calendar.DAY_OF_MONTH, 1);
            }

        }
        chevaNekiym.setDateFin(gCal.getTime());

        //calcul de la date de la tevila
        LOGGER.info("Rajout de la tevila pour la personne di'Id={}", personne.getId());
        Tevila tevila = new Tevila();
        newPurification.setTevila(tevila);
        tevila.setPurification(newPurification);

        tevila.setElevation(niddahEncours.getElevation());
        tevila.setLatitude(niddahEncours.getLatitude());
        tevila.setLongitude(niddahEncours.getLongitude());
        tevila.setLocationName(niddahEncours.getLocationName());
        tevila.setTimeZone(niddahEncours.getTimeZone());
        tevila.setDateTevila(gCal.getTime());
        niddahRepository.update(niddahEncours);

    }

    /**
     * Cette méthode à pour objectif de recalculer la periode de purification
     * d'une personne dans le cas ou le mohdahouk n'est pas bon.
     *
     * @param idMohDahouk
     * @param idFemme
     * @param localisation
     * @throws NiddahDataException
     */
    public void mohdDahoukIsKo(Long idMohDahouk, Long idFemme, Localisation localisation) throws NiddahDataException {
        MohDahouk md = niddahRepository.getMohDaoukByIdAndIdFemme(idMohDahouk, idFemme);

        if (md == null) {
            throw new NiddahDataException("Le mohdahouk d'id:" + idMohDahouk + "n'existe pas pour la personne d'id=" + idFemme);
        }
        md.setEtatMoh(Boolean.FALSE);
        Personne personne = md.getPurification().getNiddah().getPersonne();
        LOGGER.info("La personne d'id={} a un mohdaouk pas bon", personne.getId());
        //Suppression des bedikot 
        Purification oldPurification = md.getPurification();
        // oldPurification.getNiddah().removePurification(oldPurification);
        Niddah niddahEncours = md.getPurification().getNiddah();
        ChevaNekiym tmp = oldPurification.getChevaNekiym();
        oldPurification.deleteChevaNekiym();
        this.niddahRepository.delete(tmp);

        //niddahEncours.getPurifications().
        if (localisation.getLocalited()) {
            niddahEncours.setElevation(localisation.getElevation());
            niddahEncours.setLatitude(localisation.getLatitude());
            niddahEncours.setLongitude(localisation.getLongitude());
            niddahEncours.setLocationName(localisation.getLocationName());
            niddahEncours.setTimeZone(localisation.getTimeZone());
        } else {
            niddahEncours.setElevation(personne.getElevation());
            niddahEncours.setLatitude(personne.getLatitude());
            niddahEncours.setLongitude(personne.getLongitude());
            niddahEncours.setLocationName(personne.getLocationName());
            niddahEncours.setTimeZone(personne.getTimeZone());
        }
        LOGGER.info("Rajout d'une purification pour la personne d'id={}", personne.getId());
        Purification newPurification = new Purification();
        niddahEncours.addPurification(newPurification);
        Calendar gCal = GregorianCalendar.getInstance();
        gCal.setTime(md.getDateMoh());
        gCal.add(Calendar.DAY_OF_MONTH, 1);
        Date dateNewPurification = gCal.getTime();
        newPurification.setDatePurification(dateNewPurification);
        newPurification.setElevation(niddahEncours.getElevation());
        newPurification.setLatitude(niddahEncours.getLatitude());
        newPurification.setLongitude(niddahEncours.getLongitude());
        newPurification.setLocationName(niddahEncours.getLocationName());
        newPurification.setTimeZone(niddahEncours.getTimeZone());

        //calcul de la date du hefsek tahara
        LOGGER.info("Rajout du hefsektahara pour la personne d'id={}", personne.getId());
        HefsekTahara ht = new HefsekTahara();
        newPurification.setHefsekTahara(ht);
        ht.setPurification(newPurification);
        ht.setElevation(niddahEncours.getElevation());
        ht.setLatitude(niddahEncours.getLatitude());
        ht.setLongitude(niddahEncours.getLongitude());
        ht.setLocationName(niddahEncours.getLocationName());
        ht.setTimeZone(niddahEncours.getTimeZone());
        JkalDate jKalDateHT = new JkalDate(dateNewPurification, ht.getLocationName(), ht.getLatitude(), ht.getLongitude(), ht.getElevation(), ht.getTimeZone());
        ht.setDateHefsek(jKalDateHT.getCoucherSoleil());

        //calcul du Mohdahouk
        if (personne.getConfiguration().isDoMohDahouk()) {
            LOGGER.info("Rajout d'un mohdahouk pour la personne d'id={}", personne.getId());
            md = new MohDahouk();
            newPurification.setMohDahouk(md);
            md.setPurification(newPurification);
            md.setElevation(niddahEncours.getElevation());
            md.setLatitude(niddahEncours.getLatitude());
            md.setLongitude(niddahEncours.getLongitude());
            md.setLocationName(niddahEncours.getLocationName());
            md.setTimeZone(niddahEncours.getTimeZone());
            JkalDate jKalDateMD = new JkalDate(dateNewPurification, md.getLocationName(), md.getLatitude(), md.getLongitude(), md.getElevation(), md.getTimeZone());
            md.setDateMoh(jKalDateMD.getCoucherSoleil());
        } else {
            LOGGER.info("La personne d'id={} n'a pas l'habitude d'accomplir de mohdahouk");
        }
        //Calcul des cheva nekiym
        LOGGER.info("Rajout de la periode des cheva nekiyim de la personne d'id={}", personne.getId());
        ChevaNekiym chevaNekiym = new ChevaNekiym();
        newPurification.setChevaNekiym(chevaNekiym);
        chevaNekiym.setPurification(newPurification);
        chevaNekiym.setElevation(niddahEncours.getElevation());
        chevaNekiym.setLatitude(niddahEncours.getLatitude());
        chevaNekiym.setLongitude(niddahEncours.getLongitude());
        chevaNekiym.setLocationName(niddahEncours.getLocationName());
        chevaNekiym.setTimeZone(niddahEncours.getTimeZone());
        gCal.setTime(dateNewPurification);
        gCal.add(Calendar.DAY_OF_MONTH, 1);
        chevaNekiym.setDateDebut(gCal.getTime());
        int i = 0;
        for (i = 0; i < 7; i++) {
            Bedikot bd = new Bedikot();
            chevaNekiym.addBedikot(bd);
            bd.setElevation(niddahEncours.getElevation());
            bd.setLatitude(niddahEncours.getLatitude());
            bd.setLongitude(niddahEncours.getLongitude());
            bd.setLocationName(niddahEncours.getLocationName());
            bd.setTimeZone(niddahEncours.getTimeZone());
            JkalDate jKalDateBedikot = new JkalDate(gCal.getTime(), ht.getLocationName(), ht.getLatitude(), ht.getLongitude(), ht.getElevation(), ht.getTimeZone());
            bd.setDateBedika1(jKalDateBedikot.getLeverSoleil());
            bd.setDateBedika2(jKalDateBedikot.getCoucherSoleil());
            if (i < 6) {
                gCal.add(Calendar.DAY_OF_MONTH, 1);
            }

        }
        chevaNekiym.setDateFin(gCal.getTime());

        //calcul de la date de la tevila
        LOGGER.info("Rajout d'une tevila pour la personne d'id={}", personne.getId());
        Tevila tevila = new Tevila();
        newPurification.setTevila(tevila);
        tevila.setPurification(newPurification);
        tevila.setElevation(niddahEncours.getElevation());
        tevila.setLatitude(niddahEncours.getLatitude());
        tevila.setLongitude(niddahEncours.getLongitude());
        tevila.setLocationName(niddahEncours.getLocationName());
        tevila.setTimeZone(niddahEncours.getTimeZone());
        tevila.setDateTevila(gCal.getTime());
        niddahRepository.update(niddahEncours);

    }

    /**
     * Cette fonction a pour objectif de gestion des bedikots
     *
     * @param idBedika
     * @param idFemme
     * @param numBedika
     * @param localisation
     * @return JKalveredCodeRetour.Niddah_Retry_HT_NOW dans le cas ou la bedika1
     * n'est pas bonne on reprogramme un ht le jour meme
     * JKalveredCodeRetour.Niddah_Retry_HT_NOW dans le cas ou la bedika2 n'est
     * pas bonne car on demande à la personne si elle a de refaire un ht le
     * moment meme
     * @throws NiddahDataException
     */
    public int setBedikotKO(Long idBedika, Long idFemme, NumBedika numBedika, Localisation localisation) throws NiddahDataException {
        Bedikot bd = niddahRepository.getBedikotByIdAndIdFemme(idBedika, idFemme);
        int res = JKalveredCodeRetour.Niddah_Retry_HT_NOW;
        if (bd == null) {
            throw new NiddahDataException("La bedika d'id:" + idBedika + "n'existe pas pour la personne d'id=" + idFemme);
        }
        switch (numBedika) {
            case Bedika1 -> {
                //La bedika du matin n'est pas bonne on peut réessayer un ht le jour meme
                Personne personne = bd.getChevaNekiym().getPurification().getNiddah().getPersonne();
                LOGGER.info("La personne d'id={} a la bedika du matin pas bonne", personne.getId());
                //Suppression des bedikot 
                Purification oldPurification = bd.getChevaNekiym().getPurification();
                // oldPurification.getNiddah().removePurification(oldPurification);
                Niddah niddahEncours = bd.getChevaNekiym().getPurification().getNiddah();
                ChevaNekiym tmp = oldPurification.getChevaNekiym();
                bd.setEtatBedika1(Boolean.FALSE);
                niddahRepository.deleteBedikotNull(tmp.getId());
                if (localisation.getLocalited()) {
                    niddahEncours.setElevation(localisation.getElevation());
                    niddahEncours.setLatitude(localisation.getLatitude());
                    niddahEncours.setLongitude(localisation.getLongitude());
                    niddahEncours.setLocationName(localisation.getLocationName());
                    niddahEncours.setTimeZone(localisation.getTimeZone());
                } else {
                    niddahEncours.setElevation(personne.getElevation());
                    niddahEncours.setLatitude(personne.getLatitude());
                    niddahEncours.setLongitude(personne.getLongitude());
                    niddahEncours.setLocationName(personne.getLocationName());
                    niddahEncours.setTimeZone(personne.getTimeZone());
                }
                LOGGER.info("Rajout d'une purification pour la personne d'id={}", personne.getId());
                Purification newPurification = new Purification();
                niddahEncours.addPurification(newPurification);
                Calendar gCal = GregorianCalendar.getInstance();
                gCal.setTime(bd.getDateBedika1());
                //gCal.add(Calendar.DAY_OF_MONTH, 1);
                Date dateNewPurification = gCal.getTime();
                newPurification.setDatePurification(dateNewPurification);
                newPurification.setElevation(niddahEncours.getElevation());
                newPurification.setLatitude(niddahEncours.getLatitude());
                newPurification.setLongitude(niddahEncours.getLongitude());
                newPurification.setLocationName(niddahEncours.getLocationName());
                newPurification.setTimeZone(niddahEncours.getTimeZone());

                //calcul de la date du hefsek tahara
                LOGGER.info("Rajout du hefsektahara pour la personne d'id={}", personne.getId());
                HefsekTahara ht = new HefsekTahara();
                newPurification.setHefsekTahara(ht);
                ht.setPurification(newPurification);
                ht.setElevation(niddahEncours.getElevation());
                ht.setLatitude(niddahEncours.getLatitude());
                ht.setLongitude(niddahEncours.getLongitude());
                ht.setLocationName(niddahEncours.getLocationName());
                ht.setTimeZone(niddahEncours.getTimeZone());
                JkalDate jKalDateHT = new JkalDate(dateNewPurification, ht.getLocationName(), ht.getLatitude(), ht.getLongitude(), ht.getElevation(), ht.getTimeZone());
                ht.setDateHefsek(jKalDateHT.getCoucherSoleil());

                //calcul du Mohdahouk
                if (personne.getConfiguration().isDoMohDahouk()) {
                    LOGGER.info("Rajout d'un mohdahouk pour la personne d'id={}", personne.getId());
                    MohDahouk md = new MohDahouk();
                    newPurification.setMohDahouk(md);
                    md.setPurification(newPurification);
                    md.setElevation(niddahEncours.getElevation());
                    md.setLatitude(niddahEncours.getLatitude());
                    md.setLongitude(niddahEncours.getLongitude());
                    md.setLocationName(niddahEncours.getLocationName());
                    md.setTimeZone(niddahEncours.getTimeZone());
                    JkalDate jKalDateMD = new JkalDate(dateNewPurification, md.getLocationName(), md.getLatitude(), md.getLongitude(), md.getElevation(), md.getTimeZone());
                    md.setDateMoh(jKalDateMD.getCoucherSoleil());
                } else {
                    LOGGER.info("La personne d'id={} n'a pas l'habitude d'accomplir de mohdahouk");
                }
                //Calcul des cheva nekiym
                LOGGER.info("Rajout de la periode des cheva nekiyim de la personne d'id={}", personne.getId());
                ChevaNekiym chevaNekiym = new ChevaNekiym();
                newPurification.setChevaNekiym(chevaNekiym);
                chevaNekiym.setPurification(newPurification);
                chevaNekiym.setElevation(niddahEncours.getElevation());
                chevaNekiym.setLatitude(niddahEncours.getLatitude());
                chevaNekiym.setLongitude(niddahEncours.getLongitude());
                chevaNekiym.setLocationName(niddahEncours.getLocationName());
                chevaNekiym.setTimeZone(niddahEncours.getTimeZone());
                gCal.setTime(dateNewPurification);
                gCal.add(Calendar.DAY_OF_MONTH, 1);
                chevaNekiym.setDateDebut(gCal.getTime());
                int i = 0;
                for (i = 0; i < 7; i++) {
                    bd = new Bedikot();
                    chevaNekiym.addBedikot(bd);
                    bd.setElevation(niddahEncours.getElevation());
                    bd.setLatitude(niddahEncours.getLatitude());
                    bd.setLongitude(niddahEncours.getLongitude());
                    bd.setLocationName(niddahEncours.getLocationName());
                    bd.setTimeZone(niddahEncours.getTimeZone());
                    JkalDate jKalDateBedikot = new JkalDate(gCal.getTime(), ht.getLocationName(), ht.getLatitude(), ht.getLongitude(), ht.getElevation(), ht.getTimeZone());
                    bd.setDateBedika1(jKalDateBedikot.getLeverSoleil());
                    bd.setDateBedika2(jKalDateBedikot.getCoucherSoleil());
                    if (i < 6) {
                        gCal.add(Calendar.DAY_OF_MONTH, 1);
                    }

                }
                chevaNekiym.setDateFin(gCal.getTime());

                //calcul de la date de la tevila
                LOGGER.info("Rajout d'une tevila pour la personne d'id={}", personne.getId());
                Tevila tevila = new Tevila();
                newPurification.setTevila(tevila);
                tevila.setPurification(newPurification);
                tevila.setElevation(niddahEncours.getElevation());
                tevila.setLatitude(niddahEncours.getLatitude());
                tevila.setLongitude(niddahEncours.getLongitude());
                tevila.setLocationName(niddahEncours.getLocationName());
                tevila.setTimeZone(niddahEncours.getTimeZone());
                tevila.setDateTevila(gCal.getTime());
                niddahRepository.update(niddahEncours);

            }
            case Bedika2 -> {
                //La bedika du soir n'est pas bonne on retourne a l'utisateur s'il a eu le temps de refaire un ht le soir meme
                Purification oldPurification = bd.getChevaNekiym().getPurification();
                ChevaNekiym tmp = oldPurification.getChevaNekiym();
                bd.setEtatBedika2(Boolean.FALSE);
                niddahRepository.update(bd);
                niddahRepository.deleteBedikotNull(tmp.getId());

                return JKalveredCodeRetour.Niddah_Retry_HT_NOW;

            }
            default ->
                throw new AssertionError();
        }
        return res;
    }

    /**
     * Fonction qui met à true un hefsektahara
     *
     * @param idHefsekTahara
     * @param idFemme
     * @throws NiddahDataException
     */
    public void setHefesekTaharaOK(Long idHefsekTahara, Long idFemme) throws NiddahDataException {
        HefsekTahara ht = niddahRepository.getHefsekTaharaByIdAndIdFemme(idHefsekTahara, idFemme);

        if (ht == null) {
            throw new NiddahDataException("Le hefsektahra d'id:" + idHefsekTahara + "n'existe pas pour la personne d'id=" + idFemme);
        }

        ht.setEtatHefsek(true);
        niddahRepository.update(ht);
    }

    /**
     * Fonction qui met à jour a true un mohdahouk
     *
     * @param idMohDahouk
     * @param idFemme
     * @throws NiddahDataException
     */
    public void setMohDahoukOK(Long idMohDahouk, Long idFemme) throws NiddahDataException {
        MohDahouk md = niddahRepository.getMohDaoukByIdAndIdFemme(idMohDahouk, idFemme);

        if (md == null) {
            throw new NiddahDataException("La tevila d'id:" + idMohDahouk + "n'existe pas pour la personne d'id=" + idFemme);
        }
        md.setAccompli(true);
        md.setEtatMoh(true);
        niddahRepository.update(md);
    }

    /**
     * Fonction qui gère le controle de la tevila et son bon accomplissement
     *
     * @param idTevila
     * @param idFemme
     * @param localisation
     * @return JKalveredCodeRetour.Niddah_Tevila_OK si Tevila OK
     * JKalveredCodeRetour.Niddah_Bedikot_error_not_enough si les bedikots sont
     * pas suffisantes
     * @throws NiddahDataException
     */
    public int setTevilaOk(Long idTevila, Long idFemme, Localisation localisation) throws NiddahDataException {
        Tevila tevila = niddahRepository.getTevilaByIdAndIdFemme(idTevila, idFemme);
        if (tevila == null) {
            throw new NiddahDataException("Le mohdahouk d'id:" + idTevila + "n'existe pas pour la personne d'id=" + idFemme);
        }
        int res = JKalveredCodeRetour.Niddah_Tevila_OK;
        //Je controle que au moin le premier et dernier jour des shva nekiym sont bon
        List<Bedikot> listBedikot = tevila.getPurification().getChevaNekiym().getBedikots();

        Bedikot firtBedika = listBedikot.get(0);
        Bedikot lastBedika = listBedikot.get(6);
        if (Objects.equals(firtBedika.getEtatBedika1(), Boolean.TRUE) && Objects.equals(firtBedika.getEtatBedika2(), Boolean.TRUE) && Objects.equals(lastBedika.getEtatBedika1(), Boolean.TRUE) && Objects.equals(lastBedika.getEtatBedika2(), Boolean.TRUE)) {
            tevila.setEtatTevila(true);
            niddahRepository.update(tevila);
        } else {
            return JKalveredCodeRetour.Niddah_Bedikot_error_not_enough;
        }
        return res;
    }

    public int analysePurification(Long idPurification, Long idFemme) {
        int res = 0;
        Purification purification = niddahRepository.getPurificationByIdAndIdFemme(idPurification, idFemme);
        List<Bedikot> bedikots = purification.getChevaNekiym().getBedikots();
        LOGGER.info("listBedikot {}", bedikots);

        return res;
    }
}
