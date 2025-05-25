/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.service;

import airport.Models.Entities.Flights.DirectFlight;
import airport.Models.Entities.Flights.Flight;
import airport.Models.Entities.Flights.FlightSchedule;
import airport.Models.Entities.Flights.ScaledFlight;
import airport.Models.Entities.Location;
import airport.Models.Entities.Plane;
import airport.Models.Storage.JSONStorage;
import airport.controllers.utils.validators.FlightValidator;
import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.controllers.utils.validators.ValidationResult;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author saraibanez
 */
public class FlightCreationService {
    private final List<Flight> flights;
    private final JSONStorage storage;

    public FlightCreationService(List<Flight> flights, JSONStorage storage) {
        this.flights = flights;
        this.storage = storage;
    }

    public Response create(String id, Plane plane, Location departureLocation, Location scaleLocation,
                           Location arrivalLocation, FlightSchedule flightSchedule) throws IOException {
        Flight flight;
        if (scaleLocation == null) {
            flight = new DirectFlight(id, plane, departureLocation, arrivalLocation,
            flightSchedule);
        } else {
            flight = new ScaledFlight(id, plane, departureLocation, scaleLocation, arrivalLocation,
                    flightSchedule );
        }

        FlightValidator validator = new FlightValidator();
        ValidationResult validation = validator.validate(flight);
        if (!validation.isValid()) {
            return new Response(validation.getCombinedMessage(), Status.BAD_REQUEST);
        }


        flights.add(flight);
        storage.getFlightLoader().saveFlights(flights);
        return new Response("Vuelo registrado correctamente", Status.CREATED, flight);
    }
}
