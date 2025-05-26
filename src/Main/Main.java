/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import airport.Models.Entities.Flights.Flight;
import airport.Models.Entities.Location;
import airport.Models.Entities.Passenger;
import airport.Models.Entities.Plane;
import airport.Models.Observable.FlightRepository;
import airport.Models.Observable.LocationRepository;
import airport.Models.Observable.PassengerRepository;
import airport.Models.Observable.PlaneRepository;
import airport.Models.Serialization.JSONFlight;
import airport.Models.Serialization.JSONLocation;
import airport.Models.Serialization.JSONMapper;
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
import airport.controllers.service.PassengerUpdateService;
import airport.views.AirportFrame;
import com.formdev.flatlaf.FlatDarkLaf;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.UIManager;


/**
 *
 * @author yader
 */
public class Main {

    public static void main(String args[]) throws IOException {
        
        System.setProperty("flatlaf.useNativeLibrary", "false");

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // Loaders
                JSONMapper<Location> locationMapper = new JSONLocation();
                JSONMapper<Plane> planeMapper = new JSONPlane();
                JSONMapper<Passenger> passengerMapper = new JSONPassenger();
                LocationDataLoader locationLoader = new LocationDataLoader(locationMapper, "json/locations.json");
                PlaneDataLoader planeLoader = new PlaneDataLoader(planeMapper, "json/planes.json");
                PassengerDataLoader passengerLoader = new PassengerDataLoader(passengerMapper, "json/passengers.json");

                // Cargar datos base
                Map<String, Plane> planesMap = null;
                Map<String, Location> locationsMap = null;
                Map<Long, Passenger> passengerMap = null;

                try {
                    planesMap = planeLoader.loadPlanes();
                    locationsMap = locationLoader.loadLocations();
                    passengerMap = passengerLoader.loadPassengers();
                } catch (IOException e) {
                    System.out.println("Error al cargar datos: " + e.getMessage());
                }

                // Si los mapas cargaron bien
                if (planesMap != null && locationsMap != null) {
                    JSONFlight flightMapper = new JSONFlight(planesMap, locationsMap, passengerMap);
                    FlightDataLoader flightLoader = new FlightDataLoader(flightMapper, planesMap, locationsMap, "json/flights.json");
                    JSONStorage storage = new JSONStorage(planeLoader, locationLoader, passengerLoader, flightLoader);

                    try {
                        LoadedData loadedData = storage.loadAll();
                        LocationController locationController = new LocationController(locationsMap, storage);
                        PlaneController planeController = new PlaneController(planesMap, storage);
                        PassengerUpdateService passengerUpdateService = new PassengerUpdateService(passengerMap, storage);
                        PassengerController passengerController = new PassengerController(passengerMap, storage,  new ArrayList<>(),passengerUpdateService);
                        FlightController flightController = new FlightController(locationsMap, planesMap, passengerMap, storage);
                        PassengerRepository passengerRepository = new PassengerRepository(passengerController);
                        FlightRepository flightRepository = new FlightRepository(flightController);
                        LocationRepository locationRepository  =  new LocationRepository(locationController);
                        PlaneRepository planeRepository  =  new PlaneRepository(planeController);
                        AirportFrame airportFrame = new AirportFrame();
                        passengerRepository.addObserver(airportFrame);
                        airportFrame.setFlightRepository(flightRepository);
                        airportFrame.setPassengerRepository(passengerRepository);
                         airportFrame.setLocationRepository(locationRepository);
                          airportFrame.setPlaneRepository(planeRepository);
                        airportFrame.onDataChanged(); 
                        airportFrame.setVisible(true);
                        passengerRepository.notifyObservers();
                        planeRepository.notifyObservers();
                        locationRepository.notifyObservers();
                        flightRepository.notifyObservers();

                    } catch (IOException e) {
                        System.out.println("Error al cargar datos: " + e.getMessage());
                    }
                } else {
                    System.out.println("Error: No se pudieron cargar los datos correctamente. Revisa los archivos JSON.");
                }

         

            }
        });
    }
}
