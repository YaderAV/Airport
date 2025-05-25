/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.utils.validators;

import airport.Models.Entities.Flights.Flight;
import airport.controllers.utils.Validator;
import java.time.LocalDateTime;

/**
 *
 * @author yader
 */
public class FlightValidator implements Validator<Flight> {

    @Override
    public ValidationResult validate(Flight f) {
        ValidationResult result = new ValidationResult();
        if (f.getId() == null || !f.getId().matches("[A-Z]{3}\\d{3}")) {
            result.addError("El ID del formato debe ser XXXYYY. X letra Mayúscula y Y dígito");
        }
        if (f.getPlane() == null) {
            result.addError("Avión vacío");
        }
        if (f.getDepartureLocation() == null) {
            result.addError("Origen no especificada");
        }
        if (f.getArrivalLocation() == null) {
            result.addError("Destino no especificado");
        }
        if (f.getSchedule().getDepartureDate().isBefore(LocalDateTime.now()) 
                || f.getSchedule().getDepartureDate() == null) {
            result.addError("Fecha de salida inválida");
        }
        if (f.getSchedule().getHoursDurationArrival() < 0 && f.getSchedule().getHoursDurationArrival() < 0) {
            result.addError("Duración de vuelo debe ser mayor que 00:00");
        }
        return result;
    }

}
