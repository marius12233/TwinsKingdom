/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.mobs.enemies.level2;

import java.awt.Graphics;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Observer;
import  twinskingdom.entities.mobs.enemies.Enemy;
import  twinskingdom.game.LevelHandler;
import  twinskingdom.gfx.RedSnakeAssets;

/**
 * This class implements one of the second level enemies: the red snake. The 
 * creature is also included in the final level boss.
 */
public class RedSnake extends Enemy {

    protected Collection<Observer> observers;

    /**
     * The constructor provides to set the healthpoints values, the horizontal/
     * vertical bounds for the collisions management and the damage attack.
     * Also the observers list is initialized, in order to include the creature
     * in the observable enemies of the final level.
     * @param x horizontal position
     * @param y vertical position
     * @param entityAssets character asset
     */
    public RedSnake(float x, float y, int width, int height, RedSnakeAssets entityAssets) {
        super(x, y, width, height, entityAssets);
        bounds.x = 1;
        bounds.y = 1;
        bounds.width = 25;
        bounds.height = 25;
        setState(downState);
        health.setHealthPoints(3);
        health.setLives(1);
        speed = 3;
        this.setDamageAttack(2);
        observers = new LinkedList<>();
    }

    /**
     * The tick method only provides to recall the tick method of the setted state
     * and to manage the character movements.
     */
    @Override
    public void tick() {

        state.tick();
        getMovement();
        move();
    }

    /***
     * The die method recalls the observer update method to correctly update the
     * size of the final enemies manager (this condition is valid only for the 6th
     * level).
     */
    @Override
    public void die() {
        if (LevelHandler.getWorldId() == 7) {
            for (Observer o : observers) {
                o.update(this, this);
            }
        }
    }

    /**
     * This method provides to add the observer to observers list of the class.
     * @param o is the observer to add to observer list
     */
    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    /**
     * The render method provides only to recall the render method of the state.
     * @param g represents the graphics
     */
    @Override
    public void render(Graphics g) {
        state.render(g);
    }
}
