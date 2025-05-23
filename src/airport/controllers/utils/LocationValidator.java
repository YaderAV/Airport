/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.utils;


import airport.Models.Location;

/**
 *
 * @author yader
 */
public class LocationValidator implements Validator<Location> {

    @Override
    public ValidationResult validate(Location l) {
        ValidationResult result = new ValidationResult();
        if(l.getAirportId() == null || !l.getAirportId().matches("[A-Z]d{3}")) result.addError("El ID del aeropuerto debe tener 3 letras mayúsculas");
        if(l.getAirportName() == null || l.getAirportId().isBlank()) result.addError("Nombre del aeropuerto vacío");
        if(l.getAirportCountry() == null || l.getAirportCountry().isBlank()) result.addError("País vacío");
        if(l.getAirportCity() == null|| l.getAirportCity().isBlank()) result.addError("Ciudad vacía");
        if(l.getAirportLatitude()<-90 || l.getAirportLatitude()>90) result.addError("Latitud fuera de ranto (-90,90)");
        if(l.getAirportLongitude()<-180 || l.getAirportLongitude()>90) result.addError("Longitud fuera de rango (-180,180)");
        if(String.valueOf(l.getAirportLatitude()).split("\\.")[1].length()>4) result.addError("Latitud con más de 4 cifras decimales");
        if(String.valueOf(l.getAirportLongitude()).split("\\.")[1].length()>4) result.addError("Longitud con mas de 4 cifras decimales");
        
        return result;
    }
    
}
