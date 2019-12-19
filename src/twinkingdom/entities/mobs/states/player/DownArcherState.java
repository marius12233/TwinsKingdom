/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.mobs.states.player;

import twinkingdom.GameHandler;
import twinkingdom.entities.mobs.Creature;
import twinkingdom.entities.mobs.enemies.level1.Arrow;
import twinkingdom.entities.mobs.states.DownMovementState;
import twinkingdom.entities.mobs.states.DownMovementState;
import twinkingdom.gfx.EntityAssets;
import twinkingdom.utils.UtilityTimer;

/**
 *
 * @author mario
 */
public class DownArcherState extends DownMovementState{
    private UtilityTimer timer;
    public DownArcherState(Creature creature, EntityAssets asset) {
        super(creature, asset);
        timer = new UtilityTimer(500);
    }
    
    @Override
    public void attack(){
        if(timer.isTimeOver()){

            super.attack();
        //attacca con le frecce a destra
            Arrow arrow = new Arrow(creature.getX() + 20, creature.getY()+90, 10, 10);
            arrow.setState(arrow.getDownState());
            GameHandler.instance.getWorld().getEntityManager().addEntity(arrow);
        }
    }
    
}
