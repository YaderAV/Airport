/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Storage.DataLoaders;

import airport.Models.Entities.Flights.Flight;
import airport.Models.Entities.Location;
import airport.Models.Entities.Plane;
import airport.Models.Serialization.JSONFlight;
import airport.Models.Storage.Interfaces.FlightStorage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author saraibanez
 */
public class FlightDataLoader implements FlightStorage<Flight> {

    private final JSONFlight flightMapper;
    private final Map<String, Plane> planes;
    private final Map<String, Location> locations;
    private final String path;

    public FlightDataLoader(JSONFlight flightMapper, Map<String, Plane> planes, Map<String, Location> locations, String path) {
        this.flightMapper = flightMapper;
        this.planes = planes;
        this.locations = locations;
        this.path = path;
    }

    @Override
    public List<Flight> loadFlights() throws IOException {
        String content = Files.readString(Paths.get(path));
        JSONArray array = new JSONArray(content);

        List<Flight> flights = new ArrayList<>();

        for (Object o : array) {
            JSONObject obj = (JSONObject) o;
            flights.add(flightMapper.fromJSON(obj));
        }
        return flights;

    }

    @Override
    public void saveFlights(List<Flight> flights) throws IOException {
        JSONArray array = new JSONArray();
        for(Flight flight : flights){
            array.put(flightMapper.toJSON(flight));
            Files.writeString(Paths.get(path), array.toString(4));
        }
        
    }

    public String getPath() {
        return path;
    }

}
