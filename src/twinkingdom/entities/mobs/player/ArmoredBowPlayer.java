/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.mobs.player;

import twinkingdom.entities.mobs.states.player.DownArcherState;
import twinkingdom.entities.mobs.states.player.DownMageState;
import twinkingdom.entities.mobs.states.player.LeftArcherState;
import twinkingdom.entities.mobs.states.player.LeftMageState;
import twinkingdom.entities.mobs.states.player.RightArcherState;
import twinkingdom.entities.mobs.states.player.RightMageState;
import twinkingdom.entities.mobs.states.player.UpArcherState;
import twinkingdom.entities.mobs.states.player.UpMageState;
import twinkingdom.gfx.PlayerArmBowAssets;
import twinkingdom.gfx.PlayerArmSpellAssets;

/**
 *
 * @author Angelica
 */
public class ArmoredBowPlayer extends Player{
    public ArmoredBowPlayer( float x, float y, PlayerArmBowAssets pAssets, int numLives) {
        super( x, y, pAssets, numLives);
        rightState=new RightArcherState(this, pAssets);
        leftState=new LeftArcherState(this, pAssets);
        downState=new DownArcherState(this, pAssets);
        upState=new UpArcherState(this, pAssets);
        setState(downState);
    }
    
    public ArmoredBowPlayer(Player player){
        this(player.getX(), player.getY(), new PlayerArmBowAssets(), player.getNumLives());
        this.setHealth(player.getHealth());
        this.setMana(player.getMana());
    }
}
