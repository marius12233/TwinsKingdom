/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.mobs.enemies.level3;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Observer;
import twinkingdom.entities.mobs.enemies.Enemy;
import twinkingdom.game.LevelHandler;
import twinkingdom.gfx.ArmorAssets;

/**
 *
 * @author Angelica
 */
public class EnchantedArmor extends Enemy{
    
    protected Collection<Observer> observers;
    
    public EnchantedArmor(float x, float y, int width, int height, ArmorAssets entityAssets) {
        super(x, y, width, height, entityAssets);
        bounds.x = 1;
        bounds.y = 1;
        bounds.width = 25;
        bounds.height = 25;
        //health = 2;
        setState(leftState);
        health.setMaxHealthPoints(10);
        health.setHealthPoints(10);
        health.setLives(1);
        speed=2;
        observers= new LinkedList<>();
        //maxHealth=health.getHealthPoints();
        //setAttackCooldown(3000);
        //timer = new UtilityTimer(2000);
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

    /*Override
    public void getMovement() {
        MovementState statePolicy;
        checkAttacks();
        /*if((statePolicy = movementPolicy.getMovement())!=null)
            setState(statePolicy);
        movementPolicy.getAction();*/
        //movementPolicy.getMovement();

        //state.attack();
    //}

    @Override
    public void render(Graphics g) {
        state.render(g);
        g.setColor(Color.red);
        g.fillRect((int) (x - getHandler().getGameCamera().getxOffset()),
                (int) (y - getHandler().getGameCamera().getyOffset() - 5),
                (int)Math.floor((double)getWidth()/(double)health.getMaxHealthPoints() * (double)health.getHealthPoints()), 7);
    }
}
