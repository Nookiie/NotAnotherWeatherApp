package com.example.notanotherweatherapp.Model;

import java.util.List;

public class OpenForecastMap {
    private int cod;
    private String message;
    private int cnt;
    private List<OpenWeatherMap> openWeatherMap;

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<OpenWeatherMap> getOpenWeatherMap() {
        return openWeatherMap;
    }

    public void setOpenWeatherMap(List<OpenWeatherMap> openWeatherMap) {
        this.openWeatherMap = openWeatherMap;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    private City city;
}
