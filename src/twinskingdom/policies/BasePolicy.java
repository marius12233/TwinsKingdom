/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.policies;

import  twinskingdom.game.GameHandler;
import  twinskingdom.entities.mobs.Creature;

/**
 * This class implements the concept of policy.
 * A policy defines the learning agent's way of behaving at a given time. 
 * Roughly speaking, a policy is a mapping from perceived states of the environment to actions to be taken when in those states.
 * To implement the policy class we use the design pattern State. In addition to this, we also use the Template Method design pattern because, 
 * since the class must return the movement,
 * and therefore the action to be performed given a state, the behavior of each policy is the same as the basic behavior.
 *  
 */
public abstract class BasePolicy {
    
    protected boolean isAttacking=false;
    protected Creature c;
    protected GameHandler handler;

    protected BasePolicy() {
        handler = GameHandler.instance;
    }
    
    /***
     * This method defines the action to be taken by the agent according to the "state" in which he is. 
     * We use hook methods to have horizontal and vertical limits returned to us according to the policy.
     * Other hook methods are used to change the state of the agent and retrieve the action to perform.
     * All the policies accepts a lower bound and an upper bound. 
     * When the entity using this class arrives at the coordinates indicated as bound, 
     * or is involved in a collision, it changes its state to the opposite direction.
     * 
     */
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
    
    /***
     * Retrieves the action to performed by the agent who uses the policy
     */
    public abstract void getAction();
    
    public abstract void attack();
    
    public abstract boolean lowerBound();
    
    public abstract boolean upperBound();
    
    public abstract boolean tileCollision();
    
    public abstract void changeState();
    
}
