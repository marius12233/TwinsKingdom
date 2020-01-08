/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.statics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import  twinskingdom.entities.Entity;
import  twinskingdom.gfx.Animation;

/**
 *
 *  
 */
public abstract class StaticEntity extends Entity {

    protected Animation animation;

    public StaticEntity(float x, float y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void hurt(int amt) {
        return;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    public BufferedImage getCurrentAnimationFrame() {
        return animation.getCurrentFrame();
    }

}
