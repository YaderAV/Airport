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


    public abstract class Flight {
        protected String id; 
        protected Plane plane; 
        protected Location departureLocation;
        protected Location arrivalLocation;
        protected Location scaleLocation;  // Añadimos la escala (puede ser null)
        protected FlightSchedule schedule;
        protected PassengerList passengerList;

        public Flight(String id, Plane plane, Location departureLocation, Location arrivalLocation, Location scaleLocation, FlightSchedule schedule) {
            this.id = id;
            this.plane = plane;
            this.departureLocation = departureLocation;
            this.arrivalLocation = arrivalLocation;
            this.scaleLocation = scaleLocation;
            this.schedule = schedule;
            this.passengerList = new PassengerList();
            plane.addFlight(this);
        }

        public abstract boolean hasScale();

        // Getters
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

        public Location getScaleLocation() {
            return scaleLocation;
        }

        public FlightSchedule getSchedule() {
            return schedule;
        }

        public PassengerList getPassengerList() {
            return passengerList;
        }

        // Setters
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

        public void setScaleLocation(Location scaleLocation) {
            this.scaleLocation = scaleLocation;
        }

        public void setSchedule(FlightSchedule schedule) {
            this.schedule = schedule;
        }

        public void setPassengerList(PassengerList passengerList) {
            this.passengerList = passengerList;
        }

                public int getHoursDurationArrival() {
            return schedule.getHoursDurationArrival();
        }

        public int getMinutesDurationArrival() {
            return schedule.getMinutesDurationArrival();
        }

        // Duración de escala (delegamos a FlightSchedule)
        public int getHoursDurationScale() {
            return schedule.getHoursDurationScale();
        }

        public int getMinutesDurationScale() {
            return schedule.getMinutesDurationScale();
        }

    }

