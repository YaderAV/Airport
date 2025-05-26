/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controllers.utils.parser;

import airport.Models.Entities.Plane;

/**
 *
 * @author yader
 */
public class PlaneDataParser {
    public static Plane Parse(
            String ID,
            String brand,
            String model,
            String maxCapacity,
            String airline
    ) throws Exception{
        int MaxCapacity = Parsers.INTEGER.parse(maxCapacity);
        return new Plane(ID,brand, model, MaxCapacity, airline);
    }
}
