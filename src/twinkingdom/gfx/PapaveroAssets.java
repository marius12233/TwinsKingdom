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
public class PapaveroAssets extends EntityAssets {
    
    private static Map<String, Map<String, BufferedImage[]>> papaveroAnimations;

  
    
    public void init(){
        //player = sheet.crop(0, 610, 128, 128);
        BufferedImage[] papavero_down = new BufferedImage[3];
        BufferedImage[] papavero_up = new BufferedImage[3];
        BufferedImage[] papavero_left = new BufferedImage[3];
        BufferedImage[] papavero_right = new BufferedImage[3];
        BufferedImage[] papavero_up_stable = new BufferedImage[3];
        BufferedImage[] papavero_right_stable = new BufferedImage[3];
        BufferedImage[] papavero_left_stable = new BufferedImage[3];
        BufferedImage[] papavero_down_stable = new BufferedImage[3];
        BufferedImage[] papavero_down_attack = new BufferedImage[3];
        BufferedImage[] papavero_up_attack= new BufferedImage[3];
        BufferedImage[] papavero_left_attack= new BufferedImage[3];
        BufferedImage[] papavero_right_attack=new BufferedImage[3];
        
        
        
        int height= 32;
        int width = 32;
      
        int startingXAttack= 0;
        int startingYAttack= 0;
        SpriteSheet sheet1, sheet2, sheet = null;            
        sheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/papavero2.png"));


        for(int i=0;i<3;i++){
            papavero_down_attack[i] =  sheet.crop(startingXAttack + i*width , startingYAttack , width, height);
            papavero_right_attack[i] =  sheet.crop(startingXAttack + i*width , startingYAttack, width, height);
            papavero_up_attack[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + height, width, height);
            papavero_left_attack[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + height, width, height);
            
            
            
            //sheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/EBros_papavero_samurai/papavero_Samurai_Walk.png"));
            papavero_down_stable[i] =  sheet.crop(startingXAttack + i*width , startingYAttack , width, height);
            papavero_right_stable[i] =  sheet.crop(startingXAttack + i*width , startingYAttack, width, height);
            papavero_up_stable[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + height, width, height);
            papavero_left_stable[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + height, width, height);

            
            papavero_down[i] =  sheet.crop(startingXAttack + i*width , startingYAttack , width, height);
            papavero_right[i] =  sheet.crop(startingXAttack + i*width , startingYAttack , width, height);
            papavero_up[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + height, width, height);
            papavero_left[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + height, width, height);

        }
        
        
        papaveroAnimations = new HashMap();
        
        //Up state
        Map<String, BufferedImage[]> papaveroUp = new HashMap();
        papaveroUp.put("run", papavero_up);
        papaveroUp.put("stable", papavero_up_stable);
        papaveroUp.put("attack", papavero_up_attack);
        papaveroAnimations.put("up", papaveroUp);
        
        //Down state
        Map<String, BufferedImage[]> papaveroDown = new HashMap();
        papaveroDown.put("run", papavero_down);
        papaveroDown.put("stable", papavero_down_stable);
        papaveroDown.put("attack", papavero_down_attack);
        papaveroAnimations.put("down", papaveroDown);
        
        //Left State
        Map<String, BufferedImage[]> papaveroLeft = new HashMap();
        papaveroLeft.put("run", papavero_left);
        papaveroLeft.put("stable", papavero_left_stable);
        papaveroLeft.put("attack", papavero_left_attack);
        papaveroAnimations.put("left", papaveroLeft);
        
        //Right State
        Map<String, BufferedImage[]> papaveroRight = new HashMap();
        papaveroRight.put("run", papavero_right);
        papaveroRight.put("stable", papavero_right_stable);
        papaveroRight.put("attack", papavero_right_attack);
        papaveroAnimations.put("right", papaveroRight);
   
    }

    @Override
    public Map<String, Map<String, BufferedImage[]>> getAnimations() {
        return papaveroAnimations;
    }
    
}
