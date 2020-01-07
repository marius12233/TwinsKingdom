/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.mobs.player;

import twinkingdom.entities.mobs.states.player.DownMageState;
import twinkingdom.entities.mobs.states.player.LeftMageState;
import twinkingdom.entities.mobs.states.player.RightMageState;
import twinkingdom.entities.mobs.states.player.UpMageState;
import twinkingdom.gfx.PlayerArmSpellAssets;
import twinkingdom.gfx.PlayerSpellAssets;

/**
 *
 * @author Angelica
 */
public class ArmoredSpellPlayer extends Player{
    
    public ArmoredSpellPlayer( float x, float y, PlayerArmSpellAssets pAssets, int numLives) {
        super( x, y, pAssets, numLives);
        rightState=new RightMageState(this, pAssets);
        leftState=new LeftMageState(this, pAssets);
        downState=new DownMageState(this, pAssets);
        upState=new UpMageState(this, pAssets);
        setState(downState);
    }
    
    public ArmoredSpellPlayer(Player player){
        this(player.getX(), player.getY(), new PlayerArmSpellAssets(), player.getNumLives());
        this.setHealth(player.getHealth());
        this.setMana(player.getMana());
    }
    
}
