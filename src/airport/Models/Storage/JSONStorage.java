/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Storage;

import airport.Models.Flight;
import airport.Models.Location;
import airport.Models.Passenger;
import airport.Models.Plane;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author yader
 */
public class JSONStorage implements Storage {

    private final String locationsPath;
    private final String passengersPath;
    private final String planesPath;
    private final String flightsPath;

    public JSONStorage(String locationsPath, String passengersPath, String planesPath, String flightsPath) {
        this.locationsPath = locationsPath;
        this.passengersPath = passengersPath;
        this.planesPath = planesPath;
        this.flightsPath = flightsPath;
    }

    private String readFile(String path) {
        try {
            return Files.readString(Paths.get(path));
        } catch (IOException e) {
            return "[]";
        }
    }

    private void writeFile(String path, String content) {
        try (FileWriter file = new FileWriter(path)) {
            file.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Location> loadLocations() {
        String json = readFile(locationsPath);
        JSONArray array = new JSONArray(json);
        ArrayList<Location> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            list.add(Location.fromJSON(array.getJSONObject(i)));
        }
        return list;
    }

    public ArrayList<Passenger> loadPassengers() {
        String json = readFile(passengersPath);
        JSONArray array = new JSONArray(json);
        ArrayList<Passenger> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            list.add(Passenger.fromJSON(array.getJSONObject(i)));
        }
        return list;
    }

    public ArrayList<Plane> loadPlanes() {
        String json = readFile(planesPath);
        JSONArray array = new JSONArray(json);
        ArrayList<Plane> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            list.add(Plane.fromJSON(array.getJSONObject(i)));
        }
        return list;
    }

    public ArrayList<Flight> loadFlights(ArrayList<Location> locations, ArrayList<Passenger> passengers, ArrayList<Plane> planes) {
        String json = readFile(flightsPath);
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
            Location scale = null;
            if (o.has("scaleLocation")) {
                for (Location location : locations) {
                    if (location.getAirportId().equals(o.getString("scaleLocation"))) {
                       scale = location;
                    }
                }
            }
            
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

    public void saveLocations(ArrayList<Location> locations) {
        JSONArray array = new JSONArray();
        for (Location l : locations) {
            array.put(l.toJSON());
        }
        writeFile(locationsPath, array.toString(2));
    }

    public void savePassengers(ArrayList<Passenger> passengers) {
        JSONArray array = new JSONArray();
        for (Passenger p : passengers) {
            array.put(p.toJSON());
        }
        writeFile(passengersPath, array.toString(2));
    }

    public void savePlanes(ArrayList<Plane> planes) {
        JSONArray array = new JSONArray();
        for (Plane p : planes) {
            array.put(p.toJSON());
        }
        writeFile(planesPath, array.toString(2));
    }

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

        writeFile(flightsPath, array.toString(2));
    }
}
