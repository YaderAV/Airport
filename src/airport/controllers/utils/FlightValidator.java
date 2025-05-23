/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.utils;

import airport.Models.Flight;
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
        if (f.getDepartureDate().isBefore(LocalDateTime.now()) || f.getDepartureDate() == null) {
            result.addError("Fecha de salida inválida");
        }
        if (f.getHoursDurationArrival() < 0 && f.getMinutesDurationArrival() < 0) {
            result.addError("Duración de vuelo debe ser mayor que 00:00");
        }
        if (f.getScaleLocation() == null) {
            if (f.getHoursDurationArrival() > 0 || f.getMinutesDurationScale() > 0) {
                result.addError("No debe haber tiempo de escala sino hay escala");
            } else {
                if (f.getHoursDurationScale() < 0 && f.getMinutesDurationArrival() < 0) {
                    result.addError("Duración de escala inválida");
                }
            }
        }
        return result;
    }

}
