/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.statics;

import  twinskingdom.entities.statics.EnchantedLeaf;
import  twinskingdom.entities.Entity;
import  twinskingdom.entities.mobs.states.RightMovementState;
import  twinskingdom.gfx.Animation;
import  twinskingdom.gfx.Assets;
import  twinskingdom.gfx.LeafAssets;
import  twinskingdom.entities.statics.StaticEntity;
import  twinskingdom.utils.UtilityTimer;

/**
 *
 *  
 */
public class EnchantedTree extends StaticEntity {

    private UtilityTimer timer;

    public EnchantedTree(float x, float y, int width, int height) {
        super(x, y, width, height);
        bounds.x = 1;
        bounds.y = 1;
        bounds.width = 60;
        bounds.height = 60;
        animation = new Animation(20, Assets.enchantedTrees);

        timer = new UtilityTimer(3000);

    }

    @Override
    public void tick() {
        if (timer.isTimeOver()) {
            handler.getWorld().getEntityManager().addEntity(createLeaf());
        }

    }
    // animation.tick();

    public EnchantedLeaf createLeaf() {
        EnchantedLeaf leaf = new EnchantedLeaf(getX() + 70, getY() + 70, 10, 10);
        LeafAssets leafAsset = new LeafAssets();
        leafAsset.init();
        leaf.setState(new RightMovementState(leaf, leafAsset));
        return leaf;
    }

    @Override
    public void touchEntity(Entity e) {
        setChanged();
        notifyObservers();
    }

}
