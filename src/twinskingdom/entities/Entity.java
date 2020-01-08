/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import  twinskingdom.game.GameHandler;
import  twinskingdom.gui.Health;

/**
 *The Entity class represents the entities in the game. 
 * An Entity object has the size of the character on the map. 
 * Check when it hits another entity and call the impact routine.
 * 
 */
/* test test test */
public abstract class Entity extends Observable {

    protected float x, y;
    protected int width, height;
    protected GameHandler handler;
    protected Rectangle bounds;
    public boolean isActive = true;
    protected boolean isPlayer = false;
    protected Health health;

    protected Collection<Observer> observers;

    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.handler = GameHandler.instance;
        bounds = new Rectangle(0, 0, width, height);
        this.health = new Health();
        observers = new LinkedList<>();

    }

    public void hurt(int amt) {
        setHealthPoints(getHealthPoints() - amt);
        if (getHealthPoints() <= 0) {
            isActive = false;
            die();
        }
    }

    protected boolean isActive() {
        return isActive;
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public void die() {
    }

    public boolean checkEntityCollisions(float xOffset, float yOffset) {

        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this)) {
                continue;
            }
            if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
                touchEntity(e);
                return true;
            }
        }
        return false;
    }

    /***
     * This method is called when this entity touches another entity.
     */
    public void touchEntity(Entity e) {
        return;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset) {
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    public GameHandler getHandler() {
        return handler;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public int getHealthPoints() {
        return health.getHealthPoints();
    }

    public void setHealthPoints(int healthPoints) {
        this.health.setHealthPoints(healthPoints);
    }

    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public int getMaxHealthPoints() {
        return health.getMaxHealthPoints();
    }

    @Override
    public void addObserver(Observer o) {

        observers.add(o);
    }

}
