/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.statics.grabbable;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Observer;
import  twinskingdom.entities.Entity;
import  twinskingdom.gfx.Animation;
import  twinskingdom.gfx.Assets;
import twinskingdom.sounds.effects.SoundEffectManager;

/**
 *
 *  
 */
public class GrabbableStar extends GrabbableStaticEntity {

    private Animation animation;

    public GrabbableStar(float x, float y, int width, int height) {
        super(x, y, width, height);
        bounds.x = 1;
        bounds.y = 1;
        bounds.width = 20;
        bounds.height = 20;
        animation = new Animation(50, Assets.star);

    }

    @Override
    public void tick() {
        animation.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    public BufferedImage getCurrentAnimationFrame() {
        return animation.getCurrentFrame();
    }

    @Override
    public void touchEntity(Entity e) {
        //  setChanged();
        //  notifyObservers();

        for (Observer o : observers) {
            o.update(this, this);
        }
        super.touchEntity(e);
        SoundEffectManager.STAR.play();
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

}
