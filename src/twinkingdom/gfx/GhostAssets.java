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
public class GhostAssets extends EntityAssets {
    
     private static Map<String, Map<String, BufferedImage[]>> ghostAnimations;

  
    
    public void init(){
        //player = sheet.crop(0, 610, 128, 128);
        BufferedImage[] ghost_down = new BufferedImage[6];
        BufferedImage[] ghost_up = new BufferedImage[6];
        BufferedImage[] ghost_left = new BufferedImage[6];
        BufferedImage[] ghost_right = new BufferedImage[6];
        BufferedImage[] ghost_up_stable = new BufferedImage[6];
        BufferedImage[] ghost_right_stable = new BufferedImage[6];
        BufferedImage[] ghost_left_stable = new BufferedImage[6];
        BufferedImage[] ghost_down_stable = new BufferedImage[6];
        BufferedImage[] ghost_down_attack = new BufferedImage[6];
        BufferedImage[] ghost_up_attack= new BufferedImage[6];
        BufferedImage[] ghost_left_attack= new BufferedImage[6];
        BufferedImage[] ghost_right_attack=new BufferedImage[6];
        
        
        int startingX=0;
        int startingY=0;
        int height= 48;
        int width = 48;
        int startingXStable = 0;
        int startingYStable=0;
        int startingXAttack=0;
        int startingYAttack=0;
        SpriteSheet sheet1, sheet2, sheet = null;            
        sheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/ghost2.png"));


        for(int i=0;i<6;i++){
            ghost_down_attack[i] =  sheet.crop(startingXAttack + i*width , startingY , width, height);
            ghost_right_attack[i] =  sheet.crop(startingXAttack + i*width , 2*height, width, height);
            ghost_up_attack[i] =  sheet.crop(startingXAttack + i*width , 3*height, width, height);
            ghost_left_attack[i] =  sheet.crop(startingXAttack + i*width , height, width, height);
            
            
            
            //sheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/EBros_ghost_samurai/ghost_Samurai_Walk.png"));
            ghost_down_stable[i] =  sheet.crop(startingXAttack + i*width , startingY , width, height);
            ghost_right_stable[i] =  sheet.crop(startingXAttack + i*width , 2*height, width, height);
            ghost_up_stable[i] =  sheet.crop(startingXAttack + i*width , 3*height, width, height);
            ghost_left_stable[i] =  sheet.crop(startingXAttack + i*width , height, width, height);

            
            ghost_down[i] =  sheet.crop(startingXAttack + i*width , startingY , width, height);
            ghost_right[i] =  sheet.crop(startingXAttack + i*width , 2*height, width, height);
            ghost_up[i] =  sheet.crop(startingXAttack + i*width , 3*height, width, height);
            ghost_left[i] =  sheet.crop(startingXAttack + i*width , height, width, height);

        }
        
        
        ghostAnimations = new HashMap();
        
        //Up state
        Map<String, BufferedImage[]> ghostUp = new HashMap();
        ghostUp.put("run", ghost_up);
        ghostUp.put("stable", ghost_up_stable);
        ghostUp.put("attack", ghost_up_attack);
        ghostAnimations.put("up", ghostUp);
        
        //Down state
        Map<String, BufferedImage[]> ghostDown = new HashMap();
        ghostDown.put("run", ghost_down);
        ghostDown.put("stable", ghost_down_stable);
        ghostDown.put("attack", ghost_down_attack);
        ghostAnimations.put("down", ghostDown);
        
        //Left State
        Map<String, BufferedImage[]> ghostLeft = new HashMap();
        ghostLeft.put("run", ghost_left);
        ghostLeft.put("stable", ghost_left_stable);
        ghostLeft.put("attack", ghost_left_attack);
        ghostAnimations.put("left", ghostLeft);
        
        //Right State
        Map<String, BufferedImage[]> ghostRight = new HashMap();
        ghostRight.put("run", ghost_right);
        ghostRight.put("stable", ghost_right_stable);
        ghostRight.put("attack", ghost_right_attack);
        ghostAnimations.put("right", ghostRight);
   
    }

    @Override
    public Map<String, Map<String, BufferedImage[]>> getAnimations() {
        return ghostAnimations;
    }
    
}
