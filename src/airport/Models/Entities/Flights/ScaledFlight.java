/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Entities.Flights;

import airport.Models.Entities.Location;
import airport.Models.Entities.Plane;

/**
 *
 * @author yader
 */


public class ScaledFlight extends Flight {

    public ScaledFlight(String id, Plane plane, Location departureLocation, Location arrivalLocation, Location scaleLocation, FlightSchedule schedule) {
        super(id, plane, departureLocation, arrivalLocation, scaleLocation, schedule);
    }

    @Override
    public boolean hasScale() {
        return true;
    }
     public Location getScale() {
        return scaleLocation;
    }

  
}
