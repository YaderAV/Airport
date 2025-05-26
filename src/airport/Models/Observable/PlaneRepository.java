/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Observable;

import airport.Models.Entities.Plane;
import airport.controllers.PlaneController;
import airport.controllers.utils.Response;
import java.util.ArrayList;
import javax.swing.JTable;

/**
 *
 * @author saraibanez
 */
public class PlaneRepository extends ObservableBase {

    private final PlaneController planeController;

    public PlaneRepository(PlaneController planeController) {
        this.planeController = planeController;
    }

    

    public JTable getPassengerRowsR() {
        Object[][] data = planeController.getPassengerRows();
        String[] headers = planeController.getHeaders();
        return new JTable(data, headers);
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

}
