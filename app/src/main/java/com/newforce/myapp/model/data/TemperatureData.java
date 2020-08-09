package com.newforce.myapp.model.data;

public class TemperatureData {

    private int celsius;
    private String location;

    public TemperatureData(){
        this.celsius = 0;
        this.location = "Room Default";
    }

    public void setTempCelsius(int newTempCelsius){
        this.celsius = newTempCelsius;
    }

    public int getTempCelsius(){
        return celsius;
    }

    public String getStringTempCelsius(){
        return (celsius) + " ºC";
    }

    public void setLocation(String newLocation){
        this.location = newLocation;
    }

    public String getLocation(){
        return location;
    }
}
