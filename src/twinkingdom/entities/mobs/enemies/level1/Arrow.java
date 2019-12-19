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
import twinkingdom.gfx.ArrowAssets;

/**
 *
 * @author Antonia
 */
public class Arrow extends Creature{
    
   
    
    public Arrow(float x, float y, int width, int height){
        super(x, y, width, height, new ArrowAssets());
        bounds.x = 0;
        bounds.y = 1;
        bounds.width= 24;
        bounds.height= 15;
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
       // animation.tick();
               

    @Override
    public void render(Graphics g) {
       // System.out.println("LA X DELLA FOGLIA è: "+x);
       // System.out.println("L'OFFSET è: "+handler.getGameCamera().getxOffset());
        state.render(g);
        //g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    
    @Override
    public void touchGrabbable (Entity e) {
        actionOnCollision(e);
    }
    
    @Override
    public void touchEntity(Entity e){
        e.hurt(1);
        isActive=false;
    }
    
    @Override
    public void actionOnCollision(Entity e){
        e.hurt(1);
        isActive=false;
        /*setChanged();
        notifyObservers();*/
        
    }

    @Override
    public void die() {
        return;
    }

    
}
