/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.mobs;

import java.awt.Rectangle;
import twinkingdom.entities.Entity;
import twinkingdom.gfx.EntityAssets;
import twinkingdom.utils.UtilityTimer;

/**
 *
 * @author mario
 */
public abstract class Creature extends Movable{

    //public static final int DEFAULT_HEALTH = 10;
    //protected int health;
    public static final int DEFAULT_WIDTH = 64;
    public static final int DEFAULT_HEIGHT = 64;
    public static final int DEFAULT_ATTACK_COOLDOWN = 1000;

    protected boolean active = true;
    protected int damageAttack;
    private long lastAttackTimer, attackCooldown = DEFAULT_ATTACK_COOLDOWN, attackTimer = attackCooldown;
    private boolean attacking = false;

    protected UtilityTimer timerAttack;

    public Creature(float x, float y, int width, int height, EntityAssets entityAssets) {
        super(x, y, width, height, entityAssets);
        //health = DEFAULT_HEALTH;
        //maxHealth = health;
        //this.entityAssets=entityAssets;
        timerAttack = new UtilityTimer(500);

        damageAttack = 1;
//        this.move();
    }

    public Creature(float x, float y, int width, int height, EntityAssets entityAssets, int damageAttack) {
        this(x, y, width, height, entityAssets);
        this.damageAttack = damageAttack;
    }

    public void setDamageAttack(int damageAttack) {
        this.damageAttack = damageAttack;
    }

    public void checkAttacks() {
        if (!timerAttack.isTimeOver()) {
            return;
        }
        Rectangle ar = state.getAttackRectangle();
        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this)) {
                continue;
            }
            if (e.getCollisionBounds(0, 0).intersects(ar)) {
                //e.hurt(this.damageAttack);
                this.attack(e);
                return;
            }

        }

    }
    
    public void attack(Entity e){
        e.hurt(this.damageAttack);
    }

    @Override
    public abstract void die();

    public long getAttackCooldown() {
        return attackCooldown;
    }

    public void setAttackCooldown(long attackCooldown) {
        this.attackCooldown = attackCooldown;
    }
}
