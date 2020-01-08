/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.statics.grabbable;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import  twinskingdom.entities.Entity;
import  twinskingdom.gfx.Animation;
import  twinskingdom.gfx.Assets;

/**
 *
 *  
 */
public class GrabbableHealthPotion extends GrabbableStaticEntity {

    private Animation animation;
    private final int bonus = 3;

    public GrabbableHealthPotion(float x, float y, int width, int height) {
        super(x, y, width, height);
        bounds.x = 1;
        bounds.y = 1;
        bounds.width = 15;
        bounds.height = 22;
        animation = new Animation(50, Assets.healthPotion);
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
        if (e.isPlayer()) {
            e.setHealthPoints(e.getHealthPoints() + bonus);
            if (e.getHealthPoints() > e.getMaxHealthPoints()) {
                e.setHealthPoints(e.getMaxHealthPoints());
            }
        }
        super.touchEntity(e);

    }

}
