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
public class PlayerSpellAssets extends EntityAssets {
    
    private static Map<String, Map<String, BufferedImage[]>> playerAnimations;

  
    
    public void init(){
        //player = sheet.crop(0, 610, 128, 128);
        BufferedImage[] player_down = new BufferedImage[9];
        BufferedImage[] player_up = new BufferedImage[9];
        BufferedImage[] player_left = new BufferedImage[9];
        BufferedImage[] player_right = new BufferedImage[9];
        BufferedImage[] player_up_stable = new BufferedImage[9];
        BufferedImage[] player_right_stable = new BufferedImage[9];
        BufferedImage[] player_left_stable = new BufferedImage[9];
        BufferedImage[] player_down_stable = new BufferedImage[9];
        BufferedImage[] player_down_attack = new BufferedImage[8];
        BufferedImage[] player_up_attack= new BufferedImage[8];
        BufferedImage[] player_left_attack= new BufferedImage[8];
        BufferedImage[] player_right_attack=new BufferedImage[8];
        
        int height= 64;
        int width = 64;
        int startingX=0;
        int startingYSpell= 4*height;        
        int startingXStable = 0;
        int startingYStable=0;
        int startingXAttack=0;
        int startingYSlashAttack= 12*height;
        int startingYWalk= 8*height;
        SpriteSheet sheet1, sheet2, sheet = null;            
        sheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/player_spell.png"));


        for(int i=0;i<9;i++){
            if(i<8){
            player_down_attack[i] =  sheet.crop(startingXAttack + i*width, startingYSpell + 2*height , width, height);
            player_right_attack[i] =  sheet.crop(startingXAttack + i*width, startingYSpell + 3*height, width, height);
            player_up_attack[i] =  sheet.crop(startingXAttack + i*width, startingYSpell, width, height);
            player_left_attack[i] =  sheet.crop(startingXAttack + i*width, startingYSpell + height, width, height);
            }
            
            
            //sheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/EBros_player_samurai/player_Samurai_Walk.png"));
            player_down_stable[i] =  sheet.crop(startingX , startingYWalk + 2*height , width, height);
            player_right_stable[i] =  sheet.crop(startingX, startingYWalk + 3*height, width, height);
            player_up_stable[i] =  sheet.crop(startingX, startingYWalk, width, height);
            player_left_stable[i] =  sheet.crop(startingX, startingYWalk + height, width, height);

            
            player_down[i] =  sheet.crop(startingX + i*width , startingYWalk + 2*height , width, height);
            player_right[i] =  sheet.crop(startingX + i*width , startingYWalk + 3*height, width, height);
            player_up[i] =  sheet.crop(startingX + i*width , startingYWalk, width, height);
            player_left[i] =  sheet.crop(startingX + i*width , startingYWalk + height, width, height);

        }
        
        
        playerAnimations = new HashMap();
        
        //Up state
        Map<String, BufferedImage[]> playerUp = new HashMap();
        playerUp.put("run", player_up);
        playerUp.put("stable", player_up_stable);
        playerUp.put("attack", player_up_attack);
        playerAnimations.put("up", playerUp);
        
        //Down state
        Map<String, BufferedImage[]> playerDown = new HashMap();
        playerDown.put("run", player_down);
        playerDown.put("stable", player_down_stable);
        playerDown.put("attack", player_down_attack);
        playerAnimations.put("down", playerDown);
        
        //Left State
        Map<String, BufferedImage[]> playerLeft = new HashMap();
        playerLeft.put("run", player_left);
        playerLeft.put("stable", player_left_stable);
        playerLeft.put("attack", player_left_attack);
        playerAnimations.put("left", playerLeft);
        
        //Right State
        Map<String, BufferedImage[]> playerRight = new HashMap();
        playerRight.put("run", player_right);
        playerRight.put("stable", player_right_stable);
        playerRight.put("attack", player_right_attack);
        playerAnimations.put("right", playerRight);
   
    }

    @Override
    public Map<String, Map<String, BufferedImage[]>> getAnimations() {
        return playerAnimations;
    }
}
