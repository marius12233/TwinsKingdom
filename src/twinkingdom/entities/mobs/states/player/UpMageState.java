/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.mobs.states.player;


import twinkingdom.entities.mobs.Creature;
import twinkingdom.entities.mobs.player.Player;
import twinkingdom.entities.mobs.enemies.level2.FireBall;
import twinkingdom.game.GameHandler;
import twinkingdom.entities.mobs.player.PlayerMage;
import twinkingdom.entities.mobs.states.UpMovementState;
import twinkingdom.gfx.EntityAssets;
import twinkingdom.utils.UtilityTimer;

/**
 *
 * @author mario
 */
public class UpMageState extends UpMovementState{
    UtilityTimer timer;
    public UpMageState(Creature playerMage, EntityAssets asset) {
        super(playerMage, asset);
        timer = new UtilityTimer(500);
    }
    
    @Override
    public void attack(){
        Player mage = (Player) creature;
        if(timer.isTimeOver() && (mage.getMana().getMana() > 0)){
            super.attack();
            //attacca con le frecce a destra
            FireBall fire = new FireBall(creature.getX(), creature.getY()-74, 64, 164);
            fire.setState(fire.getUpState());
            GameHandler.instance.getWorld().getEntityManager().addEntity(fire);
            mage.getMana().setMana(mage.getMana().getMana() -10);
        }
    }
}
