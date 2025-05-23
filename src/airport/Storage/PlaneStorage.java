/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Storage;

import airport.Models.Plane;
import java.util.ArrayList;

/**
 *
 * @author saraibanez
 */
 public interface PlaneStorage {
       ArrayList<Plane> loadPlanes();
       void savePlanes(ArrayList<Plane> planes);
   }