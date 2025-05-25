/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.service;

import airport.Models.Entities.Passenger;
import airport.Models.Storage.JSONStorage;
import airport.controllers.utils.validators.PassengerValidator;
import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.controllers.utils.validators.ValidationResult;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author saraibanez
 */
public class PassengerRegistrationService {
    private final Map<Long, Passenger> passengers;
    private final JSONStorage storage;

    public PassengerRegistrationService(Map<Long,Passenger> passengers, JSONStorage storage) {
        this.passengers = passengers;
        this.storage = storage;
    }

    public Response register(Passenger passenger) throws IOException {
        PassengerValidator validator = new PassengerValidator(passengers);
        ValidationResult validation = validator.validate(passenger);

        if (!validation.isValid()) {
            return new Response(validation.getCombinedMessage()+"\n", Status.BAD_REQUEST);
        }
        
        passengers.put(passenger.getId(), passenger);
        storage. getPassengerLoader().savePassengers(passengers);
        return new Response("Pasajero registrado correctamente", Status.CREATED, passenger);
    }
    
}
