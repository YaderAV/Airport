/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Observable;

import airport.Models.Entities.Plane;
import airport.controllers.PlaneController;
import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import airport.controllers.utils.parser.Parsers;
import java.util.ArrayList;
/**
 *
 * @author saraibanez
 */

public class PlaneRepository extends ObservableBase {

    private final PlaneController planeController;

    public PlaneRepository(PlaneController planeController) {
        this.planeController = planeController;
    }

public ArrayList<Plane> getAllPlanes() {
    Response response = planeController.getAllPlanes();
    if (response.getStatus() == 200) {
        @SuppressWarnings("unchecked")
        ArrayList<Plane> planesList = (ArrayList<Plane>) response.getData();
        notifyObservers();
        return planesList;
    } else {
        System.out.println("Error al obtener planes: " + response.getMessage());
        return new ArrayList<>();
    }
}

    public Response createPlaneFromRawData(String id, String brand, String model, String maxCapacityStr, String airline) {
        try {
            
    // DEBUG: Imprimir todos los datos recibidos
    System.out.println("====== DEBUG - Datos recibidos para registro de avión ======");
    System.out.println("ID: " + id);
    System.out.println("Brand: " + brand);
    System.out.println("Model: " + model);
    System.out.println("Max Capacity: " + maxCapacityStr);
    System.out.println("Airline: " + airline);
    System.out.println("==============================================================");
            
            Response response = planeController.registerPlane(id, brand, model, maxCapacityStr, airline);

            if (response.getStatus() == Status.CREATED) {
                notifyObservers(); // Notifica a la vista que se ha creado un avión
            } else {
                System.err.println("Error al crear avión: " + response.getMessage());
            }

            return response;
              } catch (Exception e) {
            System.err.println("Excepción al crear avión: " + e.getMessage());
            return new Response("Error inesperado: " + e.getMessage(), Status.BAD_REQUEST);
            
        }

}
}
    
