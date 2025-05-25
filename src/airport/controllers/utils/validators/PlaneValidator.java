/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.utils.validators;

import airport.Models.Entities.Plane;

/**
 *
 * @author yader
 */
public class PlaneValidator implements Validator<Plane>{

    @Override
    public ValidationResult validate(Plane plane) {
        ValidationResult result = new ValidationResult();
        
        if(plane.getId()== null || !plane.getId().matches("[A-Z]{2}\\d{5}")) 
            result.addError("formato ID del avion debe ser XXYYYYY. X letra mayúscula y Y dígitos");
        if(plane.getBrand()== null || plane.getBrand().isBlank()) result.addError("Marca vacía");
        if(plane.getModel()== null || plane.getModel().isBlank()) result.addError("Modelo vacío");
        if (plane.getMaxCapacity()<=0) result.addError("capacidad máxima inválida");
        if(plane.getAirline()== null || plane.getAirline().isBlank()) result.addError("Aerolínea vacía");
        return result;
    }
    
}
