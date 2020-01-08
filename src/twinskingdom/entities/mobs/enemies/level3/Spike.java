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
 *
 *  
 */
public class Spike extends Creature {

    protected UtilityTimer utilityTimer;
    private World w;
    protected boolean in = false; //this attribute represents the verse of the spike

    public Spike(float x, float y, SpikeAssets spikeAssets) {
        super(x, y, 23, 23, spikeAssets);
        //setState(downState); 
        spikeAssets.init();

        utilityTimer = new UtilityTimer(1000);

    }

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

    @Override
    public void touchEntity(Entity e) {
        e.hurt(1);
        // isActive=false;
    }

    public void switchVerse() {
        if (in) {
            in = false;
        } else {
            in = true;
        }

    }

}
