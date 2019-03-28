package com.disis.weather.MOdel;

public class Model {

    String city;
    int woeid;
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWoeid() {
        return String.valueOf(woeid);
    }

    public void setWoeid(int woeid) {
        this.woeid = woeid;
    }

    public Model(String city, int woeid) {
        this.city = city;
        this.woeid = woeid;
    }

}
