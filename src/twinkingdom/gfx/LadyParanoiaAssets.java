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
public class LadyParanoiaAssets extends EntityAssets {
    
      private static Map<String, Map<String, BufferedImage[]>> ladyParanoiaAnimations;

  
    
    public void init(){
        //player = sheet.crop(0, 610, 128, 128);
        BufferedImage[] ladyParanoia_down = new BufferedImage[9];
        BufferedImage[] ladyParanoia_up = new BufferedImage[9];
        BufferedImage[] ladyParanoia_left = new BufferedImage[9];
        BufferedImage[] ladyParanoia_right = new BufferedImage[9];
        BufferedImage[] ladyParanoia_up_stable = new BufferedImage[9];
        BufferedImage[] ladyParanoia_right_stable = new BufferedImage[9];
        BufferedImage[] ladyParanoia_left_stable = new BufferedImage[9];
        BufferedImage[] ladyParanoia_down_stable = new BufferedImage[9];
        BufferedImage[] ladyParanoia_down_attack = new BufferedImage[7];
        BufferedImage[] ladyParanoia_up_attack= new BufferedImage[7];
        BufferedImage[] ladyParanoia_left_attack= new BufferedImage[7];
        BufferedImage[] ladyParanoia_right_attack=new BufferedImage[7];
        
        
        int startingX=0;
        int startingYSpell=0;
        int height= 64;
        int width = 64;
        int startingXStable = 0;
        int startingYStable=0;
        int startingXAttack=0;
        int startingYSlashAttack= 12*height;
        int startingYWalk= 8*height;
        SpriteSheet sheet1, sheet2, sheet = null;            
        sheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/QueenParanoia.png"));


        for(int i=0;i<9;i++){
            if(i<7){
            ladyParanoia_down_attack[i] =  sheet.crop(startingXAttack + i*width, startingYSpell + 2*height , width, height);
            ladyParanoia_right_attack[i] =  sheet.crop(startingXAttack + i*width, startingYSpell + 3*height, width, height);
            ladyParanoia_up_attack[i] =  sheet.crop(startingXAttack + i*width, startingYSpell, width, height);
            ladyParanoia_left_attack[i] =  sheet.crop(startingXAttack + i*width, startingYSpell + height, width, height);
            }
            
            
            //sheet = new SpriteSheet(ImageLoader.loadImage("/twinkingdom/images/EBros_ladyParanoia_samurai/ladyParanoia_Samurai_Walk.png"));
            ladyParanoia_down_stable[i] =  sheet.crop(startingX , startingYWalk + 2*height , width, height);
            ladyParanoia_right_stable[i] =  sheet.crop(startingX, startingYWalk + 3*height, width, height);
            ladyParanoia_up_stable[i] =  sheet.crop(startingX, startingYWalk, width, height);
            ladyParanoia_left_stable[i] =  sheet.crop(startingX, startingYWalk + height, width, height);

            
            ladyParanoia_down[i] =  sheet.crop(startingX + i*width , startingYWalk + 2*height , width, height);
            ladyParanoia_right[i] =  sheet.crop(startingX + i*width , startingYWalk + 3*height, width, height);
            ladyParanoia_up[i] =  sheet.crop(startingX + i*width , startingYWalk, width, height);
            ladyParanoia_left[i] =  sheet.crop(startingX + i*width , startingYWalk + height, width, height);

        }
        
        
        ladyParanoiaAnimations = new HashMap();
        
        //Up state
        Map<String, BufferedImage[]> ladyParanoiaUp = new HashMap();
        ladyParanoiaUp.put("run", ladyParanoia_up);
        ladyParanoiaUp.put("stable", ladyParanoia_up_stable);
        ladyParanoiaUp.put("attack", ladyParanoia_up_attack);
        ladyParanoiaAnimations.put("up", ladyParanoiaUp);
        
        //Down state
        Map<String, BufferedImage[]> ladyParanoiaDown = new HashMap();
        ladyParanoiaDown.put("run", ladyParanoia_down);
        ladyParanoiaDown.put("stable", ladyParanoia_down_stable);
        ladyParanoiaDown.put("attack", ladyParanoia_down_attack);
        ladyParanoiaAnimations.put("down", ladyParanoiaDown);
        
        //Left State
        Map<String, BufferedImage[]> ladyParanoiaLeft = new HashMap();
        ladyParanoiaLeft.put("run", ladyParanoia_left);
        ladyParanoiaLeft.put("stable", ladyParanoia_left_stable);
        ladyParanoiaLeft.put("attack", ladyParanoia_left_attack);
        ladyParanoiaAnimations.put("left", ladyParanoiaLeft);
        
        //Right State
        Map<String, BufferedImage[]> ladyParanoiaRight = new HashMap();
        ladyParanoiaRight.put("run", ladyParanoia_right);
        ladyParanoiaRight.put("stable", ladyParanoia_right_stable);
        ladyParanoiaRight.put("attack", ladyParanoia_right_attack);
        ladyParanoiaAnimations.put("right", ladyParanoiaRight);
   
    }

    @Override
    public Map<String, Map<String, BufferedImage[]>> getAnimations() {
        return ladyParanoiaAnimations;
    }
    
}
