/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.Models.Observable;

import java.util.ArrayList;
/**
 *
 * @author yader
 */
public abstract class ObservableBase {
   
    private final ArrayList<DataObserver> observers = new  ArrayList();
    public void addObserver(DataObserver o) {
    observers.add(o);
    }
    public void notifyObservers(){
        for(DataObserver o : observers ){
        o.onDataChanged();
        }
        
    }
}
