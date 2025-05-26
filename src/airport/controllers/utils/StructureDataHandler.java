/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.utils;

import airport.Models.Entities.Flights.Flight;
import airport.Models.Entities.Location;
import airport.Models.Entities.Passenger;
import airport.Models.Entities.Plane;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yader
 */
public class StructureDataHandler {
    public  List<Passenger> mapPassengerToList(Map<Long,Passenger> map){
    return new ArrayList<>(map.values());
    }
    
    public  Map<Long,Passenger> listPassengerToMap(List<Passenger> list){
        Map<Long,Passenger> map = new LinkedHashMap<>();
        for(Passenger p : list){
            map.put(p.getId(), p);
        }
        return map;
    }
    public  List<Plane> mapPlaneToList(Map<String,Plane> map){
    return new ArrayList<>(map.values());
    }
    
    public  Map<String,Plane> listPlaneToMap(List<Plane> list){
        Map<String,Plane> map = new LinkedHashMap<>();
        for(Plane p : list){
            map.put(p.getId(), p);
        }
        return map;
    }
    public  List<Location> mapLocationToList(Map<String,Location> map){
    return new ArrayList<>(map.values());
    }
    
    public  Map<String,Location> listLocationToMap(List<Location> list){
        Map<String,Location> map = new LinkedHashMap<>();
        for(Location l : list){
            map.put(l.getAirportID(), l);
        }
        return map;
    }
    public  List<Flight> mapFlightToList(Map<String,Flight> map){
    return new ArrayList<>(map.values());
    }
    
    public  Map<String,Flight> listFlightToMap(List<Flight> list){
        Map<String,Flight> map = new LinkedHashMap<>();
        for(Flight f : list){
            map.put(f.getId(), f);
        }
        return map;
    }
}
