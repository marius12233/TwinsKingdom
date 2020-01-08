/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.worlds;

import java.awt.Font;
import java.awt.FontFormatException;
import  twinskingdom.events.GameEventListener;
import java.awt.Graphics;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Observer;
import javax.swing.event.EventListenerList;
import  twinskingdom.game.GameHandler;
import  twinskingdom.game.Layer;
import  twinskingdom.game.RenderableLayers;
import  twinskingdom.entities.Entity;
import  twinskingdom.entities.EntityManager;
import  twinskingdom.entities.statics.Portal;
import  twinskingdom.events.GameEvent;
import  twinskingdom.events.GameEventSource;
import  twinskingdom.utils.GrabbableStarCollection;
import  twinskingdom.tiles.Tile;
import  twinskingdom.utils.Utils;

/**
 * This class is used to initialize the level worlds and their internal 
 * features/characters. The class also provides to load the layer textual files of
 * the world and to set their internal tiles. Entity Manager list and other support data structures 
 * are used for this purpose.
 */
public abstract class World implements Observer, GameEventListener, GameEventSource {

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

    //this list is used by the extending worlds to set their own characters
    protected LinkedList<Entity> entities; 

    protected int portalX;
    protected int portalY;

    protected EventListenerList listenerList = new EventListenerList();

    //the following attributes are used for vignettes' features setting 
    protected InputStream in;
    protected Font font;
    private float dimensionFont = 14;

    //the following methods are used for worlds' events management 
    @Override
    public void addGameEventListener(GameEventListener listener) {
        listenerList.add(GameEventListener.class, listener);
    }

    @Override
    public void removeGameEventListener(GameEventListener listener) {
        listenerList.remove(GameEventListener.class, listener);
    }

    @Override
    public void launchGameEvent(GameEvent evt) {
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

    /***
     * The constructor is used to create the entities' list, destinated to be
     * populated by the specific extending world, and to initialize vignettes' 
     * images and font features. 
     * @param path represents the path of the representing worlds' files
     */
    protected World(String path) {
        this.handler = GameHandler.instance;
        this.path = path;
        this.entities = new LinkedList<>();
        //this.init();
        try {
            in = World.class.getResourceAsStream("/fonts/EightBitDragon.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, in);
            font = font.deriveFont(dimensionFont);
        } catch (FontFormatException | IOException ex) {
            font = new Font("Monospaced", Font.BOLD, (int) dimensionFont);
        }
    }

    public World() {
    }

    /***
     * This method will be implemented by the extending worlds to set their own 
     * creatures.
     */
    protected abstract void setCreatures();

    /***
     * This metod is used to initialize the support structures, used to manage
     * worlds' internal creatures. 
     */
    public void init() {
        this.entities.clear();
        this.starCollection.removeAllStars();
        this.setCreatures();

        //the entity manager is populated by the specific worlds' entities
        for (Entity e : this.entities) {
            this.entityManager.addEntity(e);
        }

        //world loading
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

    /**
     * This tick method recalls Entity Manager tick own method, in order to
     * update the correct characters in the world.
     */
    public void tick() {
        //this.rl.loadLayers();
        this.entityManager.tick();
    }

    /***
     * This method is used to set the correct starting and ending map indices, 
     * depending on game camera positions.
     * @param g represents the game graphic
     */
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

    /***
     * @param x row tile index 
     * @param y column tile index
     * @return Tile object specified by the indices
     */
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

     /**
     * This method provides to load the world through its own layers' files.  
     * @param path represents the path of the specific directory, containing the
     * layers textual files loaded for the world.
     */
    protected void loadWorld(String path) {
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

    /***
     * This method is used to set worlds' tiles, related to specific 
     * IDs. This tiles represent worlds internal components. 
     * @param tileNum
     * @param x is the returned tile row index 
     * @param y is the returned tile column index 
     * @return the tile specified by x and y matrix indices
     */
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
            case 95:
                return Tile.rockTile;
            case 109:
                return Tile.flowersTile;
            case 129:
                return Tile.shrubTile;
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
            case 35:
                return Tile.castleWallTile;
            case 36:
                return Tile.castleWallTile1;
            case 37:
                return Tile.castleWallTile2;
            case 38:
                return Tile.castleWallTile3;
            case 1:
                return Tile.castleWallTile4;
            case 2:
                return Tile.castleWallTile5;
            case 57:
                return Tile.castleFloorTile;
            case 39:
                return Tile.castleCandleTile;
            case 40:
                return Tile.trfloor1Tile;
            case 42:
                return Tile.trwallTile;
            case 43:
                return Tile.column1Tile;
            case 44:
                return Tile.column2Tile;
            case 45:
                return Tile.window1Tile;
            case 46:
                return Tile.window2Tile;
            case 55:
                return Tile.blackTile;
            case 41:
                return Tile.trfloor2Tile;
            case 47:
                return Tile.ladderstTile;
            case 48:
                return Tile.ladderct1Tile;
            case 49:
                return Tile.carpet11Tile;
            case 50:
                return Tile.carpet12Tile;
            case 51:
                return Tile.carpet13Tile;
            case 52:
                return Tile.carpet21Tile;
            case 53:
                return Tile.carpet22Tile;
            case 54:
                return Tile.carpet23Tile;
            case 60:
                return Tile.throne1Tile;
            case 56:
                return Tile.throne2Tile;
            case 61:
                return Tile.ladderct2Tile;
            case 62:
                return Tile.ladderct3Tile;
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
