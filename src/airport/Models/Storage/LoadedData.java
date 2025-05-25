/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Storage;

import airport.Models.Entities.Flights.Flight;
import airport.Models.Entities.Location;
import airport.Models.Entities.Passenger;
import airport.Models.Entities.Plane;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yader
 */
public class LoadedData {
    public final Map<String, Plane> planes; 
    public final Map<String, Location> locations; 
    public final Map<Long, Passenger> passengers; 
    public final List<Flight> flights; 

    public LoadedData(Map planes, Map locations, Map passengers, List flights) {
        this.planes = planes;
        this.locations = locations;
        this.passengers = passengers;
        this.flights = flights;
    }

    public Map<String, Plane> getPlanes() {
        return planes;
    }

    public Map<String, Location> getLocations() {
        return locations;
    }

    public Map<Long, Passenger> getPassengers() {
        return passengers;
    }

    public List<Flight> getFlights() {
        return flights;
    }
}

