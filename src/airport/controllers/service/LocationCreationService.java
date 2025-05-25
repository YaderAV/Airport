/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.service;

import airport.Models.Entities.Location;
import airport.Models.Storage.JSONStorage;
import airport.controllers.utils.validators.LocationValidator;
import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.controllers.utils.validators.ValidationResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author saraibanez
 */
public class LocationCreationService {
    private final Map<String, Location> locations;
    private final JSONStorage storage;

    public LocationCreationService(Map<String,Location> locations, JSONStorage storage) {
        this.locations = locations;
        this.storage = storage;
    }

    public Response create(Location location) throws IOException {
        LocationValidator validator = new LocationValidator();
        ValidationResult validation = validator.validate(location);

        if (!validation.isValid()) {
            return new Response(validation.getCombinedMessage(), Status.BAD_REQUEST);
        }


        locations.put(location.getAirportID(), location);
        storage.getLocationLoader().saveLocations(locations);
        return new Response("Localización registrada correctamente", Status.CREATED);
    }
}
