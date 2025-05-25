/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers;
import airport.Models.Entities.Flights.Flight;
import airport.Models.Entities.Flights.FlightSchedule;
import airport.Models.Entities.Location;
import airport.Models.Entities.Passenger;
import airport.Models.Entities.Plane;
import airport.controllers.service.FlightDelayService;
import airport.controllers.service.FlightCreationService;
import airport.controllers.service.FlightAssignmentService;
import airport.Models.Observable.ObservableBase;
import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.Models.Storage.JSONStorage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yader
 */
public class FlightController extends ObservableBase {
    private final List<Flight> flights;
    private final JSONStorage storage;

    public FlightController(Map<String, Location> locations, Map<String, Plane> planes, Map<Long,Passenger> passengers, JSONStorage storage) throws IOException {
        this.storage = storage;
        this.flights = storage.loadAll().flights;
    }

    public Response createFlight(String id, Plane plane, Location departureLocation, Location scaleLocation, Location arrivalLocation,FlightSchedule flightSchedule) throws IOException {
        FlightCreationService service = new FlightCreationService(flights, storage);
        Response response = service.create(id, plane, departureLocation, scaleLocation, arrivalLocation, flightSchedule);
        if (response.getStatus() == Status.CREATED) notifyObservers();
        return response;
    }

    public Response addPassenger(Passenger passenger, String flightID) throws IOException {
        FlightAssignmentService service = new FlightAssignmentService(flights, storage);
        return service.assignPassenger(passenger, flightID);
    }

    public Response delayFlight(String id, int hourDelay, int minuteDelay) throws IOException {
        FlightDelayService service = new FlightDelayService(flights, storage);
        return service.delay(id, hourDelay, minuteDelay);
    }
}

