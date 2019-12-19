/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tutorial2dprogramming.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import tutorial2dprogramming.Game;
import tutorial2dprogramming.Handler;
import tutorial2dprogramming.staticentities.Tree;

/**
 *
 * @author SSQ1
 */
public class EntityTest extends Entity {

    private static Entity instance;

    public EntityTest() {
        super(new Handler(new Game("Title", 500, 500)),
                0, 0, 64, 64);;
    }

    @BeforeClass
    public static void setUpClass() {

        instance = new EntityTest();

    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of hurt method, of class Entity.
     */
    @Test
    public void testHurth() {
        System.out.println("----------EntityTest: hurth()--------");
        int amt;
        int health;

        amt = 10;
        instance.setHealth(0);
        try {

            health = instance.health;

            System.out.println("Salute: " + health);
            instance.hurt(amt);
            assertEquals("Danno: " + amt, instance.health, health - amt);
            assertFalse(instance.isActive);
            System.out.println(instance.health);
            System.out.println(health - amt);
        } catch (Exception e) {
            fail("Fail Hurth 1 ");
            e.printStackTrace();
        }

        //----------------------------
        amt = 100;
        instance.setHealth(100);
        try {

            health = instance.health;

            System.out.println("Salute: " + health);
            instance.hurt(amt);
            assertEquals("Danno: " + amt, instance.health, health - amt);
            assertFalse(instance.isActive);
            System.out.println(instance.health);
            System.out.println(health - amt);
        } catch (Exception e) {
            fail("Fail Hurth 2");
            e.printStackTrace();
        }
        
        //----------------------------
        instance.isActive=true;
        instance.setHealth(100);
        amt =20 ;
        
        try {

            health = instance.health;

            System.out.println("Salute: " + health);
            instance.hurt(amt);
            assertEquals("Danno: " + amt, instance.health, health - amt);
            assertTrue(instance.isActive());
            System.out.println(instance.health);
            System.out.println(health - amt);
        } catch (Exception e) {
            fail("Fail Hurth 2");
            e.printStackTrace();
        }
    //----------------------------
        instance.isActive=true;
        instance.setHealth(-10);
        amt =20 ;
        
        try {

            health = instance.health;

            System.out.println("Salute: " + health);
            instance.hurt(amt);
            assertEquals("Danno: " + amt, instance.health, health - amt);
            assertFalse(instance.isActive());
            System.out.println(instance.health);
            System.out.println(health - amt);
        } catch (Exception e) {
            fail("Fail Hurth 2");
            e.printStackTrace();
        }
    
    
    }

    

    

    /**
     * Test of checkEntityCollisions method, of class Entity.
     */
    @Test
    public void testCheckEntityCollisions() {
        
        System.out.println("checkEntityCollisions");
        float xOffset = 0.0F;
        float yOffset = 0.0F;
        Entity instance = null;
        boolean expResult = false;
        boolean result = instance.checkEntityCollisions(xOffset, yOffset);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of touchGrabbable method, of class Entity.
     */
    @Ignore
    public void testTouchGrabbable() {
        System.out.println("touchGrabbable");
        Entity e = null;
        Entity instance = null;
        instance.touchGrabbable(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actionOnCollision method, of class Entity.
     */
    @Ignore
    public void testActionOnCollision() {
        System.out.println("actionOnCollision");
        Entity e = null;
        Entity instance = null;
        instance.actionOnCollision(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCollisionBounds method, of class Entity.
     */
    @Ignore
    public void testGetCollisionBounds() {
        System.out.println("getCollisionBounds");
        float xOffset = 0.0F;
        float yOffset = 0.0F;
        Entity instance = null;
        Rectangle expResult = null;
        Rectangle result = instance.getCollisionBounds(xOffset, yOffset);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

   

    
    


    /**
     * Test of tick method, of class Entity.
     */
    @Ignore
    public void testTick() {
        System.out.println("tick");
        Entity instance = null;
        instance.tick();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of render method, of class Entity.
     */
    @Ignore
    public void testRender() {
        System.out.println("render");
        Graphics g = null;
        Entity instance = null;
        instance.render(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
   

   

    /**
     * Test of getMaxHealth method, of class Entity.
     */
    @Ignore
    public void testGetMaxHealth() {
        System.out.println("getMaxHealth");
        Entity instance = null;
        int expResult = 0;
        int result = instance.getMaxHealth();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Override
    public void tick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
