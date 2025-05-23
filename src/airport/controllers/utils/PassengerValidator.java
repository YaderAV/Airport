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
        if (p.getId()<=0 || String.valueOf(p.getId()).length()>15) result.addError("ID inválido, debe tener al mas 15 digitos y ser mayor que 0. ");
        if(p.getFirstname() == null || p.getFirstname().isBlank()) result.addError("Nombre vacío. ");
        if(p.getLastname() == null || p.getLastname().isBlank()) result.addError("Apellido vacío");
        if(p.getBirthDate() == null || p.getBirthDate().isAfter(LocalDate.now())) result.addError("fecha de nacimiento invalida");
        if(p.getPhone() <=0 || String.valueOf(p.getPhone()).length()>11) result.addError("Teléfono inválid0. Máximo 11 dígitos ");
        if(p.getCountryPhoneCode()<= 0 || String.valueOf(p.getCountryPhoneCode()).length()>3) result.addError("Código telefónico inválido. Máximo 3 digitos ");
        if(p.getCountry() == null || p.getCountry().isBlank()) result.addError("País vacío");
        return result; 
    }
    
}
