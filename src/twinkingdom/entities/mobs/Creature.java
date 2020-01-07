/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.mobs;

import java.awt.Rectangle;
import twinkingdom.game.RenderableLayers;
import twinkingdom.entities.Entity;
import twinkingdom.entities.mobs.states.DownMovementState;
import twinkingdom.entities.mobs.states.LeftMovementState;
import twinkingdom.entities.mobs.states.MovementState;
import twinkingdom.entities.mobs.states.RightMovementState;
import twinkingdom.entities.mobs.states.UpMovementState;
import twinkingdom.gfx.EntityAssets;
import twinkingdom.tiles.Tile;
import twinkingdom.utils.UtilityTimer;
import twinkingdom.worlds.World;

/**
 *
 * @author mario
 */
public abstract class Creature extends Entity {

    //public static final int DEFAULT_HEALTH = 10;
    public static final float DEFAULT_SPEED = 3.0f;
    //protected int health;
    protected float speed;
    public static final int DEFAULT_WIDTH = 64;
    public static final int DEFAULT_HEIGHT = 64;
    public static final int DEFAULT_ATTACK_COOLDOWN = 1000;

    protected float xMove, yMove;
    protected boolean active = true;
    protected MovementState state, upState, downState, leftState, rightState;
    protected int damageAttack;
    private long lastAttackTimer, attackCooldown = DEFAULT_ATTACK_COOLDOWN, attackTimer = attackCooldown;
    private boolean attacking = false;
    private boolean tileCollision=false;

    protected UtilityTimer timerAttack;

    public Creature(float x, float y, int width, int height, EntityAssets entityAssets) {
        super(x, y, width, height);
        speed = DEFAULT_SPEED;
        //health = DEFAULT_HEALTH;
        //maxHealth = health;
        //this.entityAssets=entityAssets;
        entityAssets.init();
        timerAttack = new UtilityTimer(500);

        upState = new UpMovementState(this, entityAssets);
        downState = new DownMovementState(this, entityAssets);
        leftState = new LeftMovementState(this, entityAssets);
        rightState = new RightMovementState(this, entityAssets);

        setState(downState);
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

    public void setState(MovementState state) {
        this.state = state;
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
                e.hurt(this.damageAttack);
                return;
            }

        }

    }

    @Override
    public abstract void die();

    public void move() {
        if (!checkEntityCollisions(xMove, 0f)) {
            moveX();
        }

        if (!checkEntityCollisions(0f, yMove)) {
            moveY();
        }
    }

    public void moveX() {
        if(xMove > 0) {
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT) &&
                        !collisionWithLayerTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                            !collisionWithLayerTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
                x += xMove;
                tileCollision=false;
            } else {
                x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
                tileCollision=true;
            }
            
        } else if (xMove < 0) {
            int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT) &&
                        !collisionWithLayerTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                            !collisionWithLayerTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
                x += xMove;
                tileCollision=false;
            } else {
                x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
                tileCollision=true;
            }
        }
    }
    
    public void moveY() {
        if(yMove < 0) {
            int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty) &&
                        !collisionWithLayerTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                            !collisionWithLayerTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
                y += yMove;
                tileCollision=false;
            } else {
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
                tileCollision=true;
            }
        }else if(yMove > 0) {
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty) &&
                        !collisionWithLayerTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                            !collisionWithLayerTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
                y += yMove;
                tileCollision=false;
            } else {
                y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
                tileCollision=true;
            }
        }
    }

    protected boolean collisionWithTile(int x, int y) {
        return handler.getWorld().getTile(x, y).isSolid();
    }
    
        
    protected boolean collisionWithLayerTile(int x, int y) {
        boolean flag = false;
        World world = handler.getWorld();
        RenderableLayers rl = world.getRenderableLayers();
        //System.out.println("RL is null... " + (rl == null));
        int num_layers = rl.countLayers();
        for(int i = 0; i < num_layers; i++) {
            if(world.getLayerTile(i, x, y).isSolid()) {
                flag = true;
            }
        }
        return flag;
    }


    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public long getAttackCooldown() {
        return attackCooldown;
    }

    public void setAttackCooldown(long attackCooldown) {
        this.attackCooldown = attackCooldown;
    }

    public MovementState getState() {
        return state;
    }

    public MovementState getUpState() {
        return upState;
    }

    public MovementState getDownState() {
        return downState;
    }

    public MovementState getLeftState() {
        return leftState;
    }

    public MovementState getRightState() {
        return rightState;
    }
    
    public boolean isCollidingWithTile(){
        return tileCollision;
    }
    
    

}
