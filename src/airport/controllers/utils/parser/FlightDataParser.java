/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.utils.parser;

import airport.Models.Entities.Flights.DirectFlight;
import airport.Models.Entities.Flights.Flight;
import airport.Models.Entities.Flights.FlightSchedule;
import airport.Models.Entities.Flights.ScaledFlight;
import airport.Models.Entities.Location;
import airport.Models.Entities.Plane;
import java.time.LocalDateTime;
import java.util.Map;

/**
 *
 * @author yader
 */
public class FlightDataParser {
    public static Flight Parse(
            String id,
            String planeId,
            String departureId,
            String arrivalId,
            String scaleId,
            String yearStr,
            String monthStr,
            String dayStr,
            String hourStr,
            String minuteStr,
            String hoursArrivalStr,
            String minutesArrivalStr,
            String hoursScaleStr,
            String minutesScaleStr,
            Map<String, Plane> planes,
            Map<String, Location> locations
            )throws IllegalArgumentException{
         Plane plane = planes.get(planeId);

        Location departureLocation = locations.get(departureId);
        Location arrivalLocation = locations.get(arrivalId);
        Location scale = locations.get(scaleId);
        
        int year = Integer.parseInt(yearStr);
        int month = Integer.parseInt(monthStr);
        int day = Integer.parseInt(dayStr);
        int hour = Integer.parseInt(hourStr);
        int minute = Integer.parseInt(minuteStr);

        int hoursArrival = Integer.parseInt(hoursArrivalStr);
        int minutesArrival = Integer.parseInt(minutesArrivalStr);
        int hoursScale = Integer.parseInt(hoursScaleStr);
        int minutesScale = Integer.parseInt(minutesScaleStr);

        LocalDateTime departureDate = LocalDateTime.of(year, month, day, hour, minute);
        FlightSchedule flightSchedule = new FlightSchedule(departureDate, hoursArrival, minutesArrival, hoursScale, minutesScale);
        if(scale == null){
            return new DirectFlight(id, plane, departureLocation, arrivalLocation, flightSchedule);
        }else{
            return new ScaledFlight(id, plane, departureLocation, scale, arrivalLocation,flightSchedule);
        }
        
    }
}
