/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.mobs.enemies.level3;

import java.awt.Graphics;
import java.util.LinkedList;
import twinkingdom.entities.mobs.Creature;
import twinkingdom.entities.mobs.enemies.Boss;
import twinkingdom.entities.mobs.enemies.level1.Arrow;
import twinkingdom.entities.mobs.states.RightMovementState;
import twinkingdom.entities.statics.Portal;
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
        
        policyTimer = new UtilityTimer(10000);
    }

    @Override
    //Deve fare l'update dello stato dell'oggetto
    public void tick() {
        //Cambio policy dopo un certo tempo

        //Animations
        //Per update the index
        state.tick();
        
        //checkAttacks();
        //Movement
        getMovement();
        move();

        if (policyTimer.isTimeOver()) {
            if (vertical) {
                setMovementPolicy(new HorizontalPolicy(this, (int) (getX() - 300), (int) (getX() + 300)));
                vertical = false;
            } else {

                setMovementPolicy(new VerticalPolicy(this, (int) (getY() - 300), (int) (getY() + 300)));
                vertical = true;
            }
        }
    }
    
    @Override
    public void render(Graphics g) {
        //g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        state.render(g);
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

