/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.util.Observable;
import java.util.Observer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import  twinskingdom.entities.statics.grabbable.GrabbableStar;
import  twinskingdom.utils.GrabbableStarCollection;

/**
 *
 *  
 */
public class TestGrabbableStarCollection implements Observer{
    
    private static GrabbableStarCollection gsc1;
    private static GrabbableStar gs1, gs2, gs3;
    
    public TestGrabbableStarCollection() {
        
        gsc1 = new GrabbableStarCollection(1);
        gs1 = new GrabbableStar(20, 20, 5, 5);
        gs2 = new GrabbableStar(40, 40, 5, 5);
        gs3 = new GrabbableStar(60, 60, 5, 5);
    }
    
    @BeforeClass
    public static void setUpClass() {
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
    public void testGrabbableStarCollectionClass() {
        System.out.println("-----------GrabbableStarCollectionClass-----------");
        System.out.println("");
        
        gsc1.addObserver((Observer) this);
        
        gsc1.addStar(gs2);
        gsc1.addStar(gs1);
        gsc1.addStar(gs3);
        
        assertEquals(gsc1.getSize(), 3);
        System.out.println("Number of stars grabbed: " + gsc1.getSize());
        
        gsc1.removeAllStars();
        
        assertEquals(gsc1.getSize(), 0);
        System.out.println("Number of stars grabbed: " + gsc1.getSize());
    }
    
    public void update(Observable o, Object arg) {       
    }
    
}
