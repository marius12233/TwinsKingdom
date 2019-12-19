/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.gfx;
import twinkingdom.GameHandler;
import twinkingdom.entities.Entity;
import twinkingdom.tiles.Tile;

/**
 *
 * @author mario
 */
public class GameCamera {
    //Numbers that tells us how far as do we actually draw 
    private float xOffset, yOffset;
    private GameHandler handler;

    public GameCamera(GameHandler handler, float xOffset, float yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.handler = handler;
    }
    
    public void checkBlankSpace(){
        if(xOffset < 0){
            xOffset = 0;
        }else if(xOffset > handler.getWorld().getWidth()*Tile.TILEWIDTH -handler.getWidth()){
            xOffset = handler.getWorld().getWidth()*Tile.TILEWIDTH -handler.getWidth();
        }
        if(yOffset < 0){
            yOffset = 0;
        }else if(yOffset > handler.getWorld().getheight()*Tile.TILEHEIGHT - handler.getHeight()){
        yOffset = handler.getWorld().getWidth()*Tile.TILEWIDTH -handler.getWidth();
        }
        
    }
    //Setta gli ofset in modo che il personaggio sia centrato
    public void centerOnEntity(Entity e){
        xOffset = e.getX() - handler.getWidth()/2 + e.getWidth()/2;
        yOffset = e.getY() - handler.getHeight()/2 + e.getHeight()/2;
        checkBlankSpace();
    }
    
    public void move(float xAmt, float yAmt){
        xOffset += xAmt;
        yOffset += yAmt;
        checkBlankSpace();
    }

    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }
    
    
    
    
}
