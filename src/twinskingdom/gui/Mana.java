/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.gui;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class contains the descrition of the mana points of the player,
 * constantly monitoring and updating them during the game.
 */
public class Mana extends Observable {

    private int mana;
    private int maxMana;
    private boolean enabled;
    private int manaPerSecond;
    private List<Observer> observers;
    private Thread t;

    /**
     * Creates a Mana object and starts its timer.
     */
    public Mana() {
        this.maxMana = 120;
        this.mana = 0;
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

    public void removeObserver(Observer o) {
        this.observers.remove(o);
    }

    public void setMana(int mana) {
        if (!enabled) {
            return;
        }

        this.mana = Math.max(0, Math.min(mana, this.maxMana));
        for (Observer o : observers) {
            o.update(this, this);
        }
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

    /**
     * Inner class used to implement a timer for mana regeneration.
     */
    class ManaTimer implements Runnable {

        private Mana mana;
        private boolean running = true;

        private ManaTimer(Mana mana) {
            this.mana = mana;
        }

        /**
         * Starts a thread that increases mana each second.
         */
        @Override
        public void run() {
            while (running) {
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
