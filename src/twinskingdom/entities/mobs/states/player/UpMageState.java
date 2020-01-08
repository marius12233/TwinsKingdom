/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.mobs.states.player;

import  twinskingdom.entities.mobs.Creature;
import  twinskingdom.entities.mobs.player.Player;
import  twinskingdom.entities.mobs.enemies.level2.FireBall;
import  twinskingdom.game.GameHandler;
import  twinskingdom.entities.mobs.states.UpMovementState;
import  twinskingdom.gfx.EntityAssets;
import  twinskingdom.utils.UtilityTimer;

/**
 *
 *  
 */
public class UpMageState extends UpMovementState {

    UtilityTimer timer;

    public UpMageState(Creature playerMage, EntityAssets asset) {
        super(playerMage, asset);
        timer = new UtilityTimer(500);
    }

    @Override
    public void attack() {
        Player mage = (Player) creature;
        if (timer.isTimeOver() && (mage.getMana().getMana() > 0)) {
            super.attack();
            //attacca con le frecce a destra
            FireBall fire = new FireBall(creature.getX(), creature.getY() - 74, 64, 164);
            fire.setState(fire.getUpState());
            GameHandler.instance.getWorld().getEntityManager().addEntity(fire);
            mage.getMana().setMana(mage.getMana().getMana() - 10);
        }
    }
}
