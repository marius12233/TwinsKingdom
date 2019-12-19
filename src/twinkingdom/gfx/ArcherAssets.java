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
public class ArcherAssets extends EntityAssets{
    
    private static Map<String, Map<String, BufferedImage[]>> archerAnimations;
    
    @Override
    public void init(){
        //player = sheet.crop(0, 610, 128, 128);
        BufferedImage[] monkey_down = new BufferedImage[13];
        BufferedImage[] monkey_up = new BufferedImage[13];
        BufferedImage[] monkey_left = new BufferedImage[13];
        BufferedImage[] monkey_right = new BufferedImage[13];
        BufferedImage[] monkey_up_stable = new BufferedImage[13];
        BufferedImage[] monkey_right_stable = new BufferedImage[13];
        BufferedImage[] monkey_left_stable = new BufferedImage[13];
        BufferedImage[] monkey_down_stable = new BufferedImage[13];
        BufferedImage[] monkey_down_attack = new BufferedImage[13];
        BufferedImage[] monkey_up_attack= new BufferedImage[13];
        BufferedImage[] monkey_left_attack= new BufferedImage[13];
        BufferedImage[] monkey_right_attack=new BufferedImage[13];
        
        
        int startingX=0;
        int startingY=0;
        int height= 64;
        int width = 64;
        int startingXStable = 0;
        int startingYStable=0;
        int startingXAttack=0;
        int startingYAttack=0;
        SpriteSheet sheet1, sheet2, sheet = null;            
        sheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/archer.png"));


        for(int i=0;i<13;i++){
            monkey_down_attack[i] =  sheet.crop(startingXAttack + i*width , 2*height , width, height);
            monkey_right_attack[i] =  sheet.crop(startingXAttack + i*width , 3*height, width, height);
            monkey_up_attack[i] =  sheet.crop(startingXAttack + i*width , startingYAttack, width, height);
            monkey_left_attack[i] =  sheet.crop(startingXAttack + i*width , height, width, height);
            
            
            
            //sheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/EBros_Boss_samurai/Boss_Samurai_Walk.png"));
            monkey_down_stable[i] =  sheet.crop(startingXAttack + i*width , 2*height , width, height);
            monkey_right_stable[i] =  sheet.crop(startingXAttack + i*width , 3*height, width, height);
            monkey_up_stable[i] =  sheet.crop(startingXAttack + i*width , startingYAttack, width, height);
            monkey_left_stable[i] =  sheet.crop(startingXAttack + i*width , height, width, height);

            
            monkey_down[i] =  sheet.crop(startingXAttack + i*width , 2*height , width, height);
            monkey_right[i] =  sheet.crop(startingXAttack + i*width , 3*height, width, height);
            monkey_up[i] =  sheet.crop(startingXAttack + i*width , startingYAttack, width, height);
            monkey_left[i] =  sheet.crop(startingXAttack + i*width , height, width, height);

        }
        
        
        archerAnimations = new HashMap();
        
        //Up state
        Map<String, BufferedImage[]> monkeyUp = new HashMap();
        monkeyUp.put("run", monkey_up);
        monkeyUp.put("stable", monkey_up_stable);
        monkeyUp.put("attack", monkey_up_attack);
        archerAnimations.put("up", monkeyUp);
        
        //Down state
        Map<String, BufferedImage[]> monkeyDown = new HashMap();
        monkeyDown.put("run", monkey_down);
        monkeyDown.put("stable", monkey_down_stable);
        monkeyDown.put("attack", monkey_down_attack);
        archerAnimations.put("down", monkeyDown);
        
        //Left State
        Map<String, BufferedImage[]> monkeyLeft = new HashMap();
        monkeyLeft.put("run", monkey_left);
        monkeyLeft.put("stable", monkey_left_stable);
        monkeyLeft.put("attack", monkey_left_attack);
        archerAnimations.put("left", monkeyLeft);
        
        //Right State
        Map<String, BufferedImage[]> monkeyRight = new HashMap();
        monkeyRight.put("run", monkey_right);
        monkeyRight.put("stable", monkey_right_stable);
        monkeyRight.put("attack", monkey_right_attack);
        archerAnimations.put("right", monkeyRight);
   
    }

    @Override
    public Map<String, Map<String, BufferedImage[]>> getAnimations() {
        return archerAnimations;
    }
    
}
