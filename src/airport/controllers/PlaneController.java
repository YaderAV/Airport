/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers;


import airport.Models.Observable.ObservableBase;
import airport.Models.Plane;
import airport.Models.Storage.Storage;
import airport.controllers.service.PlaneRegistrationService;
import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import java.util.ArrayList;

/**
 *
 * @author yader
 */
public class PlaneController extends ObservableBase {
    private final ArrayList<Plane> planes;
    private final Storage storage;

    public PlaneController(ArrayList<Plane> planes, Storage storage) {
        this.planes = planes;
        this.storage = storage;
    }

    public Response registerPlane(String id, String brand, String model, int maxCapacity, String airline) {
        try {
            Plane plane = new Plane(id, brand, model, maxCapacity, airline);
            PlaneRegistrationService service = new PlaneRegistrationService(planes, storage);
            Response response = service.register(plane);
            
            if (response.getStatus() == Status.CREATED) {
                notifyObservers();
            }
            return response;
        } catch (Exception e) {
            return new Response("Error al registrar avión: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}

