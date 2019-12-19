/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.mobs.enemies.level3;


import java.awt.Graphics;
import twinkingdom.GameHandler;
import twinkingdom.entities.mobs.enemies.Enemy;
import twinkingdom.entities.mobs.states.MovementState;
import twinkingdom.gfx.GhostAssets;
import twinkingdom.utils.UtilityTimer;
/**
 *
 * @author Alex1
 */
public class Ghost extends Enemy {

    private UtilityTimer timer;
    protected boolean isVisible = false;
    protected boolean isSolid = false;
    private int counter=0;
    private float[] xpos;
    private float[] ypos;

    public Ghost(float x, float y, int width, int height, GhostAssets entityAssets, float[] xposition, float[] yposition) {
        super(x, y, width, height, entityAssets);
        bounds.x = 1;
        bounds.y = 1;
        bounds.width = 48;
        bounds.height = 48;
        //health = 2;
        setState(leftState);
        health.setHealthPoints(1);
        health.setLives(1);
        speed = 4;
        isVisible = true;
        isSolid = true;
        this.timer = new UtilityTimer(2000);
        this.xpos=xposition;
        this.ypos=yposition;

        //maxHealth=health.getHealthPoints();
        //setAttackCooldown(3000);
        //timer = new UtilityTimer(2000);
    }

    @Override
    //Deve fare l'update dello stato dell'oggetto
    public void tick() {
        //Animations
        //Per update the index
        state.tick();
        //Movement
        getMovement();
        move();
        //System.out.println("mi muovo...");

        if (timer.isTimeOver()) {
            this.setX(xpos[counter]);
            this.setY(ypos[counter]);
            counter=(counter+1)%xpos.length;
            //System.out.println("cambio1");
            
            //System.out.println("nuovo timer");
        }
        
    }

    @Override
    public void die() {
    }

    /*Override
    public void getMovement() {
        MovementState statePolicy;
        checkAttacks();
        /*if((statePolicy = movementPolicy.getMovement())!=null)
            setState(statePolicy);
        movementPolicy.getAction();*/
    //movementPolicy.getMovement();
    //state.attack();
    //}
    @Override
    public void render(Graphics g) {
        state.render(g);
    }

    /*public boolean isSolid() {
        if(isVisible())
            isSolid=true;
        else
            isSolid=false;
        
        return isSolid;
    }*/
    public boolean isVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

}
