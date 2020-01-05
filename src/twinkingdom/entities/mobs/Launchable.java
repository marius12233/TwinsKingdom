/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.mobs;

import java.awt.Graphics;
import twinkingdom.entities.Entity;
import twinkingdom.gfx.EntityAssets;

/**
 *
 * @author mario
 */
public abstract class Launchable extends Movable{

    public Launchable(float x, float y, int width, int height, EntityAssets entityAssets) {
        super(x, y, width, height, entityAssets);
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
       // animation.tick();
               

    @Override
    public void render(Graphics g) {
       // System.out.println("LA X DELLA FOGLIA è: "+x);
       // System.out.println("L'OFFSET è: "+handler.getGameCamera().getxOffset());
        state.render(g);
        //g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }
    
    @Override
    public void touchEntity(Entity e){
        e.hurt(1);
        isActive=false;
    }

    
}
