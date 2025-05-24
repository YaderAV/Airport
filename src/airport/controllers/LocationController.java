/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers;

import airport.controllers.service.LocationCreationService;
import airport.Models.Location;
import airport.Models.Observable.ObservableBase;
import airport.Models.Storage.Storage;
import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import java.util.ArrayList;

/**
 *
 * @author yader
 */
public class LocationController extends ObservableBase {
  private final ArrayList<Location> locations;
  private final Storage storage;

    public LocationController(ArrayList<Location> locations, Storage storage) {
        this.locations = locations;
        this.storage = storage;
    }

    public Response createLocation(String airportId, String airportName, String airportCity, String airportCountry, double airportLatitude, double airportLongitude) {
        try {
            Location location = new Location(airportId, airportName, airportCity, airportCountry, airportLatitude, airportLongitude);
            LocationCreationService service = new LocationCreationService(locations, storage);
            Response response = service.create(location);

            if (response.getStatus() == Status.CREATED) {
                notifyObservers(); 
            }

            return response;
        } catch (Exception e) {
            return new Response("Error al registrar localización: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}
