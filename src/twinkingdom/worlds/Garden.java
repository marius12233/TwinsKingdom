/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.worlds;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import twinkingdom.entities.EntityManager;
import twinkingdom.entities.mobs.enemies.level2.BlueSnake;
import twinkingdom.entities.mobs.enemies.level2.Crow;
import twinkingdom.entities.mobs.enemies.level2.Gargoyle;
import twinkingdom.entities.mobs.enemies.level2.Poppy;
import twinkingdom.entities.mobs.enemies.level2.RedSnake;
import twinkingdom.entities.mobs.enemies.level2.YellowSnake;
import twinkingdom.policies.VerticalPolicy;
import twinkingdom.entities.statics.Portal;
import twinkingdom.entities.statics.grabbable.GrabbableHealthPotion;
import twinkingdom.entities.statics.grabbable.GrabbableStar;
import twinkingdom.events.GameEvent;
import twinkingdom.events.GameEventType;
import twinkingdom.gfx.BlueSnakeAssets;
import twinkingdom.gfx.CrowAssets;
import twinkingdom.gfx.GargoyleAssets;
import twinkingdom.gfx.PoppyAssets;
import twinkingdom.gfx.RedSnakeAssets;
import twinkingdom.gfx.YellowSnakeAssets;
import twinkingdom.policies.HorizontalPolicy;
import twinkingdom.utils.GrabbableStarCollection;
import twinkingdom.utils.UtilityTimer;

/**
 *
 * @author Amedeo
 */
public final class Garden extends World {
    private UtilityTimer timer;
    public static Class thisClass;
    private final int STARS = 3;

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
        timer = new UtilityTimer(180000, true);
    }

    @Override
    protected void setCreatures() {
        
        //Player
        entityManager.getPlayer().setX(786);
        entityManager.getPlayer().setY(293);
        
        //Stars
        GrabbableStar star1 = new GrabbableStar(213, 1986, 32, 32); 
        GrabbableStar star2 = new GrabbableStar(1889, 1278, 32, 32);
        GrabbableStar star3 = new GrabbableStar(1922, 893, 32, 32); 
        //Per demo
        star1 = new GrabbableStar(216,173, 32, 32); //2688,2750
        star2 = new GrabbableStar(333,173, 32, 32);  //450,958
        star3 = new GrabbableStar(459,173, 32, 32); //1920,1570

        star1.addObserver(starCollection);
        star2.addObserver(starCollection);
        star3.addObserver(starCollection);

        entities.add(star1);
        entities.add(star2);
        entities.add(star3);
        
        starCollection.addObserver((Observer) this);
        
        //Portal
        portalX = 1875;
        portalY = 296;
        portal = new Portal(portalX, portalY, 64, 64);
        entities.add(portal);

        portal.addGameEventListener(this);
        
        //Potions
        entities.add(new GrabbableHealthPotion(305, 360, 32, 32));
        entities.add(new GrabbableHealthPotion(1989, 1946, 32, 32));
        entities.add(new GrabbableHealthPotion(1350, 1524, 32, 32));
        
        //Yellow Snakes
        entities.add(new YellowSnake(288, 280, 32, 32, new YellowSnakeAssets()));
        
        YellowSnake yellowSnake1 = new YellowSnake(1806, 1278, 32, 32, new YellowSnakeAssets());
        yellowSnake1.setMovementPolicy(new HorizontalPolicy(yellowSnake1, (int) yellowSnake1.getX() - 150, (int) yellowSnake1.getX() + 50));
        entities.add(yellowSnake1);
        
        //Red Snakes
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
        
        //Blue Snakes
        BlueSnake blueSnake1 = new BlueSnake(288, 450, 32, 32, new BlueSnakeAssets());
        blueSnake1.setMovementPolicy(new HorizontalPolicy(blueSnake1, (int) blueSnake1.getX() - 50, (int) blueSnake1.getX() + 150));
        entities.add(blueSnake1);
        
        //Crow 
        entities.add(new Crow(436, 1184, 32, 32, new CrowAssets()));
        entities.add(new Crow(644, 509, 32, 32, new CrowAssets()));
        entities.add(new Crow(470, 701, 32, 32, new CrowAssets()));
        
        //Gargoyles
        Gargoyle gargoyle1 = new Gargoyle(240, 1680, 64, 64, new GargoyleAssets());
        gargoyle1.setMovementPolicy(new VerticalPolicy(gargoyle1, (int) gargoyle1.getY() - 100, (int) gargoyle1.getY() + 300));
        entities.add(gargoyle1);
        
        Gargoyle gargoyle2 = new Gargoyle(1730, 210, 64, 64, new GargoyleAssets());
        gargoyle2.setMovementPolicy(new VerticalPolicy(gargoyle2, (int) gargoyle2.getY() - 300, (int) gargoyle2.getY() + 300));
        entities.add(gargoyle2);
        
        Gargoyle gargoyle3 = new Gargoyle(1570, 884, 64, 64, new GargoyleAssets());
        gargoyle3.setMovementPolicy(new VerticalPolicy(gargoyle3, (int) gargoyle3.getY() - 100, (int) gargoyle3.getY() + 50));
        entities.add(gargoyle3);
        
        //Poppy
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
        super.entityManager.tick();
        if (timer.isTimeOverDescendent()) {
            System.out.println("Tempo scaduto!");
            launchGameEvent(new GameEvent(this, GameEventType.LEVEL_FAILED));
        }
        //Stampa di prova per posizionare gli oggetti sulla mappa
       // System.out.println("Cordinate X:" + entityManager.getPlayer().getX() + " Y:" + entityManager.getPlayer().getY());
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        g.setColor(Color.white);
        g.setFont(new Font("Monospaced", Font.BOLD, 25));
        g.drawString(timer.getTimeDescendent(), 378, 50);
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
