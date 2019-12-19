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
public class GargoyleAssets extends EntityAssets {
    
    
         private static Map<String, Map<String, BufferedImage[]>> gargoyleAnimations;

  
    
    public void init(){
        
        BufferedImage[] gargoyle_down = new BufferedImage[9];
        BufferedImage[] gargoyle_up = new BufferedImage[9];
        BufferedImage[] gargoyle_left = new BufferedImage[9];
        BufferedImage[] gargoyle_right = new BufferedImage[9];
        BufferedImage[] gargoyle_up_stable = new BufferedImage[9];
        BufferedImage[] gargoyle_right_stable = new BufferedImage[9];
        BufferedImage[] gargoyle_left_stable = new BufferedImage[9];
        BufferedImage[] gargoyle_down_stable = new BufferedImage[9];
        BufferedImage[] gargoyle_down_attack = new BufferedImage[6];
        BufferedImage[] gargoyle_up_attack= new BufferedImage[6];
        BufferedImage[] gargoyle_left_attack= new BufferedImage[6];
        BufferedImage[] gargoyle_right_attack=new BufferedImage[6];
        
        
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
        sheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/gargoyle.png"));


        for(int i=0;i<9;i++){
            if(i<6){
            gargoyle_down_attack[i] =  sheet.crop(startingXAttack + i*width, startingYSlashAttack + 2*height , width, height);
            gargoyle_right_attack[i] =  sheet.crop(startingXAttack + i*width, startingYSlashAttack + 3*height, width, height);
            gargoyle_up_attack[i] =  sheet.crop(startingXAttack + i*width, startingYSlashAttack, width, height);
            gargoyle_left_attack[i] =  sheet.crop(startingXAttack + i*width, startingYSlashAttack + height, width, height);
            }
            
            
           
            gargoyle_down_stable[i] =  sheet.crop(startingX , startingYWalk + 2*height , width, height);
            gargoyle_right_stable[i] =  sheet.crop(startingX, startingYWalk + 3*height, width, height);
            gargoyle_up_stable[i] =  sheet.crop(startingX, startingYWalk, width, height);
            gargoyle_left_stable[i] =  sheet.crop(startingX, startingYWalk + height, width, height);

            
            gargoyle_down[i] =  sheet.crop(startingX + i*width , startingYWalk + 2*height , width, height);
            gargoyle_right[i] =  sheet.crop(startingX + i*width , startingYWalk + 3*height, width, height);
            gargoyle_up[i] =  sheet.crop(startingX + i*width , startingYWalk, width, height);
            gargoyle_left[i] =  sheet.crop(startingX + i*width , startingYWalk + height, width, height);

        }
        
        
        gargoyleAnimations = new HashMap();
        
        //Up state
        Map<String, BufferedImage[]> gargoyleUp = new HashMap();
        gargoyleUp.put("run", gargoyle_up);
        gargoyleUp.put("stable", gargoyle_up_stable);
        gargoyleUp.put("attack", gargoyle_up_attack);
        gargoyleAnimations.put("up", gargoyleUp);
        
        //Down state
        Map<String, BufferedImage[]> gargoyleDown = new HashMap();
        gargoyleDown.put("run", gargoyle_down);
        gargoyleDown.put("stable", gargoyle_down_stable);
        gargoyleDown.put("attack", gargoyle_down_attack);
        gargoyleAnimations.put("down", gargoyleDown);
        
        //Left State
        Map<String, BufferedImage[]> gargoyleLeft = new HashMap();
        gargoyleLeft.put("run", gargoyle_left);
        gargoyleLeft.put("stable", gargoyle_left_stable);
        gargoyleLeft.put("attack", gargoyle_left_attack);
        gargoyleAnimations.put("left", gargoyleLeft);
        
        //Right State
        Map<String, BufferedImage[]> gargoyleRight = new HashMap();
        gargoyleRight.put("run", gargoyle_right);
        gargoyleRight.put("stable", gargoyle_right_stable);
        gargoyleRight.put("attack", gargoyle_right_attack);
        gargoyleAnimations.put("right", gargoyleRight);
   
    }

    @Override
    public Map<String, Map<String, BufferedImage[]>> getAnimations() {
        return gargoyleAnimations;
    }
    
}
