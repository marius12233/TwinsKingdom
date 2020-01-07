/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.worlds;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;
import twinkingdom.game.GameHandler;
import twinkingdom.entities.mobs.enemies.level3.Boss3;
import twinkingdom.entities.statics.Portal;
import twinkingdom.events.GameEvent;
import twinkingdom.events.GameEventType;
import twinkingdom.gfx.Boss3Assets;
import twinkingdom.gfx.ImageLoader;
import twinkingdom.gui.Health;
import twinkingdom.utils.GrabbableStarCollection;
import twinkingdom.utils.UtilityTimer;

/**
 *
 * @author Antonia
 */
public final class Dungeon3 extends World {

    private Boss3 boss;
    
    // Variables used to manage the vignette at the beginning of the level
    private BufferedImage vignette;
    private UtilityTimer timer_vignette;
    private boolean vignette_mode = true;
    
    // Variables used to manage the last scene with the boss
    private BufferedImage vignette_boss_1;
    private BufferedImage vignette_boss_2;
    private UtilityTimer timer_vignette_boss_1;
    private UtilityTimer timer_vignette_boss_2;
    private boolean boss_is_dead_1 = false;
    private boolean boss_is_dead_2 = false;
    
    public Dungeon3() {
        super("res/worlds/boss_level3/");
        this.starCollection = new GrabbableStarCollection(0);
    }

    @Override
    public void init() {
        super.init();
        
        // Initialization of the variables used for the vignette at the beginning
        vignette = ImageLoader.loadImage("/images/vignette.png");
        timer_vignette = new UtilityTimer(8000, true);
        
        // Initialization of the variables used for the last scene with the boss
        timer_vignette_boss_1 = new UtilityTimer();
        timer_vignette_boss_2 = new UtilityTimer();
        vignette_boss_1 = ImageLoader.loadImage("/images/cutscenes/Player_and_Paladin_1.png");
        vignette_boss_2 = ImageLoader.loadImage("/images/cutscenes/Player_and_Paladin_2.png");
    }

    @Override
    protected void setCreatures() {
        
        // The player will face down when the level starts
        entityManager.getPlayer().setState(entityManager.getPlayer().getDownState());
        
        boss = new Boss3(589, 609, new Boss3Assets());
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
    public void tick() {
        GameHandler.getInstance().getGameCamera().centerOnEntity(super.entityManager.getPlayer());
        if (timer_vignette.isTimeOverDescendent())
            vignette_mode = false;
        else if (vignette_mode == false) {
            super.entityManager.tick();
        }
        
        // Operations done for the last scene with the boss
        if (boss_is_dead_1) {
            if (timer_vignette_boss_1.isTimeOverDescendent()) {
                boss_is_dead_1 = false;
                boss_is_dead_2 = true;
            }
        }
        if (boss_is_dead_2) {
            if (timer_vignette_boss_2.isTimeOverDescendent()) {
                boss_is_dead_2 = false;
                entityManager.getPlayer().setX(510);
                entityManager.getPlayer().setY(585);
                entityManager.getPlayer().setState(entityManager.getPlayer().getRightState());
            }
        }
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        
        // Operations done when the vignette at the beginning of the level is active
        if (vignette_mode) {
            g.drawImage(vignette, 36, 390, null);
            g.setFont(font);
            g.drawString("The third knight, the Paladin, is waiting to fight against you! He's very dangerous!", 55, 420);
            g.drawString("Defeat him to gain his solid armor and reach the throne room.", 55, 440);
        }
        
        // Operations done for the last scene with the boss
        if (boss_is_dead_1)
            g.drawImage(vignette_boss_1, 0, 0, null);
        if (boss_is_dead_2)
            g.drawImage(vignette_boss_2, 0, 0, null);
    }

    @Override
    public void update(Observable o, Object arg) {
        Health h = (Health) o;
        if(h.getHealthPoints() <= 0) {
            boss_is_dead_1 = true;
            timer_vignette_boss_1 = new UtilityTimer(5000, true);
            timer_vignette_boss_2 = new UtilityTimer(5000, true);
            portal.setUnlocked(true);
        }
    }

    @Override
    public void onGameEventActionPerformed(GameEvent evt) {
        if(evt.getType() == GameEventType.PORTAL_PASSED)
                    launchGameEvent(new GameEvent(this, GameEventType.LEVEL_COMPLETED));
    }
}
