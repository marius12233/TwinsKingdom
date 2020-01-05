/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.game;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author bened
 */
public class GameSettings extends Observable {
    private char upKey, leftKey, rightKey, downKey;
    private boolean audioEnabled;
    private List<Observer> observers;

    public GameSettings() {
        this.upKey = 'W';
        this.downKey = 'S';
        this.leftKey = 'A';
        this.rightKey = 'D';
        this.audioEnabled = true;
        observers = new LinkedList<>();
    }

    public char getUpKey() {
        return upKey;
    }

    public void setUpKey(char upKey) {
        this.upKey = upKey;
        notifyObservers();
    }

    public char getLeftKey() {
        return leftKey;
    }

    public void setLeftKey(char leftKey) {
        this.leftKey = leftKey;
        notifyObservers();
    }

    public char getRightKey() {
        return rightKey;
    }

    public void setRightKey(char rightKey) {
        this.rightKey = rightKey;
        notifyObservers();
    }

    public char getDownKey() {
        return downKey;
    }

    public void setDownKey(char downKey) {
        this.downKey = downKey;
        notifyObservers();

    }

    public boolean isAudioEnabled() {
        return audioEnabled;
    }

    public void setAudioEnabled(boolean audioEnabled) {
        this.audioEnabled = audioEnabled;
        notifyObservers();
    }
    
    @Override
    public void addObserver(Observer o) {
        if(o == null)
            return;
        observers.add(o);
        o.update(this, this);
    }
    
    @Override
    public void notifyObservers() {
        for(Observer o: observers)
            o.update(this, this);
    }
    
    
    
}
