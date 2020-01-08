/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.mobs.enemies.level3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Map;
import  twinskingdom.entities.mobs.Creature;
import  twinskingdom.entities.mobs.enemies.Boss;
import  twinskingdom.entities.mobs.enemies.level1.Arrow;
import  twinskingdom.entities.mobs.states.RightMovementState;
import  twinskingdom.gfx.Animation;
import  twinskingdom.gfx.ArrowAssets;
import  twinskingdom.gfx.PaladinAssets;
import  twinskingdom.policies.HorizontalPolicy;
import  twinskingdom.policies.VerticalPolicy;
import  twinskingdom.utils.UtilityTimer;

/**
 This class implements the third level boss, setting their movement policies 
 * (horizontal or vertical) according to the timer value. 
 * Meanwhile, boss weapons are continuously fired.
 */
public class PaladinBoss extends Boss {

    ArrowAssets arrowAsset = new ArrowAssets();

    private UtilityTimer policyTimer;
    private boolean vertical = false;
    private VerticalPolicy verticalPolicy;
    private HorizontalPolicy horizontalPolicy;

    private Animation animationAttackR, animationAttackL, animationAttackU, animationAttackD, actualAnimation;

    /**
     * The constructor provides to set the healthpoints values, the horizontal/
     * vertical bounds for the collisions management, the movement policy and to
     * set the correct attack animations.
     * @param x horizontal position
     * @param y vertical position
     * @param paladinAssets character asset
     */
    public PaladinBoss(float x, float y, PaladinAssets paladinAssets) {
        super(x, y, 80, Creature.DEFAULT_HEIGHT, paladinAssets);
        
        setMovementPolicy(new HorizontalPolicy(this, (int) (getX() - 300), (int) (getX() + 300)));
        
        bounds.x = 15;
        bounds.y = 10;
        bounds.width = 30;
        bounds.height = 50;
       
        health.setMaxHealthPoints(10);
        health.setHealthPoints(10);
        health.setLives(1);
        
        setState(state);

        Map<String, BufferedImage[]> asr = paladinAssets.getAnimations().get("right");
        animationAttackR = new Animation(50, asr.get("attack"));
        Map<String, BufferedImage[]> asu = paladinAssets.getAnimations().get("up");
        animationAttackU = new Animation(50, asu.get("attack"));
        Map<String, BufferedImage[]> asd = paladinAssets.getAnimations().get("down");
        animationAttackD = new Animation(50, asd.get("attack"));
        Map<String, BufferedImage[]> asl = paladinAssets.getAnimations().get("left");
        animationAttackL = new Animation(50, asl.get("attack"));

        policyTimer = new UtilityTimer(10000);
    }

    /**
     * The tick method sets the character movement policies, according to timer
     * reached value. It sets also the animation attacks, depending on the character
     * state.
     */
    @Override
    public void tick() {
        
        if (this.getState() == leftState) {
            actualAnimation = animationAttackL;
        }
        if (this.getState() == rightState) {
            actualAnimation = animationAttackR;
        }
        if (this.getState() == downState) {
            actualAnimation = animationAttackD;
        }
        if (this.getState() == upState) {
            actualAnimation = animationAttackU;
        }

        actualAnimation.tick();
        
        //Movement
        getMovement();
        move();

        if (policyTimer.isTimeOver()) {
            if (vertical) {
                setMovementPolicy(new HorizontalPolicy(this, (int) (getX() - 300), (int) (getX() + 300)));
                
                vertical = false;
            } else {

                setMovementPolicy(new VerticalPolicy(this, (int) (getY() - 300), (int) (getY() + 300)));
                
                vertical = true;
            }
        }
    }

    /***
     * The render method sets the graphic features.
     * @param g 
     */
    @Override
    public void render(Graphics g) {

        if (actualAnimation != null) {
            g.drawImage(actualAnimation.getCurrentFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width - 2, height - 2, null);
        }
        //state.render(g);
        g.setColor(Color.red);
        g.fillRect((int) (x - getHandler().getGameCamera().getxOffset()),
                (int) (y - getHandler().getGameCamera().getyOffset() - 5),
                (int) Math.floor((double) getWidth() / (double) health.getMaxHealthPoints() * (double) health.getHealthPoints()), 7);
    }

    /***
     * This method creates the character weapons, used to defeate the player.
     * @return weapon object
     */
    @Override
    public Arrow createWeapon() {
        Arrow arrow = createWeapon((int) getX() + 50, (int) getY() + 50, 10, 10);
        return arrow;
    }

    /***
     * This method also creates the characher weapon, depending also on its 
     * position coordinates. 
     * @param x is the horizontal index position
     * @param y is the vertical index position
     * @param width is the character width
     * @param height is the character height
     * @return Arrow object
     */
    @Override
    public Arrow createWeapon(int x, int y, int width, int height) {
        Arrow arrow = new Arrow(x, y, width, height);
        arrow.setState(new RightMovementState(arrow, arrowAsset));
        return arrow;
    }

}
