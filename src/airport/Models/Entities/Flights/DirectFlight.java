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
public class DirectFlight extends Flight{

    public DirectFlight(String id, Plane plane, Location departureLocation, Location arrivalLocation, FlightSchedule schedule) {
        super(id, plane, departureLocation, arrivalLocation, schedule);
    }
    
    @Override
    public boolean hasScale() {
        return false;
    }

    public FlightSchedule getSchedule() {
        return schedule;
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
