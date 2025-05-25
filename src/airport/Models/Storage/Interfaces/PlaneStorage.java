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
 * @param <Plane>
 */
 public interface PlaneStorage<String, Plane> {
       Map<String, Plane> loadPlanes() throws IOException;
       void savePlanes( Map<String, Plane> planes) throws IOException;
   }