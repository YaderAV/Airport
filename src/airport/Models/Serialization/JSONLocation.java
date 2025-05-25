/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Serialization;

import airport.Models.Entities.Location;
import org.json.JSONObject;

/**
 *
 * @author yader
 */
public class JSONLocation implements JSONMapper<Location> {
    @Override
    public JSONObject toJSON(Location loc) {
        JSONObject obj = new JSONObject();
        obj.put("airportId", loc.getAirportID());
        obj.put("airportName", loc.getAirportName());
        obj.put("airportCity", loc.getAirportCity());
        obj.put("airportCountry", loc.getAirportCountry());
        obj.put("airportLatitude", loc.getAirportLatitude());
        obj.put("airportLongitude", loc.getAirportLongitude());
        return obj;
    }

    @Override
    public Location fromJSON(JSONObject obj) {
        return new Location(
            obj.getString("airportId"),
            obj.getString("airportName"),
            obj.getString("airportCity"),
            obj.getString("airportCountry"),
            obj.getDouble("airportLatitude"),
            obj.getDouble("airportLongitude")
        );
    }

   
}
