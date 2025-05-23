/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Storage;

import airport.Models.Location;
import airport.Storage.LocationStorage;

import static airport.utils.FileUtils.readFile;
import static airport.utils.FileUtils.writeFile;
import java.util.ArrayList;
import org.json.JSONArray;

/**
 *
 * @author saraibanez
 */
public class JSONLocationStorage implements LocationStorage {
    private final String path;

    public JSONLocationStorage(String path) {
        this.path = path;
    }

    @Override
    public ArrayList<Location> loadLocations() {
       String json = readFile(path);
        JSONArray array = new JSONArray(json);
        ArrayList<Location> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            list.add(Location.fromJSON(array.getJSONObject(i)));
        }
        return list;

    }

    @Override
    public void saveLocations(ArrayList<Location> locations) {
         JSONArray array = new JSONArray();
        for (Location l : locations) {
            array.put(l.toJSON());
        }
        writeFile(path, array.toString(2));

}
}