/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.mobs.states.player;

import  twinskingdom.game.GameHandler;
import  twinskingdom.entities.mobs.Creature;
import  twinskingdom.entities.mobs.enemies.level1.Arrow;
import  twinskingdom.entities.mobs.states.UpMovementState;
import  twinskingdom.gfx.EntityAssets;
import  twinskingdom.utils.UtilityTimer;

/**
 *
 *  
 */
public class UpArcherState extends UpMovementState {

    UtilityTimer timer = new UtilityTimer(500);

    public UpArcherState(Creature creature, EntityAssets asset) {
        super(creature, asset);
    }

    @Override
    public void attack() {
        if (timer.isTimeOver()) {
            super.attack();
            //attacca con le frecce sopra

            Arrow arrow = new Arrow(creature.getX() + 20, creature.getY() - 50, 10, 10);
            arrow.setState(arrow.getUpState());
            GameHandler.instance.getWorld().getEntityManager().addEntity(arrow);
        }
    }

}
