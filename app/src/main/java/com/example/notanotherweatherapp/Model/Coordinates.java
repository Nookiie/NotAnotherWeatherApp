package com.example.notanotherweatherapp.Model;

public class Coordinates {
    private double lat;
    private double lon;

    public Coordinates(double lat, double lon){
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat(){
        return lat;
    }

    public double getLon(){
        return lon;
    }

    public void setLat(double lat){
        this.lat = lat;
    }

    public void setLon(double lon){
        this.lon = lon;
    }
}
