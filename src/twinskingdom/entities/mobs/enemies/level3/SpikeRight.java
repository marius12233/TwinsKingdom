/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.mobs.enemies.level3;

import java.awt.Graphics;
import  twinskingdom.gfx.SpikeAssets;

/**
 *
 *  
 */
public class SpikeRight extends Spike {

    /**
     * The constructor is used to set the spike state and the horizontal/vertical
     * bounds for the collisions.
     * @param x is the horizontal position
     * @param y is the vertical position
     * @param spikeAssets represents the class assets
     */
    public SpikeRight(float x, float y, SpikeAssets spikeAssets) {
        super(x, y, spikeAssets);

        bounds.x = -25;
        bounds.y = 0;
        bounds.width = 50;
        bounds.height = 30;
        setState(rightState);

    }

    /**
     * The tick method provides to call the enter/exit methods, according to 
     * in value.
     */
    @Override
    public void tick() {

        if (in) {
            enter();
        } else {
            exit();
        }

        super.tick();
    }
    
    /**
     * The render method provides to call the state render method. 
     * @param g represents the graphics
     */
    @Override
    public void render(Graphics g) {
        //g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        state.render(g);
    }
    
    /**
     * The enter method provides to decrese the spike width and its horizontal
     * collisions bounds.
     */
    public void enter() {
        if (width >= 0) {
            width--;
        }

        checkAttacks();
        bounds.width--;

    }
    
    /**
     * The exit method provides to increase the spike width and to increase its 
     * horizontal bounds.
     */
    public void exit() {

        width++;

        checkAttacks();
        bounds.width++;

    }
}
