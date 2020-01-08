/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.mobs.states.player;

import  twinskingdom.game.GameHandler;
import  twinskingdom.entities.mobs.Creature;
import  twinskingdom.entities.mobs.enemies.level1.Arrow;
import  twinskingdom.entities.mobs.states.DownMovementState;
import  twinskingdom.gfx.EntityAssets;
import  twinskingdom.utils.UtilityTimer;

/**
 *
 *  
 */
public class DownArcherState extends DownMovementState {

    private UtilityTimer timer;

    public DownArcherState(Creature creature, EntityAssets asset) {
        super(creature, asset);
        timer = new UtilityTimer(500);
    }

    @Override
    public void attack() {
        if (timer.isTimeOver()) {

            super.attack();
            //attacca con le frecce a destra
            Arrow arrow = new Arrow(creature.getX() + 20, creature.getY() + 90, 10, 10);
            arrow.setState(arrow.getDownState());
            GameHandler.instance.getWorld().getEntityManager().addEntity(arrow);
        }
    }

}
