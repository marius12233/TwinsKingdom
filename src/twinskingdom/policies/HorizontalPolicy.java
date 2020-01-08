/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.policies;

import  twinskingdom.entities.mobs.Creature;
import  twinskingdom.entities.mobs.states.MovementState;

/**
 *
 *  
 */
public class HorizontalPolicy extends BasePolicy{
    protected MovementState leftState;
    protected MovementState rightState;
    protected int startX;
    protected int endX;
    
    public HorizontalPolicy(Creature c, int startX, int endX){
        super();
        this.c=c;
        this.leftState=c.getLeftState();
        this.rightState=c.getRightState();
        this.startX=startX;
        this.endX=endX;
        c.setState(leftState);
    }
    @Override
    public void getAction() {
        c.getState().move();
    }

    @Override
    public boolean lowerBound() {
        return c.getX()<=startX;
    }

    @Override
    public boolean upperBound() {
        return c.getX()>=endX;
    }

    @Override
    public boolean tileCollision() {
        return c.isCollidingWithTile();
    }

    @Override
    public void changeState() {
        if(c.getState().equals(leftState)){
            c.setState(rightState);
        }
        else if(c.getState().equals(rightState)){
            c.setState(leftState);
        }
            
        //getAction();
    }    

    @Override
    public void attack() {
        return;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

}
