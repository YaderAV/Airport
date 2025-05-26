/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Entities;

/**
 *
 * @author yader
 */
public class LocationTableModelAdapter {
    public static Object[] toRow(Location l){
        return new Object[]{
        l.getAirportID(),
        l.getAirportName(),
        l.getAirportCity(),
        l.getAirportCountry()
        };
    }
    public static String[]getColimHeader(){
        return new String[]{
            "Airport ID","Airport Name", "City", "Country"
        };
    }
}
