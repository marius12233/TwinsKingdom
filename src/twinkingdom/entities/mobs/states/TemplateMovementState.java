/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.mobs.states;

import java.awt.Rectangle;
import twinkingdom.gfx.Animation;
import twinkingdom.gfx.EntityAssets;

/**
 * The abstract base class of the Template Method Pattern.
 * @author mario
 */
public abstract class TemplateMovementState extends MovementState{
    
    protected Animation animationStable;
    protected Animation animationRun;
    protected Animation animationAttack;
    protected EntityAssets asset;

    @Override
    public void move() {
        animation = animationRun;
        setMove();
    }

    @Override
    public void stay() {
        animation = animationStable;
    }

    @Override
    public void attack() {
        
        animation=animationAttack;
    }
    /***
     * Set the direction and the amount of space that the Entity has to go ahead.
     * Hook method.
     */
    public abstract void  setMove();
    
    /***
     * Set the size and the position of the attack rectangle based on the entity's collision bound rectangle.
     * Hook method.
     * @param ar : the attack rectangle to set dimensions.
     * @param cb : the entity's bound rectangle used in other classes to detect collisions.
     */
    public abstract void setSize(Rectangle ar, Rectangle cb);
    
    /***
     * Creates the attack rectangle to manage the attack events.
     * @return attack rectangle.
     */
    @Override
    public Rectangle getAttackRectangle(){
        Rectangle cb = creature.getCollisionBounds(0, 0);
        Rectangle ar = new Rectangle();
        int arSize=30;
        ar.width=arSize;
        ar.height=arSize;
        setSize(ar, cb);
        return ar;
    }
    
    @Override
    public EntityAssets getEntityAssets(){
        return asset;
    }
}
