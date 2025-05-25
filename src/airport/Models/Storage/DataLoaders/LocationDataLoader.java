/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Storage.DataLoaders;

import airport.Models.Entities.Location;
import airport.Models.Serialization.JSONMapper;
import airport.Models.Storage.Interfaces.LocationStorage;

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
public class LocationDataLoader implements LocationStorage {
    private final JSONMapper<Location> mapper; 
    private final String path;

    public LocationDataLoader(JSONMapper<Location> mapper, String path) {
        this.mapper = mapper;
        this.path = path;
    }
    @Override
    public Map loadLocations() throws IOException {
        String content = Files.readString(Paths.get(path));
        JSONArray array  = new JSONArray(content);
        
        Map<String, Location> locations = new HashMap<>();
        
        for(Object o: array){
        JSONObject obj = (JSONObject) o;
        Location location = mapper.fromJSON(obj);
        locations.put(location.getAirportID(), location);
        
        }
        return locations;
    }

    @Override
    public void saveLocations(Map locations) throws IOException {
        JSONArray array = new JSONArray();
        for (Object loc : locations.values()) {
            array.put(mapper.toJSON((Location) loc));
        }
        Files.writeString(Paths.get(path), array.toString(4));
    }

    public String getPath() {
        return path;
    }
    
}

    
