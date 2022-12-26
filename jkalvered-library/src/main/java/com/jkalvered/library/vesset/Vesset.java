package com.jkalvered.library.vesset;

import com.jkalvered.core.entite.Niddah;
import com.jkalvered.library.tools.JewishDateEcart;

import com.jkalvered.library.exception.JKalVeredException;
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

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Pre-rempli un niddahDto
     *
     * @param date
     * @param latitude
     * @param longitude
     * @param elevation
     * @param locationName
     * @param timezone
     * @return
     */
    public static Niddah fillNiddah(Date date, double latitude, double longitude, double elevation, String locationName, String timezone) {
        Niddah niddahDto = new Niddah(date, locationName, latitude, longitude, elevation, timezone);

        return niddahDto;

    }

    

    /**
     * Retourne le nombre de jour d'écart entre deux niddahs
     *
     * @param niddah1
     * @param niddah2
     * @return
     */
    public static int getHaflagaEntreDeuxCycle(Niddah niddah1, Niddah niddah2) throws JKalVeredException{
        if (niddah2.getjKalDate().getDateGregorian().before(niddah1.getjKalDate().getDateGregorian())) {
            throw new JKalVeredException("La date 1 : " + niddah1.getjKalDate().getDateGregorian() + " doit être avant la 2 : " + niddah2.getjKalDate().getDateGregorian());
        }
        return Days.daysBetween(new LocalDate(niddah1.getjKalDate().getDateGregorian()), new LocalDate(niddah2.getjKalDate().getDateGregorian())).getDays()+1;

    }

    /**
     * Determine si le niddah est régulier selon le jour du mois
     *
     * @param listNiddah liste des 3 derniers niddahs
     * @return
     */
    public static boolean isCycleKavouaHahodesh(List<Niddah> listNiddah) {
        if (listNiddah.size() != 3) {
            throw new IllegalArgumentException("la taille de la liste de niddah doit être égal à 3");
        }
        Collections.sort(listNiddah);
        Niddah niddah1 = listNiddah.get(0);
        Niddah niddah2 = listNiddah.get(1);
        Niddah niddah3 = listNiddah.get(2);
        int max = Math.max(niddah1.getjKalDate().getJewishDate().getJewishYear(), Math.max(niddah2.getjKalDate().getJewishDate().getJewishYear(), niddah3.getjKalDate().getJewishDate().getJewishYear()));
        int min = Math.min(niddah1.getjKalDate().getJewishDate().getJewishYear(), Math.min(niddah2.getjKalDate().getJewishDate().getJewishYear(), niddah3.getjKalDate().getJewishDate().getJewishYear()));

        //Test s'il n'y a pas deux année d'écart
        return (max - min <= 1)
                //La Ona doit être identique
                && niddah1.getOna() == niddah2.getOna()
                && niddah1.getOna() == niddah3.getOna() && (niddah1.getjKalDate().getJewishDayOfMonth() == niddah2.getjKalDate().getJewishDayOfMonth()
                && niddah1.getjKalDate().getJewishDayOfMonth() == niddah3.getjKalDate().getJewishDayOfMonth())
                && //Les mois doivent se suiuvrent avec un decalage de 1 mois en fonction de l'année embolismique
                (((niddah1.getjKalDate().getJewishMonth() + 1)
                % (niddah1.getjKalDate().isJewishLeapYear() ? 13 : 12)) == (niddah2.getjKalDate().getJewishMonth() % (niddah2.getjKalDate().isJewishLeapYear() ? 13 : 12))
                && ((niddah2.getjKalDate().getJewishMonth() + 1) % (niddah2.getjKalDate().isJewishLeapYear() ? 13 : 12)) == (niddah3.getjKalDate().getJewishMonth() % (niddah3.getjKalDate().isJewishLeapYear() ? 13 : 12)));
    }

    /**
     * Determine si le niddah est regulier sur le jour d'intervalle
     *
     * @param listNiddah liste des 4 derniers niddahs
     * @return
     */
    public static boolean isCycleKavouaHaflaga(List<Niddah> listNiddah) {
        if (listNiddah.size() != 4) {
            throw new IllegalArgumentException("la taille de la liste de niddah doit être égal à 4");
        }
        Collections.sort(listNiddah);
        Niddah niddah1 = listNiddah.get(0);
        Niddah niddah2 = listNiddah.get(1);
        Niddah niddah3 = listNiddah.get(2);
        Niddah niddah4 = listNiddah.get(3);
        return (niddah1.getOna() == niddah2.getOna()
                && niddah1.getOna() == niddah3.getOna()
                && niddah1.getOna() == niddah4.getOna())
                && (niddah2.getHaflaga() == niddah3.getHaflaga()
                && niddah3.getHaflaga() == niddah4.getHaflaga());
    }

    /**
     * Determine si le niddah est régulier sur le jour de la semaine toutes les x
     * semaines
     *
     * @param listNiddah liste des 4 derniers niddahs
     * @return
     */
    public static boolean isCycleKavouaHashavoua(List<Niddah> listNiddah) {
        if (listNiddah.size() != 4) {
            throw new IllegalArgumentException("la taille de la liste de niddah doit être égal à 4");
        }
        Collections.sort(listNiddah);
        Niddah niddah1 = listNiddah.get(0);
        Niddah niddah2 = listNiddah.get(1);
        Niddah niddah3 = listNiddah.get(2);
        Niddah niddah4 = listNiddah.get(3);
        if (niddah1.getOna() == niddah2.getOna()
                && niddah1.getOna() == niddah3.getOna()
                && niddah1.getOna() == niddah4.getOna()) {
            if (niddah2.getHaflaga() % 7 == niddah3.getHaflaga() % 7
                    && niddah3.getHaflaga() % 7 == niddah4.getHaflaga() % 7) {
                Calendar cal = GregorianCalendar.getInstance();
                int jour1, jour2, jour3;
                cal.setTime(niddah2.getjKalDate().getDateGregorian());
                jour1 = cal.get(Calendar.DAY_OF_WEEK);
                cal.setTime(niddah3.getjKalDate().getDateGregorian());
                jour2 = cal.get(Calendar.DAY_OF_WEEK);
                cal.setTime(niddah3.getjKalDate().getDateGregorian());
                jour3 = cal.get(Calendar.DAY_OF_WEEK);

                if ((jour1 == jour2) && (jour2 == jour3)) {
                    return true;
                }

            }

        }

        return false;
    }

    /**
     * Determine si le niddah est régulier sur le jour du mois se décalant de x
     * jours
     *
     * @param listNiddah liste des 4 derniers niddahs
     * @return
     */
    public static boolean isCycleKavouaDilougHahodesh(List<Niddah> listNiddah) {
        if (listNiddah.size() != 4) {
            throw new IllegalArgumentException("la taille de la liste de niddah doit être égal à 4");
        }
        Collections.sort(listNiddah);
        Niddah niddah1 = listNiddah.get(0);
        Niddah niddah2 = listNiddah.get(1);
        Niddah niddah3 = listNiddah.get(2);
        Niddah niddah4 = listNiddah.get(3);
        JewishDateEcart ecart1 = new JewishDateEcart(niddah1, niddah2, niddah1.getOna());
        JewishDateEcart ecart2 = new JewishDateEcart(niddah2, niddah3, niddah2.getOna());
        JewishDateEcart ecart3 = new JewishDateEcart(niddah3, niddah4, niddah3.getOna());

        return niddah1.getOna() == niddah2.getOna()
                && ecart1.equalsEcart(ecart2) && ecart2.equalsEcart(ecart3);
    }

    /**
     * Determine si le niddah est regulier sur le decalage du jour du mois de x
     * jour avec retour
     *
     * @param listNiddah liste des 12 derniers niddahs
     * @return
     */
    public static boolean isCycleKavouaDilougHahodeshHozer(List<Niddah> listNiddah) {
        if (listNiddah.size() != 12) {
            throw new IllegalArgumentException("la taille de la liste de niddah doit être égal à 12");
        }
        List<JewishDateEcart> listJourEcart = new ArrayList<>();
        Niddah precedent = listNiddah.get(0);
        Niddah origine = listNiddah.get(0);
        int i = 1;

        while (i < listNiddah.size()) {
            listJourEcart.add(new JewishDateEcart(precedent, listNiddah.get(i), listNiddah.get(i).getOna()));
            i++;
            if (i % 4 == 0 && i < listNiddah.size()) {
                if (origine.getjKalDate().getJewishDayOfMonth() == listNiddah.get(i).getjKalDate().getJewishDayOfMonth()) {
                    precedent = listNiddah.get(i);
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
        JewishDateEcart niddahDepart = listJourEcart.get(0);
        ArrayList<JewishDateEcart> list = new ArrayList<>();
        list.add(niddahDepart);
        for (i = 1; i < listJourEcart.size(); i++) {
            JewishDateEcart ecartEncours = listJourEcart.get(i);
            if (ecartEncours.getJour() != niddahDepart.getJour()) {
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
     * Determine si le niddah est régulier sur le decalage de x jour sur la
     * haflaga
     *
     * @param listNiddah liste des 5 derniers niddahs
     * @return
     */
    public static boolean isCycleKavouaDilougHaflaga(List<Niddah> listNiddah) {
        if (listNiddah.size() != 5) {
            throw new IllegalArgumentException("la taille de la liste de niddah doit être égal à 4");
        }
        Collections.sort(listNiddah);
        Niddah niddah1 = listNiddah.get(0);
        Niddah niddah2 = listNiddah.get(1);
        Niddah niddah3 = listNiddah.get(2);
        Niddah niddah4 = listNiddah.get(3);
        Niddah niddah5 = listNiddah.get(4);
        JewishDateEcart ecart2 = new JewishDateEcart(niddah2, niddah3, niddah2.getOna());
        JewishDateEcart ecart3 = new JewishDateEcart(niddah3, niddah4, niddah3.getOna());
        JewishDateEcart ecart4 = new JewishDateEcart(niddah4, niddah5, niddah4.getOna());

        return niddah1.getOna() == niddah2.getOna()
                && ecart2.equalsHaflaga(ecart3)
                && ecart3.equalsHaflaga(ecart4);
    }

    /**
     * Determine si le niddah est regulier sur le decalage de x jour sur la
     * haflaga avec retour
     *
     * @param listNiddah liste des 9 derniers niddahs
     * @return
     * @throws UnsupportedOperationException
     */
    public static boolean isCycleKavouaDilougHaflagaHozer(List<Niddah> listNiddah) throws IllegalArgumentException {
        if (listNiddah.size() != 9) {
            throw new IllegalArgumentException("la taille de la liste de niddah doit être égal à 9");
        }
        Collections.sort(listNiddah);
        List<ArrayList> groupList = new ArrayList<>();
        Niddah niddahDepart = listNiddah.get(0);
        ArrayList<Niddah> list = new ArrayList<>();
        list.add(niddahDepart);
        for (int i = 1; i < listNiddah.size(); i++) {
            Niddah niddahEncours = listNiddah.get(i);
            if (niddahEncours.getHaflaga() != niddahDepart.getHaflaga()) {
                list.add(niddahEncours);
            } else {
                groupList.add(list);
                list = new ArrayList<>();
                list.add(niddahEncours);

            }
            if (i == listNiddah.size() - 1) {
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
