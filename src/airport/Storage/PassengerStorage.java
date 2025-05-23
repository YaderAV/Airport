package airport.Storage;

import airport.Models.Passenger;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author saraibanez
 */
  public interface PassengerStorage {
       ArrayList<Passenger> loadPassengers();
       void savePassengers(ArrayList<Passenger> passengers);
   }
