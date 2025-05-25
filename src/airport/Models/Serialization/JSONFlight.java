/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Serialization;

import airport.Models.Entities.Flights.DirectFlight;
import airport.Models.Entities.Flights.Flight;
import airport.Models.Entities.Flights.FlightSchedule;
import airport.Models.Entities.Flights.ScaledFlight;
import airport.Models.Entities.Location;
import airport.Models.Entities.Plane;
import java.time.LocalDateTime;
import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author yader    
 */
public class JSONFlight implements JSONMapper<Flight> {
    private final Map<String, Plane> planes; 
    private final Map<String, Location> locations;

    public JSONFlight(Map<String, Plane> planes, Map<String, Location> locations) {
        this.planes = planes;
        this.locations = locations;
    }

    @Override
    public JSONObject toJSON(Flight f) {
        System.out.println("toJSON - INICIANDO con Flight ID: " + f.getId());

        JSONObject json = new JSONObject();
        json.put("id", f.getId());
        json.put("plane", f.getPlane().getId());  // Corrección: usamos solo el ID
        json.put("departureLocation", f.getDepartureLocation().getAirportID());
        json.put("arrivalLocation", f.getArrivalLocation().getAirportID());
        json.put("departureDate", f.getSchedule().getDepartureDate().toString());
        json.put("hoursDurationArrival", f.getSchedule().getHoursDurationArrival());
        json.put("minutesDurationArrival", f.getSchedule().getMinutesDurationArrival());
        json.put("hoursDurationScale", f.getSchedule().getHoursDurationScale());
        json.put("minutesDurationScale", f.getSchedule().getMinutesDurationScale());

        if (f instanceof ScaledFlight) {
            ScaledFlight sf = (ScaledFlight) f;
            json.put("scaleLocation", sf.getScale().getAirportID()); 
        } else {
            json.put("scaleLocation", JSONObject.NULL);
        }

        System.out.println("toJSON - OBJETO JSON FINAL: " + json.toString());
        return json;
    }

    @Override
    public Flight fromJSON(JSONObject json) {
        System.out.println("fromJSON - INICIANDO: " + json);

        String id = json.getString("id");
        String planeId = json.getString("plane");
        Plane plane = planes.get(planeId);
        System.out.println("fromJSON - Plane encontrado: " + plane);

        String departureId = json.getString("departureLocation");
        String arrivalId = json.getString("arrivalLocation");
        String scaleId = json.optString("scaleLocation", null);
        System.out.println("fromJSON - DepartureID: " + departureId + ", ArrivalID: " + arrivalId + ", ScaleID: " + scaleId);

        Location departure = locations.get(departureId);
        Location arrival = locations.get(arrivalId);
        Location scale = (scaleId != null && !scaleId.equals("null")) ? locations.get(scaleId) : null;

        System.out.println("fromJSON - Locations: Departure: " + departure + ", Arrival: " + arrival + ", Scale: " + scale);

        LocalDateTime date = LocalDateTime.parse(json.getString("departureDate"));
        int hArr = json.getInt("hoursDurationArrival");
        int mArr = json.getInt("minutesDurationArrival");
        int hScale = json.getInt("hoursDurationScale");
        int mScale = json.getInt("minutesDurationScale");
        FlightSchedule flightSchedule = new FlightSchedule(date, hArr, mArr, hScale, mScale);

        Flight result;
        if (scale != null) {
            result = new ScaledFlight(id, plane, departure, arrival, scale, flightSchedule);
        } else {
            result = new DirectFlight(id, plane, departure, arrival, flightSchedule);
        }

        System.out.println("fromJSON - Flight creado: " + result.getId());
        return result;
    }
}



