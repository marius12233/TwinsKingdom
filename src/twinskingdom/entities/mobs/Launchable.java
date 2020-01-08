/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.mobs;

import java.awt.Graphics;
import  twinskingdom.entities.Entity;
import  twinskingdom.gfx.EntityAssets;

/**
 *
 */
public abstract class Launchable extends Movable {

    public Launchable(float x, float y, int width, int height, EntityAssets entityAssets) {
        super(x, y, width, height, entityAssets);
    }

    @Override

    public void tick() {
        state.tick();
        state.move();
        move();
        if (isCollidingWithTile()) {
            isActive = false;
        }

    }
    // animation.tick();

    @Override
    public void render(Graphics g) {
        state.render(g);
    }

    @Override
    public void touchEntity(Entity e) {
        e.hurt(1);
        isActive = false;
    }

}
