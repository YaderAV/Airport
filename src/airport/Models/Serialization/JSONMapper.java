/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Serialization;

import org.json.JSONObject;

/**
 *
 * @author yader
 */
public interface JSONMapper<T> {
    JSONObject toJSON(T obj);
    T fromJSON(JSONObject json);
}
