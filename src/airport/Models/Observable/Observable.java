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
public abstract class Observable {
    private final ArrayList<Observer> observers = new ArrayList<>();
    
    public void addObserver(Observer o){
    observers.add(o);
    }
    public void removeObserver(Observer o){
    observers.remove(o);
    }
    public void notifyObservers(){
    for(Observer o : observers){
        o.update();
    }
    }
    
}
