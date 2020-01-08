/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.events;

import java.util.EventObject;

/**
 * This class holds simple information about events relevant to game statuses and inputs.
 */
public class GameEvent extends EventObject {

    private GameEventType type;

    public GameEvent(Object source, GameEventType type) {
        super(source);
        this.type = type;
    }

    /**
     * The methods retrieves the type of the event.
     * @return the type of the event 
     */
    public GameEventType getType() {
        return type;
    }
}
