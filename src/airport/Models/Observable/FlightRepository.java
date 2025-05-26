/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Observable;

import airport.Models.Entities.Flights.Flight;
import airport.Models.Entities.Location;
import airport.Models.Entities.Passenger;
import airport.Models.Entities.Plane;
import airport.controllers.FlightController;
import airport.controllers.utils.Response;
import airport.controllers.utils.Status;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yader
 */
public class FlightRepository extends ObservableBase {
    private final FlightController flightController;
    private final Map<String, Plane> planes;
    private final Map<String, Location> locations;

    public FlightRepository(FlightController flightController, Map<String, Plane> planes, Map<String, Location> locations) {
        this.flightController = flightController;
        this.planes = planes;
        this.locations = locations;
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

public Response createFlightFromRawData(
    String id, 
    String planeID, 
    String departureLocationID, 
    String arrivalLocationID, 
    String scaleLocationID, 
    String year, 
    String month, 
    String day, 
    String hour, 
    String minute, 
    String arrivalHour, 
    String arrivalMinute, 
    String scaleHour, 
    String scaleMinute
) {
    try {
        Response response = flightController.createFlight(
            id, 
            planeID, 
            departureLocationID, 
            arrivalLocationID, 
            scaleLocationID, 
            year, 
            month, 
            day, 
            hour, 
            minute, 
            arrivalHour, 
            arrivalMinute, 
            scaleHour, 
            scaleMinute, 
            this.planes,        // Aquí pasamos el mapa de aviones
            this.locations      // Aquí pasamos el mapa de ubicaciones
        );

        if (response.getStatus() == Status.CREATED) {
            notifyObservers(); // Notifica a la vista que se ha creado un vuelo
        } else {
            System.err.println("Error al crear vuelo: " + response.getMessage());
        }

        return response;
    } catch (Exception e) {
        System.err.println("Excepción al crear vuelo: " + e.getMessage());
        return new Response("Error inesperado: " + e.getMessage(), Status.BAD_REQUEST);
    }
}


  
}


