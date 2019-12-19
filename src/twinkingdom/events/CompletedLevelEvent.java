/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.events;

/**
 *
 * @author bened
 */

import java.util.EventListener;
import java.util.EventObject;

public class CompletedLevelEvent extends EventObject {
    
    public CompletedLevelEvent(Object source) {
        super(source);
    }
    
}
