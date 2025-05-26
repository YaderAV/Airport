/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Entities;

import java.util.ArrayList;
import java.util.List;

/**
         *
         * @author yader
         */

public class PassengerList {

    private List<Passenger> passengers = new ArrayList<>();

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    public List<Passenger> getPassengers() {
        return new ArrayList<>(passengers);
    }
    public int count(){
        return passengers.size();
    }
}
