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
import  twinskingdom.entities.mobs.enemies.level3.EnchantedArmor;
import  twinskingdom.entities.mobs.enemies.level3.Ghost;
import  twinskingdom.entities.mobs.enemies.level3.SpikeDown;
import  twinskingdom.entities.mobs.enemies.level3.SpikeLeft;
import  twinskingdom.entities.mobs.enemies.level3.SpikeRight;
import  twinskingdom.entities.mobs.enemies.level3.SpikeUp;
import  twinskingdom.entities.statics.Portal;
import  twinskingdom.entities.statics.grabbable.GrabbableHealthPotion;
import  twinskingdom.entities.statics.grabbable.GrabbableStar;
import  twinskingdom.events.GameEvent;
import  twinskingdom.events.GameEventType;
import  twinskingdom.gfx.ArmorAssets;
import  twinskingdom.gfx.GhostAssets;
import  twinskingdom.gfx.ImageLoader;
import  twinskingdom.gfx.Layer3Feature;
import  twinskingdom.gfx.SpikeAssets;
import  twinskingdom.policies.HorizontalPolicy;
import  twinskingdom.utils.GrabbableStarCollection;
import  twinskingdom.utils.UtilityTimer;

/**
 *
 *  
 */
public final class Castle extends World {

    public static Class thisClass;
    private final int STARS = 3;
    private Layer3Feature layerFeature;

    // Variables used to manage the vignette at the beginning of the level
    private BufferedImage vignette;
    private UtilityTimer timer_vignette;
    private boolean vignette_mode = true;

    // Variables used to manage the vignette that appears when the portal is unlocked
    private UtilityTimer timer_vignette_portal;
    private boolean vignette_portal = false;

    static {
        thisClass = Castle.class;
    }

    public Castle() {
        super("res/worlds/world3/");
        this.starCollection = new GrabbableStarCollection(STARS);
    }

    @Override
    public void init() {
        super.init();
        starCollection.addObserver((Observer) this);
        layerFeature = new Layer3Feature(entityManager.getPlayer());

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

        entityManager.getPlayer().setX(600);
        entityManager.getPlayer().setY(500);

        //stars                                                     //right positions
        GrabbableStar star1 = new GrabbableStar(2004, 1576, 32, 32); //2004,1576
        GrabbableStar star2 = new GrabbableStar(1971, 1161, 32, 32);  //1971,1161
        GrabbableStar star3 = new GrabbableStar(900, 63, 32, 32); //900,63
        star1 = new GrabbableStar(262, 128, 32, 32); //2688,2750
        star2 = new GrabbableStar(379, 128, 32, 32);  //450,958
        star3 = new GrabbableStar(472, 128, 32, 32); //1920,1570

        star1.addObserver(starCollection);
        star2.addObserver(starCollection);
        star3.addObserver(starCollection);

        entities.add(star1);
        entities.add(star2);
        entities.add(star3);

        //down spikes
        entities.add(new SpikeDown(850, 665, new SpikeAssets()));
        entities.add(new SpikeDown(1412, 665, new SpikeAssets()));
        entities.add(new SpikeDown(1826, 52, new SpikeAssets()));
        entities.add(new SpikeDown(723, 1560, new SpikeAssets()));
        entities.add(new SpikeDown(1092, 55, new SpikeAssets()));
        entities.add(new SpikeDown(472, 56, new SpikeAssets()));

        //right spikes
        entities.add(new SpikeRight(1500, 308, new SpikeAssets()));
        entities.add(new SpikeRight(1500, 520, new SpikeAssets()));
        entities.add(new SpikeRight(860, 1698, new SpikeAssets()));
        entities.add(new SpikeRight(23, 726, new SpikeAssets()));

        //up spikes
        entities.add(new SpikeUp(286, 2210, new SpikeAssets()));
        entities.add(new SpikeUp(721, 2210, new SpikeAssets()));
        entities.add(new SpikeUp(1072, 2210, new SpikeAssets()));
        entities.add(new SpikeUp(1800, 2210, new SpikeAssets()));

        //left spikes
        entities.add(new SpikeLeft(737, 300, new SpikeAssets()));
        entities.add(new SpikeLeft(737, 587, new SpikeAssets()));
        entities.add(new SpikeLeft(2210, 717, new SpikeAssets()));

        //enchanted armors
        entities.add(new EnchantedArmor(350, 2050, 64, 64, new ArmorAssets()));
        entities.add(new EnchantedArmor(877, 1400, 64, 64, new ArmorAssets()));
        entities.add(new EnchantedArmor(1689, 1975, 64, 64, new ArmorAssets()));
        entities.add(new EnchantedArmor(1484, 409, 64, 64, new ArmorAssets()));
        entities.add(new EnchantedArmor(896, 453, 64, 64, new ArmorAssets()));
        entities.add(new EnchantedArmor(857, 189, 64, 64, new ArmorAssets()));
        entities.add(new EnchantedArmor(1698, 1111, 64, 64, new ArmorAssets()));
        entities.add(new EnchantedArmor(516, 1111, 64, 64, new ArmorAssets()));
        entities.add(new EnchantedArmor(280, 209, 64, 64, new ArmorAssets()));
        entities.add(new EnchantedArmor(357, 728, 64, 64, new ArmorAssets()));

        //health potions
        entities.add(new GrabbableHealthPotion(1923, 202, 32, 32));
        entities.add(new GrabbableHealthPotion(30, 726, 32, 32));
        entities.add(new GrabbableHealthPotion(600, 1611, 32, 32));

        //ghosts
        float[] arrayx1 = new float[]{282, 420, 350};//886
        float[] arrayy1 = new float[]{1750, 1900, 1740};//735
        float[] arrayx2 = new float[]{1600, 1740, 1670};
        float[] arrayy2 = new float[]{1700, 1800, 1750};
        float[] arrayx3 = new float[]{1104, 1250, 1150};
        float[] arrayy3 = new float[]{1862, 2100, 1950};

        Ghost g1 = new Ghost(282, 1753, 32, 32, new GhostAssets(), arrayx1, arrayy1);
        g1.setMovementPolicy(new HorizontalPolicy(g1, (int) g1.getX() - 200, (int) g1.getX() + 200));
        entities.add(g1);
        Ghost g2 = new Ghost(1600, 1700, 32, 32, new GhostAssets(), arrayx2, arrayy2);
        g2.setMovementPolicy(new HorizontalPolicy(g2, (int) g2.getX() - 400, (int) g2.getX() + 400));
        entities.add(g2);
        Ghost g3 = new Ghost(1104, 1862, 32, 32, new GhostAssets(), arrayx3, arrayy3);
        g3.setMovementPolicy(new HorizontalPolicy(g3, (int) g3.getX() - 200, (int) g3.getX() + 200));
        entities.add(g3);

        //portal position settings
        portalX = 645;
        portalY = 203;
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
        layerFeature.tick();

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
        layerFeature.render(g);

        // Operations done when the vignette at the beginning of the level is active
        if (vignette_mode) {
            g.drawImage(vignette, 36, 390, null);
            g.setFont(font);
            g.drawString("You're is in the castle and it's really dark! There's only a fading light around you!", 55, 420);
            g.drawString("Collect all the stars to reach the Paladin's dungeon.", 55, 440);
        }

        // Operations done for the vignette that appears when the portal is unlocked
        if (vignette_portal) {
            g.drawImage(vignette, 36, 390, null);
            g.setFont(font);
            g.drawString("You've unlocked the portal! Find it and reach the Paladin's dungeon!", 70, 420);
            g.drawString("Be ready for your third battle!", 70, 440);
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
