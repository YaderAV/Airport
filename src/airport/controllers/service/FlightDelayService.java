/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.service;

import airport.Models.Flight;
import airport.Models.Storage.Storage;
import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author saraibanez
 */
public class FlightDelayService {
    private final ArrayList<Flight> flights;
    private final Storage storage;

    public FlightDelayService(ArrayList<Flight> flights, Storage storage) {
        this.flights = flights;
        this.storage = storage;
    }

    public Response delay(String id, int hourDelay, int minuteDelay) {
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

        LocalDateTime newDepartureDate = flight.getDepartureDate()
            .plusHours(hourDelay)
            .plusMinutes(minuteDelay);
        flight.setDepartureDate(newDepartureDate);

        storage.saveFlights(flights);
        return new Response("Vuelo retrasado exitosamente", Status.OK, flight);
    }
}
