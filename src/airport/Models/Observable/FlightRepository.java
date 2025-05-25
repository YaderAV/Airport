/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Observable;

import airport.Models.Entities.Flights.Flight;
import airport.controllers.FlightController;
import airport.controllers.utils.Response;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yader
 */
public class FlightRepository extends ObservableBase {

    private final List<Flight> flights = new ArrayList();
    private final FlightController flightController;

    public FlightRepository(FlightController flightController) {
        this.flightController = flightController;
    }

  public ArrayList<Flight> getAllFlights() {
    Response response = flightController.getAllFlights();
    if (response.getStatus() == 200) {
        List<Flight> flightsList = (List<Flight>) response.getData();
         System.out.println("DEBUG - Response data de getallflights" +response.getData() );
        if (flightsList != null) {
            flights.clear();
            flights.addAll(flightsList);
            notifyObservers();
            return new ArrayList<>(flights);
        } else {
            System.out.println("DEBUG - Response.getObject() es null");
            return new ArrayList<>();
        }
    } else {
        System.out.println("Error al obtener vuelos: " + response.getMessage());
        return new ArrayList<>();
    }
}

    public List<Flight> getFlightsByPassengerId(long passengerId) {
    return flightController.getFlightsByPassengerId(passengerId);
}


   
}
