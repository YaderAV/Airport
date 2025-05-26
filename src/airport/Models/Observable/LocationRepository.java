/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Observable;

import airport.Models.Entities.Location;
import airport.controllers.LocationController;
import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
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
    public Response createLocationFromRawData(String id, String name, String city, String country, String latitudeStr, String longitudeStr) {
    try {
        // Llamar al controlador para procesar los datos
        Response response = locationController.createLocation(id, name, city, country, latitudeStr, longitudeStr);

        if (response.getStatus() == Status.CREATED) {
            notifyObservers();  // Notificar a las vistas que hay cambios
        } else {
            System.err.println("Error al registrar ubicación: " + response.getMessage());
        }

        return response;

    } catch (Exception e) {
        e.printStackTrace();
        return new Response("Error inesperado al registrar ubicación: " + e.getMessage(), Status.BAD_REQUEST);
    }
}


}
