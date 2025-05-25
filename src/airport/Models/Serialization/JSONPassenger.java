/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Serialization;

import airport.Models.Entities.Passenger;
import java.time.LocalDate;
import org.json.JSONObject;

/**
 *
 * @author yader
 */
public class JSONPassenger implements JSONMapper<Passenger> {

    @Override
    public JSONObject toJSON(Passenger pass) {
        JSONObject obj = new JSONObject();
        obj.put("id", pass.getId());
        obj.put("firstname", pass.getFirstname());
        obj.put("lastname", pass.getLastname());
        obj.put("birthDate", pass.getBirthDate());
        obj.put("countryPhoneCode", pass.getCountryPhoneCode());
        obj.put("phone", pass.getPhone());
        obj.put("country", pass.getCountry());
        return obj;
    }

    @Override
    public Passenger fromJSON(JSONObject json) {
        return new Passenger(
        json.getLong("id"),
        json.getString("firstname"),
        json.getString("lastname"),
        LocalDate.parse(json.getString("birthDate")),
        json.getInt("countryPhoneCode"),
        json.getLong("phone"),
        json.getString("country")
        );

    }
    
}
