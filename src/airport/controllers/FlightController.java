/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers;
import airport.Models.Entities.Flights.Flight;
import airport.Models.Entities.Location;
import airport.Models.Entities.Passenger;
import airport.Models.Entities.Plane;
import airport.controllers.service.FlightDelayService;
import airport.controllers.service.FlightCreationService;
import airport.controllers.service.FlightAssignmentService;
import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.Models.Storage.JSONStorage;
import airport.controllers.utils.parser.FlightDataParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yader
 */

public class FlightController {
    private final List<Flight> flights;
    private final JSONStorage storage;

    public FlightController(Map<String, Location> locations, Map<String, Plane> planes, Map<Long, Passenger> passengers, JSONStorage storage) throws IOException {
        this.storage = storage;
        this.flights = storage.loadAll().flights;
        System.out.println("DEBUG - flights: " + flights);
    }

    public Response createFlight(String id,
            String planeId,
            String departureId,
            String arrivalId,
            String scaleId,
            String yearStr,
            String monthStr,
            String dayStr,
            String hourStr,
            String minuteStr,
            String hoursArrivalStr,
            String minutesArrivalStr,
            String hoursScaleStr,
            String minutesScaleStr,
            Map<String, Plane> planes,
            Map<String, Location> locations) throws IOException {
        Flight flight = FlightDataParser.Parse(id, planeId, departureId, arrivalId, scaleId, yearStr, monthStr, dayStr, hourStr, minuteStr, hoursArrivalStr, minutesArrivalStr, hoursScaleStr, minutesScaleStr, planes, locations);
        FlightCreationService service = new FlightCreationService(flights, storage);
        Response response = service.create(flight);
        if (response.getStatus() == Status.CREATED) {
            sortFlights();
        }

        return response;
    }
public Response addPassengerToFlight(Passenger passenger, String flightID) throws IOException {
    System.out.println("=== [DEBUG] Entrando a addPassengerToFlight ===");
    System.out.println("Passenger recibido: " + passenger);
    System.out.println("flightID recibido: " + flightID);

    try {
        if (passenger == null) {
            System.out.println("[DEBUG] El pasajero es nulo. Abortando operación.");
            return new Response( "El pasajero no puede ser nulo.",Status.BAD_REQUEST);
        }

        if (flightID == null || flightID.isEmpty()) {
            System.out.println("[DEBUG] El ID de vuelo es inválido o nulo. Abortando operación.");
            return new Response("El ID de vuelo no puede ser nulo o vacío.",Status.BAD_REQUEST);
        }

        FlightAssignmentService service = new FlightAssignmentService(flights, storage);
        Response response = service.assignPassenger(passenger, flightID);

        System.out.println("[DEBUG] Respuesta del servicio: " + response);
        return response;

    } catch (IOException e) {
        System.out.println("[ERROR] Excepción de IO: " + e.getMessage());
        e.printStackTrace();
        throw e; // Rethrow para quien lo maneje arriba
    } catch (Exception e) {
        System.out.println("[ERROR] Excepción general: " + e.getMessage());
        e.printStackTrace();
        return new Response( "Error inesperado: " + e.getMessage(),Status.BAD_REQUEST);
    }
}


    public Response delayFlight(String id, int hourDelay, int minuteDelay) throws IOException {
        FlightDelayService service = new FlightDelayService(flights, storage);
        return service.delay(id, hourDelay, minuteDelay);
    }

    public Response getAllFlights() {
        sortFlights();
        return new Response("Lista de vuelos obtenida.", Status.OK, flights);
    }

   
    private void sortFlights() {
        flights.sort(Comparator.comparing(f -> f.getSchedule().getDepartureDate()));
    }
    
    public List<Flight> getFlightsByPassengerId(long passengerId) {

    System.out.println("DEBUG - Iniciando búsqueda de vuelos para el pasajero con ID: " + passengerId);

    List<Flight> passengerFlights = new ArrayList<>();

    for (Flight flight : flights) {
        System.out.println("DEBUG - Revisando vuelo: " + flight.getId());

        List<Passenger> passengers = flight.getPassengerList().getPassengers();
        System.out.println("DEBUG - Pasajeros en el vuelo " + flight.getId() + ":");
        for (Passenger p : passengers) {
            System.out.println("    -> " + p.getId() + " - " + p.getFullname());

            if (p.getId() == passengerId) {
                System.out.println(">>> MATCH ENCONTRADO: Pasajero " + p.getId() + " está en el vuelo " + flight.getId());
                passengerFlights.add(flight);
                break;
            }
        }
    }

    System.out.println("DEBUG - Total de vuelos encontrados para pasajero " + passengerId + ": " + passengerFlights.size());
    for (Flight f : passengerFlights) {
        System.out.println("    Vuelo encontrado: " + f.getId() + " - " + f.getSchedule().getDepartureDate());
    }

    passengerFlights.sort(Comparator.comparing(f -> f.getSchedule().getDepartureDate()));

    return passengerFlights;
}

    public List<Flight> getFlights() {
        return flights;
    }

    public JSONStorage getStorage() {
        return storage;
    }

}
