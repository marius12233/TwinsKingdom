/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.mobs.enemies;

import  twinskingdom.entities.Entity;
import  twinskingdom.entities.mobs.Creature;
import  twinskingdom.gfx.EntityAssets;
import  twinskingdom.policies.HorizontalPolicy;
import  twinskingdom.policies.BasePolicy;

/**
 *
 */
public abstract class Enemy extends Creature {

    protected BasePolicy movementPolicy = null;

    public Enemy(float x, float y, int width, int height, EntityAssets entityAssets) {
        super(x, y, width, height, entityAssets);
        setMovementPolicy(new HorizontalPolicy(this, (int) (x - 300), (int) (x + 300)));
    }

    @Override
    public void attack(Entity e) {
        if (e.isPlayer()) {
            super.attack(e);
        }
    }

    /**
     * *
     * Policy to obtain the movement of the enemy
     */
    public void getMovement() {
        checkAttacks();
        movementPolicy.getMovement();
    }

    public BasePolicy getMovementPolicy() {
        return movementPolicy;
    }

    public void setMovementPolicy(BasePolicy movementPolicy) {
        this.movementPolicy = movementPolicy;
    }

}
