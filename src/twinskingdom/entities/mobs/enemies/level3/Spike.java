/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.mobs.enemies.level3;

import java.awt.Graphics;
import  twinskingdom.entities.Entity;
import  twinskingdom.entities.mobs.Creature;
import  twinskingdom.gfx.SpikeAssets;
import  twinskingdom.utils.UtilityTimer;
import  twinskingdom.worlds.World;

/**
 * This class implements one of the third level enemy items: the spike. It is
 * extended by the other spike classes, representing its possible configurations.
 * The spikes movements depend on the increasing/decreasing of the height, in case
 * of vertical spikes, and of the width (for horizontal spikes).
 */
public class Spike extends Creature {

    protected UtilityTimer utilityTimer;
    private World w;
    protected boolean in = false; //this attribute represents the verse of the spike

    /**
     * The constructor is used to set the assets and to initialize the timer.
     * @param x is the horizontal position
     * @param y is the vertical position
     * @param spikeAssets represents the class assets
     */
    public Spike(float x, float y, SpikeAssets spikeAssets) {
        super(x, y, 23, 23, spikeAssets);
        //setState(downState); 
        spikeAssets.init();

        utilityTimer = new UtilityTimer(1000);

    }

    
    /**
     * The tick method switches the verse of the spike spill. 
     */
    @Override
    //Deve fare l'update dello stato dell'oggetto
    public void tick() {

        if (utilityTimer.isTimeOver()) {

            switchVerse();
        }

    }

    @Override
    public void die() {
        return;
    }

    @Override
    public void render(Graphics g) {
        //g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        //state.render(g);
    }

    /**
     * This method provides to set the default value for the player damage caused 
     * by the spike touch.
     * @param e is the entity involved in the damage
     */
    @Override
    public void touchEntity(Entity e) {
        e.hurt(1);
        // isActive=false;
    }

    /**
     * This class provides to switch the in value, which manages the spike spill.
     */
    public void switchVerse() {
        if (in) {
            in = false;
        } else {
            in = true;
        }

    }

}
