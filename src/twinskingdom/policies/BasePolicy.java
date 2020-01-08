/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.policies;

import  twinskingdom.game.GameHandler;
import  twinskingdom.entities.mobs.Creature;

/**
 *
 *  
 */
public abstract class BasePolicy {
    
    protected boolean isAttacking=false;
    protected Creature c;
    protected GameHandler handler;

    protected BasePolicy() {
        handler = GameHandler.instance;
    }
    
    public void getMovement(){

            if(lowerBound() || upperBound()){
                changeState();
       
            }else
            if(tileCollision()){
                changeState();
                c.move();
            }
           getAction();
    
    }
    
    public abstract void getAction();
    
    public abstract void attack();
    
    public abstract boolean lowerBound();
    
    public abstract boolean upperBound();
    
    public abstract boolean tileCollision();
    
    public abstract void changeState();
    
}
