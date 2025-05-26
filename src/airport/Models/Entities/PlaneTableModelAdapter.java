/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Entities;

/**
 *
 * @author yader
 */
public class PlaneTableModelAdapter {
        public static Object[] toRow(Plane p){
        return new Object[]{
        p.getId(),
        p.getBrand(),
        p.getModel(),
        p.getMaxCapacity(),
        p.getAirline(),
        p.getFlightsPlane().size(),
        };
    }
    public static String[]getColimHeader(){
        return new String[]{
            "ID","Brand", "Model", "Max Capacity", "Airline", "Number Flights"
        };
    }
}
