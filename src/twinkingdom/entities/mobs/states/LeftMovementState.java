/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.mobs.states;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Map;
import twinkingdom.entities.mobs.Creature;
import twinkingdom.gfx.Animation;
import twinkingdom.gfx.EntityAssets;

/**
 * The state of the entity moving in the left position.
 * 
 * @author mario
 */
public class LeftMovementState extends TemplateMovementState{
    
    public LeftMovementState(Creature creature, EntityAssets asset){
        this.asset=asset;
        this.creature=creature;
        Map<String, BufferedImage[]> as = asset.getAnimations().get("left");
        animationStable = new Animation(50, as.get("stable"));
        animationRun = new Animation(50, as.get("run"));
        animationAttack = new Animation(50, as.get("attack"));
        animation=animationStable;
    }

    @Override
    public void setMove() {
        creature.setxMove(-creature.getSpeed());
        creature.setyMove(0);
    }

    @Override
    public void setSize(Rectangle ar, Rectangle cb) {
        ar.x = cb.x - ar.width;
        ar.y = cb.y + cb.height/2 - ar.height/2;
    }
    
}
