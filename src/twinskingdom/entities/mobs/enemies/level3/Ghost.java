/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.mobs.enemies.level3;

import java.awt.Graphics;
import  twinskingdom.entities.mobs.enemies.Enemy;
import  twinskingdom.gfx.GhostAssets;
import  twinskingdom.utils.UtilityTimer;

/**
 * This class implements one of the third level enemies: the ghost. It is managed
 * differently from the other creatures because its movements are setted through
 * a positions vector.
 */
public class Ghost extends Enemy {

    private UtilityTimer timer;
    //the following attribute is used to manage the ghost visibility
    protected boolean isVisible = false; 
    protected boolean isSolid = false;
    private int counter = 0;
    private float[] xpos; //horizontal positions' vector
    private float[] ypos; //vertical positions' vector

    /**
     * The constructor provides to set the healthpoints values, the horizontal/
     * vertical bounds for the collisions management and the damage attack.
     * A timer is initialized to change correctly the position of the creature,
     * throught the input vectors. 
     * @param x horizontal position
     * @param y vertical position
     * @param xposition vector for the horizional positions' setting
     * @param yposition vector for the vertical positions' setting
     * @param entityAssets character asset
     */
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
        speed = 4; //speed setting
        isVisible = true;
        isSolid = true;
        this.timer = new UtilityTimer(2000);
        this.xpos = xposition;
        this.ypos = yposition;

        //maxHealth=health.getHealthPoints();
        //setAttackCooldown(3000);
        //timer = new UtilityTimer(2000);
    }

    /**
     * The tick method only provides to recall the tick method of the setted state
     * and to manage the character movements. These operations are done accordingly
     * to the positions setted in the vectors.
     */
    @Override
    public void tick() {
        //Animations
        //Per update the index
        state.tick();
        //Movement
        getMovement();
        move();

        if (timer.isTimeOver()) {
            this.setX(xpos[counter]);
            this.setY(ypos[counter]);
            counter = (counter + 1) % xpos.length;
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
    /**
     * The render method provides only to recall the render method of the state.
     * @param g represents the graphics
     */
    @Override
    public void render(Graphics g) {
        state.render(g);
    }

    
    public boolean isVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

}
