/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.mobs.enemies.level2;

import java.awt.Graphics;
import  twinskingdom.entities.mobs.enemies.Enemy;
import  twinskingdom.gfx.PoppyAssets;

/**
 * This class implements one of the second level enemies: the poppy. 
 */
public class Poppy extends Enemy {

     /**
     * The constructor provides to set the healthpoints values, the horizontal/
     * vertical bounds for the collisions management and the damage attack.
     * @param x horizontal position
     * @param y vertical position
     * @param entityAssets character asset
     */
    public Poppy(float x, float y, int width, int height, PoppyAssets entityAssets) {
        super(x, y, width, height, entityAssets);
        bounds.x = 2;
        bounds.y = 2;
        bounds.width = 35;
        bounds.height = 35;
        //setState(state);
        health.setHealthPoints(5);
        health.setLives(1);
        speed = 2; //speed setting
        this.setDamageAttack(2);
    }

    /**
     * The tick method only provides to recall the tick method of the setted state
     * and to manage the character movements.
     */
    @Override
    public void tick() {
        state.tick();
        getMovement();
    }

    @Override
    public void die() {
    }

    /**
     * The render method provides only to recall the render method of the state.
     * @param g represents the graphics
     */
    @Override
    public void render(Graphics g) {
        state.render(g);
    }
}
