/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.mobs.enemies.finalLevel;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import  twinskingdom.entities.Entity;

/**
 * This class has been created to manage the enemies' waves in the final boss level.
 * level
 *
 */
public class FinalEnemiesManager extends Observable implements Observer {

    private List<Entity> entities;
    protected Collection<Observer> observers;

    public FinalEnemiesManager() {
        entities = new LinkedList();
        observers = new LinkedList<>();
    }

    /***
     * This method, recalled by the final boss level, adds its enemies to entities list.
     * @param e represents the entity to add to the list
     */
    public void addEntity(Entity e) {
        entities.add(e);
        e.addObserver(this);
    }

    /**
     * This method sets the class observer.
     * @param o represents the observer to add to the observers list
     */
    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

   /**
     * The update method removes the observable entity to entities list, checking
     * its size. If it reaches 0 value, the class observer is recalled. This latter
     * is represented by the game final boss, which re-appears when all the 
     * wave enemies die. 
     * @param o
     * @param arg 
     */
    @Override
    public void update(Observable o, Object arg) {
        entities.remove((Entity) o);

        //observers' updating, dependent on the deletion of all the enemies' current wave
        if (entities.size() == 0) {
            for (Observer obs : observers) {
                obs.update(this, this);
            }
        }
    }
}
