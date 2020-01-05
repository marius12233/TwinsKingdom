/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemenugui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author bened
 */
public class LauncherController implements Initializable {

   @FXML private ImageView logoImg;
    @FXML private Button newGameButton, loadGameButton, settingsButton, exitButton, confirmButton, cancelButton;
    @FXML Pane exitPopup;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        exitPopup.setVisible(false);
        File file = new File("res/images/logo.png");
        Image image = new Image(file.toURI().toString());
        logoImg.setImage(image);
   
        newGameButton.setOnMouseClicked((Event event) -> {
            System.out.println("Game started");
        });

        loadGameButton.setOnMouseClicked((Event event) -> {
            System.out.println("Loading a game...");
        });
        
        settingsButton.setOnMouseClicked((Event event) -> {
            System.out.println("Opening settings...");
        });
        
        exitButton.setOnMouseClicked((Event event) -> {
            System.out.println("Exiting the game...");
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
        
        
    }
    
}
