/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import javax.swing.event.EventListenerList;
import twinkingdom.events.GameEvent;
import twinkingdom.events.GameEventListener;
import twinkingdom.events.GameEventType;

/**
 *
 * @author mario
 */
public class KeyManager implements KeyListener{
    
    private boolean[] keys;
    public boolean up, down, left, right, attack, swordSelected, bowSelected, spellSelected;
    private int move=0;
    private LinkedList<Integer> stack;
    
    protected EventListenerList listenerList = new EventListenerList();

   
    
    public KeyManager(){
        keys = new boolean[500];
        stack=new LinkedList();
    }
    
    //Mette a true la direzione che è stata premuta a ogni tick
    public void tick(){
        //queue.clear();
        //up = keys[KeyEvent.VK_UP];
        //left = keys[KeyEvent.VK_LEFT];
        //right = keys[KeyEvent.VK_RIGHT];
        //down = keys[KeyEvent.VK_DOWN];
        //attack = keys[KeyEvent.VK_SPACE];
        up = (getNext() == KeyEvent.VK_UP);
        left = (getNext() == KeyEvent.VK_LEFT);
        right = (getNext() == KeyEvent.VK_RIGHT);
        down = (getNext() == KeyEvent.VK_DOWN);
        attack = (getNext() == KeyEvent.VK_SPACE);
        swordSelected = (getNext() == KeyEvent.VK_1);
        bowSelected = (getNext() == KeyEvent.VK_2);
        spellSelected = (getNext() == KeyEvent.VK_3);
        
        if(swordSelected) {
            launchGameEvent(new GameEvent(this, GameEventType.WEAPON_SELECTED_SWORD));
        } else if(bowSelected) {
            launchGameEvent(new GameEvent(this, GameEventType.WEAPON_SELECTED_BOW));
        } else if(spellSelected) {
            launchGameEvent(new GameEvent(this, GameEventType.WEAPON_SELECTED_SPELL));
        }

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
        if (isPressing()){return stack.getFirst();}
        else return 0;
        
    }
    
    public boolean isPressing(){
        return stack.size()>0;
    }
    
}
