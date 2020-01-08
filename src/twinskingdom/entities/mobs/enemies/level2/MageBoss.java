/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.mobs.enemies.level2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import  twinskingdom.entities.mobs.Creature;
import  twinskingdom.entities.mobs.enemies.Boss;
import  twinskingdom.entities.mobs.enemies.level2.FireBall;
import  twinskingdom.entities.mobs.states.RightMovementState;
import  twinskingdom.gfx.MageAssets;
import  twinskingdom.gfx.FireBallAssets;
import  twinskingdom.policies.HorizontalArcherPolicy;
import  twinskingdom.policies.VerticalArcherPolicy;
import  twinskingdom.utils.UtilityTimer;

/**
 This class implements the second level boss, setting their movement policies 
 * (horizontal or vertical) according to the timer value. 
 * Meanwhile, boss weapons are continuously fired.
 */
public class MageBoss extends Boss {

    FireBallAssets fireBallAsset = new FireBallAssets();
    private UtilityTimer policyTimer;
    private boolean vertical = false;
    private VerticalArcherPolicy verticalPolicy;
    private HorizontalArcherPolicy horizontalPolicy;

    /**
     * The constructor provides to set the healthpoints values, the horizontal/
     * vertical bounds for the collisions management, the movement policy and to
     * generate boss weapons. 
     * @param x horizontal position
     * @param y vertical position
     * @param mageAssets character asset
     */
    public MageBoss(float x, float y, MageAssets mageAssets) {
        super(x, y, 80, Creature.DEFAULT_HEIGHT, mageAssets);
        weapons = new LinkedList();

        //movement policies' setting
        setMovementPolicy(new HorizontalArcherPolicy(this, (int) (getX() - 300), (int) (getX() + 300)));
        
        //boss weapons generation
        for (int i = 0; i < 10; i++) {
            weapons.add(createWeapon());
        }
        
        bounds.x = 25;
        bounds.y = 30;
        bounds.width = 15;
        bounds.height = 22;
        health.setMaxHealthPoints(7);
        health.setHealthPoints(7);
        health.setLives(1);
        
        fireBallAsset.init();
        policyTimer = new UtilityTimer(100000);
    }

    /**
     * The tick method sets the character movement policies, according to timer
     * reached value. 
     */
    @Override
    public void tick() {
       
        state.tick();
       
        getMovement();
        move();

        if (policyTimer.isTimeOver()) {
            if (vertical) {
                setMovementPolicy(new HorizontalArcherPolicy(this, (int) (getX() - 300), (int) (getX() + 300)));
                vertical = false;
            } else {

                setMovementPolicy(new VerticalArcherPolicy(this, (int) (getY() - 300), (int) (getY() + 300)));
                vertical = true;
            }
        }
    }

    /***
     * This method creates the character weapons, used to defeate the player.
     * @return weapon object
     */
    @Override
    public FireBall createWeapon() {
        FireBall fireBall = createWeapon((int) getX() + 300, (int) getY() - 300, 48, 48);
        return fireBall;
    }

    /***
     * This method also creates the characher weapon, depending also on its 
     * position coordinates. 
     * @param x is the horizontal index position
     * @param y is the vertical index position
     * @param width is the character width
     * @param height is the character height
     * @return FireBall object
     */
    @Override
    public FireBall createWeapon(int x, int y, int width, int height) {
        FireBall fireBall = new FireBall(x, y, width, height);
        fireBall.setState(new RightMovementState(fireBall, fireBallAsset));
        return fireBall;
    }

    /***
     * The render method sets the graphic features.
     * @param g 
     */
    @Override
    public void render(Graphics g) {
        state.render(g);
        g.setColor(Color.red);
        g.fillRect((int) (x - getHandler().getGameCamera().getxOffset()),
                (int) (y - getHandler().getGameCamera().getyOffset() - 5),
                (int) Math.floor((double) getWidth() / (double) health.getMaxHealthPoints() * (double) health.getHealthPoints()), 7);
    }
}
