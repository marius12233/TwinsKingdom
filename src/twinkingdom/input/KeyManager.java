/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.event.EventListenerList;
import twinkingdom.events.GameEvent;
import twinkingdom.events.GameEventListener;
import twinkingdom.events.GameEventType;
import twinkingdom.game.GameSettings;

/**
 *
 * @author mario
 */
public class KeyManager implements KeyListener, Observer {
    
    private boolean[] keys;
    public boolean up, down, left, right, attack, swordSelected, bowSelected, spellSelected, pauseSelected;
    private char upKey = 'W', downKey = 'S', leftKey = 'A', rightKey = 'D', swordSelectedKey = '1', bowSelectedKey = '2', spellSelectedKey = '3';
    private final char pauseKey = 'P';
    private int move=0;
    private LinkedList<Integer> stack;
    
    protected EventListenerList listenerList = new EventListenerList();

   
    
    public KeyManager(){
        keys = new boolean[500];
        stack=new LinkedList();
    }
    
    //Mette a true la direzione che è stata premuta a ogni tick
    public void tick(){
        
        up = (getNext() == KeyEvent.getExtendedKeyCodeForChar(upKey));
        left = (getNext() == KeyEvent.getExtendedKeyCodeForChar(leftKey));
        right = (getNext() == KeyEvent.getExtendedKeyCodeForChar(rightKey));
        down = (getNext() == KeyEvent.getExtendedKeyCodeForChar(downKey));
        attack = (getNext() == KeyEvent.VK_SPACE);
        swordSelected = (getNext() == KeyEvent.getExtendedKeyCodeForChar(swordSelectedKey));
        bowSelected = (getNext() == KeyEvent.getExtendedKeyCodeForChar(bowSelectedKey));
        spellSelected = (getNext() == KeyEvent.getExtendedKeyCodeForChar(spellSelectedKey));
        
        pauseSelected = (getNext() == KeyEvent.getExtendedKeyCodeForChar(pauseKey));
        
        if(swordSelected) {
            launchGameEvent(new GameEvent(this, GameEventType.WEAPON_SELECTED_SWORD));
        } else if(bowSelected) {
            launchGameEvent(new GameEvent(this, GameEventType.WEAPON_SELECTED_BOW));
        } else if(spellSelected) {
            launchGameEvent(new GameEvent(this, GameEventType.WEAPON_SELECTED_SPELL));
        }
        
        if (pauseSelected) {
            keys[(int) pauseKey] = false;
            stack.removeFirstOccurrence((int) pauseKey);
            launchGameEvent(new GameEvent(this, GameEventType.GAME_PAUSE));
        }
        
        pauseSelected = false;

    }
    
    public void addGameEventListener(GameEventListener listener) {
        listenerList.add(GameEventListener.class, listener);
    }

    public void removeGameEventListener(GameEventListener listener) {
        listenerList.remove(GameEventListener.class, listener);
    }

    void launchGameEvent(GameEvent evt) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i = i + 2) {
            if (listeners[i] == GameEventListener.class) {
                ((GameEventListener) listeners[i + 1]).onGameEventActionPerformed(evt);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //keys = new boolean[256];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        if(!stack.contains(e.getKeyCode())){
            stack.addFirst(e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
        stack.removeFirstOccurrence(e.getKeyCode());
    }
    
    public int getNext(){
        if (isPressing())
            return stack.getFirst();
        
        return 0;
        
    }
    
    public boolean isPressing(){
        return stack.size()>0;
    }

    @Override
    public void update(Observable o, Object arg) {
        GameSettings settings = (GameSettings) o;
        upKey = Character.toUpperCase(settings.getUpKey());
        downKey = Character.toUpperCase(settings.getDownKey());
        leftKey = Character.toUpperCase(settings.getLeftKey());
        rightKey = Character.toUpperCase(settings.getRightKey());
    }
    
}
