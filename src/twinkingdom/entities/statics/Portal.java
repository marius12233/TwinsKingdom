/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.statics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;
import javax.swing.event.EventListenerList;
import twinkingdom.entities.Entity;
import twinkingdom.gfx.Animation;
import twinkingdom.gfx.Assets;
import twinkingdom.utils.GrabbableStarCollection;
import twinkingdom.events.CompletedLevelEvent;
import twinkingdom.events.GameEvent;
import twinkingdom.events.GameEventListener;
import twinkingdom.events.GameEventType;

/**
 *
 * @author mario
 */
public class Portal extends StaticEntity  {

    private boolean unlocked = false;

    protected EventListenerList listenerList = new EventListenerList();

    public void addGameEventListener(GameEventListener listener) {
        listenerList.add(GameEventListener.class, listener);
    }

    public void removeGameEventListener(GameEventListener listener) {
        listenerList.remove(GameEventListener.class, listener);
    }

    void launchGameEvent(GameEvent evt) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i = i + 2) {
            if (listeners[i] == GameEventListener.class) {
                ((GameEventListener) listeners[i + 1]).onGameEventActionPerformed(evt);
            }
        }
    }
    
    public Portal(float x, float y, int width, int height) {
        super(x, y, width, height);
        bounds.x = 1;
        bounds.y = 1;
        bounds.width = 50;
        bounds.height = 50;
        animation = new Animation(50, Assets.portal);

    }
    
    

    @Override
    public void tick() {
        animation.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    public BufferedImage getCurrentAnimationFrame() {
        return animation.getCurrentFrame();
    }

    @Override
    public void actionOnCollision(Entity e) {

        if (unlocked) {
            System.out.println("PORTALE SBLOCCATO!");
            /*handler.getGame().changeLevel();
            for (Observer o : observers) {

                o.update(this, this);
            }*/
            this.launchGameEvent(new GameEvent(this, GameEventType.PORTAL_PASSED));
        }
    }


    
    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }
}
