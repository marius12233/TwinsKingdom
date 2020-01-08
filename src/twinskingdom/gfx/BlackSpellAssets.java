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
public class BlackSpellAssets extends EntityAssets {

    private static Map<String, Map<String, BufferedImage[]>> blackSpellAnimations;

    public void init() {
        //player = sheet.crop(0, 610, 128, 128);
        BufferedImage[] blackSpell_down = new BufferedImage[8];
        BufferedImage[] blackSpell_up = new BufferedImage[8];
        BufferedImage[] blackSpell_left = new BufferedImage[8];
        BufferedImage[] blackSpell_right = new BufferedImage[8];
        BufferedImage[] blackSpell_up_stable = new BufferedImage[8];
        BufferedImage[] blackSpell_right_stable = new BufferedImage[8];
        BufferedImage[] blackSpell_left_stable = new BufferedImage[8];
        BufferedImage[] blackSpell_down_stable = new BufferedImage[8];
        BufferedImage[] blackSpell_down_attack = new BufferedImage[8];
        BufferedImage[] blackSpell_up_attack = new BufferedImage[8];
        BufferedImage[] blackSpell_left_attack = new BufferedImage[8];
        BufferedImage[] blackSpell_right_attack = new BufferedImage[8];

        int startingX = 0;
        int startingY = 0;
        int height = 64;
        int width = 64;
        int startingXStable = 0;
        int startingYStable = 0;
        int startingXAttack = 0;
        int startingYAttack = 0;
        SpriteSheet sheet1, sheet2, sheet = null;
        sheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/blackSpell.png"));

        for (int i = 0; i < 8; i++) {
            blackSpell_down_attack[i] = sheet.crop(startingXAttack + i * width, startingYAttack + 6 * height, width, height);
            blackSpell_right_attack[i] = sheet.crop(startingXAttack + i * width, startingYAttack + 4 * height, width, height);
            blackSpell_up_attack[i] = sheet.crop(startingXAttack + i * width, startingYAttack + 2 * height, width, height);
            blackSpell_left_attack[i] = sheet.crop(startingXAttack + i * width, startingYAttack, width, height);

            //sheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/EBros_blackSpell_samurai/blackSpell_Samurai_Walk.png"));
            blackSpell_down_stable[i] = sheet.crop(startingXAttack + i * width, startingYAttack + 6 * height, width, height);
            blackSpell_right_stable[i] = sheet.crop(startingXAttack + i * width, startingYAttack + 4 * height, width, height);
            blackSpell_up_stable[i] = sheet.crop(startingXAttack + i * width, startingYAttack + 2 * height, width, height);
            blackSpell_left_stable[i] = sheet.crop(startingXAttack + i * width, startingYAttack, width, height);

            blackSpell_down[i] = sheet.crop(startingXAttack + i * width, startingYAttack + 6 * height, width, height);
            blackSpell_right[i] = sheet.crop(startingXAttack + i * width, startingYAttack + 4 * height, width, height);
            blackSpell_up[i] = sheet.crop(startingXAttack + i * width, startingYAttack + 2 * height, width, height);
            blackSpell_left[i] = sheet.crop(startingXAttack + i * width, startingYAttack, width, height);

        }

        blackSpellAnimations = new HashMap();

        //Up state
        Map<String, BufferedImage[]> blackSpellUp = new HashMap();
        blackSpellUp.put("run", blackSpell_up);
        blackSpellUp.put("stable", blackSpell_up_stable);
        blackSpellUp.put("attack", blackSpell_up_attack);
        blackSpellAnimations.put("up", blackSpellUp);

        //Down state
        Map<String, BufferedImage[]> blackSpellDown = new HashMap();
        blackSpellDown.put("run", blackSpell_down);
        blackSpellDown.put("stable", blackSpell_down_stable);
        blackSpellDown.put("attack", blackSpell_down_attack);
        blackSpellAnimations.put("down", blackSpellDown);

        //Left State
        Map<String, BufferedImage[]> blackSpellLeft = new HashMap();
        blackSpellLeft.put("run", blackSpell_left);
        blackSpellLeft.put("stable", blackSpell_left_stable);
        blackSpellLeft.put("attack", blackSpell_left_attack);
        blackSpellAnimations.put("left", blackSpellLeft);

        //Right State
        Map<String, BufferedImage[]> blackSpellRight = new HashMap();
        blackSpellRight.put("run", blackSpell_right);
        blackSpellRight.put("stable", blackSpell_right_stable);
        blackSpellRight.put("attack", blackSpell_right_attack);
        blackSpellAnimations.put("right", blackSpellRight);

    }

    @Override
    public Map<String, Map<String, BufferedImage[]>> getAnimations() {
        return blackSpellAnimations;
    }
}
