/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.utils.validators;

import airport.Models.Entities.Passenger;
import java.time.LocalDate;
import java.util.Map;

/**
 *
 * @author yader
 */
public class PassengerValidator implements Validator<Passenger> {
    private final Map<Long, Passenger> existingPassengers;

    public PassengerValidator(Map<Long, Passenger> existingPassengers) {
        this.existingPassengers = existingPassengers;
    }
    

    @Override
    public ValidationResult validate(Passenger p) {
   
        ValidationResult result = new ValidationResult();
        if (p.getId() <= 0 || String.valueOf(p.getId()).length() > 15)
            result.addError("ID inválido, debe tener al más 15 dígitos y ser mayor que 0. ");
        if(existingPassengers.containsKey(p.getId())) result.addError("Ya existe un pasajero con este ID");
        if (p.getFirstname() == null || p.getFirstname().isBlank()) result.addError("Nombre vacío.");
        if (p.getLastname() == null || p.getLastname().isBlank()) result.addError("Apellido vacío.");
        if (p.getBirthDate() == null || p.getBirthDate().isAfter(LocalDate.now())) result.addError("Fecha de nacimiento inválida.");
        if (p.getPhone() <= 0 || String.valueOf(p.getPhone()).length() > 11) result.addError("Teléfono inválido. Máximo 11 dígitos.");
        if (p.getCountryPhoneCode() <= 0 || String.valueOf(p.getCountryPhoneCode()).length() > 3) result.addError("Código telefónico inválido. Máximo 3 dígitos y mayor que 0.");
        if (p.getCountry() == null || p.getCountry().isBlank()) result.addError("País vacío.");

        return result;
    }
    
}
