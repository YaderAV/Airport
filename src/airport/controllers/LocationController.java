/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers;

import airport.Models.Entities.Location;
import airport.Models.Entities.LocationTableModelAdapter;
import airport.Models.Entities.PassengerTableModelAdapter;
import airport.Models.Observable.LocationRepository;
import airport.controllers.service.LocationCreationService;
import airport.Models.Storage.JSONStorage;
import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.controllers.utils.parser.LocationsDataParser;
import java.util.List;
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
            LocationCreationService service = new LocationCreationService(locations, storage);
            Response response = service.create(location);
            return response;
        } catch (Exception e) {
            return new Response("Error al registrar localización: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
    
     
   public Response getAllLocations() {
    var sortedList = locations.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .toList();

        return new Response("Lista de localizaciones obtenida.", Status.OK, sortedList);
}
public Object[][] getPassengerRows() {
        LocationController lc = new LocationController(locations, storage);
        LocationRepository lr = new LocationRepository(lc);
        List<Location> locations = (List<Location>) lc.getAllLocations().getData();
        Object [][] rows = new Object[locations.size()][];
        for(int i= 0; i<locations.size(); i++){
            rows[i] = LocationTableModelAdapter.toRow(locations.get(i));
        }
        return rows;
    }
    public String[] getHeaders(){
        return PassengerTableModelAdapter.getColimHeader();
    }
}
