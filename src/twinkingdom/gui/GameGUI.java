/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.gui;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import twinkingdom.gfx.ImageLoader;

/**
 *
 * @author bened
 */
public class GameGUI {

    /**
     * @param args the command line arguments
     */
    private String title;
    private static final int FRAME_WIDTH = 1006, FRAME_HEIGHT = 556;
    private static final int GAME_SCENE_WIDTH = 797, GAME_SCENE_HEIGHT = 457;
    private static final int SIDEBAR_WIDTH = 192, SIDEBAR_HEIGHT = 518;
    private JFrame frame;
    private BackgroundPane backgroundPane;
    private GameScenePanel gameScene;
    private ManaBar manaBar;
    private HealthBar healthBar;
    private SidebarPanel sidebar;
    private LevelNamePanel lnp;
    private StarsPanel starsPanel;
    private Canvas canvas;

    public GameGUI(String title) throws IOException {
        this.title = title;
        createFrame(title);
        this.setFrameBackground("/gui/bg.png");
        this.createGameScenePanel();
        this.createLevelNamePanel();
        this.createSidebar();
        this.createStarsPanel();
        this.createHealthBar();
        this.createManaBar();

        FrameDragListener frameDragListener = new FrameDragListener(frame);
        frame.addMouseListener(frameDragListener);
        frame.addMouseMotionListener(frameDragListener);
        frame.setVisible(true);
        frame.pack();

       

        manaBar.value = 90;
        manaBar.repaint();



        lnp.repaint();
        LevelUtilities.currentLevel = 2;
        lnp.repaint();
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == 'q') {
                    System.exit(0);//To change body of generated methods, choose Tools | Templates.
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 'q') {
                    System.exit(0); //To change body of generated methods, choose Tools | Templates.
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyChar() == 'q') {
                    System.exit(0); //To change body of generated methods, choose Tools | Templates.
                }
            }
        });
        JPanel panel = new MotionPanel(frame);
        panel.setVisible(true);

    }
    

    private void createFrame(String title) {
        this.frame = new JFrame(title);
        frame.setSize(GameGUI.FRAME_WIDTH, GameGUI.FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setTitle(title);
    }

    private void setFrameBackground(String imgPath) throws IOException {
        BufferedImage bi = ImageLoader.loadImage(imgPath);//read(this.getClass().getResource(imgPath));
        this.backgroundPane = new BackgroundPane();
        backgroundPane.setLayout(null);
        backgroundPane.setBackground(bi);
        frame.setContentPane(backgroundPane);
        frame.setBackground(Color.BLACK);
    }

    private void createGameScenePanel() {
        this.gameScene = new GameScenePanel();
        gameScene.setBackground(new Color(28, 27, 27));
        gameScene.setLayout(null);
        gameScene.setOpaque(false);
        Border gameSceneBorder = BorderFactory.createLineBorder(new Color(232, 142, 0));
        gameScene.setPreferredSize(new Dimension(GameGUI.GAME_SCENE_WIDTH, GameGUI.GAME_SCENE_HEIGHT));
        gameScene.setBorder(gameSceneBorder);
        frame.getContentPane().add(gameScene);
        gameScene.setBounds(10, 10, GameGUI.GAME_SCENE_WIDTH, GameGUI.GAME_SCENE_HEIGHT);

        this.canvas = new Canvas();
        this.canvas.setSize(GameGUI.GAME_SCENE_WIDTH - 2, GameGUI.GAME_SCENE_HEIGHT - 2);
        this.canvas.setPreferredSize(new Dimension(GameGUI.GAME_SCENE_WIDTH - 2, GameGUI.GAME_SCENE_HEIGHT - 2));
        this.canvas.setMaximumSize(new Dimension(GameGUI.GAME_SCENE_WIDTH - 2, GameGUI.GAME_SCENE_HEIGHT - 2));
        this.canvas.setMinimumSize(new Dimension(GameGUI.GAME_SCENE_WIDTH - 2, GameGUI.GAME_SCENE_HEIGHT - 2));
        canvas.setFocusable(false);

        gameScene.add(canvas);
        canvas.setBounds(1, 1, GameGUI.GAME_SCENE_WIDTH - 2, GameGUI.GAME_SCENE_HEIGHT - 2);

    }

    private void createSidebar() throws IOException {

        BackgroundPane sidebarContainer = new BackgroundPane();
        sidebarContainer.setLayout(null);
        sidebarContainer.setOpaque(false);
        BufferedImage sidebarBackground = ImageLoader.loadImage("/gui/sidebar_bg.png");//ImageIO.read(GameGUI.class.getResource("/res/gui/sidebar_bg.png"));
        sidebarContainer.setBackground(sidebarBackground);

        frame.getContentPane().add(sidebarContainer);
        sidebarContainer.setBounds(810, 10, GameGUI.SIDEBAR_WIDTH, GameGUI.SIDEBAR_HEIGHT);

        this.sidebar = new SidebarPanel();
        sidebarContainer.add(sidebar);
        sidebar.setBounds(20, 100, 145, 300);

    }

    private void createLevelNamePanel() {
        lnp = new LevelNamePanel();
        lnp.setLayout(null);
        lnp.setOpaque(false);
        frame.getContentPane().add(lnp);
        lnp.setBounds(800, 520, 100, 20);
        lnp.setSize(140, 30);

    }

    private void createHealthBar() {

        try {
            this.healthBar = new HealthBar();
        } catch (IOException ex) {
            Logger.getLogger(GameGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        healthBar.setLayout(null);
        healthBar.setOpaque(false);
        healthBar.setPreferredSize(new Dimension(334, 82));
        healthBar.setSize(334, 82);

        frame.getContentPane().add(healthBar);
        healthBar.setBounds(10, 477, 334, 82);

    }

    private void createManaBar() {
        try {
            this.manaBar = new ManaBar();
        } catch (IOException ex) {
            Logger.getLogger(GameGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        manaBar.setLayout(null);
        manaBar.setOpaque(false);
        manaBar.setPreferredSize(new Dimension(334, 82));
        manaBar.setSize(334, 82);

        frame.getContentPane().add(manaBar);
        manaBar.setBounds(480, 477, 330, 82);
    }

    private void createStarsPanel() throws IOException {
        this.starsPanel = new StarsPanel();
        this.starsPanel.setSize(60, 70);
        this.starsPanel.setPreferredSize(new Dimension(60, 70));
        frame.getContentPane().add(starsPanel);
        this.starsPanel.setBounds(385, 490, 60, 70);

        starsPanel.setLayout(null);
        starsPanel.setOpaque(false);
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public ManaBar getManaBar() {
        return this.manaBar;
    }

    public HealthBar getHealthBar() {
        return this.healthBar;
    }

    public LevelNamePanel getLevelNamePanel() {
        return this.lnp;
    }

    public GameScenePanel getGameScenePanel() {
        return this.gameScene;
    }

    public SidebarPanel getSidebar() {
        return this.sidebar;
    }

    public WeaponPanel getWeaponPanel() {
        return this.getSidebar().getWeaponPanel();
    }

    public JPanel getBackgroundPane() {
        return this.backgroundPane;

    }

    public StarsPanel getStarsPanel() {
        return starsPanel;
    }
    

    public static class FrameDragListener extends MouseAdapter {

        private final JFrame frame;
        private Point mouseDownCompCoords = null;

        public FrameDragListener(JFrame frame) {
            this.frame = frame;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            mouseDownCompCoords = null;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            mouseDownCompCoords = e.getPoint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            Point currCoords = e.getLocationOnScreen();
            frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
        }
    }

}
