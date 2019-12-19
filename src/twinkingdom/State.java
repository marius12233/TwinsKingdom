/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom;

import java.awt.Graphics;

/**
 *
 * @author mario
 */
public abstract class State {
    
    private static State currentState=null;
    protected GameHandler handler;
    public static void setState(State state){
        currentState=state;
    }
    
    public static State getState(){
        return currentState;
    }
        
    public State(GameHandler handler){
        this.handler = handler;
    }
    
    public abstract void tick();
    public abstract void render(Graphics g);
}
