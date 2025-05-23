/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.utils;

import airport.Models.Passenger;
import java.time.LocalDate;

/**
 *
 * @author yader
 */
public class PassengerValidator implements Validator<Passenger> {

    @Override
    public ValidationResult validate(Passenger p) {
        ValidationResult result = new ValidationResult();
        if (p.getId()<=0) result.addError("ID inválido. ");
        if(p.getFirstname() == null || p.getFirstname().isBlank()) result.addError("Nombre vacío. ");
        if(p.getLastname() == null || p.getLastname().isBlank()) result.addError("Apellido vacío");
        if(p.getBirthDate() == null || p.getBirthDate().isAfter(LocalDate.now())) result.addError("fecha de nacimiento invalida");
        if(p.getPhone() <=0) result.addError("Teléfone inválida. ");
        if(p.getCountryPhoneCode()<=0) result.addError("Código de país inválido. ");

        return result; 
    }
    
}
