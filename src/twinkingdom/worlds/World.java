/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.worlds;

import twinkingdom.events.GameEventListener;
import twinkingdom.events.CompletedLevelEvent;
import java.awt.Graphics;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.Observer;
import javax.swing.event.EventListenerList;
import twinkingdom.GameHandler;
import twinkingdom.Layer;
import twinkingdom.RenderableLayers;
import twinkingdom.entities.Entity;
import twinkingdom.entities.EntityManager;
import twinkingdom.gui.StarsPanel;
import twinkingdom.entities.statics.Portal;
import twinkingdom.events.GameEvent;
import twinkingdom.events.GameEventType;
import twinkingdom.utils.GrabbableStarCollection;
import twinkingdom.tiles.Tile;
import twinkingdom.utils.Utils;

/**
 *
 * @author mario
 */
public abstract class World implements Observer, GameEventListener {

    protected int width, height;
    protected int[][] tiles;
    protected int spawnX;
    protected int spawnY;
    protected GameHandler handler;
    protected GrabbableStarCollection starCollection;
    protected RenderableLayers rl;
    protected String path;
    //Entities
    protected EntityManager entityManager;
    protected Portal portal;

    //protected LinkedList<Creature> creatures;
    protected LinkedList<Entity> entities;

    protected int portalX;
    protected int portalY;

    protected EventListenerList listenerList = new EventListenerList();

    public void addGameEventListener(GameEventListener listener) {
        listenerList.add(GameEventListener.class, listener);
    }

    public void removeGameEventListener(GameEventListener listener) {
        listenerList.remove(GameEventListener.class, listener);
    }

    void launchGameEvent(GameEvent evt) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i = i + 2) {
            if (listeners[i] == GameEventListener.class) {
                ((GameEventListener) listeners[i + 1]).onGameEventActionPerformed(evt);
            }
        }
    }

    public GrabbableStarCollection getStarCollection() {
        return starCollection;
    }

    protected World(String path) {
        this.handler = GameHandler.instance;
        this.path = path;
        System.out.println("Constructing world from... " + path);
        //creatures=new LinkedList<Creature>();
        this.entities = new LinkedList<>();
        //this.init();

    }

    public World() {
    }

    protected abstract void setCreatures();

    public void init() {
        this.entities.clear();
        this.starCollection.removeAllStars();
        this.setCreatures();

        for (Entity e : this.entities) {
            this.entityManager.addEntity(e);
        }

        loadWorld(path);
        this.rl = new RenderableLayers(this.tiles[0].length, this.tiles.length, path);
        this.rl.loadLayers();

    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void tick() {
        //this.rl.loadLayers();
        this.entityManager.tick();
    }

    public void render(Graphics g) {
        //Servono per non far disegnare ogni volta tutta la mappa
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));

                for (int i = 0; i < rl.countLayers(); i++) {
                    getLayerTile(i, x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
                }
            }
        }

        entityManager.render(g);
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return Tile.invisibleTile;
        }

        Tile t = Tile.tiles[tiles[x][y]];

        if (t == null) {
            return Tile.invisibleTile;
        }
        return t;
    }

    //Carica un file per costruire il mondo
    protected void loadWorld(String path) {
        System.out.println("Loading world from... " + path);
        String file = Utils.loadFileAsString(path + "layer1.txt");
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);

        tiles = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 2]);
            }
        }
    }

    public Tile getLayerTile(int tileNum, int x, int y) {

        Layer layer = this.rl.getLayer(tileNum);

        int[][] matrix = layer.getMatrix();

        int tile_id = matrix[x][y];

        switch (tile_id) {
            case 21:
                return Tile.grassTile;
            case 24:
                return Tile.groundTile;
            case 81:
                return Tile.waterTile;
            case 172:
                return Tile.treesTile11;
            case 173:
                return Tile.treesTile12;
            case 174:
                return Tile.treesTile13;
            case 175:
                return Tile.treesTile14;
            case 191:
                return Tile.treesTile21;
            case 192:
                return Tile.treesTile22;
            case 193:
                return Tile.treesTile23;
            case 194:
                return Tile.treesTile24;
            case 210:
                return Tile.treesTile31;
            case 211:
                return Tile.treesTile32;
            case 212:
                return Tile.treesTile33;
            case 213:
                return Tile.treesTile34;
            case 229:
                return Tile.treesTile41;
            case 230:
                return Tile.treesTile42;
            case 231:
                return Tile.treesTile43;
            case 232:
                return Tile.treesTile44;
            default:
                return Tile.invisibleTile;
        }
    }

    public int getWidth() {
        return width;
    }

    public int getheight() {
        return height;
    }

    public RenderableLayers getRenderableLayers() {
        return this.rl;
    }

    @Override
    public abstract void onGameEventActionPerformed(GameEvent evt);

}
