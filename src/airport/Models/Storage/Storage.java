/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Storage;

import java.util.ArrayList;

/**
 *
 * @author yader
 */
public class Storage {
    // Instancia Singleton

    private static Storage instance;

    // Atributos del Storage
    String ruta; 
    

    private Storage() {
        
    }

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

   
}
