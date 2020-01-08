/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.worlds;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;
import  twinskingdom.game.GameHandler;
import  twinskingdom.entities.EntityManager;
import  twinskingdom.entities.statics.EnchantedTree;
import  twinskingdom.entities.mobs.enemies.level1.Bat;
import  twinskingdom.entities.mobs.enemies.level1.Spider;
import  twinskingdom.gfx.BatAssets;
import  twinskingdom.policies.VerticalPolicy;
import  twinskingdom.entities.statics.Portal;
import  twinskingdom.entities.statics.grabbable.GrabbableHealthPotion;
import  twinskingdom.entities.statics.grabbable.GrabbableStar;
import  twinskingdom.events.GameEvent;
import  twinskingdom.events.GameEventType;
import  twinskingdom.gfx.ImageLoader;
import  twinskingdom.gfx.SpiderAssets;
import  twinskingdom.utils.GrabbableStarCollection;
import  twinskingdom.utils.UtilityTimer;

/**
 *
 *  
 */
public final class Forest extends World {

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
        thisClass = Forest.class;
    }

    public Forest() {
        super("res/worlds/world1/");
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
    }

    @Override
    protected void setCreatures() {

        // The player will face down when the level starts
        entityManager.getPlayer().setState(entityManager.getPlayer().getDownState());

        GrabbableStar star1 = new GrabbableStar(2688, 2750, 32, 32); //2688, 2750
        GrabbableStar star2 = new GrabbableStar(450, 958, 32, 32);   //450, 958
        GrabbableStar star3 = new GrabbableStar(1920, 1570, 32, 32); //1920, 1570

        star1 = new GrabbableStar(204, 200, 32, 32); //2688, 2750
        star2 = new GrabbableStar(260, 187, 32, 32); //450, 958
        star3 = new GrabbableStar(359, 187, 32, 32); //1920, 1570

        star1.addObserver(starCollection);
        star2.addObserver(starCollection);
        star3.addObserver(starCollection);

        entities.add(star1);
        entities.add(star2);
        entities.add(star3);

        // Enemies for star 1
        entities.add(new Bat(2700, 2868, 32, 32, new BatAssets()));
        entities.add(new Spider(3200, 2808, 32, 32, new SpiderAssets()));
        entities.add(new EnchantedTree(2139, 2792, 90, 90));

        // Enemies for star 2
        entities.add(new EnchantedTree(528, 1225, 90, 90));
        entities.add(new Spider(825, 931, 32, 32, new SpiderAssets()));

        Bat batV2 = new Bat(747, 520, 32, 32, new BatAssets());
        batV2.setMovementPolicy(new VerticalPolicy(batV2, (int) batV2.getY() - 200, (int) batV2.getY() + 200));
        entities.add(batV2);
        Bat batV3 = new Bat(847, 550, 32, 32, new BatAssets());
        batV3.setMovementPolicy(new VerticalPolicy(batV3, (int) batV3.getY() - 200, (int) batV3.getY() + 200));
        entities.add(batV3);
        Bat batV4 = new Bat(547, 500, 32, 32, new BatAssets());
        batV4.setMovementPolicy(new VerticalPolicy(batV4, (int) batV4.getY() - 200, (int) batV4.getY() + 200));
        entities.add(batV4);

        // Enemies for star 3
        entities.add(new Bat(1945, 1520, 32, 32, new BatAssets()));
        entities.add(new Bat(1900, 1600, 32, 32, new BatAssets()));
        entities.add(new Spider(1800, 1900, 32, 32, new SpiderAssets()));
        entities.add(new EnchantedTree(1480, 1030, 90, 90));

        // Enemies on the road
        entities.add(new Spider(961, 2064, 32, 32, new SpiderAssets()));
        entities.add(new Spider(1226, 630, 32, 32, new SpiderAssets()));
        entities.add(new Spider(2840, 1690, 32, 32, new SpiderAssets()));

        // Enchanted trees on the road
        entities.add(new EnchantedTree(260, 1300, 90, 90));
        entities.add(new EnchantedTree(1808, 1173, 90, 90));

        // Health potions on the road
        entities.add(new GrabbableHealthPotion(250, 2800, 32, 32));
        entities.add(new GrabbableHealthPotion(2187, 1686, 32, 32));
        entities.add(new GrabbableHealthPotion(3045, 459, 32, 32));

        // Bats on the road
        entities.add(new Bat(961, 2727, 32, 32, new BatAssets()));
        entities.add(new Bat(217, 2400, 32, 32, new BatAssets()));
        entities.add(new Bat(217, 2600, 32, 32, new BatAssets()));
        entities.add(new Bat(1633, 2781, 32, 32, new BatAssets()));
        entities.add(new Bat(1127, 201, 32, 32, new BatAssets()));
        entities.add(new Bat(2654, 2505, 32, 32, new BatAssets()));
        entities.add(new Bat(2375, 212, 32, 32, new BatAssets()));
        entities.add(new Spider(150, 300, 32, 32, new SpiderAssets()));
        entities.add(new Bat(145, 880, 32, 32, new BatAssets()));
        entities.add(new Bat(150, 980, 32, 32, new BatAssets()));
        entities.add(new Bat(155, 1050, 32, 32, new BatAssets()));
        entities.add(new Bat(150, 1100, 32, 32, new BatAssets()));
        Bat batVertical1 = new Bat(600, 500, 32, 32, new BatAssets());
        batVertical1.setMovementPolicy(new VerticalPolicy(batVertical1, (int) batVertical1.getY() - 500, (int) batVertical1.getY() + 500));
        entities.add(batVertical1);

        // Coordinates for the portal position
        portalX = 500; // For the demo
        portalY = 200; // For the demo
        portal = new Portal(portalX, portalY, 64, 64);
        entities.add(portal);

        portal.addGameEventListener(this);

        starCollection.addObserver((Observer) this);
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        super.entityManager = entityManager;
    }

    @Override
    public void tick() {
        GameHandler.getInstance().getGameCamera().centerOnEntity(super.entityManager.getPlayer());
        if (timer_vignette.isTimeOverDescendent()) {
            vignette_mode = false;
        } else if (vignette_mode == false) {
            super.entityManager.tick();
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
            g.drawString("You're in the forest near the castle. Be careful! Lots of enemies are around you!", 55, 420);
            g.drawString("Collect all the stars to reach the Archer's dungeon.", 55, 440);
        }

        // Operations done for the vignette that appears when the portal is unlocked
        if (vignette_portal) {
            g.drawImage(vignette, 36, 390, null);
            g.setFont(font);
            g.drawString("You've unlocked the portal! Find it and reach the Archer's dungeon!", 70, 420);
            g.drawString("Be ready for your first battle!", 70, 440);
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
