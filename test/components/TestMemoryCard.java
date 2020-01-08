/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import  twinskingdom.saves.Checkpoint;
import  twinskingdom.saves.MemoryCard;

/**
 *
 *  
 */
public class TestMemoryCard {
    
    private static SortedSet<Checkpoint> checkpoints = new TreeSet<>();;
    private static Checkpoint cp1, cp2, cp3, control;
    private static int lives1, lives2, lives3;
    private static int levelID1, levelID2, levelID3;
    
    public TestMemoryCard() {
        
        cp1 = new Checkpoint(lives1, levelID1);
        cp2 = new Checkpoint(lives2, levelID2);
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        lives1 = 3;
        levelID1 = 0;
        
        lives2 = 2;
        levelID2 = 1;
        
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
    public void testAddRemoveAndGetAllSavedGames() {
        System.out.println("");
        System.out.println("----------------add()--and--getAllSavedGames()-----------------");
        
        MemoryCard.clear();
        System.out.println("Insert this checkpoint: " + cp1.getLives() + " " + cp1.getLevelId());
        System.out.println("Insert this checkpoint: " + cp2.getLives() + " " + cp2.getLevelId());
        MemoryCard.add(cp1); 
        MemoryCard.add(cp2);
        
        checkpoints = (SortedSet<Checkpoint>) MemoryCard.getAllSavedGames();
        assertNotNull(checkpoints);
        
        Iterator<Checkpoint> iter = checkpoints.iterator();
        int i=0;
        
        while(iter.hasNext()) {
            
            control = iter.next();
            i++;
            System.out.println("Checkpoint numero: " + i);
            System.out.println("Lives: " + control.getLives());
            System.out.println("Level: " + control.getLevelId());
            System.out.println("");           
        }
        
        assertEquals("Wrong number of checkpoints", i, 2);
        
        System.out.println("---------------remove()--and--getAllSavedGames()----------------");
        
        System.out.println("Remove this checkpoint: " + cp2.getLives() + " " + cp2.getLevelId());
        MemoryCard.remove(cp2);
        
        iter = checkpoints.iterator();
        i=0;
        
        while(iter.hasNext()) {
            
            control = iter.next();
            i++;
            System.out.println("Checkpoint numero: " + i);
            System.out.println("Lives: " + control.getLives());
            System.out.println("Level: " + control.getLevelId());
            System.out.println("");           
        }
        
        assertEquals("Wrong number of checkpoints", i, 1);
        
        System.out.println("Remove this checkpoint: " + cp1.getLives() + " " + cp1.getLevelId());
        MemoryCard.remove(cp1);
      
        iter = checkpoints.iterator();
        i=0;
        
        while(iter.hasNext()) {
            i++;
        }
        
        assertEquals("Wrong number of checkpoints", i, 0);
        System.out.println("The set is empty");
            
    }
}
