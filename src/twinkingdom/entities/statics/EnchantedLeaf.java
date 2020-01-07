/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.statics;

import java.awt.Graphics;
import twinkingdom.entities.mobs.Creature;
import twinkingdom.entities.Entity;
import twinkingdom.entities.mobs.Launchable;
import twinkingdom.entities.mobs.Movable;
import twinkingdom.gfx.LeafAssets;

/**
 *
 * @author Antonia
 */
public class EnchantedLeaf extends Launchable{
    
   
    
    public EnchantedLeaf(float x, float y, int width, int height){
        super(x, y, width, height, new LeafAssets());
        bounds.x = 0;
        bounds.y = 1;
        bounds.width= 24;
        bounds.height= 15;

        
        
        speed=10;


        setState(rightState);
        //animation = new Animation(500, Assets.enchantedLeaf);
    }

    
    @Override
    
    public void tick() {
        state.tick();
        state.move();
        move();
        if(isCollidingWithTile()){
            isActive=false;
        }
        
    }

}