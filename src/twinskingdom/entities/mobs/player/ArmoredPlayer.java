/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.mobs.player;

import  twinskingdom.gfx.PlayerArmSwordAssets;
import twinskingdom.sounds.effects.SoundEffectManager;

/**
 *
 *  
 */
public class ArmoredPlayer extends Player{
    
    private boolean damage=true;
    
     public ArmoredPlayer( float x, float y, PlayerArmSwordAssets pAssets, int numLives) {
        super(x,y,pAssets,numLives);
    }
    
    public ArmoredPlayer(float x, float y, PlayerArmSwordAssets pAssets) {
        this(x, y, pAssets, 3);
    }
    
    public ArmoredPlayer(Player player){
        this(player.getX(), player.getY(), new PlayerArmSwordAssets(), player.getNumLives());
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
    public void playSound(){
        
        SoundEffectManager.SWORD.play();
    }
    
}
