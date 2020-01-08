/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.mobs.enemies.level1;

import  twinskingdom.entities.mobs.Launchable;
import  twinskingdom.gfx.ArrowAssets;

/**
 * This class provides to implement the archer boss weapon.
 */
public class Arrow extends Launchable {

    /**
     * The constructor sets the class state and the horizontal/vertical bounds
     * used for the collisions' management.
     * @param x
     * @param y
     * @param width
     * @param height 
     */
    public Arrow(float x, float y, int width, int height) {
        super(x, y, width, height, new ArrowAssets());
        bounds.x = 0;
        bounds.y = 1;
        bounds.width = 24;
        bounds.height = 15;
        setState(rightState);
        speed = speed * 2; //speed setting
        //animation = new Animation(500, Assets.enchantedLeaf);
    }

}
