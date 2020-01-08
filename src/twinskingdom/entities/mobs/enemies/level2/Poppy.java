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
 *
 */
public class Poppy extends Enemy {

    public Poppy(float x, float y, int width, int height, PoppyAssets entityAssets) {
        super(x, y, width, height, entityAssets);
        bounds.x = 2;
        bounds.y = 2;
        bounds.width = 35;
        bounds.height = 35;
        //setState(state);
        health.setHealthPoints(5);
        health.setLives(1);
        speed = 2;
        this.setDamageAttack(2);
    }

    @Override
    public void tick() {
        state.tick();
        getMovement();
    }

    @Override
    public void die() {
    }

    @Override
    public void render(Graphics g) {
        state.render(g);
    }
}
