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
 * @author Alex1
 */
public class WolfAssets extends EntityAssets{
    
    private static Map<String, Map<String, BufferedImage[]>> wolfAnimations;

  
    
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
        
        
        int startingX=0;
        int startingY=0;
        int height=64;
        int width = 48;
        int startingXStable = 0;
        int startingYStable=0;
        int startingXAttack=0;
        int startingYAttack=0;
        SpriteSheet sheet1, sheet2, sheet = null;            
        sheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/werewolf-NESW.png"));


        for(int i=0;i<3;i++){
            bat_down_attack[i] =  sheet.crop(startingXAttack + i*width , height*2 , width, height);
            bat_right_attack[i] =  sheet.crop(startingXAttack + i*width , height, width, height);
            bat_up_attack[i] =  sheet.crop(startingXAttack + i*width , startingY, width, height);
            bat_left_attack[i] =  sheet.crop(startingXAttack + i*width ,3*height, width, height);
            
            
            
            //sheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/EBros_Boss_samurai/Boss_Samurai_Walk.png"));
            bat_down_stable[i] =  sheet.crop(startingXAttack + i*width , height*2 , width, height);
            bat_right_stable[i] =  sheet.crop(startingXAttack + i*width , height, width, height);
            bat_up_stable[i] =  sheet.crop(startingXAttack + i*width , startingY, width, height);
            bat_left_stable[i] =  sheet.crop(startingXAttack + i*width ,3*height, width, height);

            
            bat_down[i] =  sheet.crop(startingXAttack + i*width , startingYAttack , width, height);
            bat_right[i] =  sheet.crop(startingXAttack + i*width , height, width, height);
            bat_up[i] =  sheet.crop(startingXAttack + i*width , startingY, width, height);
            bat_left[i] =  sheet.crop(startingXAttack + i*width ,3*height, width, height);

        }
        
        
        wolfAnimations = new HashMap();
        
        //Up state
        Map<String, BufferedImage[]> batUp = new HashMap();
        batUp.put("run", bat_up);
        batUp.put("stable", bat_up_stable);
        batUp.put("attack", bat_up_attack);
        wolfAnimations.put("up", batUp);
        
        //Down state
        Map<String, BufferedImage[]> batDown = new HashMap();
        batDown.put("run", bat_down);
        batDown.put("stable", bat_down_stable);
        batDown.put("attack", bat_down_attack);
        wolfAnimations.put("down", batDown);
        
        //Left State
        Map<String, BufferedImage[]> batLeft = new HashMap();
        batLeft.put("run", bat_left);
        batLeft.put("stable", bat_left_stable);
        batLeft.put("attack", bat_left_attack);
        wolfAnimations.put("left", batLeft);
        
        //Right State
        Map<String, BufferedImage[]> bossRight = new HashMap();
        bossRight.put("run", bat_right);
        bossRight.put("stable", bat_right_stable);
        bossRight.put("attack", bat_right_attack);
        wolfAnimations.put("right", bossRight);
   
    }

    @Override
    public Map<String, Map<String, BufferedImage[]>> getAnimations() {
        return wolfAnimations;
    }
    
}
