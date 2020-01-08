/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.mobs.enemies;

import java.awt.Graphics;
import java.util.LinkedList;
import  twinskingdom.entities.Entity;
import  twinskingdom.entities.mobs.enemies.Enemy;
import  twinskingdom.gfx.EntityAssets;
import  twinskingdom.gui.Health;

/**
 *
 */
public abstract class Boss<T> extends Enemy {

    protected LinkedList<T> weapons;

    public Boss(float x, float y, int width, int height, EntityAssets entityAssets) {
        super(x, y, width, height, entityAssets);
    }

    @Override
    public void die() {
        this.setNumLives(0);
    }

    @Override
    public void render(Graphics g) {
        // g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        state.render(g);
    }

    public LinkedList<T> getWeapons() {
        return weapons;
    }

    @Override
    public void touchEntity(Entity e) {
        setChanged();
        notifyObservers();
    }

    public int getNumLives() {
        return health.getLives();
    }

    public void setNumLives(int numLives) {
        health.setLives(numLives);
    }

    public Health getLifeObservable() {
        return health;
    }

    public abstract T createWeapon();

    public abstract T createWeapon(int x, int y, int width, int height);

}
