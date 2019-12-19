/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.utils;

import twinkingdom.entities.statics.grabbable.GrabbableStar;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author mario
 */
public class GrabbableStarCollection extends Observable implements Observer{
    
    private LinkedList<GrabbableStar> collection;
    private LinkedList<Observer> observers;
    private int maxStars;
    
    public GrabbableStarCollection(int maxStars){
        collection = new LinkedList<>();
        observers=new LinkedList<>();
        this.maxStars = maxStars;
    }
    
    @Override
    public void addObserver(Observer o) {
        observers.add(o);
         for (Observer ob: observers) {
                ob.update(this, this);
         } 
    }
    
    public int getMaxStars() {
        return this.maxStars;
    }
    
    public void addStar(GrabbableStar star){
        collection.add(star);
            for (Observer o: observers) {
                o.update(this, this);
            } 
        
    }
    
    public void removeAllStars() {
        collection.clear();
        for (Observer o: observers) {
                o.update(this, this);
       } 
        System.out.println("Test... " + collection.size());
    }
    
    public int getSize(){
        return collection.size();
    }

    @Override
    public void update(Observable o, Object arg) {
        
        addStar((GrabbableStar) o);
        setChanged();
        notifyObservers();
        System.out.println("Chiamato il metodo Update");
        System.out.println(collection);
        
    }
    
    
}
