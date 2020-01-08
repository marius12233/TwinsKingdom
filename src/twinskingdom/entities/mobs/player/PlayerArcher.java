/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.mobs.player;

import  twinskingdom.entities.mobs.player.Player;
import  twinskingdom.entities.mobs.states.player.DownArcherState;
import  twinskingdom.entities.mobs.states.player.LeftArcherState;
import  twinskingdom.entities.mobs.states.player.RightArcherState;
import  twinskingdom.entities.mobs.states.player.UpArcherState;
import  twinskingdom.gfx.PlayerBowAssets;

/**
 *
 *  
 */
public class PlayerArcher extends Player {

    public PlayerArcher(float x, float y, PlayerBowAssets pAssets, int numLife) {
        super(x, y, pAssets, numLife);
        rightState = new RightArcherState(this, pAssets);
        leftState = new LeftArcherState(this, pAssets);
        downState = new DownArcherState(this, pAssets);
        upState = new UpArcherState(this, pAssets);
        setDamageAttack(2);
        setState(downState);
    }

    public PlayerArcher(Player player) {
        this(player.getX(), player.getY(), new PlayerBowAssets(), player.getNumLives());
        this.setHealth(player.getHealth());
        this.setMana(player.getMana());
    }

}
