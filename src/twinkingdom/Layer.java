/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom;

import twinkingdom.tiles.Tile;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import twinkingdom.gfx.Assets;

/**
 *
 * @author Clover
 */
public class Layer {
    
    private int width, height;
    private int[][] matrix;
    
    public Layer(int width, int height, String path) {
        this.width = width;
        this.height = height;
        this.matrix = loadLayer(path, width, height);
    }
    
    public int[][] loadLayer(String path, int width, int height) {
        
        Scanner scanner = null;
        matrix = new int[height][width];
        
        File f = null;
        try {
            f = new File(path);
            scanner = new Scanner(f);
            for(int j = 0; j < width; j++) {
                for(int i = 0; i < height; i++) {
                    if(scanner.hasNextInt()) {
                        matrix[i][j] = scanner.nextInt();
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Layer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(scanner != null)
                scanner.close();
        }
        
        return matrix;
    }
    
    public void render(Graphics g, GameHandler handler) {
        for(int j = 0; j < this.width; j++) {
            for(int i = 0; i < this.height; i++) {
                switch (this.matrix[i][j]) {
                    case 21:
                        g.drawImage(Assets.grass, (int) (i*Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (j*Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()), 32, 32, null);
                        break;
                    case 24:
                        g.drawImage(Assets.ground, (int) (i*Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (j*Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()), 32, 32, null);
                        break;
                    case 81:
                        g.drawImage(Assets.water, (int) (i*Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (j*Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()), 32, 32, null);
                        break;
                    case 172:
                        g.drawImage(Assets.trees[0], (int) (i*Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (j*Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()), 32, 32, null);
                        break;
                    case 173:
                        g.drawImage(Assets.trees[1], (int) (i*Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (j*Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()), 32, 32, null);
                        break;
                    case 174:
                        g.drawImage(Assets.trees[2], (int) (i*Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (j*Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()), 32, 32, null);
                        break;
                    case 175:
                        g.drawImage(Assets.trees[3], (int) (i*Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (j*Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()), 32, 32, null);
                        break;
                    case 191:
                        g.drawImage(Assets.trees[4], (int) (i*Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (j*Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()), 32, 32, null);
                        break;
                    case 192:
                        g.drawImage(Assets.trees[5], (int) (i*Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (j*Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()), 32, 32, null);
                        break;
                    case 193:
                        g.drawImage(Assets.trees[6], (int) (i*Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (j*Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()), 32, 32, null);
                        break;
                    case 194:
                        g.drawImage(Assets.trees[7], (int) (i*Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (j*Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()), 32, 32, null);
                        break;
                    case 210:
                        g.drawImage(Assets.trees[8], (int) (i*Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (j*Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()), 32, 32, null);
                        break;
                    case 211:
                        g.drawImage(Assets.trees[9], (int) (i*Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (j*Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()), 32, 32, null);
                        break;
                    case 212:
                        g.drawImage(Assets.trees[10], (int) (i*Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (j*Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()), 32, 32, null);
                        break;
                    case 213:
                        g.drawImage(Assets.trees[11], (int) (i*Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (j*Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()), 32, 32, null);
                        break;
                    case 229:
                        g.drawImage(Assets.trees[12], (int) (i*Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (j*Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()), 32, 32, null);
                        break;
                    case 230:
                        g.drawImage(Assets.trees[13], (int) (i*Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (j*Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()), 32, 32, null);
                        break;
                    case 231:
                        g.drawImage(Assets.trees[14], (int) (i*Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (j*Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()), 32, 32, null);
                        break;
                    case 232:
                        g.drawImage(Assets.trees[15], (int) (i*Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (j*Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()), 32, 32, null);
                        break;
                    default:
                        g.drawImage(Assets.invisible, (int) (i*Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (j*Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()), 32, 32, null);
                        break;
                }
            }
        }
    }
    
    public void printLayer() {
        for(int j = 0; j<this.matrix[0].length; j++) {
            for(int i = 0; i<this.matrix.length; i++) {
                System.out.print(this.matrix[i][j] + " ");
            }
            System.out.println("\n");
        }
    }
    
    public int[][] getMatrix() {
        return this.matrix;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}