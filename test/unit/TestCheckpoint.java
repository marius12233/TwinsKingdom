/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import  twinskingdom.saves.Checkpoint;
import static  twinskingdom.saves.Checkpoint.saveCheckpoint;

/**
 *
 *  
 */
public class TestCheckpoint {
    
    private static Checkpoint cp1, cp2, cp3;
    private static int lives1, lives2, lives3;
    private static int levelID1, levelID2, levelID3;

    
    public TestCheckpoint() {
        
        cp1 = new Checkpoint(lives1, levelID1);
        cp2 = new Checkpoint(lives2, levelID2);
        cp3 = new Checkpoint(lives3, levelID3);
    }
    
    @BeforeClass
    public static void setUpClass() {

        lives1 = 3;
        levelID1 = 0;
        
        lives2 = 2;
        levelID2 = 1;
        
        lives3 = 0;
        levelID3 = 0;
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
    public void testSaveCheckpoint() throws ClassNotFoundException {
        System.out.println("");
        System.out.println("--------saveCheckpoint()--------");
        
        saveCheckpoint(cp1);       
        
        try (ObjectInputStream oos = new ObjectInputStream(new FileInputStream(new File("saves/Checkpoint.bin")))) {
            oos.readObject();
            oos.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }    
        
        assertNotNull(cp1);
        assertEquals(cp1.getLives(), 3);
        assertEquals(cp1.getLevelId(), 0);
        
        System.out.println("Lives: " + cp1.getLives());
        System.out.println("Level: " + cp1.getLevelId());
        System.out.println("");

        saveCheckpoint(cp2);
        
        try (ObjectInputStream oos = new ObjectInputStream(new FileInputStream(new File("saves/Checkpoint.bin")))) {
            oos.readObject();
            oos.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }        
        
        assertNotNull(cp2);
        assertEquals(cp2.getLives(), 2);
        assertEquals(cp2.getLevelId(), 1);
        
        System.out.println("Lives: " + cp2.getLives());
        System.out.println("Level: " + cp2.getLevelId());
        
    }
    
    @Test
    public void testLoadCheckpoint() {
        System.out.println("");
        System.out.println("--------loadCheckpoint()--------");
        
        System.out.println("Lives: " + cp3.getLives());
        System.out.println("Level: " + cp3.getLevelId());
        System.out.println("");
        
        try (ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(new File("saves/Checkpoint.bin")))) {
            oos.writeObject(cp1);
            oos.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
        
        cp3 = loadCheckpoint();
                
        assertNotNull(cp3);
        assertEquals(cp3.getLives(), cp1.getLives());
        assertEquals(cp3.getLevelId(), cp1.getLevelId());
        
        System.out.println("Lives: " + cp3.getLives());
        System.out.println("Level: " + cp3.getLevelId());
        System.out.println("");
        
        try (ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(new File("saves/Checkpoint.bin")))) {
            oos.writeObject(cp2);
            oos.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
        
        cp3 = loadCheckpoint();
        
        assertNotNull(cp3);
        assertEquals(cp3.getLives(), cp2.getLives());
        assertEquals(cp3.getLevelId(), cp2.getLevelId());
        
        System.out.println("Lives: " + cp3.getLives());
        System.out.println("Level: " + cp3.getLevelId());
        
    }
    
        public Checkpoint loadCheckpoint() {
        Checkpoint checkpoint = null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("saves/Checkpoint.bin")))) {
            checkpoint = (Checkpoint) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } 
        
        return checkpoint;
    }
    
}
