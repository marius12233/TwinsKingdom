/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.mobs.states.player;


import twinkingdom.game.GameHandler;
import twinkingdom.entities.mobs.player.PlayerMage;
import twinkingdom.entities.mobs.states.RightMovementState;
import twinkingdom.entities.statics.FireBall;
import twinkingdom.gfx.EntityAssets;
import twinkingdom.utils.UtilityTimer;

/**
 *
 * @author mario
 */
public class RightMageState extends RightMovementState{
    UtilityTimer timer;
    public RightMageState(PlayerMage playerMage, EntityAssets asset) {
        super(playerMage, asset);
        timer = new UtilityTimer(500);
    }
    
    @Override
    public void attack(){
        super.attack();
        //attacca con le frecce a destra
        PlayerMage mage = (PlayerMage) creature;
        if(timer.isTimeOver() && (mage.getMana().getMana() > 0)){
            FireBall fire = new FireBall(creature.getX() + 42, creature.getY()+10, 64, 64);
            fire.setState(fire.getRightState());
            GameHandler.instance.getWorld().getEntityManager().addEntity(fire);
            mage.getMana().setMana(mage.getMana().getMana() -10);
        }
    }
    
}
