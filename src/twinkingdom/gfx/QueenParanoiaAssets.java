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
public class QueenParanoiaAssets extends EntityAssets {
    
      private static Map<String, Map<String, BufferedImage[]>> queenParanoiaAnimations;

  
    
    public void init(){
        //player = sheet.crop(0, 610, 128, 128);
        BufferedImage[] queenParanoia_down = new BufferedImage[9];
        BufferedImage[] queenParanoia_up = new BufferedImage[9];
        BufferedImage[] queenParanoia_left = new BufferedImage[9];
        BufferedImage[] queenParanoia_right = new BufferedImage[9];
        BufferedImage[] queenParanoia_up_stable = new BufferedImage[9];
        BufferedImage[] queenParanoia_right_stable = new BufferedImage[9];
        BufferedImage[] queenParanoia_left_stable = new BufferedImage[9];
        BufferedImage[] queenParanoia_down_stable = new BufferedImage[9];
        BufferedImage[] queenParanoia_down_attack = new BufferedImage[7];
        BufferedImage[] queenParanoia_up_attack= new BufferedImage[7];
        BufferedImage[] queenParanoia_left_attack= new BufferedImage[7];
        BufferedImage[] queenParanoia_right_attack=new BufferedImage[7];
        
        
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
            queenParanoia_down_attack[i] =  sheet.crop(startingXAttack + i*width, startingYSpell + 2*height , width, height);
            queenParanoia_right_attack[i] =  sheet.crop(startingXAttack + i*width, startingYSpell + 3*height, width, height);
            queenParanoia_up_attack[i] =  sheet.crop(startingXAttack + i*width, startingYSpell, width, height);
            queenParanoia_left_attack[i] =  sheet.crop(startingXAttack + i*width, startingYSpell + height, width, height);
            }
            
            
            //sheet = new SpriteSheet(ImageLoader.loadImage("/twinkingdom/images/EBros_queenParanoia_samurai/queenParanoia_Samurai_Walk.png"));
            queenParanoia_down_stable[i] =  sheet.crop(startingX , startingYWalk + 2*height , width, height);
            queenParanoia_right_stable[i] =  sheet.crop(startingX, startingYWalk + 3*height, width, height);
            queenParanoia_up_stable[i] =  sheet.crop(startingX, startingYWalk, width, height);
            queenParanoia_left_stable[i] =  sheet.crop(startingX, startingYWalk + height, width, height);

            
            queenParanoia_down[i] =  sheet.crop(startingX + i*width , startingYWalk + 2*height , width, height);
            queenParanoia_right[i] =  sheet.crop(startingX + i*width , startingYWalk + 3*height, width, height);
            queenParanoia_up[i] =  sheet.crop(startingX + i*width , startingYWalk, width, height);
            queenParanoia_left[i] =  sheet.crop(startingX + i*width , startingYWalk + height, width, height);

        }
        
        
        queenParanoiaAnimations = new HashMap();
        
        //Up state
        Map<String, BufferedImage[]> queenParanoiaUp = new HashMap();
        queenParanoiaUp.put("run", queenParanoia_up);
        queenParanoiaUp.put("stable", queenParanoia_up_stable);
        queenParanoiaUp.put("attack", queenParanoia_up_attack);
        queenParanoiaAnimations.put("up", queenParanoiaUp);
        
        //Down state
        Map<String, BufferedImage[]> queenParanoiaDown = new HashMap();
        queenParanoiaDown.put("run", queenParanoia_down);
        queenParanoiaDown.put("stable", queenParanoia_down_stable);
        queenParanoiaDown.put("attack", queenParanoia_down_attack);
        queenParanoiaAnimations.put("down", queenParanoiaDown);
        
        //Left State
        Map<String, BufferedImage[]> queenParanoiaLeft = new HashMap();
        queenParanoiaLeft.put("run", queenParanoia_left);
        queenParanoiaLeft.put("stable", queenParanoia_left_stable);
        queenParanoiaLeft.put("attack", queenParanoia_left_attack);
        queenParanoiaAnimations.put("left", queenParanoiaLeft);
        
        //Right State
        Map<String, BufferedImage[]> queenParanoiaRight = new HashMap();
        queenParanoiaRight.put("run", queenParanoia_right);
        queenParanoiaRight.put("stable", queenParanoia_right_stable);
        queenParanoiaRight.put("attack", queenParanoia_right_attack);
        queenParanoiaAnimations.put("right", queenParanoiaRight);
   
    }

    @Override
    public Map<String, Map<String, BufferedImage[]>> getAnimations() {
        return queenParanoiaAnimations;
    }
    
}
