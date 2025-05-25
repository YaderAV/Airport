package airport.Models.Storage;

import airport.Models.Entities.Flights.Flight;
import airport.Models.Entities.Location;
import airport.Models.Entities.Passenger;
import airport.Models.Entities.Plane;
import airport.Models.Storage.DataLoaders.FlightDataLoader;
import airport.Models.Storage.DataLoaders.LocationDataLoader;
import airport.Models.Storage.DataLoaders.PassengerDataLoader;
import airport.Models.Storage.DataLoaders.PlaneDataLoader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JSONStorage   {
    private final PlaneDataLoader planeLoader; 
    private final LocationDataLoader locationLoader; 
    private final PassengerDataLoader passengerLoader; 
    private final FlightDataLoader flightLoader; 

    public JSONStorage(PlaneDataLoader planeLoader, LocationDataLoader locationLoader, PassengerDataLoader passengerLoader, FlightDataLoader flightLoader) {
        this.planeLoader = planeLoader;
        this.locationLoader = locationLoader;
        this.passengerLoader = passengerLoader;
        this.flightLoader = flightLoader;
    }
    
    
    public LoadedData loadAll() throws IOException{
        Map<String, Plane> planes = planeLoader.loadPlanes();
        Map<String, Location> locations = locationLoader.loadLocations();
        Map<Long, Passenger> passengers = passengerLoader.loadPassengers();
        List<Flight> flights = flightLoader.loadFlights();
        return new LoadedData(planes, locations, passengers, flights);
    }

    public PlaneDataLoader getPlaneLoader() {
        return planeLoader;
    }

    public LocationDataLoader getLocationLoader() {
        return locationLoader;
    }

    public PassengerDataLoader getPassengerLoader() {
        return passengerLoader;
    }

    public FlightDataLoader getFlightLoader() {
        return flightLoader;
    }
    
}
