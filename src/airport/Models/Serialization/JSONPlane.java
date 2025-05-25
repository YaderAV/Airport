/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Serialization;

import airport.Models.Entities.Plane;
import org.json.JSONObject;

/**
 *
 * @author yader
 */
public class JSONPlane implements JSONMapper<Plane> {

    @Override
    public JSONObject toJSON(Plane plane) {
        JSONObject obj = new JSONObject();
        obj.put("id", plane.getId());
        obj.put("brand", plane.getBrand());
        obj.put("model", plane.getModel());
        obj.put("maxCapacity", plane.getMaxCapacity());
        obj.put("airline", plane.getAirline());
        return obj;

    }

    @Override
    public Plane fromJSON(JSONObject json) {
         return new Plane(
        json.getString("id"),
        json.getString("brand"),
        json.getString("model"),
        json.getInt("maxCapacity"),
        json.getString("airline")
    );

    }

}
