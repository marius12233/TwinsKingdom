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
public class ArmorAssets extends EntityAssets {
    
     
    private static Map<String, Map<String, BufferedImage[]>> armorAnimations;

  
    
    public void init(){
        //player = sheet.crop(0, 610, 128, 128);
        BufferedImage[] armor_down = new BufferedImage[5];
        BufferedImage[] armor_up = new BufferedImage[5];
        BufferedImage[] armor_left = new BufferedImage[5];
        BufferedImage[] armor_right = new BufferedImage[5];
        BufferedImage[] armor_up_stable = new BufferedImage[5];
        BufferedImage[] armor_right_stable = new BufferedImage[5];
        BufferedImage[] armor_left_stable = new BufferedImage[5];
        BufferedImage[] armor_down_stable = new BufferedImage[5];
        BufferedImage[] armor_down_attack = new BufferedImage[5];
        BufferedImage[] armor_up_attack= new BufferedImage[5];
        BufferedImage[] armor_left_attack= new BufferedImage[5];
        BufferedImage[] armor_right_attack=new BufferedImage[5];
        
        
        
        int height= 64;
        int width = 64;
      
        int startingXAttack=0;
        int startingYAttack=0;
        SpriteSheet sheet1, sheet2, sheet = null;            
        sheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/darksoldier.png"));


        for(int i=0;i<5;i++){
            armor_down_attack[i] =  sheet.crop(startingXAttack + i*width , startingYAttack , width, height);
            armor_right_attack[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + 2*height, width, height);
            armor_up_attack[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + height, width, height);
            armor_left_attack[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + 3*height, width, height);
            
            
            
            //sheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/EBros_armor_samurai/armor_Samurai_Walk.png"));
            armor_down_stable[i] =  sheet.crop(startingXAttack + i*width , startingYAttack , width, height);
            armor_right_stable[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + 2*height, width, height);
            armor_up_stable[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + height, width, height);
            armor_left_stable[i] =  sheet.crop(startingXAttack + i*width , startingYAttack +3*height, width, height);

            
            armor_down[i] =  sheet.crop(startingXAttack + i*width , startingYAttack , width, height);
            armor_right[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + 2*height, width, height);
            armor_up[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + height, width, height);
            armor_left[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + 3*height, width, height);

        }
        
        
        armorAnimations = new HashMap();
        
        //Up state
        Map<String, BufferedImage[]> armorUp = new HashMap();
        armorUp.put("run", armor_up);
        armorUp.put("stable", armor_up_stable);
        armorUp.put("attack", armor_up_attack);
        armorAnimations.put("up", armorUp);
        
        //Down state
        Map<String, BufferedImage[]> armorDown = new HashMap();
        armorDown.put("run", armor_down);
        armorDown.put("stable", armor_down_stable);
        armorDown.put("attack", armor_down_attack);
        armorAnimations.put("down", armorDown);
        
        //Left State
        Map<String, BufferedImage[]> armorLeft = new HashMap();
        armorLeft.put("run", armor_left);
        armorLeft.put("stable", armor_left_stable);
        armorLeft.put("attack", armor_left_attack);
        armorAnimations.put("left", armorLeft);
        
        //Right State
        Map<String, BufferedImage[]> armorRight = new HashMap();
        armorRight.put("run", armor_right);
        armorRight.put("stable", armor_right_stable);
        armorRight.put("attack", armor_right_attack);
        armorAnimations.put("right", armorRight);
   
    }

    @Override
    public Map<String, Map<String, BufferedImage[]>> getAnimations() {
        return armorAnimations;
    }
    
}
