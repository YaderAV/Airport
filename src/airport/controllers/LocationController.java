/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers;

import airport.Models.Entities.Location;
import airport.controllers.service.LocationCreationService;
import airport.Models.Storage.JSONStorage;
import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.controllers.utils.parser.LocationsDataParser;
import java.util.Map;

/**
 *
 * @author yader
 */
public class LocationController {
  private final Map<String, Location> locations;
  private final JSONStorage storage;

    public LocationController(Map<String,Location> locations, JSONStorage storage) {
        this.locations = locations;
        this.storage = storage;
    }

    public Response createLocation(
            String IDAirportText,
            String nameAirportText,
            String cityAirportText,
            String countryAirportText,
            String latitudeAirportText,
            String longitudeAirportText
    ) {
        try {
            
            Location location = LocationsDataParser.Parse(IDAirportText, nameAirportText, cityAirportText, countryAirportText, latitudeAirportText, longitudeAirportText);
             // DEBUG: Mostrar datos recibidos y procesados por el parser
    System.out.println("====== DEBUG - Datos devueltos por LocationsDataParser.Parse ======");
    System.out.println("ID: " + location.getAirportID());
    System.out.println("Name: " + location.getAirportName());
    System.out.println("City: " + location.getAirportCity());
    System.out.println("Country: " + location.getAirportCountry());
    System.out.println("Latitude: " + location.getAirportLatitude());
    System.out.println("Longitude: " + location.getAirportLongitude());
    System.out.println("==============================================================");


// Llamada a Parse

            
            LocationCreationService service = new LocationCreationService(locations, storage);
            Response response = service.create(location);
            return response;
        } catch (Exception e) {
            return new Response("Error al registrar localización: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

   public Response getAllLocations() {
    return new Response("Lista de locations obtenida.", Status.OK, locations);
}

}
