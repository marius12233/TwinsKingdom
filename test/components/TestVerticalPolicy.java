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
import  twinskingdom.entities.mobs.enemies.level3.EnchantedArmor;
import  twinskingdom.gfx.PlayerAssets;
import  twinskingdom.policies.VerticalPolicy;
import  twinskingdom.entities.mobs.player.Player;
import  twinskingdom.entities.mobs.states.DownMovementState;
import  twinskingdom.entities.mobs.states.MovementState;
import  twinskingdom.entities.mobs.states.UpMovementState;
import  twinskingdom.gfx.ArmorAssets;

/**
 *
 *  
 */
public class TestVerticalPolicy {
    
    private static VerticalPolicy Hp1, Hp2;
    private static Player player;
    private static EnchantedArmor armor;
    private static PlayerAssets playerAssets;
    private static ArmorAssets armorAssets;
    
    public TestVerticalPolicy() {
        
        player = new Player(0.0f, 0.0f, playerAssets);
        armor = new EnchantedArmor(1, 1, 30, 35, armorAssets);
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        playerAssets = new PlayerAssets();
        armorAssets = new ArmorAssets();
        
        playerAssets.init();
        armorAssets.init();
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
        
        MovementState upStateA = new UpMovementState(armor, armorAssets);
        MovementState upStateP = new UpMovementState(player, playerAssets);
        
        armor.setState(upStateA);
        player.setState(upStateP);
        
        assertTrue(player.getState() instanceof UpMovementState);
        System.out.println("State of player is: " + player.getState());
        System.out.println("Y of player is: " +  player.getY());
        System.out.println("");
        
        player.setY(10);
        Hp1 = new VerticalPolicy(player, 0, 10); 
        
        assertTrue(player.getState() instanceof DownMovementState);
        System.out.println("State of player is: " +  player.getState());
        System.out.println("Y of player is: " + player.getY());
        System.out.println("");
        
        assertTrue(armor.getState() instanceof UpMovementState);
        System.out.println("State of armor is: " + armor.getState());
        System.out.println("Y of player is: " +  armor.getY());
        System.out.println("");
        
        armor.setY(15);
        Hp2 = new VerticalPolicy(armor, 20, 30);
        
        assertTrue(armor.getState() instanceof DownMovementState);
        System.out.println("State of armor is: " +  armor.getState());
        System.out.println("Y of armor is: " + armor.getY());
        System.out.println("");
    }
}
