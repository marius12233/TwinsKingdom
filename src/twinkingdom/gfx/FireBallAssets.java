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
public class FireBallAssets extends EntityAssets {
    
     private static Map<String, Map<String, BufferedImage[]>> fireballAnimations;

  
    
    public void init(){
        //player = sheet.crop(0, 610, 128, 128);
        BufferedImage[] fireball_down = new BufferedImage[8];
        BufferedImage[] fireball_up = new BufferedImage[8];
        BufferedImage[] fireball_left = new BufferedImage[8];
        BufferedImage[] fireball_right = new BufferedImage[8];
        BufferedImage[] fireball_up_stable = new BufferedImage[8];
        BufferedImage[] fireball_right_stable = new BufferedImage[8];
        BufferedImage[] fireball_left_stable = new BufferedImage[8];
        BufferedImage[] fireball_down_stable = new BufferedImage[8];
        BufferedImage[] fireball_down_attack = new BufferedImage[8];
        BufferedImage[] fireball_up_attack= new BufferedImage[8];
        BufferedImage[] fireball_left_attack= new BufferedImage[8];
        BufferedImage[] fireball_right_attack=new BufferedImage[8];
        
        
        int startingX=0;
        int startingY=0;
        int height= 64;
        int width = 64;
        int startingXStable = 0;
        int startingYStable=0;
        int startingXAttack=0;
        int startingYAttack=0;
        SpriteSheet sheet1, sheet2, sheet = null;            
        sheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/fireball.png"));


        for(int i=0;i<8;i++){
            fireball_down_attack[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + 6*height , width, height);
            fireball_right_attack[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + 4*height, width, height);
            fireball_up_attack[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + 2*height, width, height);
            fireball_left_attack[i] =  sheet.crop(startingXAttack + i*width , startingYAttack , width, height);
            
            
            
            //sheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/EBros_fireball_samurai/fireball_Samurai_Walk.png"));
            fireball_down_stable[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + 6*height , width, height);
            fireball_right_stable[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + 4*height, width, height);
            fireball_up_stable[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + 2*height, width, height);
            fireball_left_stable[i] =  sheet.crop(startingXAttack + i*width , startingYAttack , width, height);

            
            fireball_down[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + 6*height , width, height);
            fireball_right[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + 4*height, width, height);
            fireball_up[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + 2*height, width, height);
            fireball_left[i] =  sheet.crop(startingXAttack + i*width , startingYAttack , width, height);

        }
        
        
        fireballAnimations = new HashMap();
        
        //Up state
        Map<String, BufferedImage[]> fireballUp = new HashMap();
        fireballUp.put("run", fireball_up);
        fireballUp.put("stable", fireball_up_stable);
        fireballUp.put("attack", fireball_up_attack);
        fireballAnimations.put("up", fireballUp);
        
        //Down state
        Map<String, BufferedImage[]> fireballDown = new HashMap();
        fireballDown.put("run", fireball_down);
        fireballDown.put("stable", fireball_down_stable);
        fireballDown.put("attack", fireball_down_attack);
        fireballAnimations.put("down", fireballDown);
        
        //Left State
        Map<String, BufferedImage[]> fireballLeft = new HashMap();
        fireballLeft.put("run", fireball_left);
        fireballLeft.put("stable", fireball_left_stable);
        fireballLeft.put("attack", fireball_left_attack);
        fireballAnimations.put("left", fireballLeft);
        
        //Right State
        Map<String, BufferedImage[]> fireballRight = new HashMap();
        fireballRight.put("run", fireball_right);
        fireballRight.put("stable", fireball_right_stable);
        fireballRight.put("attack", fireball_right_attack);
        fireballAnimations.put("right", fireballRight);
   
    }

    @Override
    public Map<String, Map<String, BufferedImage[]>> getAnimations() {
        return fireballAnimations;
    }
    
    
}
