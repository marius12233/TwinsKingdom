/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.game;

import java.util.LinkedList;
import java.util.List;
import  twinskingdom.worlds.*;

/**
 *
 */
public class LevelHandler {

    private List<World> worlds;
    private int currentWorld;
    private static int worldId; //this attribute is used statically for some worlds' controls

    public LevelHandler() {

        this.currentWorld = -1;
        this.worldId = -1;
        this.worlds = new LinkedList<>();
        this.worlds.add(new Intro());
        this.worlds.add(new Forest());
        this.worlds.add(new Dungeon1());
        this.worlds.add(new Garden());
        this.worlds.add(new Dungeon2());
        this.worlds.add(new Castle());
        this.worlds.add(new Dungeon3());
        this.worlds.add(new ThroneRoom());
    }

    public void setCurrentWorld(int id) {
        this.currentWorld = id;
        worldId = id;
    }

    public int getCurrentWorldId() {
        return this.currentWorld;
    }

    public static int getWorldId() {
        return worldId;
    }

    public World getCurrentWorld() {
        return this.worlds.get(currentWorld);
    }

    public World getNextWorld() {
        if (currentWorld + 1 <= this.worlds.size()) {
            worldId = currentWorld + 1;
        }
        return ((currentWorld + 1 == this.worlds.size()) ? null : this.worlds.get(++currentWorld));
    }

}
