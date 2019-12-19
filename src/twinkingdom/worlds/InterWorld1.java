/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.worlds;

import twinkingdom.events.GameEventListener;
import java.awt.Graphics;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;
import twinkingdom.RenderableLayers;
import twinkingdom.entities.Entity;
import twinkingdom.entities.EntityManager;
import twinkingdom.entities.statics.EnchantedTree;
import twinkingdom.entities.mobs.enemies.level1.Bat;
import twinkingdom.entities.mobs.enemies.level1.Monster;
import twinkingdom.entities.mobs.enemies.level1.Wolf;
import twinkingdom.gfx.BatAssets;
import twinkingdom.gfx.MonsterAssets;
import twinkingdom.gfx.WolfAssets;
import twinkingdom.gui.StarsPanel;
import twinkingdom.policies.VerticalPolicy;
import twinkingdom.entities.statics.Portal;
import twinkingdom.entities.statics.grabbable.GrabbableHealthPotion;
import twinkingdom.entities.statics.grabbable.GrabbableStar;
import twinkingdom.events.GameEvent;
import twinkingdom.events.GameEventType;
import twinkingdom.utils.GrabbableStarCollection;

/**
 *
 * @author Antonia
 */
public final class InterWorld1 extends World {

    public static Class thisClass;
    private final int STARS = 3;

    static {
        thisClass = InterWorld1.class;
    }

    public InterWorld1() {
        super("res/worlds/world1/");
        this.starCollection = new GrabbableStarCollection(STARS);
    }

    @Override
    public void init() {
        super.init();
        starCollection.addObserver((Observer) this);
    }

    @Override
    protected void setCreatures() {

        GrabbableStar star1 = new GrabbableStar(2688, 2750, 32, 32); //2688,2750
        GrabbableStar star2 = new GrabbableStar(450, 958, 32, 32);  //450,958
        GrabbableStar star3 = new GrabbableStar(1920, 1570, 32, 32); //1920,1570
        star1 = new GrabbableStar(150, 190, 32, 32); //2688,2750
        star2 = new GrabbableStar(150, 200, 32, 32);  //450,958
        star3 = new GrabbableStar(180, 170, 32, 32); //1920,1570

        star1.addObserver(starCollection);
        star2.addObserver(starCollection);
        star3.addObserver(starCollection);

        entities.add(star1);
        entities.add(star2);
        entities.add(star3);

        //enemies for the star1
        entities.add(new Bat(2700, 2868, 32, 32, new BatAssets()));
        entities.add(new Monster(3200, 2808, new MonsterAssets()));
        entities.add(new EnchantedTree(2139, 2792, 90, 90));

        //enemies for the star2
        entities.add(new EnchantedTree(528, 1225, 90, 90));
        entities.add(new Monster(825, 931, new MonsterAssets()));

        Bat batV2 = new Bat(747, 520, 32, 32, new BatAssets());
        batV2.setMovementPolicy(new VerticalPolicy(batV2, (int) batV2.getY() - 200, (int) batV2.getY() + 200));
        entities.add(batV2);
        Bat batV3 = new Bat(847, 550, 32, 32, new BatAssets());
        batV3.setMovementPolicy(new VerticalPolicy(batV3, (int) batV3.getY() - 200, (int) batV3.getY() + 200));
        entities.add(batV3);
        Bat batV4 = new Bat(547, 500, 32, 32, new BatAssets());
        batV4.setMovementPolicy(new VerticalPolicy(batV4, (int) batV4.getY() - 200, (int) batV4.getY() + 200));
        entities.add(batV4);

        //enemies for the star3
        entities.add(new Bat(1945, 1520, 32, 32, new BatAssets()));
        entities.add(new Bat(1900, 1600, 32, 32, new BatAssets()));
        entities.add(new Monster(1800, 1900, new MonsterAssets()));
        entities.add(new EnchantedTree(1480, 1030, 90, 90));

        //bosses on the road
        entities.add(new Monster(961, 2064, new MonsterAssets()));
        entities.add(new Monster(1226, 630, new MonsterAssets()));
        entities.add(new Monster(2840, 1690, new MonsterAssets()));

        //trees on the road
        entities.add(new EnchantedTree(260, 1300, 90, 90));
        entities.add(new EnchantedTree(1808, 1173, 90, 90));

        //potions on the road
        entities.add(new GrabbableHealthPotion(250, 2800, 32, 32));
        entities.add(new GrabbableHealthPotion(2187, 1686, 32, 32));
        entities.add(new GrabbableHealthPotion(3045, 459, 32, 32));

        //bats on the road
        entities.add(new Bat(961, 2727, 32, 32, new BatAssets()));
        entities.add(new Bat(217, 2400, 32, 32, new BatAssets()));
        entities.add(new Bat(217, 2600, 32, 32, new BatAssets()));
        entities.add(new Bat(1633, 2781, 32, 32, new BatAssets()));
        entities.add(new Bat(1127, 201, 32, 32, new BatAssets()));
        entities.add(new Bat(2654, 2505, 32, 32, new BatAssets()));
        entities.add(new Bat(2375, 212, 32, 32, new BatAssets()));
        entities.add(new Wolf(150, 300, 32, 32, new WolfAssets()));
        entities.add(new Bat(145, 880, 32, 32, new BatAssets()));
        entities.add(new Bat(150, 980, 32, 32, new BatAssets()));
        entities.add(new Bat(155, 1050, 32, 32, new BatAssets()));
        entities.add(new Bat(150, 1100, 32, 32, new BatAssets()));
        Bat batVertical1 = new Bat(600, 500, 32, 32, new BatAssets());
        batVertical1.setMovementPolicy(new VerticalPolicy(batVertical1, (int) batVertical1.getY() - 500, (int) batVertical1.getY() + 500));
        entities.add(batVertical1);

        //portal position settings
        portalX = 500;
        portalY = 200;
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
        super.entityManager.tick();//super.tick();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
    }

    @Override
    public void update(Observable o, Object arg) {
        GrabbableStarCollection stars = (GrabbableStarCollection) o;
        if (stars.getSize() == 3) {
            portal.setUnlocked(true);
            System.out.println("Portale sbloccato");
        }
    }

    @Override
    public void onGameEventActionPerformed(GameEvent evt) {
        if (evt.getType() == GameEventType.PORTAL_PASSED) {
            launchGameEvent(new GameEvent(this, GameEventType.LEVEL_COMPLETED));
        }
    }

}
