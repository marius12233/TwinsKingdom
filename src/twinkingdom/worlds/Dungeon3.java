/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.worlds;

import java.util.Observable;
import java.util.Observer;
import twinkingdom.entities.mobs.enemies.level1.ArcherBoss;
import twinkingdom.entities.mobs.enemies.level3.Boss3;
import twinkingdom.entities.statics.Portal;
import twinkingdom.events.GameEvent;
import twinkingdom.events.GameEventType;
import twinkingdom.gfx.ArcherAssets;
import twinkingdom.gfx.Boss3Assets;
import twinkingdom.gui.Health;
import twinkingdom.utils.GrabbableStarCollection;

/**
 *
 * @author Antonia
 */
public final class Dungeon3 extends World {

    private Boss3 boss;
    
    public Dungeon3() {
        super("res/worlds/boss_level3/");
        this.starCollection = new GrabbableStarCollection(0);
    }

    @Override
    public void init() {
        super.init();

    }

    @Override
    protected void setCreatures() {
        
       
        
        boss = new Boss3(589, 609, new  Boss3Assets());
        entities.add(boss);
        
        boss.getLifeObservable().addObserver((Observer) this);

        portal = new Portal(1050, 332, 64, 64);
        entities.add(portal);

        System.out.println("Dungeon set creatures...");
        entityManager.getPlayer().setX(736);
        entityManager.getPlayer().setY(900);
       
        portal.addGameEventListener(this);
      

    }

    @Override
    public void update(Observable o, Object arg) {
        Health h = (Health) o;
        if(h.getHealthPoints() <= 0) {
            portal.setUnlocked(true);
        }
    }

    @Override
    public void onGameEventActionPerformed(GameEvent evt) {
        if(evt.getType() == GameEventType.PORTAL_PASSED)
                    launchGameEvent(new GameEvent(this, GameEventType.LEVEL_COMPLETED));
    }

}
