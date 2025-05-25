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
        JSONObject json = new JSONObject();
        json.put("id", f.getId());
        json.put("plane", f.getPlane());
        json.put("departureLocation", f.getDepartureLocation());
        json.put("arrivalLocation", f.getArrivalLocation());
        json.put("departureDate", f.getSchedule().getDepartureDate());
        json.put("hoursDurationArrival", f.getSchedule().getHoursDurationArrival());
        json.put("minutesDurationArrival", f.getSchedule().getMinutesDurationArrival());
        json.put("hoursDurationScale", f.getSchedule().getHoursDurationScale());
        json.put("minutesDurationScale", f.getSchedule().getMinutesDurationScale());
        
        if(f instanceof ScaledFlight){
            ScaledFlight sf = (ScaledFlight) f;
            json.put("scaleLocation", sf.getScale().getAirportID()); 
        }else {
        json.put("scaleLocation", JSONObject.NULL);
        json.put("hoursDurationScale", 0);
        json.put("minutesDurationScale", 0);
        
        }
        return json;
    }

    @Override
    public Flight fromJSON(JSONObject json) {
        String id = json.getString("id");
        Plane plane = planes.get(json.getString("plane"));
        Location departure = locations.get(json.getString("departureLocation")); 
        Location arrival = locations.get(json.get("arrivalLocation"));
        String scaleId = json.optString("scaleLocation", null);
        LocalDateTime date = LocalDateTime.parse(json.getString("departureDate"));
        int hArr = json.getInt("hoursDurationArrival");
        int mArr = json.getInt("minutesDurationArrival");
        int hScale = json.getInt("hoursDurationScale");
        int mScale = json.getInt("minutesDurationScale");
        FlightSchedule flightSchedule = new  FlightSchedule(date, hArr, mArr, hScale, mScale);
        
        if(scaleId == null || scaleId.equals("null")){
            return new DirectFlight(id, plane, departure, arrival, flightSchedule);
        }else{
            Location scale = locations.get("scaleId");
            return new ScaledFlight (id, plane, departure, scale, arrival, flightSchedule);
        }
    }

}
