
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.mobs.enemies.level1;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import  twinskingdom.entities.mobs.Creature;
import  twinskingdom.entities.mobs.enemies.Boss;
import  twinskingdom.gfx.ArcherAssets;
import  twinskingdom.gfx.ArrowAssets;
import  twinskingdom.policies.HorizontalArcherPolicy;
import  twinskingdom.policies.VerticalArcherPolicy;
import  twinskingdom.utils.UtilityTimer;
import  twinskingdom.entities.mobs.states.RightMovementState;

/**
 This class implements the first level boss, setting their movement policies
 * (horizontal or vertical) according to the timer value. 
 * Meanwhile, boss weapons are continuously fired.
 */
public class ArcherBoss extends Boss {

    ArrowAssets arrowAsset = new ArrowAssets();

    private UtilityTimer policyTimer;
    private boolean vertical = false;
    private VerticalArcherPolicy verticalPolicy;
    private HorizontalArcherPolicy horizontalPolicy;

    /**
     * The constructor provides to set the healthpoints values, the horizontal/
     * vertical bounds for the collisions management and the movement policy. 
     * @param x horizontal position
     * @param y vertical position
     * @param boss2Assets character asset
     */
    public ArcherBoss(float x, float y, ArcherAssets boss2Assets) {
        super(x, y, 80, Creature.DEFAULT_HEIGHT, boss2Assets);
        weapons = new LinkedList();
        for (int i = 0; i < 10; i++) {
            weapons.add(createWeapon());
        }
        
        setMovementPolicy(new HorizontalArcherPolicy(this, (int) (getX() - 300), (int) (getX() + 300)));
        
        bounds.x = 25;
        bounds.y = 30;
        bounds.width = 15;
        bounds.height = 22;
         
        health.setMaxHealthPoints(3);
        health.setHealthPoints(3);
        health.setLives(1);
        arrowAsset.init();
        policyTimer = new UtilityTimer(10000);
    }

   /**
     * The tick method sets the character movement policies, according to timer
     * reached value. 
     */
    @Override
    public void tick() {
        
        state.tick();
        //Movement
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
