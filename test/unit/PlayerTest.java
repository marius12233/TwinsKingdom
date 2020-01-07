/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import twinkingdom.gfx.PlayerAssets;
import twinkingdom.entities.statics.grabbable.GrabbableHealthPotion;
import twinkingdom.entities.mobs.player.Player;

/**
 *
 * @author aless
 */
public class PlayerTest {
    
    private static Player player;
    private static PlayerAssets playerAssets;
    
    public PlayerTest(){    
        
        player = new Player(0.0f, 0.0f, playerAssets, 3);
    }
    
    @BeforeClass
    public static void setUpClass() {
        playerAssets = new PlayerAssets();
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
    @Test
    public void testDie() {
 
        System.out.println("");
        System.out.println("--------die()--------");
        
        player.setNumLives(3);
        System.out.println("Number of lives: " + player.getNumLives());
        player.setHealthPoints(20);
        System.out.println("Life points: " + player.getHealthPoints());
        System.out.println("");
        
        player.die();
        assertTrue(player.isActive);
        assertEquals(player.getNumLives(), 2);
        assertEquals(player.getMaxHealthPoints(), player.getHealthPoints());
        if (player.getNumLives() == 0) {
            System.out.println("You're dead");
            System.out.println("Number of lives: " + player.getNumLives());
            System.out.println("");
        }
        else {
            System.out.println("You're alive");
            System.out.println("Number of lives: " + player.getNumLives());
            System.out.println("");
        }
        
        player.setNumLives(0);
        
        player.die();
        assertEquals("You're dead", player.getNumLives(), 0);
        if (player.getNumLives() == 0) {
            System.out.println("You're dead");
            System.out.println("Number of lives: " + player.getNumLives());
            System.out.println("");
        }
        else {
            System.out.println("You're alive");
            System.out.println("Number of lives: " + player.getNumLives());
            System.out.println("");
        }
        
    }
    
    @Test
    public void testTouchGrabbable() {
        System.out.println("");
        System.out.println("--------touchGrabbable()--------");
        
        GrabbableHealthPotion ph = new GrabbableHealthPotion(1.0f, 1.0f, 15, 22);
        
        assertTrue(ph.isActive);
        System.out.println("");
        System.out.println("Potion is active: " + ph.isActive);
        System.out.println("");
        
        player.touchGrabbable(ph);
        
        assertFalse(ph.isActive);
        System.out.println("Potion is active: " + ph.isActive);
        System.out.println("");
        
    }
    
    @Test
    public void testHurth() {
        System.out.println("");
        System.out.println("----------EntityTest: hurth()--------");
        
        player.setHealthPoints(6);
        player.setNumLives(1);
        int health_points = player.getHealthPoints();
        int amt = 2;
        
        System.out.println("Healthpoints before damaged: " + player.getHealthPoints());
        System.out.println("Damage: " + amt);
        
        player.hurt(amt);
        
        assertEquals("Wrong value", player.getHealthPoints(),health_points - amt);
        assertTrue(player.isActive);
        
        System.out.println("Healthpoints after damaged: " + player.getHealthPoints());
        System.out.println("");
        
        amt = 4;
        
        System.out.println("Healthpoints before damaged: " + player.getHealthPoints());
        System.out.println("Damage: " + amt);
        
        health_points = player.getHealthPoints();
        player.hurt(amt);
     
        assertEquals("Wrong value", player.getHealthPoints(), health_points - amt);
        assertFalse(player.isActive);
        System.out.println("Healthpoints before damaged: " + player.getHealthPoints());
        System.out.println("Damage: " + amt);
    }
    
}
