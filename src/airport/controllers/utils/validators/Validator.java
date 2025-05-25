/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.utils.validators;

import airport.controllers.utils.validators.ValidationResult;

/**
 *
 * @author yader
 */
public interface Validator<T> {
    ValidationResult validate (T entitie);
}
