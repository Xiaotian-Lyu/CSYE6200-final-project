package cc.forestadventure.controller;

import cc.forestadventure.Director;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class OverController {

    @FXML
    private ImageView flag;

    @FXML
    private ImageView toIndex;

    @FXML
    void mouseClickedToIndex(MouseEvent event) {
    	Director.getInstance().toIndex(); // return back to first page
    }

    @FXML
    void mouseEnteredToIndex(MouseEvent event) {
    	toIndex.setOpacity(0.5);
    }

    @FXML
    void mouseExitedToIndex(MouseEvent event) {
    	toIndex.setOpacity(1);
    }
    
    //if win change the text
    public void flagSuccess() {
        flag.setImage(new Image("resources/GameWinText.png"));
    }

}
