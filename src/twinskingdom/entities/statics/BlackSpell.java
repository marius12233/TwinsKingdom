/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.statics;

import java.awt.Graphics;
import  twinskingdom.entities.Entity;
import  twinskingdom.entities.mobs.Creature;
import  twinskingdom.gfx.BlackSpellAssets;

/**
 *
 *  
 */
public class BlackSpell extends Creature {

    public BlackSpell(float x, float y, int width, int height) {
        super(x, y, width, height, new BlackSpellAssets());
        bounds.x = 0;
        bounds.y = 1;
        bounds.width = 24;
        bounds.height = 15;
        setState(rightState);
        speed = speed * 2;
        //animation = new Animation(500, Assets.enchantedLeaf);
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

    @Override
    public void die() {
    }

}
