/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.service;

import airport.Models.Entities.Flights.Flight;
import airport.Models.Entities.Passenger;
import airport.Models.Storage.JSONStorage;
import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author saraibanez
 */
public class FlightAssignmentService {
    private final List<Flight> flights;
    private final JSONStorage storage;

    public FlightAssignmentService(List<Flight> flights, JSONStorage storage) {
        this.flights = flights;
        this.storage = storage;
    }

    public Response assignPassenger(Passenger passenger, String flightID) throws IOException {
        Flight flight = flights.stream()
            .filter(f -> f.getId().equals(flightID))
            .findFirst()
            .orElse(null);

        flight.getPassengerList().addPassenger(passenger);
        passenger.getFlights().add(flight);
        storage. getFlightLoader().saveFlights(flights);

        return new Response("Pasajero añadido al vuelo", Status.OK, flight);
    }
}