/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.mobs.states;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import twinkingdom.entities.mobs.Creature;
import twinkingdom.gfx.Animation;
import twinkingdom.gfx.EntityAssets;

/**
 * The State abstract class in the State Design Pattern.
 * The client is represented by the particular entity that uses the pattern.
 * In this case the state is the position, so the movement direction of the entity
 * If the entity is in the left direction, it will be set the state of that entity to RightState,
 * the same thing for left, up and down movement.
 * In particular this class manages the animations of the entity.
 * Each entity has an animation for each movement (right, left, top and down) and each movement has a different
 * animation if it is in the attack, Idle or running action.
 * Given that the MovementState class manages directions, we would that it manages even the left, right, top and down
 * rectangle when it is detected a collision during an attack.
 *
 * @author mario
 */
public abstract class MovementState {
    
    /***
     * Represents the current animation
     */
    protected Animation animation;

    /**
     * The creature that uses the pattern State as client
     */
    protected Creature creature;
    

    public void tick(){
        animation.tick();
    }
    
    /**
     * The method to render the current image of the animation frame
     * @param g
     */
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame()
                , (int) (creature.getX() - creature.getHandler().getGameCamera().getxOffset()),
                (int) (creature.getY() - creature.getHandler().getGameCamera().getyOffset()),
                creature.getWidth(), creature.getHeight(), null);
    }
    
    /**
     * Manages the animation of the entity in running phase.
     * It changes the current animation of an entity
     */
    public abstract void move();
    
    /**
     * Manages the animation of the entity in Idle phase.
     * It changes the current animation of an entity
    */
    public abstract void stay();
    
    /***
     * Manages the animation of the entity in attack phase.
     * It changes the current animation of an entity
     */
    public abstract void attack();
    
    /**
     *  This method return the rectangle according to the direction in which the entity attacks.
     *  @return a rectangle according to the position in which the entity attacks.
     */
    public abstract Rectangle getAttackRectangle();
    
    /***
     * It manages the current animation.
     * Recall that an animations is a BufferedImage that contains several images and so several frames.
     * @return the current animation frame, that is the current image to display to generate the animation.
     */
    public BufferedImage getCurrentAnimationFrame(){
        return animation.getCurrentFrame();
    }
    
    public boolean equals(MovementState other){
        if(this.animation.getCurrentFrame().equals(other.animation.getCurrentFrame()))
            return true;
        return false;
    }
    
    public abstract EntityAssets getEntityAssets();
}
