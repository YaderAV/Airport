/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Observable;

import airport.Models.Entities.Location;
import airport.controllers.LocationController;
import airport.controllers.utils.Response;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author saraibanez
 */


public class LocationRepository extends ObservableBase {

    private final LocationController locationController;

    public LocationRepository(LocationController locationController) {
        this.locationController = locationController;
    }

    public ArrayList<Location> getAllLocations() {
       Response response = locationController.getAllLocations();
       System.out.println("DEBUG - response.getData(): " + response.getData());
       if (response.getStatus() == 200) {
           Map<String, Location> locationsMap = (Map<String, Location>) response.getData();
           return new ArrayList<>(locationsMap.values());  // Convertir el Map en Lista
       } else {
           System.out.println("Error: " + response.getMessage());
           return new ArrayList<>();
       }
   }

}
