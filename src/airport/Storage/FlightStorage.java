

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package airport.Storage;

import airport.Models.Flight;
import airport.Models.Location;
import airport.Models.Passenger;
import airport.Models.Plane;
import java.util.ArrayList;

/**
 *
 * @author saraibanez
 */

   public interface FlightStorage {
       ArrayList<Flight> loadFlights(ArrayList<Location> locations, ArrayList<Passenger> passengers, ArrayList<Plane> planes);
       void saveFlights(ArrayList<Flight> flights);
   }