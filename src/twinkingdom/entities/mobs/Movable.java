/*
 * This class manages the movement and collision of entities
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.mobs;

import java.awt.Graphics;
import twinkingdom.RenderableLayers;
import twinkingdom.entities.Entity;
import static twinkingdom.entities.mobs.Creature.DEFAULT_SPEED;
import twinkingdom.entities.mobs.states.DownMovementState;
import twinkingdom.entities.mobs.states.LeftMovementState;
import twinkingdom.entities.mobs.states.MovementState;
import twinkingdom.entities.mobs.states.RightMovementState;
import twinkingdom.entities.mobs.states.UpMovementState;
import twinkingdom.gfx.EntityAssets;
import twinkingdom.tiles.Tile;
import twinkingdom.worlds.World;

/**
 *
 * @author mario
 */
public abstract class Movable extends Entity{
    
    protected MovementState state, upState, downState, leftState, rightState;
    protected float xMove, yMove;
    protected float speed;
    public static final float DEFAULT_SPEED = 3.0f;
    protected boolean tileCollision=false;

    public Movable(float x, float y, int width, int height, EntityAssets entityAssets) {
        super(x, y, width, height);
        speed = DEFAULT_SPEED;

        entityAssets.init();
        
        upState = new UpMovementState(this, entityAssets);
        downState = new DownMovementState(this, entityAssets);
        leftState = new LeftMovementState(this, entityAssets);
        rightState = new RightMovementState(this, entityAssets);

        setState(downState);
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
    
    public void setState(MovementState state) {
        this.state = state;
    }
    

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

       
    public boolean isCollidingWithTile(){
        return tileCollision;
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

    
}
