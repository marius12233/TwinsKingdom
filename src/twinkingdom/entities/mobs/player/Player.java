/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.mobs.player;

import java.awt.Graphics;
import twinkingdom.entities.Entity;
import twinkingdom.entities.mobs.Creature;
import twinkingdom.gfx.EntityAssets;
import twinkingdom.gfx.PlayerAssets;
import twinkingdom.gui.Mana;

/**
 *
 * @author mario
 */
public class Player extends Creature {

    //private int numLives;
    
    protected Mana mana;

    public Player(float x, float y, EntityAssets pAssets, int lives) {
        super( x, y, Creature.DEFAULT_WIDTH, Creature.DEFAULT_HEIGHT, pAssets);
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
        if (mana == null) {
            this.mana = new Mana();
        }
        
        setState(downState);

        /* 
        
         upState = new UpEntityState(this, pAssets);
         downState = new DownEntityState(this, pAssets);
         leftState =new LeftEntityState(this, pAssets);
         rightState = new RightEntityState(this, pAssets);
         entityState = new DownEntityState(this, pAssets);
        
         setState(new AttackState(entityState));*/
    }
    
    public Player(float x, float y, PlayerAssets pAssets) {
        this(x, y, pAssets, 3);
    }
    
    public Player(Player player) {
        this(player.getX(), player.getY(), new PlayerAssets(), player.getNumLives());
        this.setHealth(player.getHealth());
        this.setMana(player.getMana());
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

        //System.out.println(x+" "+y);
        handler.getGameCamera().centerOnEntity(this);
    }

    public Mana getMana() {
        return mana;
    }

    public void setMana(Mana mana) {
        this.mana = mana;
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
    
    
    /*@Override
    public void setHealthPoints(int newHealth) {
        super.setHealthPoints(newHealth);
        //setChanged();
        //notifyObservers();;
        //life.setHealthPoints(newHealth);
        System.out.println("Vita: "+health.getHealthPoints()+" Num. Vite: "+health.getLifes());
    }*/
    

}
