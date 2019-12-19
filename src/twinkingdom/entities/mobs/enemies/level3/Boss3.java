/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.mobs.enemies.level3;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Map;
import twinkingdom.entities.mobs.Creature;
import twinkingdom.entities.mobs.enemies.Boss;
import twinkingdom.entities.mobs.enemies.level1.Arrow;
import twinkingdom.entities.mobs.states.RightMovementState;
import twinkingdom.entities.statics.Portal;
import twinkingdom.gfx.Animation;
import twinkingdom.gfx.ArcherAssets;
import twinkingdom.gfx.ArrowAssets;
import twinkingdom.gfx.Boss3Assets;
import twinkingdom.policies.HorizontalArcherPolicy;
import twinkingdom.policies.HorizontalPolicy;
import twinkingdom.policies.VerticalArcherPolicy;
import twinkingdom.policies.VerticalPolicy;
import twinkingdom.utils.UtilityTimer;
import twinkingdom.worlds.World;

/**
 *
 * @author Alex1
 */
public class Boss3 extends Boss {

    ArrowAssets arrowAsset = new ArrowAssets();
    
    private UtilityTimer policyTimer;
    private boolean vertical = false;
    private VerticalPolicy verticalPolicy;
    private HorizontalPolicy horizontalPolicy;

    private Animation animationAttackR, animationAttackL, animationAttackU, animationAttackD,actualAnimation;
    
    public Boss3(float x, float y, Boss3Assets boss3Assets) {
        super(x, y, 80, Creature.DEFAULT_HEIGHT, boss3Assets);
       // weapons = new LinkedList();
        /*
        for (int i = 0; i < 10; i++) {
            weapons.add(createWeapon());
        }
                */
        //verticalPolicy = new VerticalArcherPolicy(handler, this,(int) (getY()-300), (int)(getY()+300));
        //horizontalPolicy = new HorizontalArcherPolicy(handler, this,(int) (getX()-300), (int)(getX()+300));
        setMovementPolicy(new HorizontalPolicy(this, (int) (getX() - 300), (int) (getX() + 300)));
        //setMovementPolicy(verticalPolicy);
        bounds.x = 25;
        bounds.y = 30;
        bounds.width = 15;
        bounds.height = 22;
        //setState(leftState); 
        health.setHealthPoints(100);
        health.setLives(1);
        //arrowAsset.init();
        setState(state);
        
        Map<String, BufferedImage[]> asr = boss3Assets.getAnimations().get("right");
        animationAttackR = new Animation(50, asr.get("attack"));
        Map<String, BufferedImage[]> asu = boss3Assets.getAnimations().get("up");
        animationAttackU = new Animation(50, asu.get("attack"));
        Map<String, BufferedImage[]> asd = boss3Assets.getAnimations().get("down");
        animationAttackD = new Animation(50, asd.get("attack"));
        Map<String, BufferedImage[]> asl = boss3Assets.getAnimations().get("left");
        animationAttackL = new Animation(50, asl.get("attack"));
        
        policyTimer = new UtilityTimer(10000);
    }

    @Override
    //Deve fare l'update dello stato dell'oggetto
    public void tick() {
        //Cambio policy dopo un certo tempo

        //Animations
        //Per update the index
        //state.tick();
        
        if(this.getState()==leftState)
            actualAnimation=animationAttackL;
        if(this.getState()==rightState)
           actualAnimation=animationAttackR;
        if(this.getState()==downState)
            actualAnimation=animationAttackD;
        if(this.getState()==upState)
            actualAnimation=animationAttackU;
        
        actualAnimation.tick();
        //checkAttacks();
        //Movement
        getMovement();
        move();

        if (policyTimer.isTimeOver()) {
            if (vertical) {
                setMovementPolicy(new HorizontalPolicy(this, (int) (getX() - 300), (int) (getX() + 300)));
               // animationAttack.tick();
                vertical = false;
            } else {

                setMovementPolicy(new VerticalPolicy(this, (int) (getY() - 300), (int) (getY() + 300)));
               // animationAttack2.tick();
                vertical = true;
            }
        }
    }
    
    @Override
    public void render(Graphics g) {
       
        if(actualAnimation!=null)
        g.drawImage(actualAnimation.getCurrentFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width-2, height-2, null);
        //state.render(g);
    }

    @Override
    public Arrow createWeapon() {
        Arrow arrow = createWeapon((int) getX() + 50, (int) getY() + 50, 10, 10);
        return arrow;
    }

    @Override
    public Arrow createWeapon(int x, int y, int width, int height) {
        Arrow arrow = new Arrow( x, y, width, height);
        arrow.setState(new RightMovementState(arrow, arrowAsset));
        return arrow;
    }


}

