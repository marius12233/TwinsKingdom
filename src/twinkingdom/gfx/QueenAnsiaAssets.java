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
public class QueenAnsiaAssets extends EntityAssets {
    
      
         private static Map<String, Map<String, BufferedImage[]>> queenAnsiaAnimations;

  
    
    public void init(){
        //player = sheet.crop(0, 610, 128, 128);
        BufferedImage[] queenAnsia_down = new BufferedImage[9];
        BufferedImage[] queenAnsia_up = new BufferedImage[9];
        BufferedImage[] queenAnsia_left = new BufferedImage[9];
        BufferedImage[] queenAnsia_right = new BufferedImage[9];
        BufferedImage[] queenAnsia_up_stable = new BufferedImage[9];
        BufferedImage[] queenAnsia_right_stable = new BufferedImage[9];
        BufferedImage[] queenAnsia_left_stable = new BufferedImage[9];
        BufferedImage[] queenAnsia_down_stable = new BufferedImage[9];
        BufferedImage[] queenAnsia_down_attack = new BufferedImage[7];
        BufferedImage[] queenAnsia_up_attack= new BufferedImage[7];
        BufferedImage[] queenAnsia_left_attack= new BufferedImage[7];
        BufferedImage[] queenAnsia_right_attack=new BufferedImage[7];
        
        
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
        sheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/QueenAnsia.png"));


        for(int i=0;i<9;i++){
            if(i<7){
            queenAnsia_down_attack[i] =  sheet.crop(startingXAttack + i*width, startingYSpell + 2*height , width, height);
            queenAnsia_right_attack[i] =  sheet.crop(startingXAttack + i*width, startingYSpell + 3*height, width, height);
            queenAnsia_up_attack[i] =  sheet.crop(startingXAttack + i*width, startingYSpell, width, height);
            queenAnsia_left_attack[i] =  sheet.crop(startingXAttack + i*width, startingYSpell + height, width, height);
            }
            
            
            //sheet = new SpriteSheet(ImageLoader.loadImage("/twinkingdom/images/EBros_queenAnsia_samurai/queenAnsia_Samurai_Walk.png"));
            queenAnsia_down_stable[i] =  sheet.crop(startingX , startingYWalk + 2*height , width, height);
            queenAnsia_right_stable[i] =  sheet.crop(startingX, startingYWalk + 3*height, width, height);
            queenAnsia_up_stable[i] =  sheet.crop(startingX, startingYWalk, width, height);
            queenAnsia_left_stable[i] =  sheet.crop(startingX, startingYWalk + height, width, height);

            
            queenAnsia_down[i] =  sheet.crop(startingX + i*width , startingYWalk + 2*height , width, height);
            queenAnsia_right[i] =  sheet.crop(startingX + i*width , startingYWalk + 3*height, width, height);
            queenAnsia_up[i] =  sheet.crop(startingX + i*width , startingYWalk, width, height);
            queenAnsia_left[i] =  sheet.crop(startingX + i*width , startingYWalk + height, width, height);

        }
        
        
        queenAnsiaAnimations = new HashMap();
        
        //Up state
        Map<String, BufferedImage[]> queenAnsiaUp = new HashMap();
        queenAnsiaUp.put("run", queenAnsia_up);
        queenAnsiaUp.put("stable", queenAnsia_up_stable);
        queenAnsiaUp.put("attack", queenAnsia_up_attack);
        queenAnsiaAnimations.put("up", queenAnsiaUp);
        
        //Down state
        Map<String, BufferedImage[]> queenAnsiaDown = new HashMap();
        queenAnsiaDown.put("run", queenAnsia_down);
        queenAnsiaDown.put("stable", queenAnsia_down_stable);
        queenAnsiaDown.put("attack", queenAnsia_down_attack);
        queenAnsiaAnimations.put("down", queenAnsiaDown);
        
        //Left State
        Map<String, BufferedImage[]> queenAnsiaLeft = new HashMap();
        queenAnsiaLeft.put("run", queenAnsia_left);
        queenAnsiaLeft.put("stable", queenAnsia_left_stable);
        queenAnsiaLeft.put("attack", queenAnsia_left_attack);
        queenAnsiaAnimations.put("left", queenAnsiaLeft);
        
        //Right State
        Map<String, BufferedImage[]> queenAnsiaRight = new HashMap();
        queenAnsiaRight.put("run", queenAnsia_right);
        queenAnsiaRight.put("stable", queenAnsia_right_stable);
        queenAnsiaRight.put("attack", queenAnsia_right_attack);
        queenAnsiaAnimations.put("right", queenAnsiaRight);
   
    }

    @Override
    public Map<String, Map<String, BufferedImage[]>> getAnimations() {
        return queenAnsiaAnimations;
    }
    
}

