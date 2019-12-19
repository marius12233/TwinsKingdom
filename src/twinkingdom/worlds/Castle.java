/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.worlds;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import twinkingdom.entities.EntityManager;
import twinkingdom.entities.mobs.enemies.level3.EnchantedArmor;
import twinkingdom.entities.mobs.enemies.level3.Ghost;
import twinkingdom.entities.mobs.enemies.level3.SpikeDown;
import twinkingdom.entities.mobs.enemies.level3.SpikeLeft;
import twinkingdom.entities.mobs.enemies.level3.SpikeRight;
import twinkingdom.entities.mobs.enemies.level3.SpikeUp;
import twinkingdom.entities.statics.Portal;
import twinkingdom.entities.statics.grabbable.GrabbableHealthPotion;
import twinkingdom.entities.statics.grabbable.GrabbableStar;
import twinkingdom.events.GameEvent;
import twinkingdom.events.GameEventType;
import twinkingdom.gfx.ArmorAssets;
import twinkingdom.gfx.GhostAssets;
import twinkingdom.gfx.Layer3Feature;
import twinkingdom.gfx.SpikeAssets;
import twinkingdom.policies.HorizontalPolicy;
import twinkingdom.utils.GrabbableStarCollection;

/**
 *
 * @author Antonia
 */
public final class Castle extends World {

    public static Class thisClass;
    private final int STARS = 3;
    private Layer3Feature layerFeature;
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
        layerFeature=new Layer3Feature(handler,entityManager.getPlayer());
        
    }

    @Override
    protected void setCreatures() {
        
        
        entityManager.getPlayer().setX(600);
        entityManager.getPlayer().setY(500);

        //stars                                                     //right positions
        GrabbableStar star1 = new GrabbableStar(2688, 2750, 32, 32); //2004,1576
        GrabbableStar star2 = new GrabbableStar(450, 958, 32, 32);  //1971,1161
        GrabbableStar star3 = new GrabbableStar(1920, 1570, 32, 32); //900,63
        star1 = new GrabbableStar(150, 190, 32, 32); //2688,2750
        star2 = new GrabbableStar(150, 200, 32, 32);  //450,958
        star3 = new GrabbableStar(180, 170, 32, 32); //1920,1570

        star1.addObserver(starCollection);
        star2.addObserver(starCollection);
        star3.addObserver(starCollection);

        entities.add(star1);
        entities.add(star2);
        entities.add(star3);

        
        //down spikes
        entities.add(new SpikeDown(850,665,new SpikeAssets()));
        entities.add(new SpikeDown(1412,665,new SpikeAssets()));
        entities.add(new SpikeDown(1826,52,new SpikeAssets()));
        entities.add(new SpikeDown(723,1560,new SpikeAssets()));
        entities.add(new SpikeDown(1092,55,new SpikeAssets()));
        
        //right spikes
        entities.add(new SpikeRight(1500,308,new SpikeAssets()));
        entities.add(new SpikeRight(1500,520,new SpikeAssets()));
        entities.add(new SpikeRight(860,1698,new SpikeAssets()));
        entities.add(new SpikeRight(12,726,new SpikeAssets()));
        
        //up spikes
        entities.add(new SpikeUp(286,2210,new SpikeAssets()));
        entities.add(new SpikeUp(721,2210,new SpikeAssets()));
        entities.add(new SpikeUp(1072,2210,new SpikeAssets()));
        entities.add(new SpikeUp(1800,2210,new SpikeAssets()));
        
        //left spikes
        
        entities.add(new SpikeLeft(737,300,new SpikeAssets()));
        entities.add(new SpikeLeft(737,587,new SpikeAssets()));
        entities.add(new SpikeLeft(2210,717,new SpikeAssets()));
        
        
        //enchanted armors
        entities.add(new EnchantedArmor(350,2050,64,64, new ArmorAssets()));
        entities.add(new EnchantedArmor(877,1400,64,64, new ArmorAssets()));
        entities.add(new EnchantedArmor(1689,1975,64,64, new ArmorAssets()));
        entities.add(new EnchantedArmor(1484,409,64,64, new ArmorAssets()));
        entities.add(new EnchantedArmor(896,453,64,64, new ArmorAssets()));
        entities.add(new EnchantedArmor(857,189,64,64, new ArmorAssets()));
        entities.add(new EnchantedArmor(1698,1111,64,64, new ArmorAssets()));
        entities.add(new EnchantedArmor(516,1111,64,64, new ArmorAssets()));
        
         //health potions
        entities.add(new GrabbableHealthPotion(1923,202, 32, 32));
        entities.add(new GrabbableHealthPotion(17,726, 32, 32));
        entities.add(new GrabbableHealthPotion(600,1611, 32, 32));
        
        //ghosts
        float[] arrayx1 = new float[]{282,420,350};//886
        float[] arrayy1 = new float[]{1750,1900,1740};//735
        float[] arrayx2 = new float[]{1600,1740,1670};
        float[] arrayy2 = new float[]{1700,1800,1750};
        float[] arrayx3 = new float[]{1104,1250,1150};
        float[] arrayy3 = new float[]{1862,2100,1950};
        
        Ghost g1=new Ghost(282,1753, 32,32, new GhostAssets(),arrayx1,arrayy1);
        g1.setMovementPolicy(new HorizontalPolicy(g1, (int) g1.getX() - 200, (int) g1.getX() + 200));
        entities.add(g1);
        Ghost g2=new Ghost(1600, 1700, 32,32, new GhostAssets(), arrayx2, arrayy2);
        g2.setMovementPolicy(new HorizontalPolicy(g2, (int) g2.getX() - 400, (int) g2.getX() + 400));
        entities.add(g2);
        Ghost g3=new Ghost(1104, 1862, 32,32, new GhostAssets(), arrayx3, arrayy3);
        g3.setMovementPolicy(new HorizontalPolicy(g3, (int) g3.getX() - 200, (int) g3.getX() + 200));
        entities.add(g3);
        
        //portal position settings
        portalX = 600;
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
        layerFeature.tick();
    }

    
    @Override
    public void render(Graphics g){
        super.render(g);
        layerFeature.render(g);
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