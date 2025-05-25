package airport.Models.Storage.Interfaces;

import java.io.IOException;
import java.util.Map;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author saraibanez
 * @param <Long>
 * @param <Passenger>
 */
  public interface PassengerStorage<Long, Passenger> {
       Map<Long, Passenger>loadPassengers() throws IOException;
       void savePassengers( Map<Long, Passenger> passengers) throws IOException;
   }
