
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.mobs.enemies.level1;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import  twinskingdom.entities.mobs.Creature;
import  twinskingdom.entities.mobs.enemies.Boss;
import  twinskingdom.gfx.ArcherAssets;
import  twinskingdom.gfx.ArrowAssets;
import  twinskingdom.policies.HorizontalArcherPolicy;
import  twinskingdom.policies.VerticalArcherPolicy;
import  twinskingdom.utils.UtilityTimer;
import  twinskingdom.entities.mobs.states.RightMovementState;

/**
 *
 */
public class ArcherBoss extends Boss {

    ArrowAssets arrowAsset = new ArrowAssets();

    private UtilityTimer policyTimer;
    private boolean vertical = false;
    private VerticalArcherPolicy verticalPolicy;
    private HorizontalArcherPolicy horizontalPolicy;

    public ArcherBoss(float x, float y, ArcherAssets boss2Assets) {
        super(x, y, 80, Creature.DEFAULT_HEIGHT, boss2Assets);
        weapons = new LinkedList();
        for (int i = 0; i < 10; i++) {
            weapons.add(createWeapon());
        }
        //verticalPolicy = new VerticalArcherPolicy(handler, this,(int) (getY()-300), (int)(getY()+300));
        //horizontalPolicy = new HorizontalArcherPolicy(handler, this,(int) (getX()-300), (int)(getX()+300));
        setMovementPolicy(new HorizontalArcherPolicy(this, (int) (getX() - 300), (int) (getX() + 300)));
        //setMovementPolicy(verticalPolicy);
        bounds.x = 25;
        bounds.y = 30;
        bounds.width = 15;
        bounds.height = 22;
        //setState(leftState); 
        health.setMaxHealthPoints(3);
        health.setHealthPoints(3);
        health.setLives(1);
        arrowAsset.init();
        policyTimer = new UtilityTimer(10000);
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
    public void render(Graphics g) {
        state.render(g);
        g.setColor(Color.red);
        g.fillRect((int) (x - getHandler().getGameCamera().getxOffset()),
                (int) (y - getHandler().getGameCamera().getyOffset() - 5),
                (int) Math.floor((double) getWidth() / (double) health.getMaxHealthPoints() * (double) health.getHealthPoints()), 7);
    }

    @Override
    public Arrow createWeapon() {
        Arrow arrow = createWeapon((int) getX() + 50, (int) getY() + 50, 10, 10);
        return arrow;
    }

    @Override
    public Arrow createWeapon(int x, int y, int width, int height) {
        Arrow arrow = new Arrow(x, y, width, height);
        arrow.setState(new RightMovementState(arrow, arrowAsset));
        return arrow;
    }

}
