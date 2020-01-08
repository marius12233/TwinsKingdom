/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.mobs.states.player;

import  twinskingdom.game.GameHandler;
import  twinskingdom.entities.mobs.Creature;
import  twinskingdom.entities.mobs.enemies.level1.Arrow;
import  twinskingdom.entities.mobs.states.RightMovementState;
import  twinskingdom.gfx.EntityAssets;
import  twinskingdom.utils.UtilityTimer;

/**
 *
 *  
 */
public class RightArcherState extends RightMovementState {

    UtilityTimer timer;

    public RightArcherState(Creature creature, EntityAssets asset) {
        super(creature, asset);
        timer = new UtilityTimer(500);
    }

    @Override
    public void attack() {
        super.attack();
        //attacca con le frecce a destra
        if (timer.isTimeOver()) {
            Arrow arrow = new Arrow(creature.getX() + 100, creature.getY() + 20, 10, 10);
            arrow.setState(arrow.getRightState());
            GameHandler.instance.getWorld().getEntityManager().addEntity(arrow);
        }
    }

}
