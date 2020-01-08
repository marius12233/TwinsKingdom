/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.statics;

import  twinskingdom.entities.mobs.Launchable;
import  twinskingdom.gfx.LeafAssets;

/**
 *
 *  
 */
public class EnchantedLeaf extends Launchable {

    public EnchantedLeaf(float x, float y, int width, int height) {
        super(x, y, width, height, new LeafAssets());
        bounds.x = 0;
        bounds.y = 1;
        bounds.width = 24;
        bounds.height = 15;

        speed = 10;

        setState(rightState);
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

}
