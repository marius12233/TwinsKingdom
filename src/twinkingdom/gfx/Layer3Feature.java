/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.gfx;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import twinkingdom.GameHandler;
import twinkingdom.entities.Entity;
import twinkingdom.entities.mobs.player.Player;

/**
 *
 * @author Antonia
 */
public class Layer3Feature {

    private Graphics g;
    private int player_x, player_y;
    private int x, y;
    private BufferedImage imageLevel3;
    private Player player;
    private GameHandler handler;
    private int width, height;
    private int xOffset, yOffset;

    public Layer3Feature(Player player) {
        
        this.player = player;
        this.handler = GameHandler.instance;
        this.x = 0;
        this.y = 0;
        this.xOffset = 0;
        this.yOffset = 0;
        this.imageLevel3 = ImageLoader.loadImage("/gui/level3_mask.png");

//ImageIO.read(GameGUI.class.getResource("/res/gui/sidebar_bg.png"));
        this.width = imageLevel3.getWidth();
        this.height = imageLevel3.getHeight();


    }

    public void tick() {
        centerOnEntity(handler.getPlayer());
        x = (int) (handler.getPlayer().getX() - xOffset);
        y = (int) (handler.getPlayer().getY() - yOffset);
    

    }

    public void centerOnEntity(Entity e) {
        xOffset= (int) ( width/2 -e.getWidth()/2 +handler.getGameCamera().getxOffset());
        yOffset=(int) ( height/2 -e.getHeight()/2 + handler.getGameCamera().getyOffset());
        
    }

    public void render(Graphics g) {

        g.setColor(Color.black);

        g.drawImage(imageLevel3, x, y, null);
        

       // System.out.println("sono qui rander layer3Feature. x: " + x+ "y: "+y);

    }
}

