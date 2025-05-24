/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.service;

import airport.Models.Flight;
import airport.Models.Passenger;
import airport.Models.Storage.Storage;
import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import java.util.ArrayList;

/**
 *
 * @author saraibanez
 */
public class FlightAssignmentService {
    private final ArrayList<Flight> flights;
    private final Storage storage;

    public FlightAssignmentService(ArrayList<Flight> flights, Storage storage) {
        this.flights = flights;
        this.storage = storage;
    }

    public Response assignPassenger(Passenger passenger, String flightID) {
        Flight flight = flights.stream()
            .filter(f -> f.getId().equals(flightID))
            .findFirst()
            .orElse(null);

        if (flight == null) {
            return new Response("Vuelo no encontrado", Status.NOT_FOUND);
        }

        flight.addPassenger(passenger);
        passenger.addFlight(flight);
        storage.saveFlights(flights);

        return new Response("Pasajero añadido al vuelo", Status.OK, flight);
    }
}