/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.tiles;

import twinkingdom.tiles.InvisibleTile;
import twinkingdom.tiles.GroundTile;
import twinkingdom.tiles.GrassTile;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import twinkingdom.tiles.TreesTile11;
import twinkingdom.tiles.WaterTile;

/**
 *
 * @author Clover
 */
public class Tile {
    
    //STATIC VARIABLES
    public static Tile[] tiles = new Tile[256];
    
    public static Tile invisibleTile = new InvisibleTile(0);
    
    public static Tile grassTile = new GrassTile(21);
    public static Tile groundTile = new GroundTile(24);
    
    public static Tile treesTile11 = new TreesTile11(0,172);
    public static Tile treesTile12 = new TreesTile11(1, 173);
    public static Tile treesTile13 = new TreesTile11(2, 174);
    public static Tile treesTile14 = new TreesTile11(3, 175);
    public static Tile treesTile21 = new TreesTile11(4, 191);
    public static Tile treesTile22 = new TreesTile11(5, 192);
    public static Tile treesTile23 = new TreesTile11(6, 193);
    public static Tile treesTile24 = new TreesTile11(7, 194);
    public static Tile treesTile31 = new TreesTile11(8, 210);
    public static Tile treesTile32 = new TreesTile11(9, 211);
    public static Tile treesTile33 = new TreesTile11(10, 212);
    public static Tile treesTile34 = new TreesTile11(11, 213);
    public static Tile treesTile41 = new TreesTile11(12, 229);
    public static Tile treesTile42 = new TreesTile11(13, 230);
    public static Tile treesTile43 = new TreesTile11(14, 231);
    public static Tile treesTile44 = new TreesTile11(15, 232);
    
    public static Tile waterTile = new WaterTile(81);
    
    //CLASS
    public static final int TILEWIDTH = 32, TILEHEIGHT = 32;
    
    protected BufferedImage texture;
    protected final int id;
    
    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;
        
        tiles[id] = this;
    }
    
    public void tick() {
        
    }
    
    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }
    
    public boolean isSolid() {
        return false;
    }
    
    public int getId() {
        return id;
    }
    
    public BufferedImage getTexture() {
        return this.texture;
    }   
}