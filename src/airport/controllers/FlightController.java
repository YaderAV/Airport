/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers;

import airport.Models.Flight;
import airport.Models.Location;
import airport.Models.Observable.FlightRepository;
import airport.Models.Observable.ObservableBase;
import airport.Models.Passenger;
import airport.Models.Plane;
import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import java.time.LocalDateTime;
import java.util.ArrayList;
import airport.Models.Storage.Storage;
import airport.controllers.utils.FlightValidator;
import airport.controllers.utils.ValidationResult;

/**
 *
 * @author yader
 */
public class FlightController extends ObservableBase{
    private final ArrayList<Flight> flights; 
    private final Storage storage; 

    public FlightController(ArrayList<Location>locations, ArrayList<Plane> planes,ArrayList<Passenger> passengers, Storage storage) {
        this.storage = storage;
        this.flights = storage.loadFlights(locations, passengers, planes);
    }
    
    
    public Response createFlight(String id, 
            Plane plane, 
            Location departureLocation, 
            Location scaleLocation, 
            Location arrivalLocation, 
            LocalDateTime departureDate, 
            int hoursDurationArrival, 
            int minutesDurationArrival, 
            int hoursDurationScale, 
            int minutesDurationScale){
        Flight flight; 
        FlightValidator flightValidator = new FlightValidator();
        if(scaleLocation == null){
        flight = new Flight (id, plane, departureLocation,arrivalLocation,
                departureDate,hoursDurationArrival, minutesDurationArrival);
        }else{
        flight = new Flight (id,plane,departureLocation, scaleLocation,
                arrivalLocation,departureDate, hoursDurationArrival, 
                minutesDurationArrival, hoursDurationScale, minutesDurationScale);
        }
        ValidationResult flightValidation = flightValidator.validate(flight);
        if(!flightValidation.isValid()){
            return new Response(flightValidation.getCombinedMessage(),Status.BAD_REQUEST);
        }
            for (Flight f : flights){
                if(f.getId()== flight.getId()){
                    return new Response("El ID del vuelo ya está registrado", Status.BAD_REQUEST);
                }
            }
            
            flights.add(flight);
            storage.saveFlights(flights);
            notifyObservers();
            return new Response("Vuelo registrado correctamente", Status.CREATED);
            
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
