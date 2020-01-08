/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import  twinskingdom.entities.mobs.enemies.level1.Bat;
import  twinskingdom.entities.mobs.player.Player;
import  twinskingdom.entities.mobs.states.DownMovementState;
import  twinskingdom.entities.mobs.states.LeftMovementState;
import  twinskingdom.entities.mobs.states.MovementState;
import  twinskingdom.entities.mobs.states.RightMovementState;
import  twinskingdom.gfx.BatAssets;
import  twinskingdom.gfx.PlayerAssets;
import  twinskingdom.policies.HorizontalPolicy;

/**
 *
 *  
 */
public class TestHorizontalPolicy {
    
    private static HorizontalPolicy Hp1, Hp2;
    private static Player player;
    private static Bat bat;
    private static PlayerAssets playerAssets;
    private static BatAssets batAssets;
    
    public TestHorizontalPolicy() {
        
        player = new Player(0.0f, 0.0f, playerAssets);
        bat = new Bat(1, 1, 30, 35, batAssets);
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        playerAssets = new PlayerAssets();
        batAssets = new BatAssets();
        
        playerAssets.init();
        batAssets.init();
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
    public void testGetMovement() {
        System.out.println("");
        System.out.println("--------getMovement()--------");
        
        MovementState downState = new DownMovementState(player, playerAssets);
        MovementState leftState = new LeftMovementState(player, playerAssets);
        MovementState rightState = new RightMovementState(bat, batAssets);
        
        assertTrue(player.getState() instanceof DownMovementState);
        System.out.println("State of player is: " + player.getState());
        System.out.println("X of player is: " +  player.getX());
        System.out.println("");
        
        player.setX(10);
        Hp1 = new HorizontalPolicy(player, 0, 10); 
        
        assertTrue(player.getState() instanceof LeftMovementState);
        System.out.println("State of player is: " +  player.getState());
        System.out.println("X of player is: " + player.getX());
        System.out.println("");
        
        bat.setState(rightState);
        
        assertTrue(bat.getState() instanceof RightMovementState);
        System.out.println("State of bat is: " + bat.getState());
        System.out.println("X of bat is: " +  bat.getX());
        System.out.println("");
        
        bat.setX(50);
        Hp2 = new HorizontalPolicy(bat, 20, 30);
        
        assertTrue(bat.getState() instanceof LeftMovementState);
        System.out.println("State of player is: " +  bat.getState());
        System.out.println("X of player" + bat.getX());
        System.out.println("");
    }
}

