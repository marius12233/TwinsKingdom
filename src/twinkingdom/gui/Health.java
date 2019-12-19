/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.gui;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author bened
 */
public class Health extends Observable {

    private int healthPoints;
    private int maxHealthPoints;
    private int lives;
    private List<Observer> observers;

    public Health() {
        this.maxHealthPoints = 120;
        this.healthPoints = 120;
        this.lives = 3;
        this.observers = new LinkedList<>();
    }

    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
        o.update(this, this);
    }

    public void setHealthPoints(int healthPoints) {
        
        this.healthPoints = Math.max(0, Math.min(healthPoints, this.maxHealthPoints));
        //this.healthPoints = healthPoints;
        for (Observer o : observers) 
            o.update(this, this);   
    }
    
    public int getHealthPoints() {
        return this.healthPoints;
    }

    public void setLives(int lives) {
        this.lives = lives;
        for (Observer o : observers) {
            o.update(this, this);
        }
    }

    public int getLives() {
        return this.lives;
    }

    public void setMaxHealthPoints(int maxHealthPoints) {
        this.maxHealthPoints = maxHealthPoints;
    }
    
    public int getMaxHealthPoints() {
        return this.maxHealthPoints;
    }

}
