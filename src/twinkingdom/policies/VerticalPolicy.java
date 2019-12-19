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
public class VerticalPolicy extends BasePolicy{
    protected MovementState downState;
    protected MovementState upState;
    protected int startY;
    protected int endY;
    
    public VerticalPolicy(Creature c, int startY, int endY){
        super();
        this.c=c;
        this.downState=c.getDownState();
        this.upState=c.getUpState();
        this.startY=startY;
        this.endY=endY;
        c.setState(downState);
    }


    @Override
    public void getAction() {
        c.getState().move();
    }

    @Override
    public boolean lowerBound() {
        return c.getY()<=startY;
    }

    @Override
    public boolean upperBound() {
        return c.getY()>=endY;
    }

    @Override
    public boolean tileCollision() {
        return c.isCollidingWithTile();
    }

    @Override
    public void changeState() {
        if(c.getState().equals(upState))
            c.setState(downState);
        else if(c.getState().equals(downState))
            c.setState(upState);
    }

    @Override
    public void attack() {
        return;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }
    
    
}
