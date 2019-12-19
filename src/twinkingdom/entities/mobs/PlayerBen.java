/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.mobs;

import java.awt.Graphics;
import twinkingdom.entities.Entity;
import twinkingdom.gfx.PlayerAssets;
import twinkingdom.gui.Health;

/**
 *
 * @author mario
 */


public class PlayerBen extends Creature {

    //private int numLives;
    
    

    public PlayerBen(float x, float y, PlayerAssets pAssets, int lives) {
        super(x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT, pAssets);
        bounds.x = 20;
        bounds.y = 20;
        bounds.width = 15;
        bounds.height = 25;
        //life = new Life(10,3);
        //health = 100;
        health.setHealthPoints(10);
        //maxHealth = health.getMaxHealthPoints();
        health.setLives(lives);
        health.setMaxHealthPoints(10);
        //numLives = 3;

        setState(downState);

        /* 
        
         upState = new UpEntityState(this, pAssets);
         downState = new DownEntityState(this, pAssets);
         leftState =new LeftEntityState(this, pAssets);
         rightState = new RightEntityState(this, pAssets);
         entityState = new DownEntityState(this, pAssets);
        
         setState(new AttackState(entityState));*/
    }
    
    public PlayerBen(float x, float y, PlayerAssets pAssets) {
        this(x, y, pAssets, 3);
    }

    public int getNumLives() {
        return health.getLives();
    }

    public void setNumLives(int numLives) {
        //this.numLives = numLives;
        health.setLives(numLives);
        //setChanged();
        //notifyObservers();
    }

    @Override
    //Deve fare l'update dello stato dell'oggetto
    public void tick() {
        //Animations
        //Per update the index
        state.tick();
        //Movement
        getInput();
        move();

        
        handler.getGameCamera().centerOnEntity(this);
    }

    private void getInput() {

        xMove = 0;
        yMove = 0;

        if (handler.getKeyManager().isPressing()) {


            if (handler.getKeyManager().up) {
                setState(upState);
                state.move();

            }
            if (handler.getKeyManager().down) {
                setState(downState);
                state.move();

            }
            if (handler.getKeyManager().left) {
                setState(leftState);
                state.move();

            }
            if (handler.getKeyManager().right) {
                setState(rightState);
                state.move();
            }
            if (handler.getKeyManager().attack) {
                setState(state);
                state.attack();
                checkAttacks();
                
            }

        } else {

            setState(state);
            state.stay();

        }

        //state.move();
    }

    @Override
    public void render(Graphics g) {
        //g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        state.render(g);
    }

    @Override
    public void die() {
        if (getNumLives() > 0) {
            isActive = true;
            setNumLives(getNumLives()-1);
            setHealthPoints(getMaxHealthPoints());
            //setNumLives(numLives - 1);
            //setHealth(maxHealth);
        }
        if(getNumLives()==0){
            isActive=false;
            setHealthPoints(0);
        }
            
    }

    @Override
    public void touchGrabbable(Entity e) {
        e.isActive = false;
        e.actionOnCollision(this);
    }
    
    @Override
    public void touchEntity(Entity e){
        e.actionOnCollision(e);
    }
    
    public Health getLifeObservable(){
        return health;
    }

    /*@Override
    public void setHealthPoints(int newHealth) {
        super.setHealthPoints(newHealth);
        //setChanged();
        //notifyObservers();;
        //life.setHealthPoints(newHealth);
        System.out.println("Vita: "+health.getHealthPoints()+" Num. Vite: "+health.getLifes());
    }*/
    

}
