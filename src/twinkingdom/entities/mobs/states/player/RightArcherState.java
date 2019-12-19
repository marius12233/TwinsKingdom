/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.mobs.states.player;


import twinkingdom.GameHandler;
import twinkingdom.entities.mobs.Creature;
import twinkingdom.entities.mobs.enemies.level1.Arrow;
import twinkingdom.entities.mobs.states.RightMovementState;
import twinkingdom.entities.mobs.states.RightMovementState;
import twinkingdom.gfx.EntityAssets;
import twinkingdom.utils.UtilityTimer;

/**
 *
 * @author mario
 */
public class RightArcherState extends RightMovementState{
    UtilityTimer timer;
    public RightArcherState(Creature creature, EntityAssets asset) {
        super(creature, asset);
        timer = new UtilityTimer(500);
    }
    
    @Override
    public void attack(){
        super.attack();
        //attacca con le frecce a destra
        if(timer.isTimeOver()){
            Arrow arrow = new Arrow( creature.getX() + 100, creature.getY()+ 20, 10, 10);
            arrow.setState(arrow.getRightState());
            GameHandler.instance.getWorld().getEntityManager().addEntity(arrow);
        }
    }
    
}
