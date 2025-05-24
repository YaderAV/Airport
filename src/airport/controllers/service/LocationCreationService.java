/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.service;

import airport.Models.Location;
import airport.Models.Storage.Storage;
import airport.controllers.utils.LocationValidator;
import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.controllers.utils.ValidationResult;
import java.util.ArrayList;

/**
 *
 * @author saraibanez
 */
public class LocationCreationService {
    private final ArrayList<Location> locations;
    private final Storage storage;

    public LocationCreationService(ArrayList<Location> locations, Storage storage) {
        this.locations = locations;
        this.storage = storage;
    }

    public Response create(Location location) {
        LocationValidator validator = new LocationValidator();
        ValidationResult validation = validator.validate(location);

        if (!validation.isValid()) {
            return new Response(validation.getCombinedMessage(), Status.BAD_REQUEST);
        }

        if (locations.stream().anyMatch(l -> l.getAirportId().equals(location.getAirportId()))) {
            return new Response("El ID del aeropuerto ya está registrado", Status.BAD_REQUEST);
        }

        locations.add(location);
        storage.saveLocations(locations);
        return new Response("Localización registrada correctamente", Status.CREATED);
    }
}
