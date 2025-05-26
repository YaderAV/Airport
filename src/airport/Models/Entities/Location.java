/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Entities;

import airport.controllers.Prototype;

/**
 *
 * @author yader
 */
public class Location implements Prototype<Location> {
    private final String airportID;
    private String airportName;
    private String airportCity;
    private String airportCountry;
    private double airportLatitude;
    private double airportLongitude;

    public Location(String airportID, String airportName, String airportCity, String airportCountry, double airportLatitude, double airportLongitude) {
        this.airportID = airportID;
        this.airportName = airportName;
        this.airportCity = airportCity;
        this.airportCountry = airportCountry;
        this.airportLatitude = airportLatitude;
        this.airportLongitude = airportLongitude;
    }

    public String getAirportID() {
        return airportID;
    }

    public String getAirportName() {
        return airportName;
    }

    public String getAirportCity() {
        return airportCity;
    }

    public String getAirportCountry() {
        return airportCountry;
    }

    public double getAirportLatitude() {
        return airportLatitude;
    }

    public double getAirportLongitude() {
        return airportLongitude;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public void setAirportCity(String airportCity) {
        this.airportCity = airportCity;
    }

    public void setAirportCountry(String airportCountry) {
        this.airportCountry = airportCountry;
    }

    public void setAirportLatitude(double airportLatitude) {
        this.airportLatitude = airportLatitude;
    }

    public void setAirportLongitude(double airportLongitude) {
        this.airportLongitude = airportLongitude;
    }

    @Override
    public Location clone() {
       return new Location (airportID, airportName, airportCity, airportCountry, airportLatitude, airportLongitude);
    }
    
    
}
