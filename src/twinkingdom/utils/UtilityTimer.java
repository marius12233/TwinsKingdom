/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.utils;

/**
 *
 * @author SSQ1
 */
public class UtilityTimer {

    
    public static final long DEFAULT_TIMER_ATTACK = 1000;
    private long interval, lastCall;
    private boolean isOver ;
    private long thisCall;
    private long delta;

    public UtilityTimer( ) {
        
        this.interval = DEFAULT_TIMER_ATTACK;
        this.lastCall = 0;
        this.isOver=false;
        
    }
    public UtilityTimer(long intervalMillisec ) {
        
        this.interval = intervalMillisec;
        this.lastCall =0;
        this.isOver=false;
        this.delta=0;
        
        
        
    }

    private void timerOn() {
        thisCall=System.currentTimeMillis();
     //   if (lastCall!=0){
           delta+= thisCall-lastCall; 
       // }
        
        lastCall=System.currentTimeMillis();
        
        if (delta < interval) {
            isOver=false;
            //System.out.println("DENIED ATTACK");
        } else {
            //System.out.println("puoi attaccare è passato "+delta);
            delta=0;
            isOver=true;
            
        }

    }

    public boolean isTimeOver() {
        
        timerOn();
        return isOver;
    }

    public long getInterval() {
        return this.interval;
    }

    public void setInterval(long attackTimer) {
        this.interval = attackTimer;
    }
    
    public void setAttacking(boolean value){
        this.isOver=value;
    }
}
