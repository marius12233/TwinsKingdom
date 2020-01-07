/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.mobs.enemies.finalLevel;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import twinkingdom.game.GameHandler;
import twinkingdom.entities.Entity;
import twinkingdom.entities.mobs.player.Player;

/**
 * This class has been created to manage the enemies' waves in the final boss level
 * @author Antonia
 */
public class FinalEnemiesManager extends Observable implements Observer{
    
    private List<Entity> entities;
    protected Collection<Observer> observers;

    public  FinalEnemiesManager() {
        entities = new LinkedList(); 
        observers = new LinkedList<>();
    }
    
    public void addEntity(Entity e){
        System.out.println("AGGIUNTA DELL'ENTITà!");
        entities.add(e);
        e.addObserver(this);
    }
    
    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    //entity deletion in the structure entities
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("L'ENTITà è MORTA! LA RIMUOVO DA ENTITIES");
        entities.remove((Entity) o);
        
        //observers' updating, dependent on the deletion of all the enemies' current wave
        if (entities.size()==0){
            for(Observer obs:observers){
                obs.update(this, this);
            }
        }
    }
}
