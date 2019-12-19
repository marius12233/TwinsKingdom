/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.mobs.states.player;

import twinkingdom.GameHandler;
import twinkingdom.entities.mobs.Creature;
import twinkingdom.entities.mobs.player.PlayerMage;
import twinkingdom.entities.mobs.states.DownMovementState;
import twinkingdom.entities.mobs.states.DownMovementState;
import twinkingdom.entities.statics.FireBall;
import twinkingdom.gfx.EntityAssets;
import twinkingdom.utils.UtilityTimer;

/**
 *
 * @author mario
 */
public class DownMageState extends DownMovementState{
    private UtilityTimer timer;
    public DownMageState(PlayerMage playerMage, EntityAssets asset) {
        super(playerMage, asset);
        timer = new UtilityTimer(500);
    }
    
    @Override
    public void attack(){
        PlayerMage mage = (PlayerMage) creature;
        if(timer.isTimeOver() && (mage.getMana().getMana() > 0)){

            super.attack();
        //attacca con le frecce a destra
            FireBall fire = new FireBall( creature.getX() , creature.getY()+64, 64, 64);
            fire.setState(fire.getDownState());
            GameHandler.instance.getWorld().getEntityManager().addEntity(fire);
            mage.getMana().setMana(mage.getMana().getMana() -10);
        }
    }
    
}
