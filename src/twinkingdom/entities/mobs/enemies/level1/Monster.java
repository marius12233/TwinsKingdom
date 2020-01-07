/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.mobs.enemies.level1;

import twinkingdom.entities.mobs.enemies.Enemy;
import java.awt.Graphics;
import twinkingdom.entities.mobs.Creature;
import twinkingdom.entities.statics.Portal;
import twinkingdom.gfx.MonsterAssets;
import twinkingdom.utils.UtilityTimer;

/**
 *
 * @author mario
 */
public class Monster extends Enemy {

    private UtilityTimer timer;
    private Portal portal;
    

    public Monster( float x, float y, MonsterAssets boss2Assets) {

        super(x, y, 80, Creature.DEFAULT_HEIGHT, boss2Assets);
        bounds.x = 25;
        bounds.y = 30;
        bounds.width = 15;
        bounds.height = 22;
        setState(leftState);
        health.setHealthPoints(2);
        health.setLives(1);
        //maxHealth=life.getHealthPoints();
        //setAttackCooldown(3000);
        timer = new UtilityTimer(500);
        this.handler=handler;

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
    public void render(Graphics g) {
        state.render(g);
    }
    
    public Portal getPortal() {
        return portal;
    }

    @Override
    public void die() {
    }

}
