/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.game;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javax.swing.JFrame;
import twinkingdom.saves.Checkpoint;
import twinkingdom.saves.MemoryCard;

/**
 * FXML Controller class
 *
 * @author bened
 */
public class SavedGamesViewController implements Initializable {

    @FXML
    private ListView<Checkpoint> savedGamesList;
    @FXML
    private Text backButton;

    private FXMLLoader mLLoader;
    private JFrame frame;
    private static final int FRAME_WIDTH = 1006, FRAME_HEIGHT = 556;
    private static Parent root;
    private JFrame prevFrame;

    public SavedGamesViewController() {
        this.frame = new JFrame("Twins Kingdom - Loading a game");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        JFXPanel jfxp = new JFXPanel();

        frame.add(jfxp);

        mLLoader = new FXMLLoader(getClass().getResource("/fxml/savedGamesView.fxml"));
        mLLoader.setController(this);
        try {
            root = mLLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        initFX(jfxp);
        frame.pack();
        frame.setVisible(true);
    }

    private static void initFX(JFXPanel fxPanel) {
        // This method is invoked on the JavaFX thread
        Scene scene = createScene();
        fxPanel.setScene(scene);
    }

    private static Scene createScene() {
        Scene scene = new Scene(root, javafx.scene.paint.Color.ALICEBLUE);
        return (scene);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        savedGamesList.setCellFactory((ListView<Checkpoint> lvc) -> new SavedGameCellView());
        ObservableList<Checkpoint> checkpoints = FXCollections.observableArrayList();
        checkpoints.setAll(MemoryCard.getAllSavedGames());
        savedGamesList.setItems(checkpoints);
        savedGamesList.itemsProperty().bind(new SimpleListProperty(checkpoints));
        
        savedGamesList.setOnMouseClicked((Event event) -> {
            Checkpoint selectedCheckpoint = savedGamesList.getSelectionModel().getSelectedItem();
            if(selectedCheckpoint != null && selectedCheckpoint.getLives() > 0) {
                try {
                    new Game("Twins Kingdom", selectedCheckpoint, new PauseController()).start();
                    this.frame.dispose();
                    this.prevFrame.dispose();
                } catch (IOException ex) {
                    Logger.getLogger(SavedGamesViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        backButton.setOnMouseClicked((Event event) -> {
            this.frame.dispose();
            this.prevFrame.setVisible(true);
        });
    }
    
    public void initData(JFrame prevFrame) {
        this.prevFrame = prevFrame;
    }

}
