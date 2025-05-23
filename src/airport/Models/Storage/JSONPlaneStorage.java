/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Storage;

import airport.Models.Plane;
import airport.Storage.PlaneStorage;

import static airport.utils.FileUtils.readFile;
import static airport.utils.FileUtils.writeFile;
import java.util.ArrayList;
import org.json.JSONArray;

/**
 *
 * @author saraibanez
 */
public class JSONPlaneStorage implements PlaneStorage {
      private final String path;

    public JSONPlaneStorage(String path) {
        this.path = path;
    }


    @Override
    public ArrayList<Plane> loadPlanes() {
                String json = readFile(path);
        JSONArray array = new JSONArray(json);
        ArrayList<Plane> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            list.add(Plane.fromJSON(array.getJSONObject(i)));
        }
        return list;
      }

    @Override
    public void savePlanes(ArrayList<Plane> planes) {
          JSONArray array = new JSONArray();
        for (Plane p : planes) {
            array.put(p.toJSON());
        }
        writeFile(path, array.toString(2));
    }
    
}
