/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers;

import airport.Models.Observable.ObservableBase;
import airport.Models.Passenger;
import airport.Models.Storage.Storage;
import airport.controllers.utils.PassengerValidator;
import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.controllers.utils.ValidationResult;
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
           

            Passenger p = new Passenger(id, name, lastname, birthDate, phoneCode, phone, country);
            PassengerValidator validator = new PassengerValidator();
            ValidationResult validation = validator.validate(p);

            if (!validation.isValid()) {
                return new Response(validation.getCombinedMessage(), Status.BAD_REQUEST);
            }
            boolean idRepetido = false;
            for (Passenger pass : passengers){
                if(pass.getId()== p.getId()){
                    idRepetido = true;
                    break;
                }
            }
            if(idRepetido){
                return new Response ("El ID del pasajero ya está registrado",Status.BAD_REQUEST);
            }
            passengers.add(p);
            storage.savePassengers(passengers);
            return new Response("Pasajero registrado correctamente", Status.CREATED, p);
        } catch (Exception e) {
            return new Response("Error al registrar pasajero: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

    public Response updatePassenger(long id, String name, String lastName, LocalDate birthDate,
            int phoneCode, long phone, String country) {
        Passenger passenger = passengers.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        if (passenger == null) {
            return new Response("Pasajero no encontrado.", Status.NOT_FOUND);
        }

        try {
            passenger.setFirstname(name);
            passenger.setLastname(lastName);
            passenger.setBirthDate(birthDate);
            passenger.setCountryPhoneCode(phoneCode);
            passenger.setPhone(phone);
            passenger.setCountry(country);
            storage.savePassengers(passengers);
            return new Response("Pasajero actualizado exitosamente.", Status.OK, passenger);
        } catch (Exception e) {
            return new Response("Error al actualizar pasajero: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
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
