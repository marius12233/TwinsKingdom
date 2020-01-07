/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.mobs.enemies.level2;

import java.awt.Graphics;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Observer;
import twinkingdom.entities.mobs.enemies.Enemy;
import twinkingdom.game.LevelHandler;
import twinkingdom.gfx.GargoyleAssets;
/**
 *
 * @author Amedeo
 */
public class Gargoyle extends Enemy {
    
    protected Collection<Observer> observers;
        
    public Gargoyle( float x, float y, int width, int height, GargoyleAssets entityAssets){
        super( x, y, width, height, entityAssets);
        bounds.x = 2;
        bounds.y = 2;
        bounds.width = 35;
        bounds.height = 35;
        setState(downState);
        health.setHealthPoints(5);
        health.setLives(1);
        speed=3;
        this.setDamageAttack(3);
        observers= new LinkedList<>();
    }
    
    @Override
    public void tick() {

        state.tick();
        getMovement();
        move();
    }

    @Override
    public void die() {
        if (LevelHandler.getWorldId()==7){
            for( Observer o : observers){
                o.update(this, this);
            }
        }
    }
    
    @Override
    public void addObserver(Observer o){
        observers.add(o);
    }

    @Override
    public void render(Graphics g) {
        state.render(g);
    }
}
