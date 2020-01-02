/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.entities.mobs.enemies.finalLevel;

import java.util.LinkedList;
import twinkingdom.entities.mobs.Creature;
import twinkingdom.entities.mobs.enemies.Boss;
import twinkingdom.entities.mobs.enemies.level2.FireBall;
import twinkingdom.entities.mobs.states.RightMovementState;
import twinkingdom.gfx.FireBallAssets;
import twinkingdom.gfx.QueenParanoiaAssets;
import twinkingdom.policies.HorizontalArcherPolicy;
import twinkingdom.policies.VerticalArcherPolicy;
import twinkingdom.utils.UtilityTimer;

/**
 *
 * @author Alex1
 */
public class QueenParanoia extends Boss {
    
    
    FireBallAssets fireBallAsset = new FireBallAssets();
    private UtilityTimer policyTimer;
    private boolean vertical = false;
    private VerticalArcherPolicy verticalPolicy;
    private HorizontalArcherPolicy horizontalPolicy;

    public QueenParanoia(float x, float y, QueenParanoiaAssets queenParanoiaAssets) {
        super(x, y, 80, Creature.DEFAULT_HEIGHT, queenParanoiaAssets);
        weapons = new LinkedList();

        //verticalPolicy = new VerticalArcherPolicy(handler, this,(int) (getY()-300), (int)(getY()+300));
        //horizontalPolicy = new HorizontalArcherPolicy(handler, this,(int) (getX()-300), (int)(getX()+300));
        setMovementPolicy(new HorizontalArcherPolicy(this, (int) (getX() - 300), (int) (getX() + 300)));
                for (int i = 0; i < 10; i++) {
            weapons.add(createWeapon());
        }
        //setMovementPolicy(verticalPolicy);
        bounds.x = 25;
        bounds.y = 30;
        bounds.width = 15;
        bounds.height = 22;
        health.setHealthPoints(1);
        health.setLives(1);
        //Asset.init();
        fireBallAsset.init();
        policyTimer = new UtilityTimer(100000);
    }

    @Override
    //Deve fare l'update dello stato dell'oggetto
    public void tick() {
        //Cambio policy dopo un certo tempo

        //Animations
        //Per update the index
        state.tick();
        //Movement
        getMovement();
        move();

        if (policyTimer.isTimeOver()) {
            if (vertical) {
                setMovementPolicy(new HorizontalArcherPolicy(this, (int) (getX() - 300), (int) (getX() + 300)));
                vertical = false;
            } else {

                setMovementPolicy(new VerticalArcherPolicy(this, (int) (getY() - 300), (int) (getY() + 300)));
                vertical = true;
            }
        }
    }

    @Override
    public FireBall createWeapon() {
        FireBall fireBall = createWeapon((int) getX() + 300, (int) getY() - 300, 48, 48);
        return fireBall;
    }

    @Override
    public FireBall createWeapon(int x, int y, int width, int height) {
        FireBall fireBall = new FireBall( x, y, width, height);
        fireBall.setState(new RightMovementState(fireBall, fireBallAsset));
        return fireBall;
    }    
    
}
