/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Storage;

import airport.Models.Flight;
import airport.Models.Location;
import airport.Models.Passenger;
import airport.Models.Plane;
import airport.Storage.FlightStorage;

import static airport.utils.FileUtils.readFile;
import static airport.utils.FileUtils.writeFile;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author saraibanez
 */
public class JSONFlightStorage implements FlightStorage {
    private final String path;

    public JSONFlightStorage(String path) {
        this.path = path;
    }

    @Override
    public ArrayList<Flight> loadFlights(ArrayList<Location> locations, ArrayList<Passenger> passengers, ArrayList<Plane> planes) {
        String json = readFile(path);
        JSONArray array = new JSONArray(json);
        ArrayList<Flight> list = new ArrayList<>();

        Map<String, Location> locationMap = new HashMap<>();
        for (Location l : locations) {
            locationMap.put(l.getAirportId(), l);
        }

        Map<Long, Passenger> passengerMap = new HashMap<>();
        for (Passenger p : passengers) {
            passengerMap.put(p.getId(), p);
        }

        Map<String, Plane> planeMap = new HashMap<>();
        for (Plane p : planes) {
            planeMap.put(p.getId(), p);
        }

        for (int i = 0; i < array.length(); i++) {
            JSONObject o = array.getJSONObject(i);

            Plane plane = planeMap.get(o.getString("planeId"));
            Location departure = locationMap.get(o.getString("departureId"));
            Location arrival = locationMap.get(o.getString("arrivalId"));
            if (o.has("scaleLocation")) {
                for (Location l : locations) {
                    if (l.getAirportId().equals(o.getString("scaleLocation"))) {
                       
                    }
                }
            }
            Location scale = o.has("scaleId") ? locationMap.get(o.getString("scaleId")) : null;
            LocalDateTime date = LocalDateTime.parse(o.getString("departureDate"));

            Flight f;
            if (scale == null) {
                f = new Flight(o.getString("id"), plane, departure, arrival, date,
                        o.getInt("hoursDurationArrival"), o.getInt("minutesDurationArrival"));
            } else {
                f = new Flight(o.getString("id"), plane, departure, scale, arrival, date,
                        o.getInt("hoursDurationArrival"), o.getInt("minutesDurationArrival"),
                        o.getInt("hoursDurationScale"), o.getInt("minutesDurationScale"));
            }

            JSONArray passArray = o.getJSONArray("passengerIds");
            for (int j = 0; j < passArray.length(); j++) {
                Passenger p = passengerMap.get(passArray.getLong(j));
                if (p != null) {
                    f.addPassenger(p);
                    p.addFlight(f);
                }
            }

            list.add(f);
        }

        return list;
    }

    @Override
    public void saveFlights(ArrayList<Flight> flights) {
        JSONArray array = new JSONArray();
        for (Flight f : flights) {
            JSONObject obj = new JSONObject();
            obj.put("id", f.getId());
            obj.put("planeId", f.getPlane().getId());
            obj.put("departureId", f.getDepartureLocation().getAirportId());
            obj.put("arrivalId", f.getArrivalLocation().getAirportId());
            if (f.getScaleLocation() != null) {
                obj.put("scaleId", f.getScaleLocation().getAirportId());
            }
            obj.put("departureDate", f.getDepartureDate().toString());
            obj.put("hoursDurationArrival", f.getHoursDurationArrival());
            obj.put("minutesDurationArrival", f.getMinutesDurationArrival());
            obj.put("hoursDurationScale", f.getHoursDurationScale());
            obj.put("minutesDurationScale", f.getMinutesDurationScale());

            JSONArray passengerIds = new JSONArray();
            for (Passenger p : f.getPassengers()) {
                passengerIds.put(p.getId());
            }

            obj.put("passengerIds", passengerIds);
            array.put(obj);
        }

        writeFile(path, array.toString(2));
    
    }
}
