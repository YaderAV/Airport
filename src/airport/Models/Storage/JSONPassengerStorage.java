/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Storage;

import static airport.utils.FileUtils.readFile;
import static airport.utils.FileUtils.writeFile;

import java.util.ArrayList;

import org.json.JSONArray;

import airport.Models.Passenger;
import airport.Storage.PassengerStorage;

/**
 *
 * @author saraibanez
 */
public class JSONPassengerStorage implements PassengerStorage {
    
    private final String path;
    public JSONPassengerStorage(String path) {
        this.path = path;
    }


    @Override
    public ArrayList<Passenger> loadPassengers() {
         String json = readFile(path);
        JSONArray array = new JSONArray(json);
        ArrayList<Passenger> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            list.add(Passenger.fromJSON(array.getJSONObject(i)));
        }
        return list;

    }

    @Override
    public void savePassengers(ArrayList<Passenger> passengers) {
      JSONArray array = new JSONArray();
        for (Passenger p : passengers) {
            array.put(p.toJSON());
        }
        writeFile(path, array.toString(2));
    }
    
}
