/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.utils.validators;

/**
 *
 * @author yader
 */
public class FieldValidator {
    public static boolean isTextValid(String text){
        return text != null && !text.trim().isEmpty();
    }
    public static boolean isNumeric(String text){
    return text != null && text.matches("\\d+");
    }
    public static boolean isAlphabetic(String text){
    return text != null && text.matches("a-zA-ZáéíóúÁÉÍÓÚñÑ\\s+");
    }
    public static boolean isDecimal(String text){
    return text != null && text.matches("\\d+(\\.\\d+)?");
    }
    public static boolean isLong(String text){
        try {
            Long.parseLong(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean isInteger(String text){
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
