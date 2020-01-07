/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package twinkingdom.saves;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class contains all the checkpoints of the game. It is used to collect and sort them.
 */
public final class MemoryCard {
    
    private static SortedSet<Checkpoint> games = new TreeSet<>();
    private static final String path = "saves/games.save";
    
    private MemoryCard() {}
    
    static {
        try {
            load();
        } catch (IOException ex) {
            Logger.getLogger(MemoryCard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Loads all the checkpoints written in the 'games.save' file.
     * @throws IOException 
     */
    public static void load() throws IOException {
        File f = new File(path);
        f.createNewFile();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            games = (TreeSet<Checkpoint>) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } 
    }
    
    /**
     * Saves all the checkpoints to the 'games.save' file.
     */
    public static void save() {
         try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(path)))) {
            oos.writeObject(games);
            oos.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }
    
    public static void add(Checkpoint checkpoint) {
        games.add(checkpoint);
        save();
    }
    
    public static Collection<Checkpoint> getAllSavedGames() {
        return games;
    }
    
    public static void remove(Checkpoint checkpoint) {
       
        games.remove(checkpoint);
        save();
    }   
}