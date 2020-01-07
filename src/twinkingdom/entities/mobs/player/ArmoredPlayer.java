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
import twinkingdom.gfx.PlayerArmSwordAssets;
import twinkingdom.gfx.PlayerAssets;
import twinkingdom.gfx.PlayerSpellAssets;

/**
 *
 * @author Angelica
 */
public class ArmoredPlayer extends Player{
    
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
    
}
