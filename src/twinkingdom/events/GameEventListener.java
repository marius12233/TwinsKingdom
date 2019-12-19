/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.events;

import java.util.EventListener;
import java.util.EventObject;

/**
 *
 * @author bened
 */
public interface GameEventListener extends EventListener {
  public void onGameEventActionPerformed(GameEvent evt);
}
