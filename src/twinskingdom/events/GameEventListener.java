/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.events;

import java.util.EventListener;

/**
 * This interface defines the common methods for handling a game event listener.
 */
public interface GameEventListener extends EventListener {

    /**
     * Defines the callback performed as a reaction to the given event.
     * @param evt Received event
     */
    public void onGameEventActionPerformed(GameEvent evt);
}
