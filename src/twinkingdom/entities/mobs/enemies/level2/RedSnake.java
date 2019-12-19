/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.mobs.enemies.level2;

import java.awt.Graphics;
import twinkingdom.entities.mobs.enemies.Enemy;
import twinkingdom.gfx.RedSnakeAssets;
/**
 *
 * @author Amedeo
 */
public class RedSnake extends Enemy {
        
    public RedSnake( float x, float y, int width, int height, RedSnakeAssets entityAssets) {
        super( x, y, width, height, entityAssets);
        bounds.x = 1;
        bounds.y = 1;
        bounds.width = 25;
        bounds.height = 25;
        setState(downState);
        health.setHealthPoints(3);
        health.setLives(1);
        speed=3;
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
