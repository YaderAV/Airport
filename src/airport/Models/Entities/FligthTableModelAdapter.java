/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Entities;

import airport.Models.Entities.Flights.Flight;

/**
 *
 * @author yader
 */
public class FligthTableModelAdapter {
        public static Object[] toRow(Flight f){
        return new Object[]{
        f.getId(),
        f.getDepartureLocation().getAirportID(),
        f.getScaleLocation().getAirportID(),
        f.getArrivalLocation().getAirportID(),
        f.getSchedule().getDepartureDate(),
        f.getSchedule().calculateArrival(),
        f.getPlane().getId(),
        f.getPassengerList().getPassengers().size()
        };
    }
    public static String[]getColimHeader(){
        return new String[]{
            "ID","Departure Airport ID", "Arrival Airport ID", "Scale Airport ID", "Departure Date", "Arrival Date",
            "Plane ID", "Number Passengers"
        };
    }
}
