/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import  twinskingdom.entities.mobs.enemies.level1.Arrow;
import  twinskingdom.entities.mobs.player.PlayerArcher;
import  twinskingdom.gfx.PlayerBowAssets;
import  twinskingdom.utils.UtilityTimer;

/**
 *
 *  
 */
public class TestDownArcherState {
    private static UtilityTimer timer;
    private static PlayerArcher playerArcher;
    private static PlayerBowAssets playerAssets;
    
    public TestDownArcherState() {
        
        playerArcher = new PlayerArcher(0.0f, 0.0f, playerAssets, 3);
        timer = new UtilityTimer(10000, true);
    }
    
    @BeforeClass
    public static void setUpClass() {
        playerAssets = new PlayerBowAssets();
        playerAssets.init();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testAttack() throws InterruptedException{

        playerArcher.setX(10);
        playerArcher.setY(30);
       
        System.out.println("First test");
        Arrow arrow = new Arrow(playerArcher.getX() + 20, playerArcher.getY()+90, 10, 10);
        assertEquals("Wrong value on X", (int)arrow.getX(), (int)playerArcher.getX() + 20);
        assertEquals("Wrong value on Y", (int)arrow.getY(), (int)playerArcher.getY() + 90);
        System.out.println("Arrows X: " + arrow.getX());
        System.out.println("Arrows Y: " + arrow.getY());
            
        System.out.println("Time is: " + timer.getTimeDescendent());
            
        playerArcher.setX(playerArcher.getX() + 2);
        playerArcher.setY(playerArcher.getY() + 3);
        
        System.out.println("Second test");
        arrow = new Arrow(playerArcher.getX() + 20, playerArcher.getY()+90, 10, 10);
        assertEquals("Wrong value on X", (int)arrow.getX(), (int)playerArcher.getX() + 20);
        assertEquals("Wrong value on Y", (int)arrow.getY(), (int)playerArcher.getY() + 90);
        System.out.println("Arrows X: " + arrow.getX());
        System.out.println("Arrows Y: " + arrow.getY());
            
        System.out.println("Time is: " + timer.getTimeDescendent());
            
        playerArcher.setX(playerArcher.getX() + 17);
        playerArcher.setY(playerArcher.getY() + 32);
        
        System.out.println("Third test");
        arrow = new Arrow(playerArcher.getX() + 20, playerArcher.getY()+90, 10, 10);
        assertEquals("Wrong value on X", (int)arrow.getX(), (int)playerArcher.getX() + 20);
        assertEquals("Wrong value on Y", (int)arrow.getY(), (int)playerArcher.getY() + 90);
        System.out.println("Arrows X: " + arrow.getX());
        System.out.println("Arrows Y: " + arrow.getY());
            
        System.out.println("Time is: " + timer.getTimeDescendent());
            
        TimeUnit.SECONDS.sleep(1);      
    }
}
