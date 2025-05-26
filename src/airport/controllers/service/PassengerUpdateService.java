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
import java.time.LocalDate;
import java.util.Map;

/**
 *
 * @author saraibanez
 */
public class PassengerUpdateService {
    private final Map<Long, Passenger> passengers;
    private final JSONStorage storage;

    public PassengerUpdateService(Map<Long,Passenger> passengers, JSONStorage storage) {
        this.passengers = passengers;
        this.storage = storage;
    }

   public Response update(long id, String name, String lastName, LocalDate birthDate,
                       int phoneCode, long phone, String country) throws IOException {
    Passenger passenger = passengers.get(id);
    
    if (passenger == null) {
        return new Response("Pasajero no encontrado.", Status.NOT_FOUND);
    }

    Passenger updatedPassenger = new Passenger(id, name, lastName, birthDate, phoneCode, phone, country);
      System.out.println("=== DEBUG: Datos recibidos en update() ===");
    System.out.println("ID: " + id);
    System.out.println("Nombre: " + name);
    System.out.println("Apellido: " + lastName);
    System.out.println("Fecha de nacimiento: " + birthDate);
    System.out.println("Código de país: " + phoneCode);
    System.out.println("Teléfono: " + phone);
    System.out.println("País: " + country);
    System.out.println("=========================================");
    
    PassengerValidator validator = new PassengerValidator(passengers,true);
    ValidationResult validation = validator.validate(updatedPassenger);

    if (!validation.isValid()) {
        System.out.println("DEBUG: La validación falló con errores: " + validation.getCombinedMessage());
        return new Response(validation.getCombinedMessage(), Status.BAD_REQUEST);
    }
      System.out.println("hola llegue hasta aca");

    passenger.setFirstname(name);
    passenger.setLastname(lastName);
    passenger.setBirthDate(birthDate);
    passenger.setCountryPhoneCode(phoneCode);
    passenger.setPhone(phone);
    passenger.setCountry(country);
System.out.println("DEBUG: storage = " + storage);
System.out.println("DEBUG: storage.getPassengerLoader() = " + storage.getPassengerLoader());

    storage.getPassengerLoader().savePassengers(passengers);

    return new Response("Pasajero actualizado exitosamente.", Status.OK, passenger);
}

}
