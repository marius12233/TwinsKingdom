/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.mobs.enemies.level2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import  twinskingdom.entities.mobs.Creature;
import  twinskingdom.entities.mobs.enemies.Boss;
import  twinskingdom.entities.mobs.enemies.level2.FireBall;
import  twinskingdom.entities.mobs.states.RightMovementState;
import  twinskingdom.gfx.MageAssets;
import  twinskingdom.gfx.FireBallAssets;
import  twinskingdom.policies.HorizontalArcherPolicy;
import  twinskingdom.policies.VerticalArcherPolicy;
import  twinskingdom.utils.UtilityTimer;

/**
 *
 */
public class MageBoss extends Boss {

    FireBallAssets fireBallAsset = new FireBallAssets();
    private UtilityTimer policyTimer;
    private boolean vertical = false;
    private VerticalArcherPolicy verticalPolicy;
    private HorizontalArcherPolicy horizontalPolicy;

    public MageBoss(float x, float y, MageAssets mageAssets) {
        super(x, y, 80, Creature.DEFAULT_HEIGHT, mageAssets);
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
        health.setMaxHealthPoints(7);
        health.setHealthPoints(7);
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
        FireBall fireBall = new FireBall(x, y, width, height);
        fireBall.setState(new RightMovementState(fireBall, fireBallAsset));
        return fireBall;
    }

    @Override
    public void render(Graphics g) {
        state.render(g);
        g.setColor(Color.red);
        g.fillRect((int) (x - getHandler().getGameCamera().getxOffset()),
                (int) (y - getHandler().getGameCamera().getyOffset() - 5),
                (int) Math.floor((double) getWidth() / (double) health.getMaxHealthPoints() * (double) health.getHealthPoints()), 7);
    }
}
