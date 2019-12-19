/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import twinkingdom.GameHandler;
import twinkingdom.gui.Health;

/**
 *
 * @author mario
 */

/* test test test */

public abstract class Entity  extends Observable {
    protected float x,y;
    protected int width, height;
    protected GameHandler handler;
    protected Rectangle bounds;
    public boolean isActive=true;
    protected boolean isGrabbable=false;
    protected boolean isStatic=false;
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
    
    public void hurt(int amt){
        //System.out.println(health);
        setHealthPoints(getHealthPoints() - amt);
        if(getHealthPoints() <=0){
            isActive=false;
            die();
        }
    }
    
    protected boolean isActive(){
        return isActive;
    }
    
    public void die(){
    }
    
    public boolean checkEntityCollisions(float xOffset, float yOffset){
       /* System.out.println("Check 1 ... " + (handler.getWorld() == null));
        System.out.println("Check 2 ... " + (handler.getWorld().getEntityManager() == null));
        System.out.println("Check 3 ... " + (handler.getWorld().getEntityManager().getEntities() == null));*/
        for(Entity e : handler.getWorld().getEntityManager().getEntities()){
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))){
                if(e.isGrabbable){
                    touchGrabbable(e);
                }
                else{
                    touchEntity(e);
                }
                
                return true;
            }
        }
        return false;
    }
    
    public void touchGrabbable(Entity e){
        //System.out.println("Grabbable!");
        return;
    }
    
    public void touchEntity(Entity e){
        //System.out.println("static!");
        return;
    }
    
    public void actionOnCollision(Entity e){
        //System.out.println("Action on Collision!");
        return;
    }
    
    public Rectangle getCollisionBounds(float xOffset, float yOffset){
        return new Rectangle((int) (x+bounds.x+xOffset), (int)(y+bounds.y+yOffset), bounds.width, bounds.height);
    }
    
    public GameHandler getHandler(){
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
