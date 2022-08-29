package com.jkalvered.library.vesset;

import com.jkalvered.core.dto.JewishDateEcart;
import com.jkalvered.core.dto.NiddahDto;
import com.jkalvered.library.date.JkalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.Days;
import org.joda.time.LocalDate;

/**
 * Classe determinant la régularité de la femme
 *
 * @author mini-john
 */
public class Vesset {

    private static Logger LOGGER = LogManager.getLogger();

    /**
     * Pre-rempli un cycleDto
     *
     * @param date
     * @param latitude
     * @param longitude
     * @param elevation
     * @param locationName
     * @param timezone
     * @return
     */
    public static NiddahDto fillNiddahDto(Date date, double latitude, double longitude, double elevation, String locationName, String timezone) {
        NiddahDto cycleDto = new NiddahDto();
        JkalDate jKalDate = new JkalDate(date, locationName, latitude, longitude, elevation, timezone);
        cycleDto.setDate(date);
        cycleDto.setLatitude(latitude);
        cycleDto.setLongitude(longitude);
        cycleDto.setElevation(elevation);
        cycleDto.setOna(jKalDate.getOna());
        cycleDto.setjDate(jKalDate.getJewishDate());
        return cycleDto;

    }

    /**
     * Pre-rempli un cycleDto
     *
     * @param date
     * @param latitude
     * @param longitude
     * @param elevation
     * @param locationName
     * @param timezone
     * @param cyclePrecedent
     * @return
     */
    public static NiddahDto fillNiddahDto(Date date, double latitude, double longitude, double elevation, String locationName, String timezone, NiddahDto cyclePrecedent) {
        JkalDate jKalDate = new JkalDate(date, locationName, latitude, longitude, elevation, timezone);

        NiddahDto cycleDto = new NiddahDto();
        cycleDto.setDate(date);
        cycleDto.setLatitude(latitude);
        cycleDto.setLongitude(longitude);
        cycleDto.setElevation(elevation);
        cycleDto.setOna(jKalDate.getOna());
        cycleDto.setjDate(jKalDate.getJewishDate());
        cycleDto.setHaflaga(getHaflagaEntreDeuxCycle(cyclePrecedent, cycleDto));
        return cycleDto;

    }

    /**
     * Retourne le nombre de jour d'écart entre deux cycles
     *
     * @param cycle1
     * @param cycle2
     * @return
     */
    public static int getHaflagaEntreDeuxCycle(NiddahDto cycle1, NiddahDto cycle2) {
        return Days.daysBetween(new LocalDate(cycle1.getDate()), new LocalDate(cycle2.getDate())).getDays() + 1;

    }

    /**
     * Determine si le cycle est régulier selon le jour du mois
     *
     * @param listCycle liste des 3 derniers cycles
     * @return
     */
    public static boolean isCycleKavouaHahodesh(List<NiddahDto> listCycle) {
        if (listCycle.size() != 3) {
            throw new IllegalArgumentException("la taille de la liste de cycle doit être égal à 3");
        }
        Collections.sort(listCycle);
        NiddahDto cycle1 = listCycle.get(0);
        NiddahDto cycle2 = listCycle.get(1);
        NiddahDto cycle3 = listCycle.get(2);
        int max = Math.max(cycle1.getjDate().getJewishYear(), Math.max(cycle2.getjDate().getJewishYear(), cycle3.getjDate().getJewishYear()));
        int min = Math.min(cycle1.getjDate().getJewishYear(), Math.min(cycle2.getjDate().getJewishYear(), cycle3.getjDate().getJewishYear()));

        //Test s'il n'y a pas deux année d'écart
        return (max - min <= 1)
                //La Ona doit être identique
                && cycle1.getOna() == cycle2.getOna()
                && cycle1.getOna() == cycle3.getOna() && (cycle1.getjDate().getJewishDayOfMonth() == cycle2.getjDate().getJewishDayOfMonth()
                && cycle1.getjDate().getJewishDayOfMonth() == cycle3.getjDate().getJewishDayOfMonth())
                && //Les mois doivent se suiuvrent avec un decalage de 1 mois en fonction de l'année embolismique
                (((cycle1.getjDate().getJewishMonth() + 1)
                % (cycle1.getjDate().isJewishLeapYear() ? 13 : 12)) == (cycle2.getjDate().getJewishMonth() % (cycle2.getjDate().isJewishLeapYear() ? 13 : 12))
                && ((cycle2.getjDate().getJewishMonth() + 1) % (cycle2.getjDate().isJewishLeapYear() ? 13 : 12)) == (cycle3.getjDate().getJewishMonth() % (cycle3.getjDate().isJewishLeapYear() ? 13 : 12)));
    }

    /**
     * Determine si le cycle est regulier sur le jour d'intervalle
     *
     * @param listCycle liste des 4 derniers cycles
     * @return
     */
    public static boolean isCycleKavouaHaflaga(List<NiddahDto> listCycle) {
        if (listCycle.size() != 4) {
            throw new IllegalArgumentException("la taille de la liste de cycle doit être égal à 4");
        }
        Collections.sort(listCycle);
        NiddahDto cycle1 = listCycle.get(0);
        NiddahDto cycle2 = listCycle.get(1);
        NiddahDto cycle3 = listCycle.get(2);
        NiddahDto cycle4 = listCycle.get(3);
        return (cycle1.getOna() == cycle2.getOna()
                && cycle1.getOna() == cycle3.getOna()
                && cycle1.getOna() == cycle4.getOna())
                && (cycle2.getHaflaga() == cycle3.getHaflaga()
                && cycle3.getHaflaga() == cycle4.getHaflaga());
    }

    /**
     * Determine si le cycle est régulier sur le jour de la semaine toutes les x
     * semaines
     *
     * @param listCycle liste des 4 derniers cycles
     * @return
     */
    public static boolean isCycleKavouaHashavoua(List<NiddahDto> listCycle) {
        if (listCycle.size() != 4) {
            throw new IllegalArgumentException("la taille de la liste de cycle doit être égal à 4");
        }
        Collections.sort(listCycle);
        NiddahDto cycle1 = listCycle.get(0);
        NiddahDto cycle2 = listCycle.get(1);
        NiddahDto cycle3 = listCycle.get(2);
        NiddahDto cycle4 = listCycle.get(3);
        if (cycle1.getOna() == cycle2.getOna()
                && cycle1.getOna() == cycle3.getOna()
                && cycle1.getOna() == cycle4.getOna()) {
            if (cycle2.getHaflaga() % 7 == cycle3.getHaflaga() % 7
                    && cycle3.getHaflaga() % 7 == cycle4.getHaflaga() % 7) {
                Calendar cal = GregorianCalendar.getInstance();
                int jour1, jour2, jour3;
                cal.setTime(cycle2.getDate());
                jour1 = cal.get(Calendar.DAY_OF_WEEK);
                cal.setTime(cycle3.getDate());
                jour2 = cal.get(Calendar.DAY_OF_WEEK);
                cal.setTime(cycle3.getDate());
                jour3 = cal.get(Calendar.DAY_OF_WEEK);

                if ((jour1 == jour2) && (jour2 == jour3)) {
                    return true;
                }

            }

        }

        return false;
    }

    /**
     * Determine si le cycle est régulier sur le jour du mois se décalant de x
     * jours
     *
     * @param listCycle liste des 4 derniers cycles
     * @return
     */
    public static boolean isCycleKavouaDilougHahodesh(List<NiddahDto> listCycle) {
        if (listCycle.size() != 4) {
            throw new IllegalArgumentException("la taille de la liste de cycle doit être égal à 4");
        }
        Collections.sort(listCycle);
        NiddahDto cycle1 = listCycle.get(0);
        NiddahDto cycle2 = listCycle.get(1);
        NiddahDto cycle3 = listCycle.get(2);
        NiddahDto cycle4 = listCycle.get(3);
        JewishDateEcart ecart1 = new JewishDateEcart(cycle1, cycle2, cycle1.getOna());
        JewishDateEcart ecart2 = new JewishDateEcart(cycle2, cycle3, cycle2.getOna());
        JewishDateEcart ecart3 = new JewishDateEcart(cycle3, cycle4, cycle3.getOna());

        return cycle1.getOna() == cycle2.getOna()
                && ecart1.equalsEcart(ecart2) && ecart2.equalsEcart(ecart3);
    }

    /**
     * Determine si le cycle est regulier sur le decalage du jour du mois de x
     * jour avec retour
     *
     * @param listCycle liste des 12 derniers cycles
     * @return
     */
    public static boolean isCycleKavouaDilougHahodeshHozer(List<NiddahDto> listCycle) {
        if (listCycle.size() != 12) {
            throw new IllegalArgumentException("la taille de la liste de cycle doit être égal à 12");
        }
        List<JewishDateEcart> listJourEcart = new ArrayList<>();
        NiddahDto precedent = listCycle.get(0);
        NiddahDto origine = listCycle.get(0);
        int i = 1;

        while (i < listCycle.size()) {
            listJourEcart.add(new JewishDateEcart(precedent, listCycle.get(i), listCycle.get(i).getOna()));
            i++;
            if (i % 4 == 0 && i < listCycle.size()) {
                if (origine.getjDate().getJewishDayOfMonth() == listCycle.get(i).getjDate().getJewishDayOfMonth()) {
                    precedent = listCycle.get(i);
                    i++;
                } else {
                    return false;
                }

            }
        }
        if (listJourEcart.size() != 9) {
            return false;
        }

        //Je sépare ma liste en 3 sous liste
        List<ArrayList> groupList = new ArrayList<>();
        JewishDateEcart cycleDepart = listJourEcart.get(0);
        ArrayList<JewishDateEcart> list = new ArrayList<>();
        list.add(cycleDepart);
        for (i = 1; i < listJourEcart.size(); i++) {
            JewishDateEcart ecartEncours = listJourEcart.get(i);
            if (ecartEncours.getJour() != cycleDepart.getJour()) {
                list.add(ecartEncours);
            } else {
                groupList.add(list);
                list = new ArrayList<>();
                list.add(ecartEncours);

            }
            if (i == listJourEcart.size() - 1) {
                groupList.add(list);

            }

        }
        /**
         * Je verifie que j'ai 3 groupe*
         */
        if (groupList.size() != 3) {
            return false;
        }

        return groupList.get(0).equals(groupList.get(1)) && groupList.get(0).equals(groupList.get(2));

    }

    /**
     * Determine si le cycle est régulier sur le decalage de x jour sur la
     * haflaga
     *
     * @param listCycle liste des 5 derniers cycles
     * @return
     */
    public static boolean isCycleKavouaDilougHaflaga(List<NiddahDto> listCycle) {
        if (listCycle.size() != 5) {
            throw new IllegalArgumentException("la taille de la liste de cycle doit être égal à 4");
        }
        Collections.sort(listCycle);
        NiddahDto cycle1 = listCycle.get(0);
        NiddahDto cycle2 = listCycle.get(1);
        NiddahDto cycle3 = listCycle.get(2);
        NiddahDto cycle4 = listCycle.get(3);
        NiddahDto cycle5 = listCycle.get(4);
        JewishDateEcart ecart2 = new JewishDateEcart(cycle2, cycle3, cycle2.getOna());
        JewishDateEcart ecart3 = new JewishDateEcart(cycle3, cycle4, cycle3.getOna());
        JewishDateEcart ecart4 = new JewishDateEcart(cycle4, cycle5, cycle4.getOna());

        return cycle1.getOna() == cycle2.getOna()
                && ecart2.equalsHaflaga(ecart3)
                && ecart3.equalsHaflaga(ecart4);
    }

    /**
     * Determine si le cycle est regulier sur le decalage de x jour sur la
     * haflaga avec retour
     *
     * @param listCycle liste des 9 derniers cycles
     * @return
     * @throws UnsupportedOperationException
     */
    public static boolean isCycleKavouaDilougHaflagaHozer(List<NiddahDto> listCycle) throws UnsupportedOperationException {
        if (listCycle.size() != 9) {
            throw new IllegalArgumentException("la taille de la liste de cycle doit être égal à 9");
        }
        Collections.sort(listCycle);
        List<ArrayList> groupList = new ArrayList<>();
        NiddahDto cycleDepart = listCycle.get(0);
        ArrayList<NiddahDto> list = new ArrayList<>();
        list.add(cycleDepart);
        for (int i = 1; i < listCycle.size(); i++) {
            NiddahDto cycleEncours = listCycle.get(i);
            if (cycleEncours.getHaflaga() != cycleDepart.getHaflaga()) {
                list.add(cycleEncours);
            } else {
                groupList.add(list);
                list = new ArrayList<>();
                list.add(cycleEncours);

            }
            if (i == listCycle.size() - 1) {
                groupList.add(list);

            }

        }
        /**
         * Je verifie que j'ai 3 groupe*
         */
        if (groupList.size() != 3) {
            return false;
        }
        return groupList.get(0).equals(groupList.get(1))
                && groupList.get(0).equals(groupList.get(2));

    }

}
