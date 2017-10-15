package com.niddah.library.vesset;

import com.niddah.library.date.DateNiddah;
import com.niddah.library.dto.CycleDto;
import com.niddah.library.dto.JewishDateEcart;
import com.niddah.library.enumeration.Ona;
import com.niddah.library.enumeration.TypeCycle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe determinant la régularité de la femme
 *
 * @author mini-john
 */
public class Vesset {

    private static final Logger LOGGER = LoggerFactory.getLogger(Vesset.class);

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
    public static CycleDto fillCycleDto(Date date, double latitude, double longitude, double elevation, String locationName, TimeZone timezone) {
        CycleDto cycleDto = new CycleDto();
        cycleDto.setDate(date);
        cycleDto.setLatitude(latitude);
        cycleDto.setLongitude(longitude);
        cycleDto.setElevation(elevation);
        cycleDto.setOna(DateNiddah.isOnatJour(date, cycleDto.getLGeolocation(locationName, timezone))
                ? Ona.Jour : Ona.Nuit);
        cycleDto.setjDate(DateNiddah.getDateJewish(date, cycleDto.getLGeolocation(locationName, timezone)));
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
    public static CycleDto fillCycleDto(Date date, double latitude, double longitude, double elevation, String locationName, TimeZone timezone, CycleDto cyclePrecedent) {
        CycleDto cycleDto = new CycleDto();
        cycleDto.setDate(date);
        cycleDto.setLatitude(latitude);
        cycleDto.setLongitude(longitude);
        cycleDto.setElevation(elevation);
        cycleDto.setOna(DateNiddah.isOnatJour(date, cycleDto.getLGeolocation(locationName, timezone))
                ? Ona.Jour : Ona.Nuit);
        cycleDto.setjDate(DateNiddah.getDateJewish(date, cycleDto.getLGeolocation(locationName, timezone)));
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
    public static int getHaflagaEntreDeuxCycle(CycleDto cycle1, CycleDto cycle2) {
        return Days.daysBetween(new LocalDate(cycle1.getDate()), new LocalDate(cycle2.getDate())).getDays() + 1;

    }

    /**
     * Determine si le cycle est régulier selon le jour du mois
     *
     * @param listCycle
     * @return
     */
    public static boolean isCycleKavouaHahodesh(List<CycleDto> listCycle) {
        //TODO revoir les cas de tailles invalides
        if (listCycle.size() < 3) {
            return false;
        }
        Collections.sort(listCycle);
        CycleDto cycle1 = listCycle.get(0);
        CycleDto cycle2 = listCycle.get(1);
        CycleDto cycle3 = listCycle.get(2);

        if (cycle1.getOna() == cycle2.getOna()
                && cycle1.getOna() == cycle3.getOna()) {

            if (cycle1.getjDate().getJewishDayOfMonth() == cycle2.getjDate().getJewishDayOfMonth()
                    && cycle1.getjDate().getJewishDayOfMonth() == cycle3.getjDate().getJewishDayOfMonth()) {
//                if (cycle1.getjDate().getJewishYear() == cycle2.getjDate().getJewishYear()
//                        && cycle1.getjDate().getJewishYear() == cycle3.getjDate().getJewishYear()) {
                if (cycle1.getjDate().isJewishLeapYear()) {
                    if (((cycle1.getjDate().getJewishMonth() + 1) % 13) == (cycle2.getjDate().getJewishMonth() % 13)
                            && ((cycle2.getjDate().getJewishMonth() + 1) % 13) == (cycle3.getjDate().getJewishMonth() % 13)) {
                        return true;
                    }

                } else if (((cycle1.getjDate().getJewishMonth() + 1) % 12) == (cycle2.getjDate().getJewishMonth() % 12)
                        && ((cycle2.getjDate().getJewishMonth() + 1) % 12) == (cycle3.getjDate().getJewishMonth() % 12)) {
                    return true;
                }

            }
        }
        //}
        return false;
    }

    /**
     * Determine si le cycle est regulier sur le jour d'intervalle
     *
     * @param listCycle
     * @return
     */
    public static boolean isCycleKavouaHaflaga(List<CycleDto> listCycle) {
        if (listCycle.size() < 4) {
            return false;
        }
        Collections.sort(listCycle);
        CycleDto cycle1 = listCycle.get(0);
        CycleDto cycle2 = listCycle.get(1);
        CycleDto cycle3 = listCycle.get(2);
        CycleDto cycle4 = listCycle.get(3);
        if (cycle1.getOna() == cycle2.getOna()
                && cycle1.getOna() == cycle3.getOna()
                && cycle1.getOna() == cycle4.getOna()) {
            if (cycle2.getHaflaga() == cycle3.getHaflaga()
                    && cycle3.getHaflaga() == cycle4.getHaflaga()) {
                return true;

            }
        }

        return false;
    }

    /**
     * Determine si le cycle est régulier sur le jour de la semaine toutes les x
     * semaines
     *
     * @param listCycle
     * @return
     */
    public static boolean isCycleKavouaHashavoua(List<CycleDto> listCycle) {
        if (listCycle.size() < 4) {
            return false;
        }
        Collections.sort(listCycle);
        CycleDto cycle1 = listCycle.get(0);
        CycleDto cycle2 = listCycle.get(1);
        CycleDto cycle3 = listCycle.get(2);
        CycleDto cycle4 = listCycle.get(3);
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
     * @param listCycle
     * @return
     */
    public static boolean isCycleKavouaDilougHahodesh(List<CycleDto> listCycle) {
        if (listCycle.size() < 4) {
            return false;
        }
        Collections.sort(listCycle);
        CycleDto cycle1 = listCycle.get(0);
        CycleDto cycle2 = listCycle.get(1);
        CycleDto cycle3 = listCycle.get(2);
        CycleDto cycle4 = listCycle.get(3);
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
     * @param listCycle
     * @return
     */
    public static boolean isCycleKavouaDilougHahodeshHozer(List<CycleDto> listCycle) {
        if (listCycle.size() < 12) {
            return false;
        }
        List<JewishDateEcart> listJourEcart = new ArrayList<>();
        CycleDto precedent = listCycle.get(0);
        CycleDto origine = listCycle.get(0);
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
     * @param listCycle
     * @return
     */
    public static boolean isCycleKavouaDilougHaflaga(List<CycleDto> listCycle) {
        if (listCycle.size() < 5) {
            return false;
        }
        Collections.sort(listCycle);
        CycleDto cycle1 = listCycle.get(0);
        CycleDto cycle2 = listCycle.get(1);
        CycleDto cycle3 = listCycle.get(2);
        CycleDto cycle4 = listCycle.get(3);
        CycleDto cycle5 = listCycle.get(4);
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
     * @param listCycle
     * @return
     * @throws UnsupportedOperationException
     */
    public static boolean isCycleKavouaDilougHaflagaHozer(List<CycleDto> listCycle) throws UnsupportedOperationException {
        if (listCycle.size() < 9) {
            return false;
        }
        Collections.sort(listCycle);
        List<ArrayList> groupList = new ArrayList<>();
        CycleDto cycleDepart = listCycle.get(0);
        ArrayList<CycleDto> list = new ArrayList<>();
        list.add(cycleDepart);
        for (int i = 1; i < listCycle.size(); i++) {
            CycleDto cycleEncours = listCycle.get(i);
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

    public static TypeCycle getTypeCycle(List<CycleDto> listCycle) {

        if (isCycleKavouaHahodesh(listCycle)) {
            return TypeCycle.KavouaHahodesh;
        }
        if (isCycleKavouaHaflaga(listCycle)) {
            return TypeCycle.KavouaHaflaga;
        }
        if (isCycleKavouaHashavoua(listCycle)) {
            return TypeCycle.KavouaHashavoua;
        }
        if (isCycleKavouaDilougHahodesh(listCycle)) {
            return TypeCycle.KavouaDilougHahodesh;
        }

        if (isCycleKavouaDilougHahodeshHozer(listCycle)) {
            return TypeCycle.KavouaDilougHahodeshHozer;
        }
        if (isCycleKavouaDilougHaflaga(listCycle)) {
            return TypeCycle.KavouaDilougHaflaga;
        }

        if (isCycleKavouaDilougHaflagaHozer(listCycle)) {
            return TypeCycle.KavouaDilougHaflagaHozer;
        }

        return TypeCycle.LoKavoua;
    }

}
