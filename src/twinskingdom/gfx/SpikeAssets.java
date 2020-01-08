/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.gfx;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *  
 */
public class SpikeAssets extends EntityAssets {

    private static Map<String, Map<String, BufferedImage[]>> arrowAnimations;

    @Override
    public void init() {
        //player = sheet.crop(0, 610, 128, 128);
        BufferedImage[] bat_down = new BufferedImage[1];
        BufferedImage[] bat_up = new BufferedImage[1];
        BufferedImage[] bat_left = new BufferedImage[1];
        BufferedImage[] bat_right = new BufferedImage[1];
        BufferedImage[] bat_up_stable = new BufferedImage[1];
        BufferedImage[] bat_right_stable = new BufferedImage[1];
        BufferedImage[] bat_left_stable = new BufferedImage[1];
        BufferedImage[] bat_down_stable = new BufferedImage[1];
        BufferedImage[] bat_down_attack = new BufferedImage[1];
        BufferedImage[] bat_up_attack = new BufferedImage[1];
        BufferedImage[] bat_left_attack = new BufferedImage[1];
        BufferedImage[] bat_right_attack = new BufferedImage[1];

        int startingX = 0;
        int startingY = 0;
        int height = 66;
        int width = 30;
        int height2 = 30;
        int width2 = 66;
        int startingXStable = 0;
        int startingYStable = 0;
        int startingXAttack = 0;
        int startingYAttack = 0;
        SpriteSheet downsheet = null;
        SpriteSheet upsheet = null;
        SpriteSheet leftsheet = null;
        SpriteSheet rightsheet = null;
        downsheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/spike_down.png"));
        upsheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/spike_down.png"));
        leftsheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/spike_right.png"));
        rightsheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/spike_right.png"));

        for (int i = 0; i < 1; i++) {
            bat_down_attack[i] = downsheet.crop(startingXAttack, startingYAttack, width, height);
            bat_right_attack[i] = rightsheet.crop(startingXAttack, startingYAttack, width2, height2);
            bat_up_attack[i] = upsheet.crop(startingXAttack, startingYAttack, width, height);
            bat_left_attack[i] = leftsheet.crop(startingXAttack, startingYAttack, width2, height2);

            //sheet = new SpriteSheet(ImageLoader.loadImage("/ twinskingdom/images/EBros_Boss_samurai/Boss_Samurai_Walk.png"));
            bat_down_stable[i] = downsheet.crop(startingXAttack + i * width, startingYAttack, width, height);
            bat_right_stable[i] = rightsheet.crop(startingXAttack + i * width2, startingYAttack, width2, height2);
            bat_up_stable[i] = upsheet.crop(startingXAttack + i * width, startingYAttack, width, height);
            bat_left_stable[i] = leftsheet.crop(startingXAttack + i * width2, startingYAttack, width2, height2);

            bat_down[i] = downsheet.crop(startingXAttack + i * width, startingYAttack, width, height);
            bat_right[i] = rightsheet.crop(startingXAttack + i * width2, startingYAttack, width2, height2);
            bat_up[i] = upsheet.crop(startingXAttack + i * width, startingYAttack, width, height);
            bat_left[i] = leftsheet.crop(startingXAttack + i * width2, startingYAttack, width2, height2);

        }

        arrowAnimations = new HashMap();

        //Up state
        Map<String, BufferedImage[]> batUp = new HashMap();
        batUp.put("run", bat_up);
        batUp.put("stable", bat_up_stable);
        batUp.put("attack", bat_up_attack);
        arrowAnimations.put("up", batUp);

        //Down state
        Map<String, BufferedImage[]> batDown = new HashMap();
        batDown.put("run", bat_down);
        batDown.put("stable", bat_down_stable);
        batDown.put("attack", bat_down_attack);
        arrowAnimations.put("down", batDown);

        //Left State
        Map<String, BufferedImage[]> batLeft = new HashMap();
        batLeft.put("run", bat_left);
        batLeft.put("stable", bat_left_stable);
        batLeft.put("attack", bat_left_attack);
        arrowAnimations.put("left", batLeft);

        //Right State
        Map<String, BufferedImage[]> bossRight = new HashMap();
        bossRight.put("run", bat_right);
        bossRight.put("stable", bat_right_stable);
        bossRight.put("attack", bat_right_attack);
        arrowAnimations.put("right", bossRight);

    }

    @Override
    public Map<String, Map<String, BufferedImage[]>> getAnimations() {
        return arrowAnimations;
    }

}
