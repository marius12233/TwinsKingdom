/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.mobs.states.player;

import  twinskingdom.game.GameHandler;
import  twinskingdom.entities.mobs.Creature;
import  twinskingdom.entities.mobs.enemies.level1.Arrow;
import  twinskingdom.entities.mobs.states.LeftMovementState;
import  twinskingdom.gfx.EntityAssets;
import  twinskingdom.utils.UtilityTimer;

/**
 *
 *  
 */
public class LeftArcherState extends LeftMovementState {

    private UtilityTimer timer;

    public LeftArcherState(Creature creature, EntityAssets asset) {
        super(creature, asset);
        timer = new UtilityTimer(500);
    }

    @Override
    public void attack() {
        if (timer.isTimeOver()) {
            super.attack();
            //attacca con le frecce a sinistra
            Arrow arrow = new Arrow(creature.getX() - 50, creature.getY() + 20, 10, 10);
            arrow.setState(arrow.getLeftState());
            GameHandler.instance.getWorld().getEntityManager().addEntity(arrow);
        }
    }

}
