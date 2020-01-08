/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.worlds;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;
import  twinskingdom.game.GameHandler;
import  twinskingdom.entities.mobs.enemies.level2.MageBoss;
import  twinskingdom.entities.statics.Portal;
import  twinskingdom.events.GameEvent;
import  twinskingdom.events.GameEventType;
import  twinskingdom.gfx.MageAssets;
import  twinskingdom.gfx.ImageLoader;
import  twinskingdom.gui.Health;
import  twinskingdom.utils.GrabbableStarCollection;
import  twinskingdom.utils.UtilityTimer;

/**
  This class implements the second dungeon level, setting their own characters and managing
 * vignettes appearance. Moreover, game dynamics depend on the observable boss
 * life, whose health points has to be equal to 0 to complete the level. 
 */
public final class Dungeon2 extends World {

    private MageBoss boss;

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

    public Dungeon2() {
        super("res/worlds/dungeon2/");
        this.starCollection = new GrabbableStarCollection(0);
    }

    /***
     * This method initializes vignettes' features and timers.
     */
    @Override
    public void init() {
        super.init();

        // Initialization of the variables used for the vignette at the beginning
        vignette = ImageLoader.loadImage("/images/vignette.png");
        timer_vignette = new UtilityTimer(8000, true);

        // Initialization of the variables used for the last scene with the boss
        timer_vignette_boss_1 = new UtilityTimer();
        timer_vignette_boss_2 = new UtilityTimer();
        vignette_boss_1 = ImageLoader.loadImage("/images/cutscenes/Player_and_Mage_1.png");
        vignette_boss_2 = ImageLoader.loadImage("/images/cutscenes/Player_and_Mage_2.png");
    }

    /**
     * The method populates the level with its own characters, in particular the
     * level boss (the mage). Also player positions are setted. 
     */
    @Override
    protected void setCreatures() {

        // The player will face down when the level starts
        entityManager.getPlayer().setState(entityManager.getPlayer().getDownState());

        //Player
        entityManager.getPlayer().setX(736);
        entityManager.getPlayer().setY(768);

        //Boss
        boss = new MageBoss(500f, 550f, new MageAssets());
        entities.add(boss);

        boss.getLifeObservable().addObserver((Observer) this);

        //Portal
        portal = new Portal(1000, 332, 64, 64);
        entities.add(portal);

        portal.addGameEventListener(this);

    }

    /***
     * The tick method is used to manage vignettes setting and to center the game
     * camera on the player, getted by entity manager structure. 
     */
    @Override
    public void tick() {
        GameHandler.getInstance().getGameCamera().centerOnEntity(super.entityManager.getPlayer());
        if (timer_vignette.isTimeOverDescendent()) {
            vignette_mode = false;
        } else if (vignette_mode == false) {
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
                entityManager.getPlayer().setX(520);
                entityManager.getPlayer().setY(585);
                entityManager.getPlayer().setState(entityManager.getPlayer().getRightState());
            }
        }
    }

    /***
     * The render method provides to make the vignettes appear and their related
     * images.
     * @param g 
     */
    @Override
    public void render(Graphics g) {
        super.render(g);

        // Operations done when the vignette at the beginning of the level is active
        if (vignette_mode) {
            g.drawImage(vignette, 36, 390, null);
            g.setFont(font);
            g.setColor(Color.black);
            g.drawString("The second knight, the Mage, is waiting to fight against you! He's very dangerous!", 55, 420);
            g.drawString("Defeat him to gain his astounding spells and reach the castle.", 55, 440);
        }

        // Operations done for the last scene with the boss
        if (boss_is_dead_1) {
            g.drawImage(vignette_boss_1, 0, 0, null);
        }
        if (boss_is_dead_2) {
            g.drawImage(vignette_boss_2, 0, 0, null);
        }
    }

    /***
     * The update method depends on boss level life, in order to ublock the
     * portal and to make vignette appear. These latter operations are done only
     * if 0 boss health points have reached, representing level completion. 
     * @param o
     * @param arg 
     */
    @Override
    public void update(Observable o, Object arg) {
        Health h = (Health) o;
        if (h.getHealthPoints() <= 0) {
            boss_is_dead_1 = true;
            timer_vignette_boss_1 = new UtilityTimer(5000, true);
            timer_vignette_boss_2 = new UtilityTimer(5000, true);
            portal.setUnlocked(true);
        }
    }

    @Override
    public void onGameEventActionPerformed(GameEvent evt) {
        if (evt.getType() == GameEventType.PORTAL_PASSED) {
            launchGameEvent(new GameEvent(this, GameEventType.LEVEL_COMPLETED));
        }
    }
}
