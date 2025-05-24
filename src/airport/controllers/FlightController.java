/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers;
import airport.controllers.service.FlightDelayService;
import airport.controllers.service.FlightCreationService;
import airport.controllers.service.FlightAssignmentService;
import airport.Models.Flight;
import airport.Models.Location;
import airport.Models.Observable.ObservableBase;
import airport.Models.Passenger;
import airport.Models.Plane;
import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import java.time.LocalDateTime;
import java.util.ArrayList;
import airport.Models.Storage.Storage;

/**
 *
 * @author yader
 */
public class FlightController extends ObservableBase {
    private final ArrayList<Flight> flights;
    private final Storage storage;

    public FlightController(ArrayList<Location> locations, ArrayList<Plane> planes, ArrayList<Passenger> passengers, Storage storage) {
        this.storage = storage;
        this.flights = storage.loadFlights(locations, passengers, planes);
    }

    public Response createFlight(String id, Plane plane, Location departureLocation, Location scaleLocation, Location arrivalLocation,LocalDateTime departureDate, int hoursDurationArrival, int minutesDurationArrival,int hoursDurationScale, int minutesDurationScale) {
        FlightCreationService service = new FlightCreationService(flights, storage);
        Response response = service.create(id, plane, departureLocation, scaleLocation, arrivalLocation,  departureDate, hoursDurationArrival, minutesDurationArrival,hoursDurationScale, minutesDurationScale);
        if (response.getStatus() == Status.CREATED) notifyObservers();
        return response;
    }

    public Response addPassenger(Passenger passenger, String flightID) {
        FlightAssignmentService service = new FlightAssignmentService(flights, storage);
        return service.assignPassenger(passenger, flightID);
    }

    public Response delayFlight(String id, int hourDelay, int minuteDelay) {
        FlightDelayService service = new FlightDelayService(flights, storage);
        return service.delay(id, hourDelay, minuteDelay);
    }
}

