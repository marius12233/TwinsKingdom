/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.gfx;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mario
 */
public class BatAssets extends EntityAssets{
    

    private static Map<String, Map<String, BufferedImage[]>> batAnimations;
    
    @Override
    public void init(){
        //player = sheet.crop(0, 610, 128, 128);
        BufferedImage[] bat_down = new BufferedImage[3];
        BufferedImage[] bat_up = new BufferedImage[3];
        BufferedImage[] bat_left = new BufferedImage[3];
        BufferedImage[] bat_right = new BufferedImage[3];
        BufferedImage[] bat_up_stable = new BufferedImage[3];
        BufferedImage[] bat_right_stable = new BufferedImage[3];
        BufferedImage[] bat_left_stable = new BufferedImage[3];
        BufferedImage[] bat_down_stable = new BufferedImage[3];
        BufferedImage[] bat_down_attack = new BufferedImage[3];
        BufferedImage[] bat_up_attack= new BufferedImage[3];
        BufferedImage[] bat_left_attack= new BufferedImage[3];
        BufferedImage[] bat_right_attack=new BufferedImage[3];
        
        
        int startingX=35;
        int startingY=0;
        int height=35;
        int width = 30;
        int startingXStable = 35;
        int startingYStable=0;
        int startingXAttack=35;
        int startingYAttack=0;
        SpriteSheet sheet1, sheet2, sheet = null;            
        sheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/bat.png"));


        for(int i=0;i<3;i++){
            bat_down_attack[i] =  sheet.crop(startingXAttack + i*width , startingYAttack , width, height-5);
            bat_right_attack[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + height, width, height);
            bat_up_attack[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + 2*height, width, height-5);
            bat_left_attack[i] =  sheet.crop(startingXAttack + i*width , startingYAttack+ 3*height - 13, width, height);
            
            
            
            //sheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/EBros_Boss_samurai/Boss_Samurai_Walk.png"));
            bat_down_stable[i] =  sheet.crop(startingXAttack + i*width , startingYAttack , width, height-5);
            bat_right_stable[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + height , width, height);
            bat_up_stable[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + 2*height, width, height-5);
            bat_left_stable[i] =  sheet.crop(startingXAttack + i*width , startingYAttack+ 3*height - 13, width, height);

            
            bat_down[i] =  sheet.crop(startingXAttack + i*width , startingYAttack , width, height-5);
            bat_right[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + height-2, width, height-3);
            bat_up[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + 2*height, width, height-5);
            bat_left[i] =  sheet.crop(startingXAttack + i*width , startingYAttack+ 3*height - 13, width, height);

        }
        
        
        batAnimations = new HashMap();
        
        //Up state
        Map<String, BufferedImage[]> batUp = new HashMap();
        batUp.put("run", bat_up);
        batUp.put("stable", bat_up_stable);
        batUp.put("attack", bat_up_attack);
        batAnimations.put("up", batUp);
        
        //Down state
        Map<String, BufferedImage[]> batDown = new HashMap();
        batDown.put("run", bat_down);
        batDown.put("stable", bat_down_stable);
        batDown.put("attack", bat_down_attack);
        batAnimations.put("down", batDown);
        
        //Left State
        Map<String, BufferedImage[]> batLeft = new HashMap();
        batLeft.put("run", bat_left);
        batLeft.put("stable", bat_left_stable);
        batLeft.put("attack", bat_left_attack);
        batAnimations.put("left", batLeft);
        
        //Right State
        Map<String, BufferedImage[]> bossRight = new HashMap();
        bossRight.put("run", bat_right);
        bossRight.put("stable", bat_right_stable);
        bossRight.put("attack", bat_right_attack);
        batAnimations.put("right", bossRight);
   
    }

    @Override
    public Map<String, Map<String, BufferedImage[]>> getAnimations() {
        return batAnimations;
    }
    
}
