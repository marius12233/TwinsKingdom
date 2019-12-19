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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bened
 */
public class Mana extends Observable {

    private int mana;
    private int maxMana;
    private boolean enabled;
    private int manaPerSecond;
    private List<Observer> observers;
    private Thread t;

    public Mana() {
        this.maxMana = 120;
        this.mana = 120;
        this.enabled = false;
        this.manaPerSecond = 5;
        this.observers = new LinkedList<>();
        t = new Thread(new ManaTimer(this));
        t.start();
    }

    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
        o.update(this, this);
    }

    public void setMana(int mana) {
        
        this.mana = Math.max(0, Math.min(mana, this.maxMana));
        for (Observer o : observers) 
            o.update(this, this);   
    }
    
    public int getMana() {
        return this.mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }


    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    public boolean isEnabled() {
        return enabled;
    }

    class ManaTimer implements Runnable {

        private Mana mana;
        private boolean running = true;
        private ManaTimer(Mana mana) {
            this.mana = mana;
        }

        @Override
        public void run() {
            while(running) {
                try {
                Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Mana.class.getName()).log(Level.SEVERE, null, ex);
                }
                mana.setMana(mana.mana + manaPerSecond);
            }
            
        }
    }


}
