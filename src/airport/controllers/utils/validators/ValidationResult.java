/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.utils.validators;

import java.util.ArrayList;

/**
 *
 * @author yader
 */
public class ValidationResult {
    private final ArrayList<String> errors = new ArrayList();
    public void addError(String message){
    errors.add(message);
    }
    public boolean isValid(){
    return errors.isEmpty();
    }
    public ArrayList<String> getErrors(){
        return errors;
    }
    public String getCombinedMessage(){
        String combinedMessage = "\n"; 
        for (String error : errors){
            combinedMessage = combinedMessage + error; 
        }
        return combinedMessage;
    }
    
    
}
