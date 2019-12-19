/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.events;

import java.util.EventObject;

/**
 *
 * @author bened
 */
public class GameEvent extends EventObject{
    
    private GameEventType type;

    public GameEvent(Object source, GameEventType type) {
        super(source);
        this.type = type;
    }
    
    public GameEventType getType() {
        return type;
    }
}
