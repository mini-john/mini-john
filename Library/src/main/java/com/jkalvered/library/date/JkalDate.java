/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jkalvered.library.date;

import com.jkalvered.library.enumeration.MomentJournee;
import static com.jkalvered.library.enumeration.MomentJournee.Matin;
import com.jkalvered.library.enumeration.Ona;
import com.jkalvered.library.exception.MomentException;
import com.jkalvered.library.exception.NiddahException;
import com.kosherjava.zmanim.ZmanimCalendar;
import com.kosherjava.zmanim.hebrewcalendar.JewishDate;
import com.kosherjava.zmanim.util.GeoLocation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.Days;
import org.joda.time.LocalDate;

/**
 *
 * @author jonat
 */
public class JkalDate extends JewishDate {

    private Date dateGregorian;
    private int hours;
    private int min;

    private String locationName;
    private double latitude;
    private double longitude;
    double elevation;
    private TimeZone timeZone;
    private ZmanimCalendar zc;

    public JkalDate(Date dateGregorian, String locationName, double latitude, double longitude, double elevation, String timeZone) {
        super(dateGregorian);
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(dateGregorian);
        this.dateGregorian = dateGregorian;

        this.hours = cal.get(Calendar.HOUR_OF_DAY);
        this.min = cal.get(Calendar.MINUTE);

        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
        this.timeZone = TimeZone.getTimeZone(timeZone);
        this.zc = new ZmanimCalendar(new GeoLocation(locationName, latitude, longitude, elevation, this.timeZone));
        this.zc.setCalendar(cal);
    }

    public int getHours() {
        return hours;
    }

    public Date getDateGregorian() {
        return dateGregorian;
    }

    public void setDateGregorian(Date date) {
        this.dateGregorian = date;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public ZmanimCalendar getZc() {
        return zc;
    }

    public void setZc(ZmanimCalendar zc) {
        this.zc = zc;
    }

    public GeoLocation getGeolocation() {
        return new GeoLocation(locationName, latitude, longitude, elevation, timeZone);
    }

    public JewishDate getJewishDate() {
        Date dateTemp = null;
        switch (getMomentJournee()) {
            case Jour, Matin ->
                dateTemp = dateGregorian;
            case Soir -> {
                Calendar cal = GregorianCalendar.getInstance();
                cal.setTime(dateGregorian);
                cal.add(Calendar.DAY_OF_MONTH, 1);
                dateTemp = cal.getTime();
            }

        }
        return new JewishDate(dateTemp);
    }

    public MomentJournee getMomentJournee() {
        if (zc.getSunrise().after(dateGregorian)) {
            return MomentJournee.Matin;
        } else if (zc.getSunrise().before(dateGregorian) && zc.getSunset().after(dateGregorian)) {
            return MomentJournee.Jour;
        } else if (zc.getSunset().before(dateGregorian)) {
            return MomentJournee.Soir;
        } else {
            throw new MomentException("L'heure est incorrect" + dateGregorian.toString());

        }
    }

    @Override
    public Calendar getGregorianCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getGregorianYear(), getGregorianMonth(), getGregorianDayOfMonth(), hours, min);
        return calendar;
    }

    public Ona getOna() {

        this.zc.getCalendar().setTime(dateGregorian);
        return this.zc.getSunrise().before(dateGregorian) && this.zc.getSunset().after(dateGregorian) ? Ona.Jour : Ona.Nuit;

    }
    public int getNombreJourEcart(Date date){
        int res = 0;
        if (0 <= dateGregorian.compareTo(date)) {
            throw new NiddahException("La date 1 " + dateGregorian.toString() + " doit etre avant la date 2 " + date.toString());
        }
        res = Days.daysBetween(new LocalDate(dateGregorian), new LocalDate(date)).getDays();

        return res + 1;
    }
    public int getNombreJourEcart(JkalDate date){
        int res = 0;
        if (0 <= dateGregorian.compareTo(date.getDateGregorian())) {
            throw new NiddahException("La date 1 " + dateGregorian.toString() + " doit etre avant la date 2 " + date.toString());
        }
        res = Days.daysBetween(new LocalDate(dateGregorian), new LocalDate(date.getDateGregorian())).getDays();

        return res + 1;
    }

    @Override
    public String toString() {
        return "JkalDate{" + "dateGregorian=" + dateGregorian + ", hours=" + hours + ", min=" + min + ", locationName=" + locationName + ", latitude=" + latitude + ", longitude=" + longitude + ", elevation=" + elevation + ", timeZone=" + timeZone + ", Ona=" + getOna() + '}';
    }

    private static final SimpleDateFormat formatDateWithHour = new SimpleDateFormat("dd/MM/yyyy HH:m");
    private static final SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
    private static Logger LOGGER = LogManager.getLogger();

    /**
     * Prend une chaine de caractère représentant une date et retourne la date
     * formaté
     *
     * @param dateStr
     * @return la date sur la forme dd/MM/yyyy
     * @throws ParseException
     */
    public static Date parseDate(String dateStr) throws ParseException {
        return formatDate.parse(dateStr);
    }

    /**
     * Prend une chaine de caractère représentant une date avec une heure
     *
     * @param dateStr
     * @return la date sur la forme dd/MM/yyyy HH:mm
     * @throws ParseException
     */
    public static Date parseDateWithHour(String dateStr) throws ParseException {
        return formatDateWithHour.parse(dateStr);
    }

    /**
     * Ajoute nbJour à la date gregorian
     *
     * @param date
     * @param nbJour
     * @return
     */
    public static Date addDay(Date date, int nbJour) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, nbJour);
        return cal.getTime();

    }

    /**
     * Ajoute nbMonth à la date grégorian
     *
     * @param date
     * @param nbMonth
     * @return
     */
    public static Date addMonth(Date date, int nbMonth) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, nbMonth);
        return cal.getTime();

    }

    /**
     * Retourne le prochain mois de 3O jours apres jDate
     *
     * @param jDate
     * @return
     */
    public static JewishDate getNextMonthFull(JewishDate jDate) {

        jDate.forward(Calendar.MONTH, 1);
        while (jDate.getDaysInJewishMonth() != 30) {
            jDate.forward(Calendar.MONTH, 1);
        }

        return jDate;
    }

    /**
     * Return le moment de la journée (Matin,Jour,Soir) en fonction de la
     * géolocalisation
     *
     * @param date
     * @param location
     * @return
     * @throws MomentException
     * @deprecated a remplacer par un objet jKalDate
     */
    @Deprecated()
    public static MomentJournee getMomentJournee(Date date, GeoLocation location) throws MomentException {
        ZmanimCalendar zc = new ZmanimCalendar(location);
        zc.getCalendar().setTime(date);

        if (zc.getSunrise().after(date)) {
            return MomentJournee.Matin;
        } else if (zc.getSunrise().before(date) && zc.getSunset().after(date)) {
            return MomentJournee.Jour;
        } else if (zc.getSunset().before(date)) {
            return MomentJournee.Soir;
        } else {
            throw new MomentException("L'heure est incorrect" + date.toString());

        }

    }

    /**
     * Return la date hébraique associé a la date gregorian
     *
     * @param date
     * @return
     */
    public static JewishDate getDateJewish(Date date) {

        return new JewishDate(date);
    }

    /**
     * Return la date juive associé à la date gregorian en fonction de la
     * geolocalisation
     *
     * @param date
     * @param location
     * @return
     */
    public static JewishDate getDateJewish(Date date, GeoLocation location) {

        Date dateTemp = null;
        switch (JkalDate.getMomentJournee(date, location)) {
            case Jour, Matin ->
                dateTemp = date;
            case Soir -> {
                Calendar cal = GregorianCalendar.getInstance();
                cal.setTime(date);
                cal.add(Calendar.DAY_OF_MONTH, 1);
                dateTemp = cal.getTime();
            }

        }
        return new JewishDate(dateTemp);
    }

    /**
     * Ajoute nbMonth à la JewishDate jDate
     *
     * @param jDate
     * @param nbMonth
     * @return
     */
    public static JewishDate addMonth(JewishDate jDate, int nbMonth) {
        jDate.forward(Calendar.MONTH, nbMonth);
        return jDate;
    }

    public static int getNombreJourEntreDeuxDate(Date date1, Date date2) {
        int res = 0;
        if (0 <= date1.compareTo(date2)) {
            throw new NiddahException("La date 1 " + date1.toString() + " doit etre avant la date 2 " + date2.toString());
        }
        res = Days.daysBetween(new LocalDate(date1), new LocalDate(date2)).getDays();

        return res + 1;
    }

    public static int getNombreJourEntreDeuxJewishDate(JewishDate date1, JewishDate date2) {
        int res = 0;
        if (0 <= date1.compareTo(date2)) {
            throw new NiddahException("La date 1 " + date1.toString() + " doit etre avant la date 2 " + date2.toString());
        }
        res = Days.daysBetween(new LocalDate(date1), new LocalDate(date2)).getDays();

        return res + 1;
    }

}
