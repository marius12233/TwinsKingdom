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
public class SpiderAssets extends EntityAssets {
    
    
    private static Map<String, Map<String, BufferedImage[]>> spiderAnimations;

  
    
    public void init(){
        //player = sheet.crop(0, 610, 128, 128);
        BufferedImage[] spider_down = new BufferedImage[3];
        BufferedImage[] spider_up = new BufferedImage[3];
        BufferedImage[] spider_left = new BufferedImage[3];
        BufferedImage[] spider_right = new BufferedImage[3];
        BufferedImage[] spider_up_stable = new BufferedImage[3];
        BufferedImage[] spider_right_stable = new BufferedImage[3];
        BufferedImage[] spider_left_stable = new BufferedImage[3];
        BufferedImage[] spider_down_stable = new BufferedImage[3];
        BufferedImage[] spider_down_attack = new BufferedImage[3];
        BufferedImage[] spider_up_attack= new BufferedImage[3];
        BufferedImage[] spider_left_attack= new BufferedImage[3];
        BufferedImage[] spider_right_attack=new BufferedImage[3];
        
        
        int startingX=0;
        int startingY=0;
        int height= 32;
        int width = 32;
        int startingXStable = 0;
        int startingYStable=0;
        int startingXAttack= 3*width;
        int startingYAttack=0;
        SpriteSheet sheet1, sheet2, sheet = null;            
        sheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/spider.png"));


        for(int i=0;i<3;i++){
            spider_down_attack[i] =  sheet.crop(startingXAttack + i*width , startingY, width, height);
            spider_right_attack[i] =  sheet.crop(startingXAttack + i*width , 2*height, width, height);
            spider_up_attack[i] =  sheet.crop(startingXAttack + i*width , 3*height, width, height);
            spider_left_attack[i] =  sheet.crop(startingXAttack + i*width ,height, width, height);
            
            
            
            //sheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/EBros_spider_samurai/spider_Samurai_Walk.png"));
            spider_down_stable[i] =  sheet.crop(startingXStable + i*width , startingY , width, height);
            spider_right_stable[i] =  sheet.crop(startingXStable + i*width ,2* height, width, height);
            spider_up_stable[i] =  sheet.crop(startingXStable + i*width , 3*height, width, height);
            spider_left_stable[i] =  sheet.crop(startingXStable + i*width ,height, width, height);

            
            spider_down[i] =  sheet.crop(startingX + i*width , startingY , width, height);
            spider_right[i] =  sheet.crop(startingX + i*width , 2*height, width, height);
            spider_up[i] =  sheet.crop(startingX + i*width , 3*height, width, height);
            spider_left[i] =  sheet.crop(startingX + i*width ,height, width, height);

        }
        
        
        spiderAnimations = new HashMap();
        
        //Up state
        Map<String, BufferedImage[]> spiderUp = new HashMap();
        spiderUp.put("run", spider_up);
        spiderUp.put("stable", spider_up_stable);
        spiderUp.put("attack", spider_up_attack);
        spiderAnimations.put("up", spiderUp);
        
        //Down state
        Map<String, BufferedImage[]> spiderDown = new HashMap();
        spiderDown.put("run", spider_down);
        spiderDown.put("stable", spider_down_stable);
        spiderDown.put("attack", spider_down_attack);
        spiderAnimations.put("down", spiderDown);
        
        //Left State
        Map<String, BufferedImage[]> spiderLeft = new HashMap();
        spiderLeft.put("run", spider_left);
        spiderLeft.put("stable", spider_left_stable);
        spiderLeft.put("attack", spider_left_attack);
        spiderAnimations.put("left", spiderLeft);
        
        //Right State
        Map<String, BufferedImage[]> spiderRight = new HashMap();
        spiderRight.put("run", spider_right);
        spiderRight.put("stable", spider_right_stable);
        spiderRight.put("attack", spider_right_attack);
        spiderAnimations.put("right", spiderRight);
   
    }

    @Override
    public Map<String, Map<String, BufferedImage[]>> getAnimations() {
        return spiderAnimations;
    }
    
    
}
