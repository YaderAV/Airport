/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Observable;

import airport.Models.Flight;
import java.util.ArrayList;

/**
 *
 * @author yader
 */
public class FlightRepository extends ObservableBase {

    private final ArrayList<Flight> flights = new ArrayList();

    public void addFlight(Flight flight) {
        flights.add(flight);
        for (int i = 0; i < flights.size() - 1; i++) {
            for (int j = i + 1; j < flights.size(); j++) {
                if(flights.get(i).getDepartureDate().isAfter(flights.get(j).getDepartureDate())){
                    Flight temp = flights.get(j);
                    flights.set(j, flights.get(i));
                    flights.set(i, temp);
                }
            }
        }
        notifyObservers();
    }
    public ArrayList<Flight> getAllFlights(){
        return new ArrayList<>(flights);
    }
}
