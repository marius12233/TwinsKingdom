package  twinskingdom.saves;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * This class sets or loads a checkpoint during the game, keeping some info
 * about the player's status.
 */
public class Checkpoint
        implements Serializable, Comparable<Checkpoint> {

    private int lives, levelId;
    private Date lastSaved;
    private static String path = "saves/";

    /**
     * Creates a checkpoint
     *
     * @param lives Number of player's lives.
     * @param levelId Identifier of the level.
     */
    public Checkpoint(int lives, int levelId) {
        this.lastSaved = new Date();
        this.lives = lives;
        this.levelId = levelId;
        MemoryCard.add(this);
    }

    /**
     * Loads a checkpoint.
     *
     * @return The requested checkpoint.
     */
    public static Checkpoint loadCheckpoint() {
        Checkpoint checkpoint = null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(path)))) {
            checkpoint = (Checkpoint) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return checkpoint;
    }

    /**
     * Saves a checkpoint.
     *
     * @param checkpoint The checkpoint to save.
     */
    public static void saveCheckpoint(Checkpoint checkpoint) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(path)))) {
            oos.writeObject(checkpoint);
            oos.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }

    }

    /**
     * Deletes a specific checkpoint.
     *
     * @param checkpoint The checkpoint to delete.
     * @return true if the file is successfully deleted. Otherwise, this method
     * returns false.
     */
    public static boolean removeCheckpoint(Checkpoint checkpoint) {
        MemoryCard.remove(checkpoint);
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
        this.lastSaved = new Date();
        MemoryCard.save();
        //saveCheckpoint(this);
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
        this.lastSaved = new Date();
        MemoryCard.save();
        //saveCheckpoint(this);
    }

    public Date getLastSaved() {
        return this.lastSaved;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Checkpoint other = (Checkpoint) obj;
        if (!Objects.equals(this.lastSaved, other.lastSaved)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Checkpoint o) {
        if (o == null) {
            throw (new NullPointerException());
        }

        if (this.lastSaved.before(o.lastSaved)) {
            return 1;
        } else if (this.lastSaved.after(o.lastSaved)) {
            return -1;
        }

        return 0;
    }

}
