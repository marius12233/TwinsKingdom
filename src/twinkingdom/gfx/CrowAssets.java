/*4
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
public class CrowAssets extends EntityAssets{
    
    private static Map<String, Map<String, BufferedImage[]>> crowAnimations;

  
    
    public void init(){
        //player = sheet.crop(0, 610, 128, 128);
        BufferedImage[] crow_down = new BufferedImage[4];
        BufferedImage[] crow_up = new BufferedImage[4];
        BufferedImage[] crow_left = new BufferedImage[4];
        BufferedImage[] crow_right = new BufferedImage[4];
        BufferedImage[] crow_up_stable = new BufferedImage[4];
        BufferedImage[] crow_right_stable = new BufferedImage[4];
        BufferedImage[] crow_left_stable = new BufferedImage[4];
        BufferedImage[] crow_down_stable = new BufferedImage[4];
        BufferedImage[] crow_down_attack = new BufferedImage[4];
        BufferedImage[] crow_up_attack= new BufferedImage[4];
        BufferedImage[] crow_left_attack= new BufferedImage[4];
        BufferedImage[] crow_right_attack=new BufferedImage[4];
        
        
        int startingX=0;
        int startingY=0;
        int height= 16;
        int width = 16;
        int startingXStable = 0;
        int startingYStable=0;
        int startingXAttack=0;
        int startingYAttack=0;
        SpriteSheet sheet1, sheet2, sheet = null;            
        sheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/crow.png"));


        for(int i=0;i<4;i++){
            crow_down_attack[i] =  sheet.crop(startingXAttack + i*width , startingY , width, height);
            crow_right_attack[i] =  sheet.crop(startingXAttack + i*width , startingY, width, height);
            crow_up_attack[i] =  sheet.crop(startingXAttack + i*width , height, width, height);
            crow_left_attack[i] =  sheet.crop(startingXAttack + i*width , height, width, height);
            
            
            
            //sheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/EBros_crow_samurai/crow_Samurai_Walk.png"));
            crow_down_stable[i] =  sheet.crop(startingXAttack + i*width , startingY , width, height);
            crow_right_stable[i] =  sheet.crop(startingXAttack + i*width , startingY , width, height);
            crow_up_stable[i] =  sheet.crop(startingXAttack + i*width , height, width, height);
            crow_left_stable[i] =  sheet.crop(startingXAttack + i*width , height, width, height);

            
            crow_down[i] =  sheet.crop(startingXAttack + i*width , startingY , width, height);
            crow_right[i] =  sheet.crop(startingXAttack + i*width , startingY , width, height);
            crow_up[i] =  sheet.crop(startingXAttack + i*width , height, width, height);
            crow_left[i] =  sheet.crop(startingXAttack + i*width , height, width, height);

        }
        
        
        crowAnimations = new HashMap();
        
        //Up state
        Map<String, BufferedImage[]> crowUp = new HashMap();
        crowUp.put("run", crow_up);
        crowUp.put("stable", crow_up_stable);
        crowUp.put("attack", crow_up_attack);
        crowAnimations.put("up", crowUp);
        
        //Down state
        Map<String, BufferedImage[]> crowDown = new HashMap();
        crowDown.put("run", crow_down);
        crowDown.put("stable", crow_down_stable);
        crowDown.put("attack", crow_down_attack);
        crowAnimations.put("down", crowDown);
        
        //Left State
        Map<String, BufferedImage[]> crowLeft = new HashMap();
        crowLeft.put("run", crow_left);
        crowLeft.put("stable", crow_left_stable);
        crowLeft.put("attack", crow_left_attack);
        crowAnimations.put("left", crowLeft);
        
        //Right State
        Map<String, BufferedImage[]> crowRight = new HashMap();
        crowRight.put("run", crow_right);
        crowRight.put("stable", crow_right_stable);
        crowRight.put("attack", crow_right_attack);
        crowAnimations.put("right", crowRight);
   
    }

    @Override
    public Map<String, Map<String, BufferedImage[]>> getAnimations() {
        return crowAnimations;
    }
    
}
