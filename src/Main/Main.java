/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import airport.Models.Entities.Flights.Flight;
import airport.Models.Entities.Location;
import airport.Models.Entities.Passenger;
import airport.Models.Entities.Plane;
import airport.Models.Serialization.JSONFlight;
import airport.Models.Serialization.JSONLocation;
import airport.Models.Serialization.JSONPassenger;
import airport.Models.Serialization.JSONPlane;
import airport.Models.Storage.DataLoaders.FlightDataLoader;
import airport.Models.Storage.DataLoaders.LocationDataLoader;
import airport.Models.Storage.DataLoaders.PassengerDataLoader;
import airport.Models.Storage.DataLoaders.PlaneDataLoader;
import airport.Models.Storage.JSONStorage;
import airport.Models.Storage.LoadedData;
import airport.controllers.FlightController;
import airport.controllers.LocationController;
import airport.controllers.PassengerController;
import airport.controllers.PlaneController;
import airport.views.AirportFrame;
import com.formdev.flatlaf.FlatDarkLaf;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;

/**
 *
 * @author yader
 */
public class Main {

    public static void main(String args[]) throws IOException {
        
        JSONLocation locationMapper = new JSONLocation();
        JSONPassenger passengerMapper = new JSONPassenger();
        JSONPlane planeMapper = new JSONPlane();

        LocationDataLoader locationLoader = new LocationDataLoader(locationMapper, "C:/Users/yader/Desktop/Airport/json/locations.json");
        PassengerDataLoader passengerLoader = new PassengerDataLoader(passengerMapper, "C:/Users/yader/Desktop/Airport/json/passengers.json");
        PlaneDataLoader planeLoader = new PlaneDataLoader(planeMapper, "C:/Users/yader/Desktop/Airport/json/planes.json");

        Map<String, Plane> planes = planeLoader.loadPlanes();
        Map<Long, Passenger> passengers = passengerLoader.loadPassengers();
        Map<String, Location> locations = locationLoader.loadLocations();

        JSONFlight flightMapper = new JSONFlight(planes, locations);
        FlightDataLoader flightLoader = new FlightDataLoader(flightMapper, planeLoader.loadPlanes(), locationLoader.loadLocations(), "C:/Users/yader/Desktop/Airport/json/flights.json");
        List<Flight> flights = flightLoader.loadFlights();

        JSONStorage storage = new JSONStorage(planeLoader, locationLoader, passengerLoader, flightLoader);

        LoadedData data = storage.loadAll();

        PassengerController passengerController = new PassengerController(passengers, storage);
        LocationController locationController = new LocationController(locations, storage);
        PlaneController planeController = new PlaneController(planes, storage);
        FlightController flightController = new FlightController(locations, planes, passengers, storage);
System.setProperty("flatlaf.useNativeLibrary", "false");

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new AirportFrame(storage).setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
