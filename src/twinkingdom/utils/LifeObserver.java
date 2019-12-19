/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.utils;

import java.util.Observable;
import java.util.Observer;
import twinkingdom.entities.mobs.player.Player;

/**
 *
 * @author SSQ1
 */
public class LifeObserver extends Observable implements Observer{
    
    private int health;
    private int numLives;
    
    

    @Override
    public void update(Observable o, Object arg) {
        Player p=(Player) o;
        health=p.getHealthPoints();
        numLives=p.getNumLives();
        if (numLives ==0){
            System.out.println("Game Over");
        }
        
        setChanged();
        notifyObservers();;
    }

    public int getHealth() {
        return health;
    }

    public int getNumLives() {
        return numLives;
    }
    
    
    
    
    
}
