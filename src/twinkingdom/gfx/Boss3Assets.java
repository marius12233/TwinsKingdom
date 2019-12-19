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
public class Boss3Assets extends EntityAssets {
    
    
         private static Map<String, Map<String, BufferedImage[]>> boss3Animations;

  
    
    public void init(){
        //player = sheet.crop(0, 610, 128, 128);
        BufferedImage[] boss3_down = new BufferedImage[9];
        BufferedImage[] boss3_up = new BufferedImage[9];
        BufferedImage[] boss3_left = new BufferedImage[9];
        BufferedImage[] boss3_right = new BufferedImage[9];
        BufferedImage[] boss3_up_stable = new BufferedImage[9];
        BufferedImage[] boss3_right_stable = new BufferedImage[9];
        BufferedImage[] boss3_left_stable = new BufferedImage[9];
        BufferedImage[] boss3_down_stable = new BufferedImage[9];
        BufferedImage[] boss3_down_attack = new BufferedImage[6];
        BufferedImage[] boss3_up_attack= new BufferedImage[6];
        BufferedImage[] boss3_left_attack= new BufferedImage[6];
        BufferedImage[] boss3_right_attack=new BufferedImage[6];
        
        
        int startingX=0;
        int startingYSpell=0;
        int height = 64;
        int width = 64;
        int startingXStable = 0;
        int startingYStable=0;
        int startingXAttack=0;
        int startingYSlashAttack= 12*height;
        int startingYWalk= 8*height;
        SpriteSheet sheet1, sheet2, sheet = null;            
        sheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/Boss3.png"));


        for(int i=0;i<9;i++){
            if(i<6){
            boss3_down_attack[i] =  sheet.crop(startingXAttack + i*width, startingYSlashAttack + 2*height , width, height);
            boss3_right_attack[i] =  sheet.crop(startingXAttack + i*width, startingYSlashAttack + 3*height, width, height);
            boss3_up_attack[i] =  sheet.crop(startingXAttack + i*width, startingYSlashAttack, width, height);
            boss3_left_attack[i] =  sheet.crop(startingXAttack + i*width, startingYSlashAttack + height, width, height);
            }
            
            
            boss3_down_stable[i] =  sheet.crop(startingX , startingYWalk + 2*height , width, height);
            boss3_right_stable[i] =  sheet.crop(startingX, startingYWalk + 3*height, width, height);
            boss3_up_stable[i] =  sheet.crop(startingX, startingYWalk, width, height);
            boss3_left_stable[i] =  sheet.crop(startingX, startingYWalk + height, width, height);

            
            boss3_down[i] =  sheet.crop(startingX + i*width , startingYWalk + 2*height , width, height);
            boss3_right[i] =  sheet.crop(startingX + i*width , startingYWalk + 3*height, width, height);
            boss3_up[i] =  sheet.crop(startingX + i*width , startingYWalk, width, height);
            boss3_left[i] =  sheet.crop(startingX + i*width , startingYWalk + height, width, height);

        }
        
        
        boss3Animations = new HashMap();
        
        //Up state
        Map<String, BufferedImage[]> boss3Up = new HashMap();
        boss3Up.put("run", boss3_up);
        boss3Up.put("stable", boss3_up_stable);
        boss3Up.put("attack", boss3_up_attack);
        boss3Animations.put("up", boss3Up);
        
        //Down state
        Map<String, BufferedImage[]> boss3Down = new HashMap();
        boss3Down.put("run", boss3_down);
        boss3Down.put("stable", boss3_down_stable);
        boss3Down.put("attack", boss3_down_attack);
        boss3Animations.put("down", boss3Down);
        
        //Left State
        Map<String, BufferedImage[]> boss3Left = new HashMap();
        boss3Left.put("run", boss3_left);
        boss3Left.put("stable", boss3_left_stable);
        boss3Left.put("attack", boss3_left_attack);
        boss3Animations.put("left", boss3Left);
        
        //Right State
        Map<String, BufferedImage[]> boss3Right = new HashMap();
        boss3Right.put("run", boss3_right);
        boss3Right.put("stable", boss3_right_stable);
        boss3Right.put("attack", boss3_right_attack);
        boss3Animations.put("right", boss3Right);
   
    }

    @Override
    public Map<String, Map<String, BufferedImage[]>> getAnimations() {
        return boss3Animations;
    }
    
    
}

