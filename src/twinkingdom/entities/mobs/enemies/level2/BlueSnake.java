/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.mobs.enemies.level2;

import java.awt.Graphics;
import twinkingdom.entities.mobs.enemies.Enemy;
import twinkingdom.gfx.BlueSnakeAssets;
/**
 *
 * @author Alex1
 */
public class BlueSnake extends Enemy {
        
    public BlueSnake( float x, float y, int width, int height, BlueSnakeAssets entityAssets) {
        super( x, y, width, height, entityAssets);
        bounds.x = 1;
        bounds.y = 1;
        bounds.width = 25;
        bounds.height = 25;
        setState(rightState);
        health.setHealthPoints(2);
        health.setLives(1);
        speed=1;
        this.setDamageAttack(2);
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
