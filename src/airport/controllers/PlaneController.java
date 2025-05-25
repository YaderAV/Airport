/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers;

import airport.Models.Entities.Plane;
import airport.Models.Observable.ObservableBase;
import airport.Models.Storage.JSONStorage;
import airport.controllers.service.PlaneRegistrationService;
import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.controllers.utils.parser.PlaneDataParser;
import java.util.Map;

/**
 *
 * @author yader
 */
public class PlaneController extends ObservableBase {

    private final Map<String, Plane> planes;
    private final JSONStorage storage;

    public PlaneController(Map<String, Plane> planes, JSONStorage storage) {
        this.planes = planes;
        this.storage = storage;
    }

    public Response registerPlane(
            String ID,
            String brand,
            String model,
            String maxCapacity,
            String airline
    ) {
        try {
            Plane plane = PlaneDataParser.Parse(ID, brand, model, maxCapacity, airline);
            PlaneRegistrationService service;
            service = new PlaneRegistrationService(planes, storage);
            Response response = service.register(plane);

            if (response.getStatus() == Status.CREATED) {
                notifyObservers();
            }
            return response;
        } catch (Exception e) {
            return new Response("Error al registrar avión: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

    public Map<String, Plane> getPlanes() {
        return planes;
    }

    public JSONStorage getStorage() {
        return storage;
    }

}
