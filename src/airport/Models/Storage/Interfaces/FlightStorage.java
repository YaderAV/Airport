

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package airport.Models.Storage.Interfaces;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author saraibanez
 * @param <Flight>
 */

   public interface FlightStorage<Flight>{
       List<Flight> loadFlights() throws IOException;
       void saveFlights(List<Flight> flights) throws IOException;
   }