/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.service;

import airport.Models.Entities.Flights.Flight;
import airport.Models.Storage.JSONStorage;
import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author saraibanez
 */
public class FlightDelayService {
    private final List<Flight> flights;
    private final JSONStorage storage;

    public FlightDelayService(List<Flight> flights, JSONStorage storage) {
        this.flights = flights;
        this.storage = storage;
    }

    public Response delay(String id, int hourDelay, int minuteDelay) throws IOException {
        Flight flight = flights.stream()
            .filter(f -> f.getId().equals(id))
            .findFirst()
            .orElse(null);

        if (flight == null) {
            return new Response("Vuelo no encontrado", Status.NOT_FOUND);
        }

        if (hourDelay <= 0 && minuteDelay <= 0) {
            return new Response("El retraso debe ser mayor a 00:00.", Status.BAD_REQUEST);
        }

        flight.getSchedule().delay(hourDelay, minuteDelay);

        storage.getFlightLoader().saveFlights(flights);
        return new Response("Vuelo retrasado exitosamente", Status.OK, flight);
    }
}
