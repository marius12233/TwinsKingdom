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
import  twinskingdom.entities.mobs.enemies.finalLevel.LadyParanoia;
import  twinskingdom.entities.statics.BlackSpell;
import  twinskingdom.entities.mobs.states.LeftMovementState;
import  twinskingdom.entities.mobs.states.RightMovementState;
import  twinskingdom.gfx.BlackSpellAssets;
import  twinskingdom.gfx.LadyParanoiaAssets;

/**
 *
 *  
 */
public class TestLadyParanoia {
    
    private static LadyParanoia ladyParanoia;
    private static LadyParanoiaAssets ladyAssets;
    private static BlackSpell blackSpell;
    private static BlackSpellAssets blackSpellAssets;
    
    public TestLadyParanoia() {
        
        ladyParanoia = new LadyParanoia(0.0f, 0.0f, ladyAssets);
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        ladyAssets = new LadyParanoiaAssets();
        ladyAssets.init();
        blackSpellAssets = new BlackSpellAssets();
        blackSpellAssets.init();
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
    public void testCreateWeaponOne() {
        System.out.println("-------createWeapon()--without parameters--------");
        System.out.println("");
        
        ladyParanoia.setX(30);
        ladyParanoia.setY(80);
        blackSpell = ladyParanoia.createWeapon();
        
        assertEquals((int)blackSpell.getX(), 330);
        assertEquals((int)blackSpell.getY(), -220);
        
        System.out.println("BlackSpell x value is: " + blackSpell.getX());
        System.out.println("BlackSpell y value is: " + blackSpell.getY());
        System.out.println("");
        
        ladyParanoia.setX(550);
        ladyParanoia.setY(320);
        blackSpell = ladyParanoia.createWeapon();
        
        assertEquals((int)blackSpell.getX(), 850);
        assertEquals((int)blackSpell.getY(), 20);
        
        System.out.println("BlackSpell x value is: " + blackSpell.getX());
        System.out.println("BlackSpell y value is: " + blackSpell.getY());
        System.out.println("");
    }
    
    @Test
    public void testCreateWeaponTwo() {
        System.out.println("-------createWeapon()--with parameters--------");
        System.out.println("");
        
        blackSpell = ladyParanoia.createWeapon(20, 30, 100, 100);
        
        assertEquals((int)blackSpell.getX(), 20);
        assertEquals((int)blackSpell.getY(), 30);
        assertTrue(blackSpell.getState() instanceof RightMovementState);
        
        System.out.println("BlackSpell x value is: " + blackSpell.getX());
        System.out.println("BlackSpell y value is: " + blackSpell.getY());
        System.out.println("The state of blackspell is: " + blackSpell.getState());
        System.out.println("");
        
        blackSpell.setState(new LeftMovementState(blackSpell, blackSpellAssets));
        assertTrue(blackSpell.getState() instanceof LeftMovementState);
        System.out.println("The state of blackspell is: " + blackSpell.getState());
        System.out.println("");
        
        blackSpell = ladyParanoia.createWeapon(55, 32, 100, 100);
        
        assertEquals((int)blackSpell.getX(), 55);
        assertEquals((int)blackSpell.getY(), 32);
        assertTrue(blackSpell.getState() instanceof RightMovementState);
        
        System.out.println("BlackSpell x value is: " + blackSpell.getX());
        System.out.println("BlackSpell y value is: " + blackSpell.getY());
        System.out.println("The state of blackspell is: " + blackSpell.getState());
        System.out.println("");
    }
}
