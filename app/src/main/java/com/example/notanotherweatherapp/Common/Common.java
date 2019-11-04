package com.example.notanotherweatherapp.Common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class Common {
    public static final String API_KEY = "179abdcaee4bb7425dfbf57ddb5427cd";
    public static final String API_LINK = "http://api.openweathermap.org/data/2.5/weather";

    public static String APIRequest(String lat,String lng){
        StringBuilder sb = new StringBuilder(API_LINK);
        sb.append(String.format("?lat=%s&lon=%s&APPID=%s&units=metric", lat, lng, API_KEY));
        return sb.toString();
    }

    public static String APIRequest(String city){
        StringBuilder sb = new StringBuilder(API_LINK);
        sb.append(String.format("?q=%s&APPID=%s&units=metric", city, API_KEY));
        return sb.toString();
    }

    public static String unixTimeStampToDate(double unixTimeStamp){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        date.setTime((long)unixTimeStamp*1000);
        return dateFormat.format(date);
    }

    public static String getImage(String s){
        return String.format("http://openweathermap.org/img/w/%s.png", s);
    }

    public static String getDateNow(){
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy hh:mm");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getDayOfWeek(String dateString) throws Exception{
        DateFormat dateFormat = new SimpleDateFormat(("HH:mm"));
        Calendar calendar = Calendar.getInstance();
        Date date = new SimpleDateFormat("yyyy-mm-dd").parse(dateString);

        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch(day){
            case 1:
                return "Sunday";
            case 2:
                return "Monday";
            case 3:
                return "Tuesday";
            case 4:
                return "Wednesday";
            case 5:
                return "Thursday";
            case 6:
                return "Friday";
            case 7:
                return "Saturday";
            default:
                return "Not Found!";
        }
    }

}
