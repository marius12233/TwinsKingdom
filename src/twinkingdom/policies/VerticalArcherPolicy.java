/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.policies;

import java.util.ArrayList;
import java.util.LinkedList;
import twinkingdom.GameHandler;
import twinkingdom.entities.mobs.Creature;
import twinkingdom.entities.Entity;
import twinkingdom.entities.mobs.enemies.Boss;
import twinkingdom.entities.mobs.enemies.level1.ArcherBoss;
import twinkingdom.entities.mobs.enemies.level1.Arrow;
import twinkingdom.utils.UtilityTimer;

/**
 *
 * @author mario
 */
public class VerticalArcherPolicy<T> extends VerticalPolicy{
    private UtilityTimer timer, timer2;
    private LinkedList<T> arrows;
    private Boss boss;
    
    public VerticalArcherPolicy(Boss c, int startX, int endX) {
        super(c, startX, endX);
        timer=new UtilityTimer(2000);
        timer2 = new UtilityTimer(1000);
        arrows = (LinkedList<T>) c.getWeapons();
        boss = c;
    }
    
    public boolean canAttack(){
        return ((handler.getWorld().getEntityManager().getPlayer().getY() <= (c.getY() +300)) || (handler.getWorld().getEntityManager().getPlayer().getY() > (c.getY()-300))); 
    }
    @Override
    public void getAction() {

        if(timer.isTimeOver() && canAttack()){
            attack();
        }
        if(timer2.isTimeOver())
            arrows.add((T) boss.createWeapon());

        super.getAction();        
    }
    
    @Override
    public void attack(){

        int playerX = (int) handler.getWorld().getEntityManager().getPlayer().getX();
        
        //if(playerY > (int) c.getY()) c.setState(c.getDownState()); else c.setState(c.getUpState());
        //c.getState().move();
        
        int posX[] = new int[]{50, 50, 50};
        int posY[] = new int[]{-80, 0, 80};
        int size = arrows.size();
        for(int i=0; i< Math.min(size, 3); i++){
            Creature arrow = (Creature) arrows.pop();
            //arrows.add(boss.createArrow());
            int mul=+1;
            if(playerX >= (int) boss.getX()){
                System.out.println("Il player sta a destra");
                mul=1;
                arrow.setState(arrow.getRightState());
            }else if(playerX < (int) boss.getX()){
                mul=-1;
                arrow.setState(arrow.getLeftState());

            }
            arrow.setX(boss.getX() + mul*posX[i]);
            arrow.setY(boss.getY() + posY[i]);
            handler.getWorld().getEntityManager().addEntity(arrow);
            //arrow.getState().move();
        }
        
        
        
        //c.setState(oldState);
        
        //c.getState().stay();
        //c.setState(leftState);
        //arrow.getState().move();
        //isAttacking=false;
    } 
    
}
