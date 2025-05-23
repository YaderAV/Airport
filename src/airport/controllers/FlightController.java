/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers;

import airport.Models.Flight;
import airport.Models.Location;
import airport.Models.Observable.FlightRepository;
import airport.Models.Passenger;
import airport.Models.Plane;
import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import java.time.LocalDateTime;
import java.util.ArrayList;
import airport.Models.Storage.Storage;

/**
 *
 * @author yader
 */
public class FlightController {
    private final ArrayList<Flight> flights; 
    private final Storage storage; 
    private final FlightRepository repository;

    public FlightController(ArrayList<Location>locations, ArrayList<Plane> planes,ArrayList<Passenger> passengers, Storage storage, FlightRepository repository) {
        this.storage = storage;
        this.flights = storage.loadFlights(locations, passengers, planes);
        this.repository = repository;
    }
    
    
    public Response createFlight(String id, Plane plane, Location departureLocation, Location scaleLocation, Location arrivalLocation, LocalDateTime departureDate, int hoursDurationArrival, int minutesDurationArrival, int hoursDurationScale, int minutesDurationScale){
            
            for (Flight f : flights) {
                if(f.getId().equals(id)){
                    return new Response("Ya existe un vuelo con este ID",Status.BAD_REQUEST);
                }
            }
           try {
            Flight f; 
               if (scaleLocation == null) {
                   f = new Flight(id, plane, departureLocation, arrivalLocation, departureDate,hoursDurationArrival, minutesDurationArrival);
               }else {
                   f = new Flight (id, plane, departureLocation, scaleLocation, arrivalLocation, departureDate, hoursDurationArrival,minutesDurationArrival, hoursDurationScale, minutesDurationScale);
               }
               flights.add(f);
               storage.saveFlights(flights);
               repository.addFlight(f);
               return new Response("El vuelo ha sido creado exitosamente", Status.CREATED);
               
        } catch (Exception e) {
            return new Response("Error al registrar vuelo: "+e.getMessage(),Status.INTERNAL_SERVER_ERROR);
        }
 
        
    }
    public Response addPassenger(Passenger passenger, String flightID){
        for (Flight f: flights){
            if(f.getId().equals(flightID)){
                f.addPassenger(passenger);
                passenger.addFlight(f);
                storage.saveFlights(flights);
                return new Response("Pasajero añadido al vuelo", Status.OK,f);
            }
        }
        return new Response("Vuelo no encontrado", Status.NOT_FOUND);
    }
    public Response delayFlight(String id, int hourDelay, int minuteDelay){
        for (Flight f: flights) {
            if (f.getId().equals(id)) {
                LocalDateTime newDepartureDate= f.getDepartureDate().plusHours(hourDelay).plusMinutes(minuteDelay);
                f.setDepartureDate(newDepartureDate);
                return new Response("Vuelo retrasado exitosamente",Status.OK,f);
            }
        }
        return new Response("Vuelo no encontrado", Status.NOT_FOUND);
    }
    
   
}
