/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.utils.validators;

/**
 *
 * @author yader
 * @param <T>
 */
public interface Validator<T> {
    ValidationResult validate (T entitie);
}
