package com.example.notanotherweatherapp.Common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {
    public static final String API_KEY = "";
    public static final String API_LINK = "http://api.openweathermap.org/data/2.5/weather";

    public static String APIRequest(String lat,String lng){
        StringBuilder sb = new StringBuilder(API_LINK);
        sb.append(String.format("?lat=%s&lon%s&APPID=%s&units=metric", lat, lng, API_KEY));
        return sb.toString();
    }

    public static String unixTimeStampToDate(double unixTimeStamp){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        date.setTime((long)unixTimeStamp*1000);
        return dateFormat.format(date);
    }

    public static String getImage(){
        return String.format("http://openweathermap.org/img/w/%s.png");
    }

    public static String getDateNow(){
        DateFormat dateFormat = new SimpleDateFormat("dd mmmm yyyy hh:mm");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
