/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.worlds;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Observable;
import  twinskingdom.events.GameEvent;
import  twinskingdom.events.GameEventType;
import  twinskingdom.gfx.ImageLoader;
import  twinskingdom.utils.GrabbableStarCollection;
import  twinskingdom.utils.UtilityTimer;

/**
 *
 *  
 */
public final class Intro extends World {

    // Variables used to manage the sequence of vignettes in the intro
    private BufferedImage vignette_intro_1, vignette_intro_2, vignette_intro_3, vignette_intro_4, vignette_intro_5;
    private UtilityTimer timer_vignette;
    private int number_vignette;

    public Intro() {
        super("res/worlds/intro/");
        this.starCollection = new GrabbableStarCollection(0);
    }

    @Override
    public void init() {
        super.init();

        // Initialization of the variables used for the sequence of vignettes in the intro
        number_vignette = 1;
        vignette_intro_1 = ImageLoader.loadImage("/images/game_intro/vignette_intro_1.png");
        vignette_intro_2 = ImageLoader.loadImage("/images/game_intro/vignette_intro_2.png");
        vignette_intro_3 = ImageLoader.loadImage("/images/game_intro/vignette_intro_3.png");
        vignette_intro_4 = ImageLoader.loadImage("/images/game_intro/vignette_intro_4.png");
        vignette_intro_5 = ImageLoader.loadImage("/images/game_intro/vignette_intro_5.png");
        timer_vignette = new UtilityTimer(5000, true);
    }

    @Override
    protected void setCreatures() {
    }

    @Override
    public void tick() {
        switch (number_vignette) {
            case 1:
                if (timer_vignette.isTimeOverDescendent()) {
                    number_vignette = 2;
                    timer_vignette = new UtilityTimer(5000, true);
                }
                break;
            case 2:
                if (timer_vignette.isTimeOverDescendent()) {
                    number_vignette = 3;
                    timer_vignette = new UtilityTimer(5000, true);
                }
                break;
            case 3:
                if (timer_vignette.isTimeOverDescendent()) {
                    number_vignette = 4;
                    timer_vignette = new UtilityTimer(5000, true);
                }
                break;
            case 4:
                if (timer_vignette.isTimeOverDescendent()) {
                    number_vignette = 5;
                    timer_vignette = new UtilityTimer(5000, true);
                }
                break;
            case 5:
                if (timer_vignette.isTimeOverDescendent()) {
                    number_vignette = 0;
                    this.launchGameEvent(new GameEvent(this, GameEventType.LEVEL_COMPLETED));
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void render(Graphics g) {
        switch (number_vignette) {
            case 1:
                g.drawImage(vignette_intro_1, 0, 0, null);
                break;
            case 2:
                g.drawImage(vignette_intro_2, 0, 0, null);
                break;
            case 3:
                g.drawImage(vignette_intro_3, 0, 0, null);
                break;
            case 4:
                g.drawImage(vignette_intro_4, 0, 0, null);
                break;
            case 5:
                g.drawImage(vignette_intro_5, 0, 0, null);
                break;
            default:
                break;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
    }

    @Override
    public void onGameEventActionPerformed(GameEvent evt) {
    }
}
