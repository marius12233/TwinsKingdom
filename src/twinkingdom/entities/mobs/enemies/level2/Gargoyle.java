/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.mobs.enemies.level2;

import java.awt.Graphics;
import twinkingdom.entities.mobs.enemies.Enemy;
import twinkingdom.gfx.GargoyleAssets;
/**
 *
 * @author Amedeo
 */
public class Gargoyle extends Enemy {
        
    public Gargoyle( float x, float y, int width, int height, GargoyleAssets entityAssets){
        super( x, y, width, height, entityAssets);
        bounds.x = 2;
        bounds.y = 2;
        bounds.width = 35;
        bounds.height = 35;
        setState(downState);
        health.setHealthPoints(5);
        health.setLives(1);
        speed=3;
        this.setDamageAttack(3);
    }
    
    @Override
    public void tick() {

        state.tick();
        getMovement();
        move();
    }

    @Override
    public void die() {
    }

    @Override
    public void render(Graphics g) {
        state.render(g);
    }
}
