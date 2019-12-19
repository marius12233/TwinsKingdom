/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom;

import java.awt.Graphics;
import twinkingdom.worlds.World;

/**
 *
 * @author Antonia
 */
public class GameState2 extends State {

    
    private World world;

    
    public GameState2(GameHandler handler) {
        super(handler);
     //   world = new World(handler, "res/worlds/world2.txt");
        handler.setWorld(world);
    }
    

    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }
    
    

}
