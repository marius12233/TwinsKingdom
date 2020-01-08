/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.mobs.player;

import  twinskingdom.entities.mobs.states.player.DownMageState;
import  twinskingdom.entities.mobs.states.player.LeftMageState;
import  twinskingdom.entities.mobs.states.player.RightMageState;
import  twinskingdom.entities.mobs.states.player.UpMageState;
import  twinskingdom.gfx.PlayerArmSpellAssets;

/**
 *
 *  
 */
public class ArmoredSpellPlayer extends Player{
    
    private boolean damage=true;
    
    public ArmoredSpellPlayer( float x, float y, PlayerArmSpellAssets pAssets, int numLives) {
        super( x, y, pAssets, numLives);
        rightState=new RightMageState(this, pAssets);
        leftState=new LeftMageState(this, pAssets);
        downState=new DownMageState(this, pAssets);
        upState=new UpMageState(this, pAssets);
        setDamageAttack(3);
        setState(downState);
    }
    
    public ArmoredSpellPlayer(Player player){
        this(player.getX(), player.getY(), new PlayerArmSpellAssets(), player.getNumLives());
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
