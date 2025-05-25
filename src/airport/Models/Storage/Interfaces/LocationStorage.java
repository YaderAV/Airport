/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Storage.Interfaces;

import java.io.IOException;
import java.util.Map;

/**
 *
 * @author saraibanez
 * @param <String>
 * @param <Location>
 */
public interface LocationStorage <String, Location>{
    Map<String, Location> loadLocations() throws IOException;
    void saveLocations( Map<String, Location> locations) throws IOException;
}