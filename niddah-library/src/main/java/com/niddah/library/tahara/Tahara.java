/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.library.tahara;

import com.niddah.library.constante.Constantes;
import com.niddah.library.date.DateNiddah;
import com.niddah.library.dto.ShevaNekymDto;
import com.niddah.library.enumeration.Origine;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import net.sourceforge.zmanim.ZmanimCalendar;
import net.sourceforge.zmanim.hebrewcalendar.JewishDate;
import net.sourceforge.zmanim.util.GeoLocation;

/**
 * Classe gérant le processus de purification de la femme
 *
 * @author mini-john
 */
public class Tahara {

    /**
     * Determine le jour ou le hfsek tahara est possible en fonction des jours
     * d'attentes
     *
     * @param dernierRapport
     * @param origine
     * @return
     */
    public static Date getDateHefsekPossible(Date dernierRapport, Origine origine) {
        switch (origine) {
            case Askenaze:
                return DateNiddah.addDay(dernierRapport, Constantes.NB_JOUR_ATTENTE_ASHKENAZE);

            case Sefarade:
                return DateNiddah.addDay(dernierRapport, Constantes.NB_JOUR_ATTENTE_SEPHARADE);
            default:
                return DateNiddah.addDay(dernierRapport, Constantes.NB_JOUR_ATTENTE_DEFAULT);
        }
    }

    /**
     * Determine le jour ou le hfsek tahara est possible en fonction des jours
     * d'attentes
     *
     * @param dernierRapport
     * @param origine
     * @return
     */
    public static JewishDate getDateHefsekPossible(JewishDate dernierRapport, Origine origine) {
        switch (origine) {
            case Askenaze:
                return DateNiddah.addDay(dernierRapport, Constantes.NB_JOUR_ATTENTE_ASHKENAZE);

            case Sefarade:
                return DateNiddah.addDay(dernierRapport, Constantes.NB_JOUR_ATTENTE_SEPHARADE);
            default:
                return DateNiddah.addDay(dernierRapport, Constantes.NB_JOUR_ATTENTE_DEFAULT);
        }
    }

    /**
     * Retourne la liste de sheva nekiym a faire
     *
     * @param date
     * @param location
     * @return
     */
    public static List<ShevaNekymDto> getShevaNekiym(Date date, GeoLocation location) {
        ZmanimCalendar zc = new ZmanimCalendar(location);
        List<ShevaNekymDto> shevaList = new ArrayList<>();
        zc.getCalendar().setTime(date);
        for (int i = 0; i < 7; i++) {

            ShevaNekymDto sheva = new ShevaNekymDto(zc.getSunrise(), zc.getSunset());

            shevaList.add(sheva);
            zc.getCalendar().add(Calendar.DATE, 1);
        }

        return shevaList;
    }

    /**
     * Retourne la liste de sheva nekiym a faire
     *
     * @param date
     * @param location
     * @return
     */
    public static List<ShevaNekymDto> getShevaNekiym(JewishDate date, GeoLocation location) {
        ZmanimCalendar zc = new ZmanimCalendar(location);
        List<ShevaNekymDto> shevaList = new ArrayList<>();
        zc.getCalendar().setTime(date.getTime());
        for (int i = 0; i < 7; i++) {

            ShevaNekymDto sheva = new ShevaNekymDto(zc.getSunrise(), zc.getSunset());

            shevaList.add(sheva);
            zc.getCalendar().add(Calendar.DATE, 1);
        }

        return shevaList;
    }

}
