/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jkalvered.library.tools;

import com.jkalvered.core.entite.Niddah;
import com.jkalvered.library.enumeration.Ona;
import com.jkalvered.library.math.JkalVeredMath;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.Duration;

/**
 * Classe permettant le calcul sur des Date juive
 *
 * @author mini-john
 */
public class JewishDateEcart implements Comparable<JewishDateEcart> {

    private int mois;
    private int jour;
    private int nbJourtEcart;
    private Ona ona;

    public JewishDateEcart(Niddah cycle1, Niddah cycle2, Ona ona) {
        int mois1, mois2, jour1, jour2;
        mois1 = cycle1.getjKalDate().getJewishMonth();
        mois2 = cycle2.getjKalDate().getJewishMonth();
        jour1 = cycle1.getjKalDate().getJewishDayOfMonth();
        jour2 = cycle2.getjKalDate().getJewishDayOfMonth();
        this.ona = ona;
        if (cycle1.getjKalDate().isJewishLeapYear()) {
            mois = JkalVeredMath.mod(mois2 - mois1, 13);
        } else {
            mois = JkalVeredMath.mod(mois2 - mois1, 12);

        }
        jour = JkalVeredMath.mod(jour2 - jour1, cycle1.getjKalDate().getDaysInJewishMonth());
        nbJourtEcart = cycle2.getHaflaga() - cycle1.getHaflaga();

    }

    public static Long getMinutesBetweenTwoDate(DateTime date1, DateTime date2) {
        Duration duration;
        duration = new Duration(date1, date2);
        return duration.getStandardMinutes();
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getJour() {
        return jour;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public int getNbJourtEcart() {
        return nbJourtEcart;
    }

    public void setNbJourtEcart(int nbJourtEcart) {
        this.nbJourtEcart = nbJourtEcart;
    }

    public Ona getOna() {
        return ona;
    }

    public void setOna(Ona ona) {
        this.ona = ona;
    }

    @Override
    public int compareTo(JewishDateEcart o) {
        return (this.getJour() == o.getJour() && this.getMois() == o.getMois() && this.ona == o.getOna() ? 0 : 1);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        return this.getJour() == ((JewishDateEcart) obj).getJour()
                && this.getMois() == ((JewishDateEcart) obj).getMois()
                && this.getOna() == ((JewishDateEcart) obj).getOna(); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean equalsHaflaga(JewishDateEcart ecart2) {
        return this.nbJourtEcart == ecart2.getNbJourtEcart() && this.ona == ecart2.getOna();
    }

    @Override
    public String toString() {
        return "JewishDateEcart{" + "mois=" + mois + ", jour=" + jour + ", nbJourtEcart=" + nbJourtEcart + ", ona=" + ona + '}';
    }

    public boolean equalsEcart(JewishDateEcart ecart2) {
        return this.getJour() == ecart2.getJour()
                && this.getMois() == ecart2.getMois()
                && this.getOna() == ecart2.getOna();
    }

    public static boolean isSameDay(Date date1, Date date2) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/YYYY");
        return fmt.format(date1).equals(fmt.format(date2));
    }
}
