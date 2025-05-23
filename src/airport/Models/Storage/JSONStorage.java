package airport.Models.Storage;

import java.util.ArrayList;

import airport.Models.Flight;
import airport.Models.Location;
import airport.Models.Passenger;
import airport.Models.Plane;
import airport.Storage.FlightStorage;
import airport.Storage.LocationStorage;
import airport.Storage.PassengerStorage;
import airport.Storage.PlaneStorage;

public class JSONStorage  implements LocationStorage, PlaneStorage , PassengerStorage , FlightStorage {
    private final JSONLocationStorage locationStorage;
    private final JSONPassengerStorage passengerStorage;
    private final JSONPlaneStorage planeStorage;
    private final JSONFlightStorage flightStorage;

    public JSONStorage(String locationPath, String passengerPath, String planePath, String flightPath) {
        this.locationStorage = new JSONLocationStorage(locationPath);
        this.passengerStorage = new JSONPassengerStorage(passengerPath);
        this.planeStorage = new JSONPlaneStorage(planePath);
        this.flightStorage = new JSONFlightStorage(flightPath);
    }

    @Override
    public ArrayList<Flight> loadFlights(ArrayList<Location> locations, ArrayList<Passenger> passengers,
            ArrayList<Plane> planes) {
                 return flightStorage.loadFlights(locations, passengers, planes);
    }

    @Override
    public void saveFlights(ArrayList<Flight> flights) {
        flightStorage.saveFlights( flights);
    }

    @Override
     public ArrayList<Passenger> loadPassengers() {
         return passengerStorage.loadPassengers();
    }

    public ArrayList<Plane> loadPlanes() {
         return planeStorage.loadPlanes();
    }

    @Override
    public void savePlanes(ArrayList<Plane> planes) {
        planeStorage.savePlanes(planes);
    }

    @Override
    public ArrayList<Location> loadLocations() {
         return locationStorage.loadLocations();
    }

    @Override
    public void saveLocations(ArrayList<Location> locations) {
     locationStorage.saveLocations(locations);
    }

    @Override
    public void savePassengers(ArrayList<Passenger> passengers) {
        passengerStorage.savePassengers(passengers);
    }



   
    
}
