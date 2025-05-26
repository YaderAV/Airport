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
import airport.controllers.utils.parser.Parsers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
=======
import java.util.Map;
>>>>>>> e094ffced0632ee3e9df57a482f89502b4717799

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
<<<<<<< HEAD
    public JTable onRefreshAllFlights() throws IOException{
        Object[][] data = flightController.getFlightRows();
        String [] headers = flightController.getHeaders();
        JTable table =new JTable(data, headers);
        return table;
    }
    
public JTable getPassengerRowsR() throws IOException{
        Object[][] data = flightController.getFlightRows();
        String [] headers = flightController.getHeaders();
        return new JTable(data, headers);
    }
=======
>>>>>>> e094ffced0632ee3e9df57a482f89502b4717799
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

    private Flight getFlightById(String flightID){
       return flightController.getFlights().stream()
               .filter(f->f.getId().equals(flightID)).findFirst().orElse(null);
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
<<<<<<< HEAD
  
    public DefaultTableModel updateMyFlightsTable(String passengerId)throws IOException, Exception{
        Long id = Parsers.LONG.parse(passengerId);
    List<Flight> flights = flightController.getFlightsByPassengerId(id);
    String[] headers = {
    "ID", "Departure Date", "Arrival Date"
    };
        DefaultTableModel model= new DefaultTableModel(headers,0);
        
        for(Flight f: flights){
        model.addRow(new Object[]{
        f.getId(),
        f.getSchedule().getDepartureDate().toString(),
        f.getSchedule().calculateArrival()
        });
        }
        return model;
    }
=======

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


  
>>>>>>> e094ffced0632ee3e9df57a482f89502b4717799
}


