/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.game;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javax.swing.JFrame;
import javax.swing.event.EventListenerList;
import twinkingdom.events.GameEvent;
import twinkingdom.events.GameEventListener;
import twinkingdom.events.GameEventType;

/**
 * FXML Controller class
 */
public class PauseController implements Initializable {
    
    @FXML
    private ImageView logoImg; 
    @FXML
    private Button resumeButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button exitButton;
    
    @FXML
    private Pane settingsPopup;
    @FXML
    private Pane quitPopup;

    // Exit popup buttons
    @FXML
    private Button confirmButton1;
    @FXML
    private Button cancelButton1;
    
    // Settings popup variables
    @FXML
    private Button confirmSettingsChangesButton, cancelSettingsChangesButton;
    
    @FXML
    private TextField moveUpTextField, moveRightTextField, moveDownTextField, moveLeftTextField, attackTextField, changeWeaponTextField;
    
    @FXML
    private Text errorLabel;  
    @FXML CheckBox soundCheckBox;
    
    private final int SETTINGS_NUM_FIELDS = 4;
    private char[] commands = new char[SETTINGS_NUM_FIELDS];
    private TextField[] settingsTextFields = new TextField[SETTINGS_NUM_FIELDS];
    private GameSettings gameSettings;
    
    private JFrame frame;
    private final int FRAME_WIDTH = 797, FRAME_HEIGHT = 457;
    
    private static Parent root;
    
    protected EventListenerList listenerList = new EventListenerList();
    
    public PauseController() throws IOException {
       
        Platform.setImplicitExit(false);
        JFXPanel jfxp = new JFXPanel();
        createFrame();
        frame.add(jfxp);
        this.frame.setFocusable(true);
        this.frame.setAlwaysOnTop(true);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pause.fxml"));
        loader.setController(this);
        root = loader.load();
        initFX(jfxp);
        frame.setVisible(false);
        
        frame.pack();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initSettingsFields();
        
        File file = new File("res/images/logo.png");
        Image image = new Image(file.toURI().toString());
        logoImg.setImage(image);
        
        exitButton.setOnMouseClicked((Event event) -> {
            resumeButton.setDisable(true);
            settingsButton.setDisable(true);
            exitButton.setDisable(true);
            quitPopup.setVisible(true);    
        });
        
        confirmButton1.setOnMouseClicked((Event event) -> {
            System.exit(0);
        });
        
        cancelButton1.setOnMouseClicked((Event event) -> {
            quitPopup.setVisible(false);
            resumeButton.setDisable(false);
            settingsButton.setDisable(false);
            exitButton.setDisable(false);
          
        });
        
        settingsButton.setOnMouseClicked((Event event) -> {
            settingsPopup.setVisible(true);
        });
        
        confirmSettingsChangesButton.setOnMouseClicked((Event event) -> {
            for(int i = 0; i < SETTINGS_NUM_FIELDS; i++) {
                String text = this.settingsTextFields[i].getText().trim().toLowerCase();
                if(text.isEmpty()) {
                    errorLabel.setText("All fields must be filled.");
                    return;
                }
                char command = text.charAt(0);
                if(text.length() != 1 || !Character.isLetter(command)) {             
                    errorLabel.setText("Command fields must contain one letter.");
                    return;
                }
                else {
                    if(command == 'P' || command == 'p') {
                        errorLabel.setText("P key is reserved for pause");
                        return;
                    }

                    for(int j = 0; j < i; j++) {
                        if(commands[j] == command) {
                            errorLabel.setText("All commands must be unique.");
                            return;
                        }   
                    }
                    this.commands[i] = command;
                }
            }
            
            gameSettings.setUpKey(commands[0]);
            gameSettings.setDownKey(commands[2]);
            gameSettings.setLeftKey(commands[3]);
            gameSettings.setRightKey(commands[1]);
            gameSettings.setAudioEnabled(soundCheckBox.isSelected());
            
            
            settingsPopup.setVisible(false);
            

        });
        
        cancelSettingsChangesButton.setOnMouseClicked((Event event) -> {
            settingsPopup.setVisible(false);
        });       
        
        resumeButton.setOnMouseClicked((Event event) -> {
            this.launchGameEvent(new GameEvent(this, GameEventType.GAME_RESUMED));
        }); 
    }   
    
    public void initSettingsFields() {
        this.settingsTextFields[0] = this.moveUpTextField;
        this.settingsTextFields[1] = this.moveRightTextField;
        this.settingsTextFields[2] = this.moveDownTextField;
        this.settingsTextFields[3] = this.moveLeftTextField;
    }
    
    public void setGameSettings(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
        this.moveUpTextField.setText("" + gameSettings.getUpKey());
        this.moveDownTextField.setText("" + gameSettings.getDownKey());
        this.moveRightTextField.setText("" + gameSettings.getRightKey());
        this.moveLeftTextField.setText("" + gameSettings.getLeftKey());
    }
    
    private void createFrame() {
        this.frame = new JFrame("Twins Kingdom - Pause");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
    }
    
     private static void initFX(JFXPanel fxPanel) {
        Scene scene = createScene();
        fxPanel.setScene(scene);
    }

    private static Scene createScene() {
        Scene scene = new Scene(root, javafx.scene.paint.Color.ALICEBLUE);
        return (scene);
    }
    
    public void setLocationRelativeTo(Component c) {
        frame.setLocationRelativeTo(c);
    }
    
    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
    
     public void addGameEventListener(GameEventListener listener) {
        listenerList.add(GameEventListener.class, listener);
    }

    public void removeGameEventListener(GameEventListener listener) {
        listenerList.remove(GameEventListener.class, listener);
    }

    private void launchGameEvent(GameEvent evt) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i = i + 2) {
            if (listeners[i] == GameEventListener.class) {
                ((GameEventListener) listeners[i + 1]).onGameEventActionPerformed(evt);
            }
        }
    }
}