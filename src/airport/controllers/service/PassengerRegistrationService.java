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
import java.util.ArrayList;

/**
 *
 * @author saraibanez
 */
public class PassengerRegistrationService {
    private final ArrayList<Passenger> passengers;
    private final Storage storage;

    public PassengerRegistrationService(ArrayList<Passenger> passengers, Storage storage) {
        this.passengers = passengers;
        this.storage = storage;
    }

    public Response register(Passenger passenger) {
        PassengerValidator validator = new PassengerValidator(passengers);
        ValidationResult validation = validator.validate(passenger);

        if (!validation.isValid()) {
            return new Response(validation.getCombinedMessage(), Status.BAD_REQUEST);
        }

        passengers.add(passenger);
        storage.savePassengers(passengers);
        return new Response("Pasajero registrado correctamente", Status.CREATED, passenger);
    }
    
}
