package twinkingdom;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author mario
 */
public class Launcher {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game game = new Game("Twins Kingdom");
        game.start();
    }    
}
