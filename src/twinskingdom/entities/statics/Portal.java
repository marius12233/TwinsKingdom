/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.statics;

import javax.swing.event.EventListenerList;
import  twinskingdom.entities.Entity;
import  twinskingdom.gfx.Animation;
import  twinskingdom.gfx.Assets;
import  twinskingdom.events.GameEvent;
import  twinskingdom.events.GameEventListener;
import  twinskingdom.events.GameEventSource;
import  twinskingdom.events.GameEventType;

/**
 *
 *  
 */
public class Portal extends StaticEntity implements GameEventSource {

    private boolean unlocked = false;

    protected EventListenerList listenerList = new EventListenerList();

    @Override
    public void addGameEventListener(GameEventListener listener) {
        listenerList.add(GameEventListener.class, listener);
    }

    @Override
    public void removeGameEventListener(GameEventListener listener) {
        listenerList.remove(GameEventListener.class, listener);
    }

    @Override
    public void launchGameEvent(GameEvent evt) {
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
    public void touchEntity(Entity e) {

        if (unlocked) {
            this.launchGameEvent(new GameEvent(this, GameEventType.PORTAL_PASSED));
        }
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }
}
