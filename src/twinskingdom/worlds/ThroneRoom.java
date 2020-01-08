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
import  twinskingdom.entities.mobs.enemies.finalLevel.FinalEnemiesManager;
import  twinskingdom.entities.mobs.enemies.finalLevel.QueenAnsia;
import  twinskingdom.entities.mobs.enemies.finalLevel.LadyParanoia;
import  twinskingdom.entities.mobs.enemies.level1.Bat;
import  twinskingdom.entities.mobs.enemies.level1.Spider;
import  twinskingdom.entities.mobs.enemies.level2.Gargoyle;
import  twinskingdom.entities.mobs.enemies.level2.RedSnake;
import  twinskingdom.entities.mobs.enemies.level3.EnchantedArmor;
import  twinskingdom.events.GameEvent;
import  twinskingdom.events.GameEventType;
import  twinskingdom.gfx.ArmorAssets;
import  twinskingdom.gfx.BatAssets;
import  twinskingdom.gfx.GargoyleAssets;
import  twinskingdom.gfx.ImageLoader;
import  twinskingdom.gfx.LadyParanoiaAssets;
import  twinskingdom.gfx.QueenAnsiaAssets;
import  twinskingdom.gfx.RedSnakeAssets;
import  twinskingdom.gfx.SpiderAssets;
import  twinskingdom.gui.Health;
import  twinskingdom.policies.HorizontalPolicy;
import  twinskingdom.policies.VerticalPolicy;
import  twinskingdom.utils.GrabbableStarCollection;
import  twinskingdom.utils.UtilityTimer;

/**
 *
 *  
 */
public class ThroneRoom extends World {

    private QueenAnsia qa;
    private LadyParanoia lp;
    private int lpInitLife; //lady Paranoia initial healthpoints
    private FinalEnemiesManager finalManager;
    private boolean wave1 = true;
    private boolean wave2 = true;
    private boolean wave3 = true;
    private boolean check_timer = true;
    // Variables used to manage the vignette in the final level
    private BufferedImage vignette_final_1, vignette_final_2, vignette_final_3, vignette_final_4, vignette_final_5, vignette_final_6, vignette_final_7, vignette_final_8;
    private UtilityTimer timer_vignette;
    private int number_vignette;

    public ThroneRoom() {
        super("res/worlds/final_level/");
        this.starCollection = new GrabbableStarCollection(0);
    }

    @Override
    public void init() {
        super.init();
        finalManager = new FinalEnemiesManager();
        finalManager.addObserver(lp);

        // Initialization of the variables used for the vignette in the final level
        number_vignette = 1;
        vignette_final_1 = ImageLoader.loadImage("/images/cutscenes/Player_and_Queen_1.png"); // Starting scene
        vignette_final_2 = ImageLoader.loadImage("/images/cutscenes/Player_and_Queen_2.png"); // First wave of enemies
        vignette_final_3 = ImageLoader.loadImage("/images/cutscenes/Player_and_Queen_3.png"); // Second wave of enemies
        vignette_final_4 = ImageLoader.loadImage("/images/cutscenes/Player_and_Queen_4.png"); // Decision scene
        vignette_final_5 = ImageLoader.loadImage("/images/cutscenes/Player_and_Queen_5.png"); // Bad Ending 1
        vignette_final_6 = ImageLoader.loadImage("/images/cutscenes/Player_and_Queen_6.png"); // Bad Ending 2
        vignette_final_7 = ImageLoader.loadImage("/images/cutscenes/Player_and_Queen_7.png"); // Good Ending 1
        vignette_final_8 = ImageLoader.loadImage("/images/cutscenes/Player_and_Queen_8.png"); // Good Ending 2
        timer_vignette = new UtilityTimer(5000, true);
        wave1 = true;
        wave2 = true;
    }

    @Override
    public void tick() {

        switch (number_vignette) {
            case 1: // Starting scene
                if (timer_vignette.isTimeOverDescendent()) {
                    number_vignette = 0;
                }
                break;
            case 2: // First wave of enemies
                if (timer_vignette.isTimeOverDescendent()) {
                    number_vignette = 0;
                }
                break;
            case 3: // Second wave of enemies
                if (timer_vignette.isTimeOverDescendent()) {
                    number_vignette = 0;
                }
                break;
            case 4: // Decision scene
                if (timer_vignette.isTimeOverDescendent()) {
                    number_vignette = 0;
                }
                break;
            case 5: // Bad Ending 1
                if (timer_vignette.isTimeOverDescendent()) {
                    number_vignette = 6;
                    timer_vignette = new UtilityTimer(5000, true);
                }
                break;
            case 6: // Bad Ending 2
                if (timer_vignette.isTimeOverDescendent()) {
                    number_vignette = 0;
                    this.launchGameEvent(new GameEvent(this, GameEventType.GAME_BAD_ENDING));
                }
                break;
            case 7: // Good Ending 1
                if (timer_vignette.isTimeOverDescendent()) {
                    number_vignette = 8;
                    timer_vignette = new UtilityTimer(5000, true);
                }
                break;
            case 8: // Good Ending 2
                if (timer_vignette.isTimeOverDescendent()) {
                    number_vignette = 0;
                    this.launchGameEvent(new GameEvent(this, GameEventType.GAME_COMPLETED));
                }
                break;
            default:
                super.tick();
                if (qa != null && qa.getHealthPoints() == 0 && check_timer) {
                    number_vignette = 7;
                    timer_vignette = new UtilityTimer(5000, true);
                    check_timer = false;
                }
                break;
        }

    }

    @Override
    public void render(Graphics g) {

        switch (number_vignette) {
            case 1:
                g.drawImage(vignette_final_1, 0, 0, null);
                break;
            case 2:
                g.drawImage(vignette_final_2, 0, 0, null);
                break;
            case 3:
                g.drawImage(vignette_final_3, 0, 0, null);
                break;
            case 4:
                g.drawImage(vignette_final_4, 0, 0, null);
                break;
            case 5:
                g.drawImage(vignette_final_5, 0, 0, null);
                break;
            case 6:
                g.drawImage(vignette_final_6, 0, 0, null);
                break;
            case 7:
                g.drawImage(vignette_final_7, 0, 0, null);
                break;
            case 8:
                g.drawImage(vignette_final_8, 0, 0, null);
                break;
            default:
                super.render(g);
                break;
        }
    }

    @Override
    protected void setCreatures() {

        lp = new LadyParanoia(585, 437, new LadyParanoiaAssets());
        entities.add(lp);
        lp.getLifeObservable().addObserver((Observer) this);
        lpInitLife = lp.getHealthPoints();
        
        System.out.println("LP HEALTH POINTS: " + lpInitLife);

        // The player will be face up when the level starts
        entityManager.getPlayer().setState(entityManager.getPlayer().getUpState());
        entityManager.getPlayer().setX(593);
        entityManager.getPlayer().setY(567);
        entityManager.getPlayer().setHealthPoints(25);

    }

    @Override
    public void update(Observable o, Object arg) {
        Health h = (Health) o;
        //System.out.println("NUMERO VITA:"+h.getHealthPoints());
        
        //if lady Paranoia loses (1/3)*its own healthpoints, the first enemies wave attacks the player
        if ((h.getHealthPoints()>=15) && (h.getHealthPoints()<=23)) {
            if(wave1){
                wave1 = false;
                number_vignette = 2;
                timer_vignette = new UtilityTimer(5000, true);

                // Settings of the boss and the player positions
                entityManager.getPlayer().setState(entityManager.getPlayer().getUpState());
                entityManager.getPlayer().setX(593);
                entityManager.getPlayer().setY(567);

                monsters(1);

                lp.setState(lp.getDownState());
                lp.setX(580);
                lp.setY(300);
                lp.freeze();
                lp.setHealthPoints(20);
            }
            
            
        }
        
         //if lady Paranoia loses (2/3)*its own healthpoints, the second enemies wave attacks the player
        if ((h.getHealthPoints()>=8) && (h.getHealthPoints()<=15)) {
            if(wave2){
                wave2 = false;
                number_vignette = 3;
                timer_vignette = new UtilityTimer(5000, true);

                monsters(2);

                entityManager.getPlayer().setState(entityManager.getPlayer().getUpState());
                entityManager.getPlayer().setX(593);
                entityManager.getPlayer().setY(567);

                lp.setState(lp.getDownState());
                lp.setX(580);
                lp.setY(300);
                lp.freeze();
                lp.setHealthPoints(14);
            }
            
        }
        
        if ((h.getHealthPoints()>=1) && (h.getHealthPoints()<8)) {
            if(wave3){
                wave3 = false;
                number_vignette = 4;
                timer_vignette = new UtilityTimer(5000, true);

                // Settings state player
                entityManager.getPlayer().setState(entityManager.getPlayer().getUpState());

                qa = new QueenAnsia(535, 435, new QueenAnsiaAssets());
                this.entityManager.addEntity(qa);
                finalManager.addEntity(qa);
                qa.addObserver(finalManager);
                lp.setX(640);
                lp.setY(435);
                entityManager.getPlayer().setX(593);
                entityManager.getPlayer().setY(468);
                lp.freeze();
                lp.setFinalPosition();
                lp.setHealthPoints(1);
            }
                
        }
        
        if(h.getHealthPoints() <= 0){
            number_vignette = 5;
            timer_vignette = new UtilityTimer(5000, true);
        }
        
    }
    
    public void monsters(int wave){
        
        Bat bat;
        Bat bat1;
        EnchantedArmor ea;
        EnchantedArmor ea1;
        EnchantedArmor ea2;
        Spider spider;
        Spider spider1;
        Spider spider2;
        Gargoyle gargoyle;
        RedSnake redSnake1;
        
        
        switch(wave){
            case 1:
                bat=new Bat(583, 1074, 32, 32, new BatAssets());
                ea = new EnchantedArmor(906, 940, 64, 64, new ArmorAssets());
                ea1 = new EnchantedArmor(214, 920, 64, 64, new ArmorAssets());
                bat1 = new Bat(583, 651, 32, 32, new BatAssets());
                spider = new Spider(726, 800, 32, 32, new SpiderAssets());
                spider1 = new Spider(179, 600, 32, 32, new SpiderAssets());
                spider2 = new Spider(175, 1161, 32, 32, new SpiderAssets());
                gargoyle = new Gargoyle(598, 1362, 64, 64, new GargoyleAssets());
                gargoyle.setMovementPolicy(new VerticalPolicy(gargoyle, (int) gargoyle.getY() - 200, (int) gargoyle.getY() + 300));
                redSnake1 = new RedSnake(859, 1239, 32, 32, new RedSnakeAssets());
                redSnake1.setMovementPolicy(new HorizontalPolicy(redSnake1, (int) redSnake1.getX() - 50, (int) redSnake1.getX() + 600));

                this.entityManager.addEntity(ea);
                this.entityManager.addEntity(bat);
                this.entityManager.addEntity(ea1);
                this.entityManager.addEntity(bat);
                this.entityManager.addEntity(bat1);
                this.entityManager.addEntity(spider);
                this.entityManager.addEntity(spider1);
                this.entityManager.addEntity(spider2);
                this.entityManager.addEntity(gargoyle);
                this.entityManager.addEntity(redSnake1);

                //finalManager.addEntity(ea);
                finalManager.addEntity(bat);
                finalManager.addEntity(ea1);
                finalManager.addEntity(bat);
                finalManager.addEntity(bat1);
                finalManager.addEntity(spider);
                finalManager.addEntity(spider1);
                finalManager.addEntity(spider2);
                finalManager.addEntity(gargoyle);
                finalManager.addEntity(redSnake1);
                break;
            case 2:
                bat=new Bat(400, 920, 32, 32, new BatAssets());
                ea=new EnchantedArmor(906, 840, 64, 64, new ArmorAssets());
                ea1=new EnchantedArmor(214, 870, 64, 64, new ArmorAssets());
                ea2=new EnchantedArmor(190,1269, 64, 64, new ArmorAssets());
                bat1=new Bat(583, 700, 32, 32, new BatAssets());
                spider=new Spider(726, 800, 32, 32, new SpiderAssets());
                spider1=new Spider(179, 800, 32, 32, new SpiderAssets());
                spider2=new Spider(175, 961, 32, 32, new SpiderAssets());
                gargoyle=new Gargoyle(1000,1300,64,64, new GargoyleAssets());
                gargoyle.setMovementPolicy(new VerticalPolicy(gargoyle, (int) gargoyle.getY() - 300, (int) gargoyle.getY() + 300));
                redSnake1 = new RedSnake(589,1293, 32, 32, new RedSnakeAssets());
                redSnake1.setMovementPolicy(new VerticalPolicy(redSnake1, (int) redSnake1.getY() - 200, (int) redSnake1.getY() + 300));
                
                this.entityManager.addEntity(bat);
                this.entityManager.addEntity(ea);
                this.entityManager.addEntity(ea1);
                this.entityManager.addEntity(ea2);
                this.entityManager.addEntity(bat);
                this.entityManager.addEntity(bat1);
                this.entityManager.addEntity(spider);
                this.entityManager.addEntity(spider1);
                this.entityManager.addEntity(spider2);
                this.entityManager.addEntity(gargoyle);
                this.entityManager.addEntity(redSnake1);
            
                finalManager.addEntity(bat);
                finalManager.addEntity(ea);
                finalManager.addEntity(ea1);
                finalManager.addEntity(ea2);
                finalManager.addEntity(bat);
                finalManager.addEntity(bat1);
                finalManager.addEntity(spider);
                finalManager.addEntity(spider1);
                finalManager.addEntity(spider2);
                finalManager.addEntity(gargoyle);
                finalManager.addEntity(redSnake1);
                break;
            default:
                break;
        }
        
    }

    @Override
    public void onGameEventActionPerformed(GameEvent evt) {
    }

}
