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
public class SpikeDown extends Spike {

    public SpikeDown(float x, float y, SpikeAssets spikeAssets) {
        super(x, y, spikeAssets);

        bounds.x = -25;
        bounds.y = 0;
        bounds.width = 50;
        bounds.height = 30;
        setState(downState);

    }

    @Override
    //Deve fare l'update dello stato dell'oggetto
    public void tick() {

        if (in) {
            enter();
        } else {
            exit();
        }

        super.tick();
    }

    @Override
    public void render(Graphics g) {
        //g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        state.render(g);
    }

    public void enter() {
        if (height >= 0) {
            height--;
        }

        checkAttacks();
        bounds.height--;

    }

    public void exit() {
        height++;

        checkAttacks();
        bounds.height++;

    }

}
