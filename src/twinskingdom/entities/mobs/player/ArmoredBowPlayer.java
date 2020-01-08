/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.mobs.player;

import  twinskingdom.entities.mobs.states.player.DownArcherState;
import  twinskingdom.entities.mobs.states.player.LeftArcherState;
import  twinskingdom.entities.mobs.states.player.RightArcherState;
import  twinskingdom.entities.mobs.states.player.UpArcherState;
import  twinskingdom.gfx.PlayerArmBowAssets;

/**
 *
 *  
 */
public class ArmoredBowPlayer extends Player{
    
    private boolean damage=true;
    
    public ArmoredBowPlayer( float x, float y, PlayerArmBowAssets pAssets, int numLives) {
        super( x, y, pAssets, numLives);
        rightState=new RightArcherState(this, pAssets);
        leftState=new LeftArcherState(this, pAssets);
        downState=new DownArcherState(this, pAssets);
        upState=new UpArcherState(this, pAssets);
        setState(downState);
        setDamageAttack(2);
    }
    
    public ArmoredBowPlayer(Player player){
        this(player.getX(), player.getY(), new PlayerArmBowAssets(), player.getNumLives());
        this.setHealth(player.getHealth());
        this.setMana(player.getMana());
    }
    
     @Override
    public void hurt(int amt){
        if(damage){
            super.hurt(amt);
            damage=false;
        }
        else {
            damage=true;
        }
    }
    
}
