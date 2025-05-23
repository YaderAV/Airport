/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers;

import airport.controllers.utils.Response;
import airport.controllers.utils.Status;

/**
 *
 * @author yader
 */
public class LocationController {
    public Response createAirport(String airportID, 
            String airportname, String airportcity,
            String airportcountry, float airportlatitude,
            float airportlongitude){
        if(airportID.length()>3 ){
            return new Response ("ID del aeropuerto inválido", Status.BAD_REQUEST);
        }
        if(airportlatitude<-90 ||airportlatitude>90){
            return new Response ("Latitud inválida", Status.BAD_REQUEST);
        }
        if(airportlongitude<180|| airportlongitude>180){
            return new Response ("Longitud inválida", Status.BAD_REQUEST);
        }
        return new Response("Aeropuerto creado éxitosamente",Status.OK);
    }
}
