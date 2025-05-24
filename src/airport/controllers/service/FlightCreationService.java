/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.service;

import airport.Models.Flight;
import airport.Models.Location;
import airport.Models.Plane;
import airport.Models.Storage.Storage;
import airport.controllers.utils.FlightValidator;
import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.controllers.utils.ValidationResult;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author saraibanez
 */
public class FlightCreationService {
    private final ArrayList<Flight> flights;
    private final Storage storage;

    public FlightCreationService(ArrayList<Flight> flights, Storage storage) {
        this.flights = flights;
        this.storage = storage;
    }

    public Response create(String id, Plane plane, Location departureLocation, Location scaleLocation,
                           Location arrivalLocation, LocalDateTime departureDate,
                           int hoursDurationArrival, int minutesDurationArrival,
                           int hoursDurationScale, int minutesDurationScale) {
        Flight flight;
        if (scaleLocation == null) {
            flight = new Flight(id, plane, departureLocation, arrivalLocation,
                    departureDate, hoursDurationArrival, minutesDurationArrival);
        } else {
            flight = new Flight(id, plane, departureLocation, scaleLocation, arrivalLocation,
                    departureDate, hoursDurationArrival, minutesDurationArrival,
                    hoursDurationScale, minutesDurationScale);
        }

        FlightValidator validator = new FlightValidator();
        ValidationResult validation = validator.validate(flight);
        if (!validation.isValid()) {
            return new Response(validation.getCombinedMessage(), Status.BAD_REQUEST);
        }

        boolean duplicateId = flights.stream().anyMatch(f -> f.getId().equals(id));
        if (duplicateId) {
            return new Response("El ID del vuelo ya está registrado", Status.BAD_REQUEST);
        }

        flights.add(flight);
        storage.saveFlights(flights);
        return new Response("Vuelo registrado correctamente", Status.CREATED, flight);
    }
}
