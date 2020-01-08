/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.mobs.enemies.level2;

import java.awt.Graphics;
import  twinskingdom.entities.mobs.enemies.Enemy;
import  twinskingdom.gfx.CrowAssets;

/**
 *
 */
public class Crow extends Enemy {

    public Crow(float x, float y, int width, int height, CrowAssets entityAssets) {
        super(x, y, width, height, entityAssets);
        bounds.x = 1;
        bounds.y = 1;
        bounds.width = 25;
        bounds.height = 25;
        setState(leftState);
        health.setHealthPoints(4);
        health.setLives(1);
        speed = 5;
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
