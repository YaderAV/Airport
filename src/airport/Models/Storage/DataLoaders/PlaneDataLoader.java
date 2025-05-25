/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Storage.DataLoaders;

import airport.Models.Entities.Plane;
import airport.Models.Serialization.JSONMapper;
import airport.Models.Storage.Interfaces.PlaneStorage;

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
public class PlaneDataLoader implements PlaneStorage {
      private final JSONMapper mapper;
      private final String path;

    public PlaneDataLoader(JSONMapper mapper, String path) {
        this.mapper = mapper;
        this.path = path;
    }
      
      

    @Override
    public Map loadPlanes() throws IOException {
        String content = Files.readString(Paths.get(path));
        JSONArray array = new JSONArray(content);
        Map<String, Plane> map = new HashMap<>();
        for(Object o : array){
            JSONObject obj = (JSONObject) o; 
            Plane plane = (Plane) mapper.fromJSON(obj);
            map.put(plane.getId(), plane);
        }
        return map;
    }

    @Override
    public void savePlanes(Map planes) throws IOException {
        JSONArray array = new JSONArray();
        for (Object plane : planes.values()){
            array.put(mapper.toJSON(plane));
        }
        Files.writeString(Paths.get(path), array.toString(4));
    }

    public String getPath() {
        return path;
    }

    


    
}
