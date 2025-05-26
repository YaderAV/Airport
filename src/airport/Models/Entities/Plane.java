/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Entities;

import airport.Models.Entities.Flights.Flight;
import airport.controllers.Prototype;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yader
 */
public class Plane implements Prototype<Plane>{

    private final String id;
    private String brand;
    private String model;
    private final int maxCapacity;
    private String airline;
    private final List<Flight> flightsPlane = new ArrayList<>();

    public Plane(String id, String brand, String model, int maxCapacity, String airline) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.maxCapacity = maxCapacity;
        this.airline = airline;
    }

    public void addFlight(Flight flight) {
        flightsPlane.add(flight);
    }

    public int getNumFlights() {
        return flightsPlane.size();
    }

    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public String getAirline() {
        return airline;
    }

    public List<Flight> getFlightsPlane() {
        return flightsPlane;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    @Override
    public Plane clone() {
        return new Plane(id,brand, model,maxCapacity, airline);
    }
    
    
}
