/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package twinkingdom.game;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 *
 * @author Clover
 */

public class SettingsViewController implements Initializable {
    
    private static final int NUM_FIELDS = 4;
    
    @FXML
    private Pane keyboardSettingsPane;
    
    @FXML
    private Button confirmChangesButton, cancelButton;
    
    @FXML
    private TextField moveUpTextField, moveRightTextField, moveDownTextField, moveLeftTextField, attackTextField, changeWeaponTextField;
    
    @FXML
    private Text errorLabel;
    
    private char[] commands = new char[NUM_FIELDS];
    private TextField[] fields = new TextField[NUM_FIELDS];
    
  
    
   // public SettingsViewController() {}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initFields();
        keyboardSettingsPane.setVisible(true);
        this.keyboardSettingsPane.setFocusTraversable(true);
        
        confirmChangesButton.setOnMouseClicked((Event event) -> {
            for(int i = 0; i < NUM_FIELDS; i++) {
                String text = this.fields[i].getText().trim().toLowerCase();
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
                    for(int j = 0; j < i; j++) {
                        if(commands[j] == command) {
                            errorLabel.setText("All commands must be unique.");
                            return;
                        }   
                    }
                    this.commands[i] = command;
                }
            }
            
            this.keyboardSettingsPane.setVisible(false);
            this.keyboardSettingsPane.setDisable(true);

        });
        
        cancelButton.setOnMouseClicked((Event event) -> {
                        this.keyboardSettingsPane.setVisible(false);
            this.keyboardSettingsPane.setDisable(true);
        });       
    }
    
    public void initFields() {
        this.fields[0] = this.moveUpTextField;
        this.fields[1] = this.moveRightTextField;
        this.fields[2] = this.moveDownTextField;
        this.fields[3] = this.moveLeftTextField;
        //this.fields[4] = this.attackTextField;
        //this.fields[5] = this.changeWeaponTextField;
    }
}