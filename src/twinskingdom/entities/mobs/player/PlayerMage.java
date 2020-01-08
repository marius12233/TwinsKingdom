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
import  twinskingdom.gfx.PlayerSpellAssets;

/**
 *
 *  
 */
public class PlayerMage extends Player {

    public PlayerMage(float x, float y, PlayerSpellAssets pAssets, int numLives) {
        super(x, y, pAssets, numLives);
        rightState = new RightMageState(this, pAssets);
        leftState = new LeftMageState(this, pAssets);
        downState = new DownMageState(this, pAssets);
        upState = new UpMageState(this, pAssets);
        setDamageAttack(3);
        setState(downState);
    }

    public PlayerMage(Player player) {
        this(player.getX(), player.getY(), new PlayerSpellAssets(), player.getNumLives());
        this.setHealth(player.getHealth());
        this.setMana(player.getMana());
    }

}
