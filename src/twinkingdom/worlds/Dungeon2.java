/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.worlds;


import java.util.Observable;
import java.util.Observer;
import twinkingdom.entities.mobs.enemies.Boss;
import twinkingdom.entities.mobs.enemies.level1.ArcherBoss;
import twinkingdom.entities.mobs.enemies.level2.Crow;
import twinkingdom.entities.mobs.enemies.level2.MageBoss;
import twinkingdom.entities.mobs.enemies.level3.EnchantedArmor;
import twinkingdom.entities.mobs.enemies.level3.Ghost;
import twinkingdom.gfx.ArcherAssets;
import twinkingdom.entities.statics.Portal;
import twinkingdom.events.GameEvent;
import twinkingdom.events.GameEventType;
import twinkingdom.gfx.ArmorAssets;
import twinkingdom.gfx.Boss2Assets;
import twinkingdom.gfx.CrowAssets;
import twinkingdom.gfx.GhostAssets;
import twinkingdom.gui.Health;
import twinkingdom.utils.GrabbableStarCollection;

/**
 *
 * @author Amedeo
 */
public final class Dungeon2 extends World {

    private MageBoss boss;
    
    public Dungeon2() {
        super("res/worlds/dungeon2/");
        this.starCollection = new GrabbableStarCollection(0);
    }

    @Override
    public void init() {
        super.init();

    }

    @Override
    protected void setCreatures() {
        
        //Player
        entityManager.getPlayer().setX(736);
        entityManager.getPlayer().setY(768);
        
        //Boss
        boss = new MageBoss(500f, 550f, new  Boss2Assets());
        entities.add(boss);
        
        boss.getLifeObservable().addObserver((Observer) this);
        
        //Portal
        portal = new Portal(1000, 332, 64, 64);
        entities.add(portal);
       
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
