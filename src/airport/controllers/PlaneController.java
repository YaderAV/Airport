/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers;

import airport.Models.Entities.PassengerTableModelAdapter;
import airport.Models.Entities.Plane;
import airport.Models.Entities.PlaneTableModelAdapter;
import airport.Models.Observable.PlaneRepository;
import airport.Models.Storage.JSONStorage;
import airport.controllers.service.PlaneRegistrationService;
import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.controllers.utils.parser.PlaneDataParser;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yader
 */
public class PlaneController {

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
            return response;
        } catch (Exception e) {
            return new Response("Error al registrar avión: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

   public Object[][] getPassengerRows() {
        PlaneController pc = new PlaneController(planes, storage);
        PlaneRepository pr = new PlaneRepository(pc);
        List<Plane> planes = (List<Plane>) pr.getAllPlanes();
        Object [][] rows = new Object[planes.size()][];
        for(int i= 0; i<planes.size(); i++){
            rows[i] = PlaneTableModelAdapter.toRow(planes.get(i));
        }
        return rows;
    }
    public String[] getHeaders(){
        return PassengerTableModelAdapter.getColimHeader();
    }
   public Response getAllPlanes() {
    return new Response("Lista de aviones obtenida.", 200, new ArrayList<>(planes.values()));
}

    public JSONStorage getStorage() {
        return storage;
    }
    
}
