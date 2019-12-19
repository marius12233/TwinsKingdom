/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.gfx;

import java.awt.image.BufferedImage;

/**
 *
 * @author Clover
 */
public class Assets {
    
    private static final int width = 28, height = 37;
    private static final int width2 = 35, height2 = 40;
    
    public static BufferedImage invisible;
    public static BufferedImage[] player_idle;
    public static BufferedImage[] player_right;
    
    public static BufferedImage grass;
    public static BufferedImage ground;
    public static BufferedImage[] trees = new BufferedImage[16];
    public static BufferedImage water;
    public static BufferedImage[] portal;
    public static BufferedImage[] star, healthPotion;
    
    public static BufferedImage[] enchantedTrees;
    public static BufferedImage[] enchantedLeaf;
    
    
    public static void init() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/images/assets/textures/sheet.png"));
        SpriteSheet tileset = new SpriteSheet(ImageLoader.loadImage("/images/assets/textures/tileset.png"));
        SpriteSheet final_tileset = new SpriteSheet(ImageLoader.loadImage("/images/assets/textures/Forest Tileset-min.png"));
        
        player_idle = new BufferedImage[3];
        player_right = new BufferedImage[10];
        
        star = new BufferedImage[6];
        healthPotion = new BufferedImage[1];
        portal = new BufferedImage[5];
        
        
        //CHARACTER IDLE ANIMATION
        player_idle[0] = sheet.crop(4, 5, width, height);
        player_idle[1] = sheet.crop(40, 5, width, height);
        player_idle[2] = sheet.crop(80, 5, width, height);
        
        //CHARACTER RIGHT MOVEMENT
        player_right[0] = sheet.crop(4, 96, width, height);
        player_right[1] = sheet.crop(35, 96, width + 2, height);
        player_right[2] = sheet.crop(78, 96, width + 6, height);
        player_right[3] = sheet.crop(127, 96, width, height);
        player_right[4] = sheet.crop(160, 96, width, height);
        player_right[5] = sheet.crop(195, 96, width, height);
        player_right[6] = sheet.crop(226, 96, width, height);
        player_right[7] = sheet.crop(263, 96, width, height);
        player_right[8] = sheet.crop(303, 96, width, height);
        player_right[9] = sheet.crop(340, 96, width, height);
        
        invisible = tileset.crop(0, 0, 16, 16);
        
        grass = final_tileset.crop(32, 32, 32, 32);
        
        ground = final_tileset.crop(128, 32, 32, 32);
        
        trees[0] = final_tileset.crop(0, 288, 32, 32);
        trees[1] = final_tileset.crop(32, 288, 32, 32);
        trees[2] = final_tileset.crop(64, 288, 32, 32);
        trees[3] = final_tileset.crop(96, 288, 32, 32);
        trees[4] = final_tileset.crop(0, 320, 32, 32);
        trees[5] = final_tileset.crop(32, 320, 32, 32);
        trees[6] = final_tileset.crop(64, 320, 32, 32);
        trees[7] = final_tileset.crop(96, 320, 32, 32);
        trees[8] = final_tileset.crop(0, 352, 32, 32);
        trees[9] = final_tileset.crop(32, 352, 32, 32);
        trees[10] = final_tileset.crop(64, 352, 32, 32);
        trees[11] = final_tileset.crop(96, 352, 32, 32);
        trees[12] = final_tileset.crop(0, 384, 32, 32);
        trees[13] = final_tileset.crop(32, 384, 32, 32);
        trees[14] = final_tileset.crop(64, 384, 32, 32);
        trees[15] = final_tileset.crop(96, 384, 32, 32);
        
        water = final_tileset.crop(128, 128, 32, 32);
        
        SpriteSheet sheet5 = new SpriteSheet(ImageLoader.loadImage("/images/assets/STELLE.png"));
        star[0] = sheet5.crop(0,0, 64, 64);
        star[1] = sheet5.crop(64,0, 64, 64);
        star[2] = sheet5.crop(0,64, 64, 64);
        star[3] = sheet5.crop(64,64, 64, 64);
        star[4] = sheet5.crop(0,128, 64, 64);
        star[5] = sheet5.crop(64,128, 64, 64);
        //SpriteSheet sheet6 = new SpriteSheet(ImageLoader.loadImage("/images/assets/healthPotion.png"));
        healthPotion[0] = ImageLoader.loadImage("/images/assets/health.png");
        
        SpriteSheet sheet6 = new SpriteSheet(ImageLoader.loadImage("/images/assets/portalRings2.png"));
        portal[0] = sheet6.crop(0, 0, 32, 32);
        portal[1] = sheet6.crop(32, 0, 32, 32);
        portal[2] = sheet6.crop(64, 0, 32, 32);
        portal[3] = sheet6.crop(96, 0, 32, 32);
        portal[4] = sheet6.crop(128, 0, 32, 32);
         enchantedTrees=new BufferedImage[3];
        enchantedLeaf=new BufferedImage[3];
        enchantedTrees[0]=ImageLoader.loadImage("/images/assets/enchantedTree3.png");

        enchantedLeaf[0]=ImageLoader.loadImage("/images/assets/enchantedLeaf.png");
        
    }   
}