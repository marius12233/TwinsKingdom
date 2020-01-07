/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.mobs.enemies.level2;

import java.awt.Graphics;
import twinkingdom.entities.Entity;
import twinkingdom.entities.mobs.Creature;
import twinkingdom.entities.mobs.Launchable;
import twinkingdom.entities.mobs.Movable;
import twinkingdom.gfx.FireBallAssets;

/**
 *
 * @author Alex1
 */
public class FireBall extends Launchable {
    
    
    public FireBall(float x, float y, int width, int height){
        super(x, y, width, height, new FireBallAssets());
        bounds.x = 0;
        bounds.y = 1;
        bounds.width= 24;
        bounds.height= 15;
        setState(rightState);
        speed+=speed*2;
        //animation = new Animation(500, Assets.enchantedLeaf);
    }
}
