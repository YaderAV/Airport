/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers;


import airport.Models.Observable.ObservableBase;
import airport.controllers.service.PassengerRegistrationService;
import airport.controllers.service.PassengerUpdateService;
import airport.Models.Passenger;
import airport.Models.Storage.Storage;
import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author yader
 */
public class PassengerController extends ObservableBase{

    private final ArrayList<Passenger> passengers;
    private final Storage storage;

    public PassengerController(ArrayList<Passenger> passengers, Storage storage) {
        this.passengers = passengers;
        this.storage = storage;
    }

    public Response registerPassenger(Long id, String name, String lastname, LocalDate birthDate, int phoneCode, long phone, String country) {
        try {
            Passenger passenger = new Passenger(id, name, lastname, birthDate, phoneCode, phone, country);
            PassengerRegistrationService service = new PassengerRegistrationService(passengers, storage);
            return service.register(passenger);
            
        } catch (Exception e) {
            return new Response("Error al registrar pasajero: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

    public Response updatePassenger(long id, String name, String lastName, LocalDate birthDate, int phoneCode, long phone, String country) {
        Passenger passenger = passengers.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        if (passenger == null) {
            return new Response("Pasajero no encontrado.", Status.NOT_FOUND);
        }
        PassengerUpdateService service = new PassengerUpdateService(passengers, storage);
        return service.update(id, name, lastName, birthDate, phoneCode, phone, country);
    }

    public Response getPassengerById(long id) {
        Passenger p = passengers.stream().filter(pa -> pa.getId() == id).findFirst().orElse(null);
        if (p == null) {
            return new Response("Pasajero no encontrado", Status.NOT_FOUND);
        }
        return new Response("Pasajero encontrado", Status.OK, p);
    }

    public Response getAllPassengers() {
        ArrayList<Passenger> sorted = (ArrayList<Passenger>) passengers.stream()
                .sorted(Comparator.comparingLong(Passenger::getId))
                .toList();
        return new Response("Lista de pasajeros obtenida.", Status.OK, sorted);
    }

}
