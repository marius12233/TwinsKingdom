/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinkingdom.game;

import java.io.IOException;
import java.text.SimpleDateFormat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import twinkingdom.saves.Checkpoint;

/**
 *
 * @author bened
 */
public class SavedGameCellView extends ListCell<Checkpoint> {
   
    @FXML private Text lives, lastSaved;
    @FXML private ImageView levelPicture;
    @FXML private Pane pane;

    private FXMLLoader mLLoader;
        
   

    @Override
    protected void updateItem(Checkpoint checkpoint, boolean empty) {
        
        super.updateItem(checkpoint, empty);
        if(empty || checkpoint == null) {
            setText(null);
            setGraphic(null);
            setStyle("-fx-background-color: transparent;");

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/fxml/savedGameCellView.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            
            lives.setText("X " +  checkpoint.getLives());
            lastSaved.setText("Last saved on " + new SimpleDateFormat("dd MMM yyyy 'at' HH.mm a").format(checkpoint.getLastSaved()));
            setGraphic(pane);
            setText(null);
        }

    }
}
