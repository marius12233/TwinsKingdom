/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.mobs.enemies.level2;

import java.awt.Graphics;
import twinkingdom.GameHandler;
import twinkingdom.entities.mobs.enemies.Enemy;
import twinkingdom.gfx.CrowAssets;
/**
 *
 * @author Alex1
 */
public class Crow extends Enemy {
        
    public Crow( float x, float y, int width, int height, CrowAssets entityAssets) {
        super( x, y, width, height, entityAssets);
        bounds.x = 1;
        bounds.y = 1;
        bounds.width = 25;
        bounds.height = 25;
        //health = 2;
        setState(leftState);
        health.setHealthPoints(1);
        health.setLives(1);
        speed=5;
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
