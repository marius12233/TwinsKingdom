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
    
    //Mondo 3
    public static Tile castleWallTile=new CastleWallTile(0,35); //sx wall
    public static Tile castleWallTile1=new CastleWallTile(1,36); //bot wall
    public static Tile castleWallTile2=new CastleWallTile(2,37); //dx wall
    public static Tile castleWallTile3=new CastleWallTile(3,38); //separator wall
    public static Tile castleWallTile4=new CastleWallTile(4,1); //top1 wall
    public static Tile castleWallTile5=new CastleWallTile(5,2); //top2 wall
    
    public static Tile castleCandleTile=new CastleCandleTile(39);
    public static Tile castleFloorTile=new CastleFloorTile(57);
    
    //Mondo 2
    public static Tile shrubTile = new ShrubTile(129);
    public static Tile rockTile = new RockTile(95);
    public static Tile flowersTile = new FlowersTile(109);
    
    //final level
    public static Tile trfloor1Tile=new ThroneRoomFloor1Tile(40);
    public static Tile trwallTile=new ThroneRoomWallTile(42);
    public static Tile column1Tile=new Column1Tile(43);
    public static Tile column2Tile=new Column2Tile(44);
    public static Tile window1Tile=new Window1Tile(45);
    public static Tile window2Tile=new Window2Tile(46);
    public static Tile blackTile=new BlackTile(55);
    
    public static Tile trfloor2Tile=new ThroneRoomFloor2Tile(41);
    public static Tile ladderstTile=new LadderSTTile(47);
    public static Tile ladderct1Tile=new LadderCT1Tile(48);
    public static Tile ladderct2Tile=new LadderCT2Tile(61);
    public static Tile ladderct3Tile=new LadderCT3Tile(62);
    public static Tile carpet11Tile=new Carpet11Tile(49);
    public static Tile carpet12Tile=new Carpet12Tile(50);
    public static Tile carpet13Tile=new Carpet13Tile(51);
    public static Tile carpet21Tile=new Carpet21Tile(52);
    public static Tile carpet22Tile=new Carpet22Tile(53);
    public static Tile carpet23Tile=new Carpet23Tile(54);
    
    public static Tile throne1Tile=new Throne1Tile(60);
    public static Tile throne2Tile=new Throne2Tile(56);
    
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