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

    public Response registerPassenger(Long id, String name, String lastname, LocalDate birthDate, int phoneCode, long phone, String country) {
        try {
            Passenger passenger = new Passenger(id, name, lastname, birthDate, phoneCode, phone, country);
            PassengerRegistrationService service = new PassengerRegistrationService(passengers, storage);
            if(service.register(passenger).getStatus() == Status.CREATED){
                return service.register(passenger);
            }else{
                return new Response(service.register(passenger).getMessage(),Status.BAD_REQUEST);
            }
            

        } catch (Exception e) {
            return new Response("Error al registrar pasajero: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
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

        return new Response("Lista de pasajeros obtenida.", Status.OK, sortedList);
    }

}
