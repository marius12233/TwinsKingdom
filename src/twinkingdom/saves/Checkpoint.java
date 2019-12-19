package twinkingdom.saves;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Checkpoint
        implements Serializable {

    private int lives, levelId;
    private static String path = "saves/Checkpoint.bin";

    public Checkpoint(int lives, int levelId) {
        this.lives = lives;
        this.levelId = levelId;
        saveCheckpoint(this);
    }

    public static Checkpoint loadCheckpoint() {
        Checkpoint checkpoint = null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(path)))) {
            checkpoint = (Checkpoint) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } 
        return checkpoint;
    }

    public static void saveCheckpoint(Checkpoint checkpoint) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(path)))) {
            oos.writeObject(checkpoint);
            oos.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }

    }
    
    public static boolean removeCheckpoint(Checkpoint checkpoint) {
        File toRemove = new File(path);
        return toRemove.delete();
    }

    public int getLives() {
        return lives;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLives(int lives) {
        this.lives = lives;
        saveCheckpoint(this);
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
        saveCheckpoint(this);
    }

}
