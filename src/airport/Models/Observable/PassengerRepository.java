/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Observable;

import airport.Models.Entities.Passenger;
import airport.controllers.PassengerController;
import airport.controllers.utils.Response;
import java.util.ArrayList;

import airport.controllers.utils.Status;
import java.util.List;

/**
 *
 * @author saraibanez
 */
public class PassengerRepository extends ObservableBase {

    private final PassengerController controller;
    

    public PassengerRepository(PassengerController controller) {
        this.controller = controller;
    }

    public void createPassengerFromRawData(
        String idText,
        String name,
        String lastname,
        String yearText,
        String monthText,
        String dayText,
        String phoneCodeText,
        String phoneText,
        String country
    ) {
        Response response = controller.registerPassenger(
            idText, name, lastname, yearText, monthText, dayText, phoneCodeText, phoneText, country
        );

        if (response.getStatus() == Status.CREATED) {
            notifyObservers(); // Notifica a la vista que se ha creado un pasajero
        } else {
            // Opcional: podrías tener una cola de errores o un listener para errores
            System.err.println("Error: " + response.getMessage());
        }
    }

public Iterable<Passenger> getAllPassengers() {
    Response response = controller.getAllPassengers();
    System.out.println("DEBUG - response.getData(): " + response.getData());  // ← Aquí el debug

    if (response.getStatus() == Status.OK) {
        try {
            @SuppressWarnings("unchecked")
            List<Passenger> passengers = (List<Passenger>) response.getData();
            return passengers;
        } catch (ClassCastException e) {
            System.err.println("Error: El tipo de datos no es List<Passenger>");
            return new ArrayList<>();
        }
    } else {
        System.err.println("Error al obtener pasajeros: " + response.getMessage());
        return new ArrayList<>();
    }
}
    public Passenger getPassengerById(String passengerIdStr) {
       long passengerId = Long.parseLong(passengerIdStr);
       Response response = controller.getPassengerById(passengerId);

       if (response.getStatus() == Status.OK) {
           return (Passenger) response.getData();
       } else {
           System.err.println("Error al obtener pasajero: " + response.getMessage());
           return null;
       }
   }

    }




