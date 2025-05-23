/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Storage;

import airport.Models.Flight;
import airport.Models.Location;
import airport.Models.Passenger;
import airport.Models.Plane;
import java.awt.List;
import java.util.ArrayList;

/**
 *
 * @author yader
 */
public interface Storage {
    ArrayList<Location> loadLocations();
    ArrayList<Passenger> loadPassengers();
    ArrayList<Plane> loadPlanes();
    ArrayList<Flight> loadFlights(ArrayList<Location> locations, ArrayList<Passenger> passengers, ArrayList<Plane> planes);
    
    void saveLocations(ArrayList<Location> locations);
    void savePassengers(ArrayList<Passenger> passengers);
    void savePlanes(ArrayList<Plane> planes);
    void saveFlights(ArrayList<Flight> flights);
    
}
