/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.mobs.player;

import twinkingdom.entities.mobs.player.Player;
import twinkingdom.entities.mobs.states.player.DownArcherState;
import twinkingdom.gfx.ArrowAssets;
import twinkingdom.gfx.PlayerAssets;
import twinkingdom.entities.mobs.states.player.LeftArcherState;
import twinkingdom.entities.mobs.states.player.RightArcherState;
import twinkingdom.entities.mobs.states.player.UpArcherState;
import twinkingdom.gfx.PlayerBowAssets;

/**
 *
 * @author mario
 */
public class PlayerArcher extends Player{

    public PlayerArcher(float x, float y, PlayerBowAssets pAssets, int numLife) {
        super(x, y, pAssets, numLife);
        rightState = new RightArcherState(this, pAssets);
        leftState = new LeftArcherState(this, pAssets);
        downState = new DownArcherState(this, pAssets);
        upState = new UpArcherState(this, pAssets);
        setState(downState);
    }
    
    public PlayerArcher(Player player){
        this(player.getX(), player.getY(), new PlayerBowAssets(), player.getNumLives());
        this.setHealth(player.getHealth());
    }
    
    
    
    
    
}
