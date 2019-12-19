/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.event.EventListenerList;
import twinkingdom.entities.EntityManager;
import twinkingdom.entities.mobs.player.Player;
import twinkingdom.entities.mobs.player.PlayerArcher;
import twinkingdom.entities.mobs.player.PlayerMage;
import twinkingdom.gfx.Assets;
import twinkingdom.gfx.GameCamera;
import twinkingdom.gfx.PlayerAssets;
import twinkingdom.gui.GameGUI;
import twinkingdom.gui.Health;
import twinkingdom.input.KeyManager;
import twinkingdom.events.CompletedLevelEvent;
import twinkingdom.events.GameEvent;
import twinkingdom.events.GameEventListener;
import twinkingdom.events.GameEventType;
import twinkingdom.gfx.ImageLoader;
import twinkingdom.gui.Weapons;
import twinkingdom.saves.Checkpoint;
import twinkingdom.worlds.World;

/**
 *
 * @author mario
 */
public class Game implements Runnable, Observer, GameEventListener {

    private int width, height;
    private Thread thread;
    private boolean running = false;
    private final String title;
    private BufferStrategy bs;
    private Graphics g;
    private State gameState;
    private final KeyManager keyManager;
    private GameCamera gameCamera;
    private GameHandler handler;
    private EntityManager entityManager;
    private World currentWorld;
    private LevelHandler levelHandler;
    private Image image;

    private GameGUI gui;
    private Player player;

    private Checkpoint checkpoint;

    private boolean changingLevel = false;
    
    protected EventListenerList listenerList = new EventListenerList();


    public Game(String title) {
        this.title = title;
        keyManager = new KeyManager();
    }

    public void init() throws IOException {
        changingLevel = true;      
        image = ImageLoader.loadImage("/images/levelloading2.gif");

        handler = GameHandler.getInstance();
        handler.setGame(this);

        checkpoint = Checkpoint.loadCheckpoint();
        if (checkpoint == null) {
            checkpoint = new Checkpoint(3, 0);
        }

        player = new Player(288, 320, new PlayerAssets());
        player.getHealth().setLives(checkpoint.getLives());

       // gameState = new GameState(handler);
        //State.setState(gameState);

        this.levelHandler = new LevelHandler();
        levelHandler.setCurrentWorld(checkpoint.getLevelId());

        this.entityManager = new EntityManager(handler, player);

        this.gui = new GameGUI(title);
        this.width = gui.getCanvas().getSize().width;
        this.height = gui.getCanvas().getSize().height;

        gui.getFrame().addKeyListener(keyManager);
        keyManager.addGameEventListener(this);

        gameCamera = new GameCamera(handler, 0, 0);

        Assets.init();
       // image = new ImageIcon(new URL("/images/levelloading2.gif")).getImage();
        
        render();
        render();
        render();

        changingLevel = false;
        
        this.addGameEventListener(this);
    }

    public void tick() {

        if (changingLevel) {
            return;
        }
        
        keyManager.tick();
        if (currentWorld != null) {
            currentWorld.tick();
        }

    }

    public void render() {

        //Buffer strategy è un buffer che permette di scrivere sul buffer prima di scriver sullo screen
        bs = gui.getCanvas().getBufferStrategy();
        if (bs == null) {
            gui.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();

        g.clearRect(0, 0, width, height);

        if (changingLevel) {
            g.setColor(Color.black);
            g.fillRect(0, 0, width, height);
            g.setColor(Color.WHITE);
           
            if(image != null)
            g.drawImage(image, 140, 20, null);
           
            bs.show();
            return;
        }

        if (currentWorld != null) {
            currentWorld.render(g);
        }

        bs.show();
        g.dispose();

    }

    @Override
    public void run() {
        try {
            init();
        } catch (IOException ex) {
            System.exit(1);
        }
  
        int fps = 60;

        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;
        
        changingLevel = true;

        render(); render(); render();
        image = ImageLoader.loadImage("/images/levelloading2.gif");

        
        currentWorld = levelHandler.getCurrentWorld();

        currentWorld.setEntityManager(this.entityManager);
        this.handler.setWorld((World) currentWorld);

        currentWorld.init();
        currentWorld.addGameEventListener(this);
        player.getHealth().addObserver((Observer) gui.getHealthBar());
        player.getHealth().addObserver((Observer) this);
        currentWorld.getStarCollection().addObserver((Observer) this.gui.getStarsPanel());
        
        changingLevel = false;
        
        while (running) {
            while (changingLevel) {
            }
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;

            lastTime = now;
            if (delta >= 1) {
                tick();
                render();
                delta--;
                ticks++;
            }
            if (timer >= 1000000000) {
                ticks = 0;
                timer = 0;
            }
        }
    }

    //@Override
    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stopGame() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (Exception ex) {
        }
    }
    
    public KeyManager getKeyManager() {
        return keyManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public GameGUI getGui() {
        return gui;
    }

    @Override
    public void update(Observable o, Object arg) {

        Health h = (Health) o;

        if (h.getHealthPoints() <= 0) {
            if (h.getLives() > 1) {
                resetLevel();
            } else {
               this.launchGameEvent(new GameEvent(this, GameEventType.GAME_OVER));
            }
        }
    }

    private void changeLevel() {

        
        changingLevel = true;
        image = ImageLoader.loadImage("/images/levelloading2.gif");
        currentWorld = levelHandler.getNextWorld();
        if (currentWorld == null) {
            this.launchGameEvent(new GameEvent(this, GameEventType.GAME_COMPLETED));
            changingLevel = false;
            return;
        }
        checkpoint.setLevelId(levelHandler.getCurrentWorldId());

        render();
        render();
        render();

        this.entityManager.removeAllEntities();
        this.player.getHealth().setHealthPoints(300);
        this.entityManager.setPlayer(this.player);
        currentWorld.setEntityManager(this.entityManager);

        this.handler.setWorld(currentWorld);
        currentWorld.getStarCollection().addObserver((Observer) this.gui.getStarsPanel());
        currentWorld.init();
        
        currentWorld.addGameEventListener(this);

        changingLevel = false;
    }

    private void resetLevel() {
        changingLevel = true;

        render();
        render();
        render();
        
        currentWorld = this.levelHandler.getCurrentWorld();
        currentWorld.getEntityManager().removeAllEntities();
        player.getHealth().setHealthPoints(1000);
        player.getHealth().setLives(player.getHealth().getLives() - 1);
        checkpoint.setLives(player.getHealth().getLives());

        currentWorld.getEntityManager().setPlayer(player);
        currentWorld.init();
        //currentWorld.addGameEventListener(this);

        changingLevel = false;
    }

    @Override
    public void onGameEventActionPerformed(GameEvent evt) {
        switch (evt.getType()) {
            case LEVEL_COMPLETED:
                changeLevel();
                break;
            case LEVEL_FAILED:
                if (player.getHealth().getLives() > 1){
                    resetLevel();
                } else {
                    this.launchGameEvent(new GameEvent(this, GameEventType.GAME_OVER));
                }
                break;
            case GAME_OVER:
                Checkpoint.removeCheckpoint(checkpoint);
                System.exit(0);
                break;
            case GAME_COMPLETED:
                Checkpoint.removeCheckpoint(checkpoint);
                System.out.println("YOU WON!");
                System.exit(0);
            case GAME_PAUSE:
                break; // TO DO
            case GAME_START:
                break; // TO DO
            case WEAPON_SELECTED_SWORD:
                player = new Player(player);
                entityManager.setPlayer(player);
                gui.getWeaponPanel().setWeapon(Weapons.SWORD);
                break;
            case WEAPON_SELECTED_BOW:
                player = new PlayerArcher(player);
                entityManager.setPlayer(player);
                player.getHealth().addObserver((Observer) this);
                gui.getWeaponPanel().setWeapon(Weapons.BOW);
                break;
            case WEAPON_SELECTED_SPELL:
                player = new PlayerMage(player);
                entityManager.setPlayer(player);
                entityManager.getPlayer().getHealth().addObserver((Observer) this);
                PlayerMage playerMage = (PlayerMage) player;
                playerMage.getMana().addObserver((Observer) gui.getManaBar());
                gui.getWeaponPanel().setWeapon(Weapons.SPELL);
                break;
            default:
                break;
        }
    }
    

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

}
