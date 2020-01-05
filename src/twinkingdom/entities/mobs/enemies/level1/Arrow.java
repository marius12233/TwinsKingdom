/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.mobs.enemies.level1;

import java.awt.Graphics;
import twinkingdom.GameHandler;
import twinkingdom.entities.mobs.Creature;
import twinkingdom.entities.Entity;
import twinkingdom.entities.mobs.Launchable;
import twinkingdom.entities.mobs.Movable;
import twinkingdom.gfx.ArrowAssets;

/**
 *
 * @author Antonia
 */
public class Arrow extends Launchable{
    
   
    
    public Arrow(float x, float y, int width, int height){
        super(x, y, width, height, new ArrowAssets());
        bounds.x = 0;
        bounds.y = 1;
        bounds.width= 24;
        bounds.height= 15;
        setState(rightState);
        speed=speed*2;
        //animation = new Animation(500, Assets.enchantedLeaf);
    }
   
}
