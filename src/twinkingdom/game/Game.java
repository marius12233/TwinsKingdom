/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javax.swing.SwingUtilities;
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
    private final KeyManager keyManager = new KeyManager();
    private GameCamera gameCamera;
    private GameHandler handler;
    private EntityManager entityManager;
    private World currentWorld;
    private LevelHandler levelHandler;
    private Image loadingImage;
    private Image gameOverImage;

    private GameGUI gui;
    private Player player;

    private Checkpoint checkpoint;


    protected EventListenerList listenerList = new EventListenerList();

    private PauseController pause;

    private GameSettings settings;
    
    private boolean paused = false;
    private boolean changingLevel = false;
    private boolean gameOver = false;

    public Game(String title) {
        this.title = title;

    }

    public Game(String title, Checkpoint startingCheckpoint, PauseController pause) {
        this.title = title;
        this.checkpoint = startingCheckpoint;
        this.pause = pause;
    }

    public void init() throws IOException {
        settings = new GameSettings();
        settings.addObserver((Observer) keyManager);
        pause.setGameSettings(settings);

        changingLevel = true;
        loadingImage = ImageLoader.loadImage("/images/levelloading2.gif");
        gameOverImage = ImageLoader.loadImage("/images/gameover.gif");

        handler = GameHandler.getInstance();
        handler.setGame(this);

        if (checkpoint == null) {
            checkpoint = new Checkpoint(3, 0);
        }

        player = new Player(288, 320, new PlayerAssets());
        player.getHealth().setLives(checkpoint.getLives());
        handler.setPlayer(player);

        // gameState = new GameState(handler);
        //State.setState(gameState);
        this.levelHandler = new LevelHandler();
        levelHandler.setCurrentWorld(checkpoint.getLevelId());

        this.entityManager = new EntityManager(handler, player);

        this.gui = new GameGUI(title);
        this.width = gui.getCanvas().getSize().width;
        this.height = gui.getCanvas().getSize().height;

        //pause.setVisible(false);
        pause.addGameEventListener(this);

        gui.getFrame().addKeyListener(keyManager);
        keyManager.addGameEventListener(this);

        gameCamera = new GameCamera(handler, 0, 0);

        Assets.init();
        // loadingImage = new ImageIcon(new URL("/images/levelloading2.gif")).getImage();

        render();
        render();
        render();

        changingLevel = false;

        this.addGameEventListener(this);
    }

    public void tick() {
        
        if(paused || changingLevel || gameOver)
            return;

        keyManager.tick();
        if (currentWorld != null) {
            currentWorld.tick();
        }

    }

    public void render() {

        if(paused)
            return;
        
        //Buffer strategy è un buffer che permette di scrivere sul buffer prima di scriver sullo screen
        bs = gui.getCanvas().getBufferStrategy();
        if (bs == null) {
            gui.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();

        g.clearRect(0, 0, width, height);
        
        if(gameOver) {
            System.out.println("Rendering game over");
            g.setColor(new Color(31,30,36));
            g.fillRect(0, 0, width, height);
            g.setColor(Color.WHITE);

            if (loadingImage != null) {
                g.drawImage(gameOverImage, (width - gameOverImage.getWidth(null))/2, (height - gameOverImage.getHeight(null))/2, null);
            }

            bs.show();
            return; 
        }
        
        if (changingLevel) {
            g.setColor(Color.black);
            g.fillRect(0, 0, width, height);
            g.setColor(Color.WHITE);

            if (loadingImage != null) {
                g.drawImage(loadingImage, 140, 20, null);
            }

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

        long timer = 0;
        int ticks = 0;

        changingLevel = true;

        render();
        render();
        render();
        loadingImage = ImageLoader.loadImage("/images/levelloading2.gif");

        currentWorld = levelHandler.getCurrentWorld();

        currentWorld.setEntityManager(this.entityManager);
        this.handler.setWorld((World) currentWorld);

        currentWorld.init();
        currentWorld.addGameEventListener(this);
        player.getHealth().addObserver((Observer) gui.getHealthBar());
        player.getHealth().addObserver((Observer) this);
        currentWorld.getStarCollection().addObserver((Observer) this.gui.getStarsPanel());

        changingLevel = false;

        long lastTime = System.nanoTime();
        while (running) {
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
        
        try {
            Thread.sleep(1000);
            if (gameOver)
                Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            new Launcher();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
                    this.gui.getFrame().dispose();

   
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
        loadingImage = ImageLoader.loadImage("/images/levelloading2.gif");
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
        System.out.println("Game event received..." + evt.getType() + evt.getSource());
        switch (evt.getType()) {
            case LEVEL_COMPLETED:
                changeLevel();
                break;
            case LEVEL_FAILED:
                if (player.getHealth().getLives() > 1) {
                    resetLevel();
                } else {
                    this.launchGameEvent(new GameEvent(this, GameEventType.GAME_OVER));
                }
                break;
            case GAME_OVER:
                Checkpoint.removeCheckpoint(checkpoint);
                gameOver = true;
                this.stopGame();
                render();render();render();
                break;
            case GAME_COMPLETED:
                Checkpoint.removeCheckpoint(checkpoint);
                System.out.println("YOU WON!");
                System.exit(0);
            case GAME_PAUSE:
                pause.setVisible(true);
                paused = true;
                System.out.println(changingLevel);
                pause.setLocationRelativeTo(gui.getGameScenePanel());
                this.gui.getFrame().setEnabled(false);
                break;
            case GAME_RESUMED:
                paused = false;

                pause.setVisible(false);
                this.gui.getFrame().setEnabled(true);
                this.gui.getFrame().setVisible(true);

                break;
            case GAME_EXITED:
                this.stopGame();
                pause.setVisible(false);
                this.gui.getFrame().setVisible(true);
                break;
            case GAME_START:
                break; // TO DO
            case WEAPON_SELECTED_SWORD:
                player = new Player(player);
                entityManager.setPlayer(player);
                handler.setPlayer(player);
                gui.getWeaponPanel().setWeapon(Weapons.SWORD);
                break;
            case WEAPON_SELECTED_BOW:
                if (levelHandler.getCurrentWorldId() < 2) {
                    return;
                }
                player = new PlayerArcher(player);
                entityManager.setPlayer(player);
                handler.setPlayer(player);
                player.getHealth().addObserver((Observer) this);
                gui.getWeaponPanel().setWeapon(Weapons.BOW);
                break;
            case WEAPON_SELECTED_SPELL:
                if (levelHandler.getCurrentWorldId() < 4) {
                    return;
                }
                player = new PlayerMage(player);
                entityManager.setPlayer(player);
                entityManager.getPlayer().getHealth().addObserver((Observer) this);
                handler.setPlayer(player);
                //PlayerMage playerMage = (PlayerMage) player;
                ((PlayerMage) player).getMana().addObserver((Observer) gui.getManaBar());
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
