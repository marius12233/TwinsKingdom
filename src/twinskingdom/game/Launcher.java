package  twinskingdom.game;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.JFXPanel;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import  twinskingdom.gui.GameGUI.FrameDragListener;
import  twinskingdom.gui.MotionPanel;
import  twinskingdom.sounds.SoundTrackManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Launcher is the main access point to the game,
 * responsible for creating or loading a game.
 */
public class Launcher implements Initializable {

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
     */
    private String title;
    private static final int FRAME_WIDTH = 1006, FRAME_HEIGHT = 556;
    private static final int GAME_SCENE_WIDTH = 797, GAME_SCENE_HEIGHT = 457;
    private static final int SIDEBAR_WIDTH = 192, SIDEBAR_HEIGHT = 518;
    private JFrame frame;

    public Parent root;

    @FXML
    private ImageView logoImg;
    @FXML
    private Button newGameButton, loadGameButton, settingsButton, exitButton, confirmButton, cancelButton;
    @FXML
    Pane exitPopup;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        exitPopup.setVisible(false);
        File file = new File("res/images/logo.png");
        Image image = new Image(file.toURI().toString());
        logoImg.setImage(image);

        newGameButton.setOnMouseClicked((Event event) -> {
            try {
                new Game("Twins Kingdom", null, new PauseController()).start();
                this.frame.dispose();
            } catch (IOException ex) {
                Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        loadGameButton.setOnMouseClicked((Event event) -> {
            SavedGamesViewController controller = new SavedGamesViewController();
            controller.initData(this.frame);
            frame.setVisible(false);
        });

        exitButton.setOnMouseClicked((Event event) -> {
            exitPopup.setVisible(true);
            newGameButton.setDisable(true);
            loadGameButton.setDisable(true);
            settingsButton.setDisable(true);
            exitButton.setDisable(true);
        });

        confirmButton.setOnMouseClicked((Event event) -> {
            System.exit(0);
        });

        cancelButton.setOnMouseClicked((Event event) -> {
            exitPopup.setVisible(false);
            newGameButton.setDisable(false);
            loadGameButton.setDisable(false);
            settingsButton.setDisable(false);
            exitButton.setDisable(false);
        });

        SoundTrackManager.getInstance().getCurrentSoundTrack().loop();

    }

    private void initFX(JFXPanel fxPanel) {
        // This method is invoked on the JavaFX thread
        Scene scene = createScene();
        fxPanel.setScene(scene);
    }

    private Scene createScene() {
        Scene scene = new Scene(root, javafx.scene.paint.Color.ALICEBLUE);
        return (scene);
    }

    public Launcher() throws IOException {

        createFrame();
        JFXPanel jfxp = new JFXPanel();
        frame.add(jfxp);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/launcher.fxml"));
        loader.setController(this);

        root = loader.load();

        initFX(jfxp);
        frame.setVisible(true);
        frame.pack();

        FrameDragListener frameDragListener = new FrameDragListener(frame);
        jfxp.addMouseListener(frameDragListener);
        jfxp.addMouseMotionListener(frameDragListener);
        jfxp.setVisible(true);

        JPanel panel = new MotionPanel(frame);
        jfxp.setBounds(10, 10, 300, 300);
        panel.setVisible(true);

    }

    private void createFrame() {
        this.frame = new JFrame(title);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
    }

    public static void main(String[] args) throws IOException {
        new Launcher();
    }

}
