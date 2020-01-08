/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.worlds;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;
import  twinskingdom.game.GameHandler;
import  twinskingdom.entities.EntityManager;
import  twinskingdom.entities.mobs.enemies.level2.BlueSnake;
import  twinskingdom.entities.mobs.enemies.level2.Crow;
import  twinskingdom.entities.mobs.enemies.level2.Gargoyle;
import  twinskingdom.entities.mobs.enemies.level2.Poppy;
import  twinskingdom.entities.mobs.enemies.level2.RedSnake;
import  twinskingdom.entities.mobs.enemies.level2.YellowSnake;
import  twinskingdom.policies.VerticalPolicy;
import  twinskingdom.entities.statics.Portal;
import  twinskingdom.entities.statics.grabbable.GrabbableHealthPotion;
import  twinskingdom.entities.statics.grabbable.GrabbableStar;
import  twinskingdom.events.GameEvent;
import  twinskingdom.events.GameEventType;
import  twinskingdom.gfx.BlueSnakeAssets;
import  twinskingdom.gfx.CrowAssets;
import  twinskingdom.gfx.GargoyleAssets;
import  twinskingdom.gfx.ImageLoader;
import  twinskingdom.gfx.PoppyAssets;
import  twinskingdom.gfx.RedSnakeAssets;
import  twinskingdom.gfx.YellowSnakeAssets;
import  twinskingdom.policies.HorizontalPolicy;
import  twinskingdom.utils.GrabbableStarCollection;
import  twinskingdom.utils.UtilityTimer;

/**
 *
 *  
 */
public final class Garden extends World {

    private UtilityTimer timer;
    public static Class thisClass;
    private final int STARS = 3;

    // Variables used to manage the vignette at the beginning of the level
    private BufferedImage vignette;
    private UtilityTimer timer_vignette;
    private boolean vignette_mode = true;

    // Variables used to manage the vignette that appears when the portal is unlocked
    private UtilityTimer timer_vignette_portal;
    private boolean vignette_portal = false;

    static {
        thisClass = Garden.class;
    }

    public Garden() {
        super("res/worlds/world2/");
        this.starCollection = new GrabbableStarCollection(STARS);
    }

    @Override
    public void init() {
        super.init();
        starCollection.addObserver((Observer) this);

        // Initialization of the variables used for the vignette at the beginning
        vignette = ImageLoader.loadImage("/images/vignette.png");
        timer_vignette = new UtilityTimer(8000, true);

        // Instance of the timer used for the vignette that appears when the portal is unlocked
        timer_vignette_portal = new UtilityTimer();
        timer = null;
        vignette_mode = true;
    }

    @Override
    protected void setCreatures() {

        // The player will face down when the level starts
        entityManager.getPlayer().setState(entityManager.getPlayer().getDownState());

        // Player
        entityManager.getPlayer().setX(786);
        entityManager.getPlayer().setY(293);

        // Stars
        GrabbableStar star1 = new GrabbableStar(213, 1986, 32, 32);
        GrabbableStar star2 = new GrabbableStar(1889, 1278, 32, 32);
        GrabbableStar star3 = new GrabbableStar(1922, 893, 32, 32);

        // Positions temporarely used for the demo
        star1 = new GrabbableStar(216, 173, 32, 32);  // 2688, 2750
        star2 = new GrabbableStar(333, 173, 32, 32);  // 450, 958
        star3 = new GrabbableStar(459, 173, 32, 32);  // 1920, 1570

        star1.addObserver(starCollection);
        star2.addObserver(starCollection);
        star3.addObserver(starCollection);

        entities.add(star1);
        entities.add(star2);
        entities.add(star3);

        starCollection.addObserver((Observer) this);

        // Portal
        portalX = 1875;
        portalY = 296;
        portal = new Portal(portalX, portalY, 64, 64);
        entities.add(portal);

        portal.addGameEventListener(this);

        // Potions
        entities.add(new GrabbableHealthPotion(305, 360, 32, 32));
        entities.add(new GrabbableHealthPotion(1989, 1946, 32, 32));
        entities.add(new GrabbableHealthPotion(1350, 1524, 32, 32));

        // Yellow Snakes
        entities.add(new YellowSnake(288, 280, 32, 32, new YellowSnakeAssets()));

        YellowSnake yellowSnake1 = new YellowSnake(1806, 1278, 32, 32, new YellowSnakeAssets());
        yellowSnake1.setMovementPolicy(new HorizontalPolicy(yellowSnake1, (int) yellowSnake1.getX() - 150, (int) yellowSnake1.getX() + 50));
        entities.add(yellowSnake1);

        // Red Snakes
        RedSnake redSnake1 = new RedSnake(1889, 1350, 32, 32, new RedSnakeAssets());
        redSnake1.setMovementPolicy(new VerticalPolicy(redSnake1, (int) redSnake1.getY() - 50, (int) redSnake1.getY() + 600));
        entities.add(redSnake1);

        RedSnake redSnake2 = new RedSnake(1408, 416, 32, 32, new RedSnakeAssets());
        redSnake2.setMovementPolicy(new VerticalPolicy(redSnake2, (int) redSnake2.getY() - 300, (int) redSnake2.getY() + 300));
        entities.add(redSnake2);

        RedSnake redSnake3 = new RedSnake(190, 320, 32, 32, new RedSnakeAssets());
        redSnake3.setMovementPolicy(new VerticalPolicy(redSnake3, (int) redSnake3.getY() - 50, (int) redSnake3.getY() + 600));
        entities.add(redSnake3);

        RedSnake redSnake4 = new RedSnake(415, 320, 32, 32, new RedSnakeAssets());
        redSnake4.setMovementPolicy(new VerticalPolicy(redSnake4, (int) redSnake4.getY() - 50, (int) redSnake4.getY() + 600));
        entities.add(redSnake4);

        // Blue Snakes
        BlueSnake blueSnake1 = new BlueSnake(288, 450, 32, 32, new BlueSnakeAssets());
        blueSnake1.setMovementPolicy(new HorizontalPolicy(blueSnake1, (int) blueSnake1.getX() - 50, (int) blueSnake1.getX() + 150));
        entities.add(blueSnake1);

        // Crows
        entities.add(new Crow(436, 1184, 32, 32, new CrowAssets()));
        entities.add(new Crow(644, 509, 32, 32, new CrowAssets()));
        entities.add(new Crow(470, 701, 32, 32, new CrowAssets()));

        // Gargoyles
        Gargoyle gargoyle1 = new Gargoyle(240, 1680, 64, 64, new GargoyleAssets());
        gargoyle1.setMovementPolicy(new VerticalPolicy(gargoyle1, (int) gargoyle1.getY() - 100, (int) gargoyle1.getY() + 300));
        entities.add(gargoyle1);

        Gargoyle gargoyle2 = new Gargoyle(1730, 210, 64, 64, new GargoyleAssets());
        gargoyle2.setMovementPolicy(new VerticalPolicy(gargoyle2, (int) gargoyle2.getY() - 300, (int) gargoyle2.getY() + 300));
        entities.add(gargoyle2);

        Gargoyle gargoyle3 = new Gargoyle(1570, 884, 64, 64, new GargoyleAssets());
        gargoyle3.setMovementPolicy(new VerticalPolicy(gargoyle3, (int) gargoyle3.getY() - 100, (int) gargoyle3.getY() + 50));
        entities.add(gargoyle3);

        // Poppies
        entities.add(new Poppy(285, 609, 32, 32, new PoppyAssets()));
        entities.add(new Poppy(285, 1026, 32, 32, new PoppyAssets()));
        entities.add(new Poppy(285, 1359, 32, 32, new PoppyAssets()));
        entities.add(new Poppy(1472, 884, 32, 32, new PoppyAssets()));
        entities.add(new Poppy(1422, 1524, 32, 32, new PoppyAssets()));
        entities.add(new Poppy(717, 1890, 32, 32, new PoppyAssets()));
        entities.add(new Poppy(1050, 1992, 32, 32, new PoppyAssets()));
        entities.add(new Poppy(1356, 1890, 32, 32, new PoppyAssets()));
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        super.entityManager = entityManager;
    }

    @Override
    public void tick() {
        GameHandler.getInstance().getGameCamera().centerOnEntity(super.entityManager.getPlayer());
        if (timer_vignette.isTimeOverDescendent() && timer == null) {
            vignette_mode = false;
            timer = new UtilityTimer(180000, true);
        } else if (vignette_mode == false) {
            super.entityManager.tick();
            if (timer.isTimeOverDescendent()) {
                launchGameEvent(new GameEvent(this, GameEventType.LEVEL_FAILED));
            }
        }

        if (timer_vignette_portal.isTimeOverDescendent()) {
            vignette_portal = false;
        }
    }

    @Override
    public void render(Graphics g) {
        super.render(g);

        // Operations done when the vignette at the beginning of the level is active
        if (vignette_mode) {
            g.drawImage(vignette, 36, 390, null);
            g.setFont(font);
            g.drawString("There are many strange flowers around! They're trying to put you to sleep!", 70, 420);
            g.drawString("Collect all the stars before the time's up and reach the Mage's dungeon.", 70, 440);
        } else { // Operations done when the vignette at the beginning of the level isn't active anymore
            g.setColor(Color.white);
            g.setFont(new Font("Monospaced", Font.BOLD, 25));
            g.drawString(timer.getTimeDescendent(), 378, 50);
        }

        // Operations done for the vignette that appears when the portal is unlocked
        if (vignette_portal) {
            g.drawImage(vignette, 36, 390, null);
            g.setFont(font);
            g.setColor(Color.black);
            g.drawString("You've unlocked the portal! Find it and reach the Mage's dungeon!", 70, 420);
            g.drawString("Be ready for your second battle!", 70, 440);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        GrabbableStarCollection stars = (GrabbableStarCollection) o;
        if (stars.getSize() == 3) {
            vignette_portal = true;
            timer_vignette_portal = new UtilityTimer(4000, true);
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
