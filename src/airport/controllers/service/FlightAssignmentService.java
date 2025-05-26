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
import airport.controllers.utils.StructureDataHandler;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
    System.out.println("\n==== DEBUG: Iniciando asignación de pasajero ====");
    System.out.println("DEBUG: Buscando vuelo con ID: " + flightID);
    System.out.println("DEBUG: Lista de vuelos disponibles:");

    // Imprimir todos los IDs de vuelos en la lista
    for (Flight f : flights) {
        System.out.println("DEBUG: Vuelo encontrado - ID: " + f.getId());
    }

    // Buscar el vuelo
 
    Flight flight = flights.stream()
        .filter(f -> f.getId().equals(flightID))
        .findFirst()
        .orElse(null);

    if (flight == null) {
        System.out.println("DEBUG: Vuelo NO encontrado con ID: " + flightID);
        return new Response("Vuelo no encontrado con ID: " + flightID, Status.NOT_FOUND);
    }

    System.out.println("DEBUG: Vuelo ENCONTRADO: " + flight.getId());
    System.out.println("DEBUG: Pasajero a agregar: " + passenger.getId() + " - " + passenger.getFullname());

    // Agregar pasajero al vuelo
    flight.getPassengerList().addPassenger(passenger);
    passenger.getFlights().add(flight);

    System.out.println("DEBUG: Pasajero agregado al vuelo correctamente.");
    System.out.println("DEBUG: Guardando vuelos en el almacenamiento...");

    storage.getFlightLoader().saveFlights(flights);
    Map<Long,Passenger> allPassengers = storage.getPassengerLoader().loadPassengers();
    allPassengers.put(passenger.getId(), passenger);
    
    storage.getPassengerLoader().savePassengers(allPassengers);
    
    System.out.println("==== DEBUG: Asignación finalizada ====\n");

    return new Response("Pasajero añadido al vuelo", Status.OK, flight.clone());
}

}