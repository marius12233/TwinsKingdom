/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.mobs.enemies.level1;

import java.awt.Graphics;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Observer;
import  twinskingdom.entities.mobs.enemies.Enemy;
import  twinskingdom.game.LevelHandler;
import  twinskingdom.gfx.SpiderAssets;

/**
 *
 */
public class Spider extends Enemy {

    protected Collection<Observer> observers;

    public Spider(float x, float y, int width, int height, SpiderAssets entityAssets) {
        super(x, y, width, height, entityAssets);
        bounds.x = 1;
        bounds.y = 1;
        bounds.width = 20;
        bounds.height = 30;
        //health = 2;
        setState(leftState);
        health.setHealthPoints(1);
        health.setLives(1);
        //maxHealth=life.getHealthPoints();
        //setAttackCooldown(3000);
        //timer = new UtilityTimer(2000);
        observers = new LinkedList<>();
    }

    @Override
    //Deve fare l'update dello stato dell'oggetto
    public void tick() {
        //Animations
        //Per update the index
        state.tick();
        //Movement
        getMovement();
        move();
        //handler.getGameCamera().centerOnEntity(this);
    }

    @Override
    public void die() {
        if (LevelHandler.getWorldId() == 7) {
            for (Observer o : observers) {
                o.update(this, this);
            }
        }
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void render(Graphics g) {
        state.render(g);
    }

}
