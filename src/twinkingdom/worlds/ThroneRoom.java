/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.worlds;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import twinkingdom.entities.mobs.enemies.finalLevel.FinalEnemiesManager;
import twinkingdom.entities.mobs.enemies.finalLevel.QueenAnsia;
import twinkingdom.entities.mobs.enemies.finalLevel.LadyParanoia;
import twinkingdom.entities.mobs.enemies.level1.Bat;
import twinkingdom.entities.mobs.enemies.level1.Spider;
import twinkingdom.entities.mobs.enemies.level2.BlueSnake;
import twinkingdom.entities.mobs.enemies.level2.Gargoyle;
import twinkingdom.entities.mobs.enemies.level2.RedSnake;
import twinkingdom.entities.mobs.enemies.level3.EnchantedArmor;
import twinkingdom.entities.statics.Portal;
import twinkingdom.events.GameEvent;
import twinkingdom.events.GameEventType;
import twinkingdom.gfx.ArmorAssets;
import twinkingdom.gfx.BatAssets;
import twinkingdom.gfx.BlueSnakeAssets;
import twinkingdom.gfx.GargoyleAssets;
import twinkingdom.gfx.LadyParanoiaAssets;
import twinkingdom.gfx.QueenAnsiaAssets;
import twinkingdom.gfx.RedSnakeAssets;
import twinkingdom.gfx.SpiderAssets;
import twinkingdom.gui.Health;
import twinkingdom.policies.HorizontalPolicy;
import twinkingdom.policies.VerticalPolicy;
import twinkingdom.utils.GrabbableStarCollection;

/**
 *
 * @author Angelica
 */
public class ThroneRoom extends World{
    
    private QueenAnsia qa;
    private LadyParanoia lp;
    private int lpInitLife; //lady Paranoia initial healthpoints
    private FinalEnemiesManager finalManager;
    
    public ThroneRoom() {
        super("res/worlds/final_level/");
        this.starCollection = new GrabbableStarCollection(0);
    }

    @Override
    public void tick(){
        super.tick();
        if(qa!=null && qa.getHealthPoints()==0) {
             System.out.println("BRAVO");
            launchGameEvent(new GameEvent(this, GameEventType.GAME_COMPLETED));
           
        }
    }
    @Override
    public void init() {
        super.init();
        finalManager=new FinalEnemiesManager();
        finalManager.addObserver(lp);
    }
    
    /*@Override
    public void render(Graphics g){
        entityManager.render(g);
    }*/

    @Override
    protected void setCreatures() {
        
       lp = new LadyParanoia(589, 609, new LadyParanoiaAssets());
       entities.add(lp);
       lp.getLifeObservable().addObserver((Observer) this);
       lpInitLife=lp.getHealthPoints();
     
       
       System.out.println("Final Level set creatures...");
        entityManager.getPlayer().setX(736);
        entityManager.getPlayer().setY(900);
        
        System.out.println("Lunghezza entity manager: "+entityManager.getEntities().size());
    }

    @Override
    public void update(Observable o, Object arg) {
        Health h = (Health) o;
        System.out.println("NUMERO VITA:"+h.getHealthPoints());
        
        //if lady Paranoia loses (1/3)*its own healthpoints, the first enemies wave attacks the player
        if (h.getHealthPoints() == (2*lpInitLife/3)) {
            Bat bat=new Bat(583, 1074, 32, 32, new BatAssets());
            //EnchantedArmor ea=new EnchantedArmor(906, 940, 64, 64, new ArmorAssets());
            /*
            EnchantedArmor ea1=new EnchantedArmor(214, 920, 64, 64, new ArmorAssets());
            Bat bat=new Bat(583, 1074, 32, 32, new BatAssets());
            Bat bat1=new Bat(583, 651, 32, 32, new BatAssets());
            Spider spider=new Spider(726, 800, 32, 32, new SpiderAssets());
            Spider spider1=new Spider(179, 600, 32, 32, new SpiderAssets());
            Spider spider2=new Spider(175, 1161, 32, 32, new SpiderAssets());
            Gargoyle gargoyle=new Gargoyle(598,1362,64,64, new GargoyleAssets());
            gargoyle.setMovementPolicy(new VerticalPolicy(gargoyle, (int) gargoyle.getY() - 200, (int) gargoyle.getY() + 300));
            RedSnake redSnake1 = new RedSnake(859, 1239, 32, 32, new RedSnakeAssets());
            redSnake1.setMovementPolicy(new HorizontalPolicy(redSnake1, (int) redSnake1.getX() - 50, (int) redSnake1.getX() + 600));
       */
            
            //RedSnake snake=new RedSnake()
           // this.entityManager.addEntity(ea);
            this.entityManager.addEntity(bat);
            /*
            this.entityManager.addEntity(ea1);
            this.entityManager.addEntity(bat);
            this.entityManager.addEntity(bat1);
            this.entityManager.addEntity(spider);
            this.entityManager.addEntity(spider1);
            this.entityManager.addEntity(spider2);
            this.entityManager.addEntity(gargoyle);
            this.entityManager.addEntity(redSnake1);
            */
            //finalManager.addEntity(ea);
            finalManager.addEntity(bat);
            /*
            finalManager.addEntity(ea1);
            finalManager.addEntity(bat);
            finalManager.addEntity(bat1);
            finalManager.addEntity(spider);
            finalManager.addEntity(spider1);
            finalManager.addEntity(spider2);
            finalManager.addEntity(gargoyle);
            finalManager.addEntity(redSnake1);
            */
            lp.setX(580);
            lp.setY(300);
            lp.freeze();
            
        }
        
         //if lady Paranoia loses (2/3)*its own healthpoints, the second enemies wave attacks the player
        if (h.getHealthPoints() == (lpInitLife/3)) {
            Bat bat=new Bat(400, 920, 32, 32, new BatAssets());
            //EnchantedArmor ea=new EnchantedArmor(906, 840, 64, 64, new ArmorAssets());
            /*
            EnchantedArmor ea1=new EnchantedArmor(214, 820, 64, 64, new ArmorAssets());
            EnchantedArmor ea2=new EnchantedArmor(190,1269, 64, 64, new ArmorAssets());
            Bat bat=new Bat(400, 920, 32, 32, new BatAssets());
            Bat bat1=new Bat(583, 700, 32, 32, new BatAssets());
            Spider spider=new Spider(726, 800, 32, 32, new SpiderAssets());
            Spider spider1=new Spider(179, 800, 32, 32, new SpiderAssets());
            Spider spider2=new Spider(175, 961, 32, 32, new SpiderAssets());
            Gargoyle gargoyle=new Gargoyle(1000,1300,64,64, new GargoyleAssets());
            gargoyle.setMovementPolicy(new VerticalPolicy(gargoyle, (int) gargoyle.getY() - 300, (int) gargoyle.getY() + 300));
            RedSnake redSnake1 = new RedSnake(589,1293, 32, 32, new RedSnakeAssets());
            redSnake1.setMovementPolicy(new VerticalPolicy(redSnake1, (int) redSnake1.getY() - 200, (int) redSnake1.getY() + 300));
            */
            this.entityManager.addEntity(bat);
            //RedSnake snake=new RedSnake()
            //this.entityManager.addEntity(ea);
            /*
            this.entityManager.addEntity(ea1);
            this.entityManager.addEntity(ea2);
            this.entityManager.addEntity(bat);
            this.entityManager.addEntity(bat1);
            this.entityManager.addEntity(spider);
            this.entityManager.addEntity(spider1);
            this.entityManager.addEntity(spider2);
            this.entityManager.addEntity(gargoyle);
            //this.entityManager.addEntity(redSnake);
            this.entityManager.addEntity(redSnake1);
            */
            finalManager.addEntity(bat);
            //finalManager.addEntity(ea);
            /*
            finalManager.addEntity(ea1);
            finalManager.addEntity(ea2);
            finalManager.addEntity(bat);
            finalManager.addEntity(bat1);
            finalManager.addEntity(spider);
            finalManager.addEntity(spider1);
            finalManager.addEntity(spider2);
            finalManager.addEntity(gargoyle);
            //finalManager.addEntity(redSnake);
            finalManager.addEntity(redSnake1);
            */
            lp.setX(580);
            lp.setY(300);
            lp.freeze();
        }
        
        if (h.getHealthPoints() == 1) {
            //EnchantedArmor ea=new EnchantedArmor(906, 940, 64, 64, new ArmorAssets());
            //Bat bat=new Bat(1006, 1100, 32, 32, new BatAssets());
            //Spider spider=new Spider(726, 800, 32, 32, new SpiderAssets());
            //this.entityManager.addEntity(ea);
            //this.entityManager.addEntity(bat);
            //this.entityManager.addEntity(spider);
            //finalManager.addEntity(ea);
            //ea.addObserver(finalManager);
            //finalManager.addEntity(bat);
            //finalManager.addEntity(spider);
            
            qa=new QueenAnsia(547, 446, new QueenAnsiaAssets());
            this.entityManager.addEntity(qa);
            finalManager.addEntity(qa);
            qa.addObserver(finalManager);
            lp.setX(634);
            lp.setY(446);
            entityManager.getPlayer().setX(599);
            entityManager.getPlayer().setY(446);
            lp.freeze();
            lp.setFinalPosition();
        }
        
        if(h.getHealthPoints() <= 0){
            //VIGNETTA
            
            
            launchGameEvent(new GameEvent(this, GameEventType.GAME_OVER));
        }
        /*
        if((h.getHealthPoints() <= 7) && (h.getHealthPoints() >= 5)) {
            if (help){
                System.out.println("LIVELLO UNOOOOO!");
                this.entityManager.addEntity(new EnchantedArmor(906,940,64,64, new ArmorAssets()));
                this.entityManager.addEntity(new Bat(1006,1100,32,32, new BatAssets()));
                this.entityManager.addEntity(new Spider(726,800,32,32, new SpiderAssets()));
                System.out.println("Sposto la lady!");
                lp.setX(600);
                lp.setY(350);
                help=false;
            }
            else
                System.out.println("SONO ELSE LIVELLO UNOOOOO!");
        }
        if((h.getHealthPoints() <= 4) && (h.getHealthPoints() >= 2)) {
            if (help2){
                System.out.println("LIVELLO DUEEEE!");
                this.entityManager.addEntity(new EnchantedArmor(906,940,64,64, new ArmorAssets()));
                this.entityManager.addEntity(new BlueSnake(1006,1100,64,64, new BlueSnakeAssets()));
                Gargoyle gargoyle1 = new Gargoyle(726,700,64,64, new GargoyleAssets());
                gargoyle1.setMovementPolicy(new VerticalPolicy(gargoyle1, (int) gargoyle1.getY() - 100, (int) gargoyle1.getY() + 300));
                this.entityManager.addEntity(gargoyle1);
                System.out.println("Sposto la lady!");
                lp.setX(590);
                lp.setY(340);
                help2=false;
            }
            else
                System.out.println("SONO ELSE LIVELLO DUEEEE!");
        }
        else if(h.getHealthPoints() <= 0) {
            //portal.setUnlocked(true);
        }
                */
    }

    @Override
    public void onGameEventActionPerformed(GameEvent evt) {
        //if(evt.getType() == GameEventType.PORTAL_PASSED)
                    //launchGameEvent(new GameEvent(this, GameEventType.GAME_COMPLETED));
    }

    
}
