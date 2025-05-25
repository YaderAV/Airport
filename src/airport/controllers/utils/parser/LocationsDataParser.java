/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.utils.parser;

import airport.controllers.utils.parser.Parsers;
import airport.Models.Entities.Location;

/**
 *
 * @author yader
 */
public class LocationsDataParser {

    public static Location Parse(
            String IDAirportText,
            String nameAirportText,
            String cityAirportText,
            String countryAirportText,
            String latitudeAirportText,
            String longitudeAirportText
    ) throws Exception 
    
    {
        String id = IDAirportText;
        String name=  nameAirportText;
        String city = cityAirportText;
        String country = countryAirportText;
        double latitude = Parsers.DOUBLE.parse(latitudeAirportText);
        double longitude = Parsers.DOUBLE.parse(longitudeAirportText);
        
        return new Location (id, name, city, country, latitude, longitude);
    }
}
