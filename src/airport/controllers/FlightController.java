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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yader
 */


public class FlightController  {
    private final List<Flight> flights;
    private final JSONStorage storage;
  

    public FlightController(Map<String, Location> locations, Map<String, Plane> planes, Map<Long, Passenger> passengers, JSONStorage storage) throws IOException {
        this.storage = storage;
        this.flights = storage.loadAll().flights;
        System.out.println("DEBUG - flights: " + flights); 
    }

    public Response createFlight(String id, Plane plane, Location departureLocation, Location scaleLocation, Location arrivalLocation, FlightSchedule flightSchedule) throws IOException {
        FlightCreationService service = new FlightCreationService(flights, storage);
        Response response = service.create(id, plane, departureLocation, scaleLocation, arrivalLocation, flightSchedule);
        if (response.getStatus() == Status.CREATED) {
            sortFlights();
        }
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

    public Response getAllFlights() {
        sortFlights();  // Asegura que siempre se devuelve la lista ordenada
        return new Response("Lista de vuelos obtenida.", Status.OK, flights);
    }

    private void sortFlights() {
        flights.sort(Comparator.comparing(f -> f.getSchedule().getDepartureDate()));
    }

    public List<Flight> getFlightsByPassengerId(long passengerId) {
        List<Flight> passengerFlights = new ArrayList<>();
        for (Flight flight : flights) { 
           for (Passenger p : flight.getPassengerList().getPassenger()) {
                if (p.getId() == passengerId) {
                    passengerFlights.add(flight);
                    break;
                }
            }
        }
        // Opcional: Ordenar los vuelos por fecha de salida
        passengerFlights.sort(Comparator.comparing(f -> f.getSchedule().getDepartureDate()));
        return passengerFlights;
    }
    public void addPassengerToFlight(Passenger passenger, Flight flight) {
    flight.getPassengerList().addPassenger(passenger);
}


}


