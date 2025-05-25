/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers;

import airport.Models.Entities.Passenger;
import airport.Models.Observable.ObservableBase;
import airport.controllers.service.PassengerRegistrationService;
import airport.controllers.service.PassengerUpdateService;
import airport.Models.Storage.JSONStorage;
import airport.controllers.utils.PassengerDataParser;
import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

/**
 *
 * @author yader
 */
public class PassengerController extends ObservableBase {

    private final Map<Long, Passenger> passengers;
    private final JSONStorage storage;

    public PassengerController(Map<Long, Passenger> passengers, JSONStorage storage) {
        this.passengers = passengers;
        this.storage = storage;
    }

    public Response registerPassenger( String idText, String name,String lastname,String yearText, String monthText,String dayText, String phoneCodeText,
    String phoneText, String country) 
        {
    try {
        
        Passenger passenger = PassengerDataParser.parse(
            idText, name, lastname, yearText, monthText, dayText, phoneCodeText, phoneText, country
        );
        PassengerRegistrationService service = new PassengerRegistrationService(passengers, storage);
        return service.register(passenger);

    } catch (Exception e) {
        return new Response("Error al procesar datos de pasajero: " + e.getMessage(), Status.BAD_REQUEST);
    }
        }
   

    public Response updatePassenger(long id, String name, String lastName, LocalDate birthDate, int phoneCode, long phone, String country) throws IOException {
        Passenger passenger = passengers.get(id);
        if (passenger == null) {
            return new Response("Pasajero no encontrado.", Status.NOT_FOUND);
        }
        PassengerUpdateService service = new PassengerUpdateService(passengers, storage);
        return service.update(id, name, lastName, birthDate, phoneCode, phone, country);
    }

    public Response getPassengerById(long id) {
        Passenger p = passengers.get(id);
        if (p == null) {
            return new Response("Pasajero no encontrado", Status.NOT_FOUND);
        }
        return new Response("Pasajero encontrado", Status.OK, p);
    }

public Response getAllPassengers() {
    var sortedList = passengers.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .map(Map.Entry::getValue)
            .toList(); 

    System.out.println("DEBUG - PassengerController.getAllPassengers() -> sortedList: " + sortedList);
    return new Response("Lista de pasajeros obtenida.", Status.OK, sortedList);
}


}
