/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Entities;

/**
 *
 * @author yader
 */
public class PassengerTableModelAdapter {
    public static Object[] toRow(Passenger p){
        return new Object[]{
        p.getId(),
        p.getFullname(),
        p.getBirthDate(),
        p.getAge(),
        p.getFullPhone(),
        p.getCountry(),
        p.getFlights().size(),
        };
    }
    public static String[]getColimHeader(){
        return new String[]{
            "ID","Nombre Completo", "Fecha de nacimiento", "Edad", "Telefono", "País",
            "# Vuelos"
        };
    }

}
