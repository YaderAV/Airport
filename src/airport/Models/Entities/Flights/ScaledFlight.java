/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Entities.Flights;

import airport.Models.Entities.Location;
import airport.Models.Entities.Passenger;
import airport.Models.Entities.PassengerList;
import airport.Models.Entities.Plane;
import java.util.ArrayList;

/**
 *
 * @author yader
 */
public class ScaledFlight extends Flight {
       private Location scale;
    public ScaledFlight(String id, Plane plane, Location departureLocation, Location scale, Location arrivalLocation, FlightSchedule schedule) {
        super(id, plane, departureLocation, arrivalLocation, schedule);
        this.scale = scale;
    }

    public FlightSchedule getSchedule() {
        return schedule;
    }

    @Override
    public boolean hasScale() {
        return true; 
    }

    public Location getScale() {
        return scale;
    }

    public String getId() {
        return id;
    }

    public Plane getPlane() {
        return plane;
    }

    public Location getDepartureLocation() {
        return departureLocation;
    }

    public Location getArrivalLocation() {
        return arrivalLocation;
    }

    public PassengerList getPassengerList() {
        return passengerList;
    }

    public void setScale(Location scale) {
        this.scale = scale;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public void setDepartureLocation(Location departureLocation) {
        this.departureLocation = departureLocation;
    }

    public void setArrivalLocation(Location arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public void setSchedule(FlightSchedule schedule) {
        this.schedule = schedule;
    }

    public void setPassengerList(PassengerList passengerList) {
        this.passengerList = passengerList;
    }
    
       
}
