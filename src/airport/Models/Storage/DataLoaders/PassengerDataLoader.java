/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Storage.DataLoaders;

import airport.Models.Entities.Passenger;
import airport.Models.Serialization.JSONMapper;
import airport.Models.Storage.Interfaces.PassengerStorage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author saraibanez
 */
public class PassengerDataLoader implements PassengerStorage {

    private final String path;
    private final JSONMapper<Passenger> mapper;

    public PassengerDataLoader(String path, JSONMapper<Passenger> mapper) {
        this.path = path;
        this.mapper = mapper;
    }

    @Override
    public Map loadPassengers() throws IOException {
        String content = Files.readString(Paths.get(path));
        JSONArray array = new JSONArray(content);
        Map<Long, Passenger> passengers = new HashMap<>();
        for(Object o : array){
            JSONObject obj = (JSONObject)o;
            Passenger passenger = mapper.fromJSON(obj);
            passengers.put(passenger.getId(), passenger);
        }
        return passengers; 
}

@Override
public void savePassengers(Map passengers) throws IOException {
        JSONArray array = new JSONArray(); 
        for(Object p : passengers.values()){
            array.put(mapper.toJSON((Passenger) p));
        }
        Files.writeString(Paths.get(path), array.toString(4));
    }

    public String getPath() {
        return path;
    }


    
    
}
