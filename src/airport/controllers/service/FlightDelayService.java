/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.service;

import airport.Models.Entities.Flights.Flight;
import airport.Models.Storage.JSONStorage;
import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.controllers.utils.validators.FlightValidator;
import airport.controllers.utils.validators.ValidationResult;
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

        FlightValidator validator = new FlightValidator(flights);
        ValidationResult validation = validator.validate(flight);
        if (!validation.isValid()) {
            return new Response(validation.getCombinedMessage(), Status.BAD_REQUEST);
        }

        flight.getSchedule().delay(hourDelay, minuteDelay);

        storage.getFlightLoader().saveFlights(flights);
        return new Response("Vuelo retrasado exitosamente", Status.OK, flight);
    }
}
