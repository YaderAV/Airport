/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Entities.Flights;

import airport.Models.Entities.Location;
import airport.Models.Entities.PassengerList;
import airport.Models.Entities.Plane;

/**
 *
 * @author yader
 */


public class DirectFlight extends Flight {

    public DirectFlight(String id, Plane plane, Location departureLocation, Location arrivalLocation, FlightSchedule schedule) {
        super(id, plane, departureLocation, arrivalLocation, null, schedule);
    }

    @Override
    public boolean hasScale() {
        return false;
    }
}

