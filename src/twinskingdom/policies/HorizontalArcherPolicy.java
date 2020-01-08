/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.policies;

import java.util.LinkedList;
import  twinskingdom.game.GameHandler;
import  twinskingdom.entities.mobs.Launchable;
import  twinskingdom.entities.mobs.Movable;
import  twinskingdom.entities.mobs.enemies.Boss;
import  twinskingdom.utils.UtilityTimer;

/**
 *
 *  
 */
public class HorizontalArcherPolicy extends HorizontalPolicy {
    private UtilityTimer timer, timer2;
    private LinkedList<Launchable> arrows;
    private Boss boss;
    
    public HorizontalArcherPolicy(Boss c, int startX, int endX) {
        super(c, startX, endX);
        timer=new UtilityTimer(2000);
        timer2 = new UtilityTimer(1000);
        arrows = (LinkedList<Launchable>) c.getWeapons();
        boss = c;
        this.handler = GameHandler.instance;
    }
    
    public boolean canAttack(){
        return ((handler.getWorld().getEntityManager().getPlayer().getX() <= (c.getX() +300)) || (handler.getWorld().getEntityManager().getPlayer().getX() > (c.getX()-300))); 
    }
    
    @Override
    public void getAction() {
        if(timer.isTimeOver() && canAttack()){
            attack();
        }
        if(timer2.isTimeOver())
            arrows.add((Launchable) boss.createWeapon());

        super.getAction();        
    }
    
    @Override
    public void attack(){

        int playerY = (int) handler.getWorld().getEntityManager().getPlayer().getY();
        
        //MovementState oldState = c.getState();
        //if(playerY > (int) c.getY()) c.setState(c.getDownState()); else c.setState(c.getUpState());
        //c.getState().move();
        
        int posX[] = new int[]{-80, 0, 80};
        int posY[] = new int[]{50, 50, 50};
        int size = arrows.size();
        for(int i=0; i< Math.min(size, 3); i++){
            int mul=1;
            Movable arrow = (Movable) arrows.pop();
            if(playerY >= (int) boss.getY()){
                //c.setState(c.getDownState());
                mul=+1;
                arrow.setState(arrow.getDownState());
            }else if(playerY < (int) boss.getY()){
                //c.setState(c.getUpState());
                mul=-1;
                arrow.setState(arrow.getUpState());

            }
            arrow.setX(boss.getX() + posX[i]);
            arrow.setY(boss.getY() + mul*posY[i]);
            handler.getWorld().getEntityManager().addEntity(arrow);
            //arrow.getState().move();
        }
    } 
    
}
