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
import airport.controllers.utils.parser.Parsers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yader
 */
public class FlightRepository extends ObservableBase {
    private final FlightController flightController;

    public FlightRepository(FlightController flightController) {
        this.flightController = flightController;
    }
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
}


