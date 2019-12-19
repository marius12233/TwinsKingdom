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
public class SnakeGreyAssets extends EntityAssets {
    
    private static Map<String, Map<String, BufferedImage[]>> snakeAnimations;

  
    
    public void init(){
        //player = sheet.crop(0, 610, 128, 128);
        BufferedImage[] snake_down = new BufferedImage[3];
        BufferedImage[] snake_up = new BufferedImage[3];
        BufferedImage[] snake_left = new BufferedImage[3];
        BufferedImage[] snake_right = new BufferedImage[3];
        BufferedImage[] snake_up_stable = new BufferedImage[3];
        BufferedImage[] snake_right_stable = new BufferedImage[3];
        BufferedImage[] snake_left_stable = new BufferedImage[3];
        BufferedImage[] snake_down_stable = new BufferedImage[3];
        BufferedImage[] snake_down_attack = new BufferedImage[3];
        BufferedImage[] snake_up_attack= new BufferedImage[3];
        BufferedImage[] snake_left_attack= new BufferedImage[3];
        BufferedImage[] snake_right_attack=new BufferedImage[3];
        
        
        
        int height= 48;
        int width = 48;
      
        int startingXAttack=0;
        int startingYAttack=0;
        SpriteSheet sheet1, sheet2, sheet = null;            
        sheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/snake2.png"));


        for(int i=0;i<3;i++){
            snake_down_attack[i] =  sheet.crop(startingXAttack + i*width , startingYAttack , width, height);
            snake_right_attack[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + 2*height, width, height);
            snake_up_attack[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + 3*height, width, height);
            snake_left_attack[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + height, width, height);
            
            
            
            //sheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/EBros_snake_samurai/snake_Samurai_Walk.png"));
            snake_down_stable[i] =  sheet.crop(startingXAttack + i*width , startingYAttack , width, height);
            snake_right_stable[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + 2*height, width, height);
            snake_up_stable[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + 3*height, width, height);
            snake_left_stable[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + height, width, height);

            
            snake_down[i] =  sheet.crop(startingXAttack + i*width , startingYAttack , width, height);
            snake_right[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + 2*height, width, height);
            snake_up[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + 3*height, width, height);
            snake_left[i] =  sheet.crop(startingXAttack + i*width , startingYAttack + height, width, height);

        }
        
        
        snakeAnimations = new HashMap();
        
        //Up state
        Map<String, BufferedImage[]> snakeUp = new HashMap();
        snakeUp.put("run", snake_up);
        snakeUp.put("stable", snake_up_stable);
        snakeUp.put("attack", snake_up_attack);
        snakeAnimations.put("up", snakeUp);
        
        //Down state
        Map<String, BufferedImage[]> snakeDown = new HashMap();
        snakeDown.put("run", snake_down);
        snakeDown.put("stable", snake_down_stable);
        snakeDown.put("attack", snake_down_attack);
        snakeAnimations.put("down", snakeDown);
        
        //Left State
        Map<String, BufferedImage[]> snakeLeft = new HashMap();
        snakeLeft.put("run", snake_left);
        snakeLeft.put("stable", snake_left_stable);
        snakeLeft.put("attack", snake_left_attack);
        snakeAnimations.put("left", snakeLeft);
        
        //Right State
        Map<String, BufferedImage[]> snakeRight = new HashMap();
        snakeRight.put("run", snake_right);
        snakeRight.put("stable", snake_right_stable);
        snakeRight.put("attack", snake_right_attack);
        snakeAnimations.put("right", snakeRight);
   
    }

    @Override
    public Map<String, Map<String, BufferedImage[]>> getAnimations() {
        return snakeAnimations;
    }
    
}
