/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.worlds;

import java.util.Observable;
import java.util.Observer;
import twinkingdom.entities.mobs.enemies.level1.ArcherBoss;
import twinkingdom.gfx.ArcherAssets;
import twinkingdom.entities.statics.Portal;
import twinkingdom.events.GameEvent;
import twinkingdom.events.GameEventType;
import twinkingdom.gui.Health;
import twinkingdom.utils.GrabbableStarCollection;

/**
 *
 * @author Antonia
 */
public final class Dungeon1 extends World {
    
    private ArcherBoss boss;
    
    public Dungeon1() {
        super("res/worlds/dungeon1/");
        this.starCollection = new GrabbableStarCollection(0);
    }

    @Override
    public void init() {
        super.init();

    }

    @Override
    protected void setCreatures() {
        boss = new ArcherBoss(500, 550, new  ArcherAssets());
        entities.add(boss);
        
        boss.getLifeObservable().addObserver((Observer) this);

        portal = new Portal(1200, 332, 64, 64);
        entities.add(portal);

        System.out.println("Dungeon set creatures...");
        entityManager.getPlayer().setX(736);
        entityManager.getPlayer().setY(768);
       
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
