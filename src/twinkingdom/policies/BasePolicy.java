/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.policies;

import twinkingdom.GameHandler;
import twinkingdom.entities.mobs.Creature;
import twinkingdom.entities.mobs.states.MovementState;

/**
 *
 * @author mario
 */
public abstract class BasePolicy {
    
    protected boolean isAttacking=false;
    protected Creature c;
    protected GameHandler handler;

    protected BasePolicy() {
        handler = GameHandler.instance;
    }
    
    public void getMovement(){
        //if(!isAttacking){

            if(lowerBound() || upperBound()){
                //attack();
                changeState();
       
                //attack();
            }else
            if(tileCollision()){
                changeState();
                c.move();
            }
           getAction();

       // }else{ 
            //if(isAttacking){

           // }
        //}
    
    }
    
    public abstract void getAction();
    
    public abstract void attack();
    
    public abstract boolean lowerBound();
    
    public abstract boolean upperBound();
    
    public abstract boolean tileCollision();
    
    public abstract void changeState();
    
}
