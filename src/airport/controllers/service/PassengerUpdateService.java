/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.service;

import airport.Models.Passenger;
import airport.Models.Storage.Storage;
import airport.controllers.utils.PassengerValidator;
import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.controllers.utils.ValidationResult;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author saraibanez
 */
public class PassengerUpdateService {
    private final ArrayList<Passenger> passengers;
    private final Storage storage;

    public PassengerUpdateService(ArrayList<Passenger> passengers, Storage storage) {
        this.passengers = passengers;
        this.storage = storage;
    }

    public Response update(long id, String name, String lastName, LocalDate birthDate,
                           int phoneCode, long phone, String country) {
        Passenger passenger = passengers.stream()
            .filter(p -> p.getId() == id)
            .findFirst()
            .orElse(null);

        if (passenger == null) {
            return new Response("Pasajero no encontrado.", Status.NOT_FOUND);
        }

        Passenger updatedPassenger = new Passenger(id, name, lastName, birthDate, phoneCode, phone, country);
        PassengerValidator validator = new PassengerValidator();
        ValidationResult validation = validator.validate(updatedPassenger);

        if (!validation.isValid()) {
            return new Response(validation.getCombinedMessage(), Status.BAD_REQUEST);
        }

        passenger.setFirstname(name);
        passenger.setLastname(lastName);
        passenger.setBirthDate(birthDate);
        passenger.setCountryPhoneCode(phoneCode);
        passenger.setPhone(phone);
        passenger.setCountry(country);

        storage.savePassengers(passengers);
        return new Response("Pasajero actualizado exitosamente.", Status.OK, passenger);
    }
}
