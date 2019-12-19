/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.statics;

import twinkingdom.entities.statics.EnchantedLeaf;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import twinkingdom.entities.Entity;
import twinkingdom.entities.mobs.states.RightMovementState;
import twinkingdom.gfx.Animation;
import twinkingdom.gfx.Assets;
import twinkingdom.gfx.LeafAssets;
import twinkingdom.entities.statics.StaticEntity;
import twinkingdom.utils.UtilityTimer;

/**
 *
 * @author Antonia
 */
public class EnchantedTree extends StaticEntity{
    
    private UtilityTimer timer;
   
    
    public EnchantedTree(float x, float y, int width, int height) {
        super(x, y, width, height);
        bounds.x = 1;
        bounds.y = 1;
        bounds.width= 60;
        bounds.height= 60;
        animation = new Animation(20, Assets.enchantedTrees);

        timer = new UtilityTimer(3000);

        
    }
    @Override
    public void tick() {
        if(timer.isTimeOver()){
            handler.getWorld().getEntityManager().addEntity(createLeaf() );
        }
        
    }
       // animation.tick();
    
    public EnchantedLeaf createLeaf(){
        EnchantedLeaf leaf = new EnchantedLeaf(getX()+70, getY()+70, 10,10);
        LeafAssets leafAsset = new LeafAssets();
        leafAsset.init();
        leaf.setState(new RightMovementState(leaf, leafAsset));
        return leaf;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }
    
    public BufferedImage getCurrentAnimationFrame(){
        return animation.getCurrentFrame();
    }
    
    @Override
    public void actionOnCollision(Entity e){
        setChanged();
        notifyObservers();
    }
    
    
   
    
}

