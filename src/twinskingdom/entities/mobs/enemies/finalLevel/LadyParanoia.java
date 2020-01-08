/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.entities.mobs.enemies.finalLevel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import twinskingdom.entities.mobs.Creature;
import twinskingdom.entities.mobs.enemies.Boss;
import twinskingdom.entities.mobs.states.RightMovementState;
import twinskingdom.entities.statics.BlackSpell;
import twinskingdom.gfx.BlackSpellAssets;
import twinskingdom.gfx.LadyParanoiaAssets;
import twinskingdom.policies.HorizontalArcherPolicy;
import twinskingdom.policies.VerticalArcherPolicy;
import twinskingdom.utils.UtilityTimer;

/**
 *
 */
public class LadyParanoia extends Boss implements Observer {

    BlackSpellAssets blackspellAsset = new BlackSpellAssets();
    private UtilityTimer policyTimer;
    private boolean vertical = false;
    private VerticalArcherPolicy verticalPolicy;
    private HorizontalArcherPolicy horizontalPolicy;
    private boolean freeze; //this attribute is used to manage the character freeze 
    private boolean finalPosition; //this attribute is used to manage the character final setting 

    public LadyParanoia(float x, float y, LadyParanoiaAssets ladyParanoiaAssets) {
        super(x, y, 80, Creature.DEFAULT_HEIGHT, ladyParanoiaAssets);
        weapons = new LinkedList();

        setMovementPolicy(new HorizontalArcherPolicy(this, (int) (getX() - 300), (int) (getX() + 300)));
        for (int i = 0; i < 10; i++) {
            weapons.add(createWeapon());
        }

        bounds.x = 5;
        bounds.y = 5;
        bounds.width = 40;
        bounds.height = 40;
        health.setMaxHealthPoints(24);
        health.setHealthPoints(24);
        health.setLives(1);
        //Asset.init();
        blackspellAsset.init();
        freeze = false;
        finalPosition = false;
        policyTimer = new UtilityTimer(100000);
    }

    @Override
    //Deve fare l'update dello stato dell'oggetto
    public void tick() {

        //if the character is not frozen, all the movements can be reproduced 
        if (!freeze) {
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
    public BlackSpell createWeapon() {
        BlackSpell blackSpell = createWeapon((int) getX() + 300, (int) getY() - 300, 48, 48);
        return blackSpell;
    }

    @Override
    public BlackSpell createWeapon(int x, int y, int width, int height) {
        BlackSpell blackSpell = new BlackSpell(x, y, width, height);
        blackSpell.setState(new RightMovementState(blackSpell, blackspellAsset));
        return blackSpell;
    }

    public void freeze() {
        freeze = true;
    }

    //This method is used to restore the normal character movements
    public void setFinalPosition() {
        finalPosition = true;
        setState(downState);
    }

    //hurt method override,implemented in order to manage the freeze condition
    @Override
    public void hurt(int amt) {
        if (!freeze || finalPosition) {
            super.hurt(amt);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        freeze = false;
        setX(589);
        setY(609);
    }
}
