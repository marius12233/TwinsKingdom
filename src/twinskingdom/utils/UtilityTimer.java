/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.utils;

/**
 *
 *  
 */
public class UtilityTimer {

    public static final long DEFAULT_TIMER_ATTACK = 1000;
    private long interval, lastCall;
    private boolean isOver;
    private long thisCall;
    private long delta;
    private boolean descendent = false;
    private long min;
    private long sec;
    private long totalSec;

    public UtilityTimer() {

        this.interval = DEFAULT_TIMER_ATTACK;
        this.lastCall = 0;
        this.isOver = false;

    }

    public UtilityTimer(long intervalMillisec) {

        this.interval = intervalMillisec;
        this.lastCall = 0;
        this.isOver = false;
        this.delta = 0;

    }

    public UtilityTimer(long intervalMillisec, boolean descendent) {

        this.interval = intervalMillisec;
        this.lastCall = 0;
        this.isOver = false;
        this.delta = intervalMillisec;
        this.descendent = descendent;
        this.min = interval / 1000 / 60;
    }

    private void timerOn() {
        thisCall = System.currentTimeMillis();
        //   if (lastCall!=0){
        delta += thisCall - lastCall;
        // }

        lastCall = System.currentTimeMillis();

        if (delta < interval) {
            isOver = false;
        } else {
            delta = 0;
            isOver = true;

        }

    }

    public boolean isTimeOver() {

        timerOn();
        return isOver;
    }

    private void timerOnDescendent() {
        thisCall = System.currentTimeMillis();
        if (lastCall != 0) {
            delta -= thisCall - lastCall;
        }

        lastCall = System.currentTimeMillis();

        if (delta < 0) {
            delta = interval;
            isOver = true;
        } else {
            isOver = false;
        }

    }

    public boolean isTimeOverDescendent() {

        timerOnDescendent();
        return isOver;
    }

    public long getInterval() {
        return this.interval;
    }

    public void setInterval(long attackTimer) {
        this.interval = attackTimer;
    }

    public void setAttacking(boolean value) {
        this.isOver = value;
    }

    public String getTimeDescendent() {
        totalSec = delta / 1000;
        min = totalSec / 60;
        sec = totalSec - (min * 60);

        if (sec < 10) {
            return "0" + min + ":0" + sec;
        } else {
            return "0" + min + ":" + sec;
        }
    }
}
