/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.niddah.niddah.library;
import net.sourceforge.zmanim.*;
import net.sourceforge.zmanim.util.*;
import java.util.TimeZone;
/**
 *
 * @author mini-john
 */
public class Main {
    
    public static void main(String[] args){
        System.out.println("com.niddah.niddah.library.Main.main()");
       String locationName = "Lakewood, NJ";
        double latitude = 40.096; //latitude of Lakewood, NJ
        double longitude = -74.222; //longitude of Lakewood, NJ
        double elevation = 0; //optional elevation
        //use a Valid Olson Database timezone listed in java.util.TimeZone.getAvailableIDs()
        TimeZone timeZone = TimeZone.getTimeZone("America/New_York");
        //create the location object
        GeoLocation location = new GeoLocation(locationName, latitude, longitude, elevation, timeZone);
        //create the ZmanimCalendar
        ZmanimCalendar zc = new ZmanimCalendar(location);
        //optionally set the internal calendar
        //zc.getCalendar().set(1969, Calendar.FEBRUARY, 8);
        System.out.println("Today's Zmanim for " + locationName);
        System.out.println("Sunrise: " + zc.getSunrise()); //output sunrise
        System.out.println("Sof Zman Shema GRA: " + zc.getSofZmanShmaGRA()); //output Sof Zman Shema GRA
        System.out.println("Sunset: " + zc.getSunset()); //output sunset
    

    }
    
}
