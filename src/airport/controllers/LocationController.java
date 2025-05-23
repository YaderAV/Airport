/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers;

import airport.Models.Location;
import airport.Models.Observable.ObservableBase;
import airport.Models.Storage.Storage;
import airport.controllers.utils.LocationValidator;
import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.controllers.utils.ValidationResult;
import java.util.ArrayList;

/**
 *
 * @author yader
 */
public class LocationController extends ObservableBase {
   private final ArrayList<Location> locations; 
   private final Storage storage; 

    public LocationController(ArrayList locations, Storage storage) {
        this.locations = locations;
        this.storage = storage;
    }
   
   public Response createLocation(String airportId, String airportName, String airportCity, String airportCountry, double airportLatitude, double airportLongitude){
       LocationValidator locationValidator = new LocationValidator();
       Location location = new Location(airportId, airportName, airportCity, airportCountry, airportLatitude, airportLongitude);
       ValidationResult locValidation = locationValidator.validate(location);
       if(!locValidation.isValid()){
           return new Response (locValidation.getCombinedMessage(), Status.BAD_REQUEST);
       }
       boolean idRepetido = false;
            for (Location l : locations){
                if(l.getAirportId()== location.getAirportId()){
                    idRepetido = true;
                    break;
                }
            }
            if(idRepetido){
                return new Response ("El ID del aeropuerto ya está registrado",Status.BAD_REQUEST);
            }
            locations.add(location);
            storage.saveLocations(locations);
            notifyObservers();
            return new Response ("Localizacion registrada correctamente", Status.CREATED);
   }
}
