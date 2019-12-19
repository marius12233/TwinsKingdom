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
 * @author mario
 */
public class MonsterAssets extends EntityAssets{
    
    public static BufferedImage[] monster_right;
    private static Map<String, Map<String, BufferedImage[]>> monsterAnimations;
    
    public void init(){
        //player = sheet.crop(0, 610, 128, 128);
        BufferedImage[] monster_down = new BufferedImage[5];
        BufferedImage[] monster_up = new BufferedImage[5];
        BufferedImage[] monster_left = new BufferedImage[5];
        monster_right = new BufferedImage[5];
        BufferedImage[] monster_up_stable = new BufferedImage[5];
        BufferedImage[] monster_right_stable = new BufferedImage[5];
        BufferedImage[] monster_left_stable = new BufferedImage[5];
        BufferedImage[] monster_down_stable = new BufferedImage[5];
        BufferedImage[] monster_down_attack = new BufferedImage[5];
        BufferedImage[] monster_up_attack= new BufferedImage[5];
        BufferedImage[] monster_left_attack= new BufferedImage[5];
        BufferedImage[] monster_right_attack=new BufferedImage[5];
        
        
        int startingX=10;
        int startingY=0;
        int height=250;
        int width = 250;
        int startingXStable = 0;
        int startingYStable=2060;
        int startingXAttack=10;
        int startingYAttack=0;
        SpriteSheet sheet1, sheet2, sheet = null;            
        sheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/Enemy_Ranged_v01.png"));


        for(int i=0;i<=4;i++){
            monster_up_attack[i] =  sheet.crop(startingXAttack + i*width, startingYAttack , width -30, height);
            monster_down_attack[i] =  sheet.crop(startingXAttack + i*width, startingYAttack + height, width - 30, height);
            monster_right_attack[i] =  sheet.crop(startingXAttack + i*width, startingYAttack + 2*height, width, height);
            monster_left_attack[i] =  sheet.crop(startingXAttack + i*width, startingYAttack+ 3*height, width, height);
            
            
            
            //sheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/EBros_monster_samurai/monster_Samurai_Walk.png"));
            if(i<=11){
            monster_up_stable[i] =  sheet.crop(startingXStable + i*width, startingYStable, width - 30 , height);
            monster_down_stable[i] =  sheet.crop(startingXStable + i*width, startingYStable +height, width - 30, height);
            monster_right_stable[i] =  sheet.crop(startingXStable + i*width, startingYStable + 2*height, width - 30, height);
            monster_left_stable[i] =  sheet.crop(startingXStable + i*width, startingYStable+ 3*height, width, height);
            }

            
            monster_up[i] =  sheet.crop(startingX + i*width, startingY , width - 30, height);
            monster_down[i] =  sheet.crop(startingX + i*width, startingY +height, width - 30, height);
            monster_right[i] =  sheet.crop(startingX + i*width, startingY + 2*height, width, height);
            monster_left[i] =  sheet.crop(startingX + i*width, startingY + 3*height, width, height);

        }
        
        
        monsterAnimations = new HashMap();
        
        //Up state
        Map<String, BufferedImage[]> monsterUp = new HashMap();
        monsterUp.put("run", monster_up);
        monsterUp.put("stable", monster_up_stable);
        monsterUp.put("attack", monster_up_attack);
        monsterAnimations.put("up", monsterUp);
        
        //Down state
        Map<String, BufferedImage[]> monsterDown = new HashMap();
        monsterDown.put("run", monster_down);
        monsterDown.put("stable", monster_down_stable);
        monsterDown.put("attack", monster_down_attack);
        monsterAnimations.put("down", monsterDown);
        
        //Left State
        Map<String, BufferedImage[]> monsterLeft = new HashMap();
        monsterLeft.put("run", monster_left);
        monsterLeft.put("stable", monster_left_stable);
        monsterLeft.put("attack", monster_left_attack);
        monsterAnimations.put("left", monsterLeft);
        
        //Right State
        Map<String, BufferedImage[]> monsterRight = new HashMap();
        monsterRight.put("run", monster_right);
        monsterRight.put("stable", monster_right_stable);
        monsterRight.put("attack", monster_right_attack);
        monsterAnimations.put("right", monsterRight);
   
    }

    @Override
    public Map<String, Map<String, BufferedImage[]>> getAnimations() {
        return monsterAnimations;
    }
    
}