/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Observable;

import airport.Models.Entities.Flights.Flight;
import airport.Models.Entities.Passenger;
import airport.controllers.FlightController;
import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yader
 */
public class FlightRepository extends ObservableBase {
    private final FlightController flightController;

    public FlightRepository(FlightController flightController) {
        this.flightController = flightController;
    }

    public List<Flight> getAllFlights() {
        Response response = flightController.getAllFlights();
        if (response.getStatus() == Status.OK) {
            @SuppressWarnings("unchecked")
            List<Flight> flights = (List<Flight>) response.getData();
            notifyObservers();
            return flights;
        } else {
            System.err.println("Error al obtener vuelos: " + response.getMessage());
            return new ArrayList<>();
        }
    }

    

    public void delayFlight(String flightId, int hourDelay, int minuteDelay) {
        try {
            Response response = flightController.delayFlight(flightId, hourDelay, minuteDelay);
            if (response.getStatus() == Status.OK) {
                System.out.println("DEBUG - Vuelo " + flightId + " retrasado");
                notifyObservers();
            } else {
                System.err.println("Error al retrasar vuelo: " + response.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPassengerToFlight(String flightID, Passenger passenger) throws IOException {
     Response response = flightController.addPassengerToFlight(passenger,flightID);
    }

   public String extractFlightID(String fullText) {
    return fullText.split(" ")[0]; 
}
   public List<Flight> getFlightsByPassenger (String passengerID) throws IOException {
        return   flightController.getFlightsByPassengerId(Long.parseLong(passengerID));
    }

    
}


