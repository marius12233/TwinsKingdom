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
public class MageAssets extends EntityAssets {

    private static Map<String, Map<String, BufferedImage[]>> boss2Animations;

    public void init() {
        //player = sheet.crop(0, 610, 128, 128);
        BufferedImage[] boss2_down = new BufferedImage[9];
        BufferedImage[] boss2_up = new BufferedImage[9];
        BufferedImage[] boss2_left = new BufferedImage[9];
        BufferedImage[] boss2_right = new BufferedImage[9];
        BufferedImage[] boss2_up_stable = new BufferedImage[9];
        BufferedImage[] boss2_right_stable = new BufferedImage[9];
        BufferedImage[] boss2_left_stable = new BufferedImage[9];
        BufferedImage[] boss2_down_stable = new BufferedImage[9];
        BufferedImage[] boss2_down_attack = new BufferedImage[7];
        BufferedImage[] boss2_up_attack = new BufferedImage[7];
        BufferedImage[] boss2_left_attack = new BufferedImage[7];
        BufferedImage[] boss2_right_attack = new BufferedImage[7];

        int startingX = 0;
        int startingYSpell = 0;
        int height = 64;
        int width = 64;
        int startingXStable = 0;
        int startingYStable = 0;
        int startingXAttack = 0;
        int startingYSlashAttack = 12 * height;
        int startingYWalk = 8 * height;
        SpriteSheet sheet1, sheet2, sheet = null;
        sheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/Boss2.png"));

        for (int i = 0; i < 9; i++) {
            if (i < 7) {
                boss2_down_attack[i] = sheet.crop(startingXAttack + i * width, startingYSpell + 2 * height, width, height);
                boss2_right_attack[i] = sheet.crop(startingXAttack + i * width, startingYSpell + 3 * height, width, height);
                boss2_up_attack[i] = sheet.crop(startingXAttack + i * width, startingYSpell, width, height);
                boss2_left_attack[i] = sheet.crop(startingXAttack + i * width, startingYSpell + height, width, height);
            }

            //sheet = new SpriteSheet(ImageLoader.loadImage("/ twinskingdom/images/EBros_boss2_samurai/boss2_Samurai_Walk.png"));
            boss2_down_stable[i] = sheet.crop(startingX, startingYWalk + 2 * height, width, height);
            boss2_right_stable[i] = sheet.crop(startingX, startingYWalk + 3 * height, width, height);
            boss2_up_stable[i] = sheet.crop(startingX, startingYWalk, width, height);
            boss2_left_stable[i] = sheet.crop(startingX, startingYWalk + height, width, height);

            boss2_down[i] = sheet.crop(startingX + i * width, startingYWalk + 2 * height, width, height);
            boss2_right[i] = sheet.crop(startingX + i * width, startingYWalk + 3 * height, width, height);
            boss2_up[i] = sheet.crop(startingX + i * width, startingYWalk, width, height);
            boss2_left[i] = sheet.crop(startingX + i * width, startingYWalk + height, width, height);

        }

        boss2Animations = new HashMap();

        //Up state
        Map<String, BufferedImage[]> boss2Up = new HashMap();
        boss2Up.put("run", boss2_up);
        boss2Up.put("stable", boss2_up_stable);
        boss2Up.put("attack", boss2_up_attack);
        boss2Animations.put("up", boss2Up);

        //Down state
        Map<String, BufferedImage[]> boss2Down = new HashMap();
        boss2Down.put("run", boss2_down);
        boss2Down.put("stable", boss2_down_stable);
        boss2Down.put("attack", boss2_down_attack);
        boss2Animations.put("down", boss2Down);

        //Left State
        Map<String, BufferedImage[]> boss2Left = new HashMap();
        boss2Left.put("run", boss2_left);
        boss2Left.put("stable", boss2_left_stable);
        boss2Left.put("attack", boss2_left_attack);
        boss2Animations.put("left", boss2Left);

        //Right State
        Map<String, BufferedImage[]> boss2Right = new HashMap();
        boss2Right.put("run", boss2_right);
        boss2Right.put("stable", boss2_right_stable);
        boss2Right.put("attack", boss2_right_attack);
        boss2Animations.put("right", boss2Right);

    }

    @Override
    public Map<String, Map<String, BufferedImage[]>> getAnimations() {
        return boss2Animations;
    }

}
