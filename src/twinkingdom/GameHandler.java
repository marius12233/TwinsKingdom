/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom;

import twinkingdom.gfx.GameCamera;
import twinkingdom.input.KeyManager;
import twinkingdom.worlds.World;

/**
 *
 * @author mario
 * classe che permette di accedere alle classi  Game, World, GameCamera e KeyManager
 */
public class GameHandler {
    
    private Game game;
    private World world;
    public static GameHandler instance = null;
    
    static {
        instance = new GameHandler();
    }
    
    public static GameHandler getInstance() {
        return instance;
    }

    private GameHandler() {}
    
    
    public synchronized GameCamera getGameCamera(){
                this.notifyAll();

        return game.getGameCamera();
    }
    
    public synchronized KeyManager getKeyManager(){
                this.notifyAll();

        return game.getKeyManager();
    }
    
    public synchronized int getWidth(){
                this.notifyAll();

        return game.getWidth();
    }
    
    public synchronized int getHeight(){
                this.notifyAll();

        return game.getHeight();
    }

    public synchronized Game getGame() {
                this.notifyAll();

        return game;
    }

    public synchronized void setGame(Game game) {
        this.game = game;
        this.notifyAll();
    }

    public synchronized World getWorld() {
                this.notifyAll();

        return world;
    }
    
    public synchronized void setWorld(World world){
        
        this.world = world;
                this.notifyAll();

    }

  
}
